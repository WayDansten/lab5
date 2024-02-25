package management.utility;

import exceptions.WrongInputException;
import management.commands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Invoker {
    private final CollectionManager cm = new CollectionManager();
    static boolean actuator = false;
    private static final ArrayList<String> commandHistory = new ArrayList<>();
    Map<String, Command> commands = new HashMap<>();
    {commands.put("add", new AddCommand(cm));
    commands.put("remove_by_id", new RemoveByIdCommand(cm));
    commands.put("exit", new ExitCommand(cm));
    commands.put("help", new HelpCommand(cm));
    commands.put("clear", new ClearCommand(cm));
    commands.put("show", new ShowCommand(cm));
    commands.put("info", new InfoCommand(cm));
    commands.put("remove_greater", new RemoveGreaterCommand(cm));
    commands.put("remove_lower",  new RemoveLowerCommand(cm));
    commands.put("history", new HistoryCommand(cm));
    commands.put("save", new SaveCommand(cm));
    commands.put("filter_less_than_furnish", new FilterLessThanFurnishCommand(cm));
    commands.put("count_greater_than_house", new CountGreaterThanHouseCommand(cm));}
    static Scanner receiver = new Scanner(System.in);
    static {
        receiver.useDelimiter("\n");
    }
    public void launch() {
        actuator = true;
        System.out.println("Приложение запущено");
        while (actuator) {
            try {
                System.out.println("Пожалуйста, введите команду (введите help для просмотра всех команд):");
                String line = receiver.next();
                String[] tokens = line.split(" ");
                Command command = commands.get(tokens[0]);
                commandHistory.add(tokens[0]);
                if (commandHistory.size() > 10) {
                    commandHistory.remove(0);
                }
                if (command == null) {
                    throw new WrongInputException("Несуществующая команда! Пожалуйста, повторите ввод");
                }
                if (tokens.length == 1) {
                    command.execute();
                } else {
                    command.execute(tokens[1]);
                }
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void shutDown() {
        actuator = false;
    }
    public static Scanner getReceiver() {
        return receiver;
    }
    public static void interactiveMode() {
        Invoker.receiver = new Scanner(System.in);
    }
    public static void fileMode(File file) throws FileNotFoundException {
            Invoker.receiver = new Scanner(file);
    }
    public static ArrayList<String> getCommandHistory() {
        return commandHistory;
    }
    public CollectionManager getCollectionManager() {
        return cm;
    }
}
