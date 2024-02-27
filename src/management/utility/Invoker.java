package management.utility;

import exceptions.WrongInputException;
import management.commands.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Invoker {
    /**
     * Управляет командами и вводом/выводом
     */
    private final CollectionManager cm = new CollectionManager();
    static boolean actuator = false;
    private static final ArrayList<String> commandHistory = new ArrayList<>();
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
        }
    };
    private static Scanner receiver;

    /**
     * Запускает считывание команд в том режиме, в котором Invoker находится в этот момент
     */
    public void launch() {
        actuator = true;
        System.out.println("Приложение запущено");
        while (actuator) {
            try {
                System.out.println("Пожалуйста, введите команду (введите help для просмотра всех команд):");
                String line = receiver.next();
                String[] tokens = line.split(" ");
                Command command = commands.get(tokens[0].toLowerCase());
                commandHistory.add(tokens[0].toLowerCase());
                if (commandHistory.size() > 10) {
                    commandHistory.remove(0);
                }
                if (command == null) {
                    throw new WrongInputException("Несуществующая команда! Пожалуйста, повторите ввод");
                }
                if (tokens.length == 1) {
                    command.execute("");
                } else {
                    command.execute(tokens[1]);
                }
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Завершает считывание команд
     */
    public static void shutDown() {
        actuator = false;
    }
    public static Scanner getReceiver() {
        return receiver;
    }
    public CollectionManager getCollectionManager() {
        return cm;
    }

    /**
     * Переводит Invoker в интерактивный режим (ручной ввод с консоли)
     */
    public static void interactiveMode() {
        Invoker.receiver = new Scanner(System.in);
        receiver.useDelimiter("\n");
    }

    /**
     * Переводит Invoker в режим считывания из файла
     * @param filePath Путь до файла
     * @throws IOException Выбрасывается в случае, если файл не найден
     */
    public static void fileMode(String filePath) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(fis);
        ) {
            Invoker.receiver = new Scanner(fis);
        }

    }

    /**
     * Выводит последние 10 команд
     * @return Массив имен последних 10-ти команд
     */
    public static ArrayList<String> getCommandHistory() {
        return commandHistory;
    }
}
