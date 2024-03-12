package management.utility;

import exceptions.ErrorInFunctionException;
import management.commands.*;

import java.util.*;

/**
 * Управляет командами и вводом/выводом
 */

public class Invoker {
    private static Invoker instance;
    public static Invoker getInstance() {
        if (instance == null) {
            instance = new Invoker();
        }
        return instance;
    }
    private final CollectionManager cm = new CollectionManager();
    private final InputManager inputManager = new InputManager();
    private boolean inScript = false;
    private final ArrayList<String> commandHistory = new ArrayList<>();
    Map<String, Command> commands = new HashMap<>() {
        {
            put("add", new AddCommand(cm));
            put("remove_by_id", new RemoveByIdCommand(cm));
            put("exit", new ExitCommand(cm));
            put("help", new HelpCommand(cm));
            put("clear", new ClearCommand(cm));
            put("show", new ShowCommand(cm));
            put("info", new InfoCommand(cm));
            put("remove_greater", new RemoveGreaterCommand(cm));
            put("remove_lower", new RemoveLowerCommand(cm));
            put("history", new HistoryCommand(cm));
            put("save", new SaveCommand(cm));
            put("filter_less_than_furnish", new FilterLessThanFurnishCommand(cm));
            put("filter_contains_name", new FilterContainsNameCommand(cm));
            put("count_greater_than_house", new CountGreaterThanHouseCommand(cm));
            put("update", new UpdateCommand(cm));
            put("execute_script", new ExecuteScriptCommand(cm));
        }
    };

    /**
     * Запускает считывание команд в том режиме, в котором Invoker находится в этот момент
     */
    public void launch() throws ErrorInFunctionException {
        boolean shutDown = false;
        while (!shutDown) {
            try {
                shutDown = executeCommand();
            } catch (ErrorInFunctionException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Исполняет одну введенную команду
     * @return Сигнал о конце ввода. Это может быть команда exit, конец файла или сигнал об окончании ввода
     * @throws ErrorInFunctionException Выбрасывается, если произошло любое другое исключение во время исполнения скрипта
     */

    public boolean executeCommand() throws ErrorInFunctionException {
        try {
            System.out.println("Пожалуйста, введите команду (введите help для просмотра всех команд):");
            if (inScript && !inputManager.getReceiver().hasNext()) {
                return true;
            }
            String line = inputManager.getReceiver().next();
            String[] tokens = line.split(" ");
            Command command = commands.get(tokens[0].strip().toLowerCase());
            if (commandHistory.size() > 10) {
                commandHistory.remove(0);
            }
            if (command == null) {
                System.err.println("Несуществующая команда!");
                return false;
            }
            if (tokens[0].strip().equalsIgnoreCase("exit")) {
                command.execute();
                return true;
            }
            if (tokens.length == 1) {
                commandHistory.add(tokens[0].strip().toLowerCase());
                command.execute("");
            } else {
                commandHistory.add(tokens[0].strip().toLowerCase());
                command.execute(tokens[1].strip());
            }
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }
    /**
     * Выводит последние 10 команд
     * @return Массив имен последних десяти команд
     */
    public ArrayList<String> getCommandHistory() {
        return commandHistory;
    }
    public void setInScriptState(boolean state) {
        inScript = state;
    }
    public boolean getInScriptState() {
        return inScript;
    }

    public CollectionManager getCollectionManager() {
        return cm;
    }
    public InputManager getIoManager() {
        return inputManager;
    }
}
