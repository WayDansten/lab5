package management.utility;

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
    public void launch() {
        actuator = true;
        while (true) {
            try {
                if (inScript) {
                    if (!ioManager.getReceiver().hasNext() || !actuator) {
                        break;
                    }
                }
                System.out.println("Пожалуйста, введите команду (введите help для просмотра всех команд):");
                String line = ioManager.getReceiver().next();
                String[] tokens = line.split(" ");
                Command command = commands.get(tokens[0].strip().toLowerCase());
                commandHistory.add(tokens[0].strip().toLowerCase());
                if (commandHistory.size() > 10) {
                    commandHistory.remove(0);
                }
                if (command == null) {
                    throw new WrongInputException("Несуществующая команда! Пожалуйста, повторите ввод");
                }
                if (tokens.length == 1) {
                    command.execute("");
                } else {
                    command.execute(tokens[1].strip());
                }
            } catch (WrongInputException e) {
                System.err.println(e.getMessage());
            }
        }
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
    public void setActuatorState(boolean state) {
        actuator = state;
    }
}
