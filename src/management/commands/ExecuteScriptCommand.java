package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Invoker;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Исполняет скрипт с командами
 */

public class ExecuteScriptCommand implements Command {
    CollectionManager cm;
    static boolean exceptionOccurred = false;
    private final Deque<String> openedScripts = new ArrayDeque<>();
    public ExecuteScriptCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        String filePath = args[0];
        try {
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
                openedScripts.add(filePath);
                Invoker.getInstance().setInScriptState(true);
                Invoker.getInstance().getIoManager().setFileMode(bis);
                Invoker.getInstance().launch();
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
        } catch (ErrorInFunctionException e) {
            System.err.println(e.getMessage());
            exceptionOccurred = true;
        }
    }
}
