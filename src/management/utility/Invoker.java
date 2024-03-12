package management.utility;

import exceptions.ErrorInFunctionException;
import exceptions.WrongInputException;
import management.commands.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    private final IOManager ioManager = new IOManager();
    private boolean actuator = false;
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
    public boolean executeCommand() throws ErrorInFunctionException {
        System.out.println("Пожалуйста, введите команду (введите help для просмотра всех команд):");
        if (inScript && !ioManager.getReceiver().hasNext()) {
            return true;
        }
        String line = ioManager.getReceiver().next();
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
    }
    public CollectionManager getCollectionManager() {
        return cm;
    }

    public IOManager getIoManager() {
        return ioManager;
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
    public void setActuatorState(boolean state) {
        actuator = state;
    }
}
