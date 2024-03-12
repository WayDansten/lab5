package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Invoker;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Команда, исполняющая скрипт
 */

public class ExecuteScriptCommand implements Command {
    CollectionManager cm;
    static boolean errorOccurred = false;
    static boolean recursionOccurred = false;
    private final Deque<String> openedScripts = new ArrayDeque<>();
    public ExecuteScriptCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        String filePath = args[0];
        try {
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
                boolean end = false;
                if (openedScripts.contains(filePath)) {
                    recursionOccurred = true;
                    errorOccurred = true;
                    end = true;
                    System.err.println("Обнаружена рекурсия! Дальнейшие команды из скриптов выполнены не будут!");
                }
                openedScripts.add(filePath);
                Invoker.getInstance().setInScriptState(true);
                Invoker.getInstance().getIoManager().setFileMode(bis);
                while (!end) {
                    if (errorOccurred) {
                        break;
                    }
                    try {
                        end = Invoker.getInstance().executeCommand();
                    } catch (ErrorInFunctionException e) {
                        System.err.println(e.getMessage());
                        System.err.println("Ошибка в файле " + openedScripts.getLast());
                        errorOccurred = true;
                        end = true;
                    }
                }
                openedScripts.removeLast();
                if (openedScripts.isEmpty()) {
                    Invoker.getInstance().setInScriptState(false);
                    Invoker.getInstance().getIoManager().setInteractiveMode();
                }
            }
        } catch (IOException e) {
            System.err.println("Файл не найден!");
            if (Invoker.getInstance().getInScriptState()) {
                throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
            }
        }
    }
}
