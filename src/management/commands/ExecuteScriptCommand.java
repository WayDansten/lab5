package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;

import java.io.IOError;
import java.io.IOException;

/**
 * Исполняет скрипт с командами
 */

public class ExecuteScriptCommand implements Command {
    CollectionManager cm;
    public ExecuteScriptCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String filePath = args[0];
        try {
            System.out.println("Введите путь до файла скрипта:");
            Invoker.fileMode(filePath);
        } catch (IOException e) {
            System.out.println("Файл не найден!");
        }
    }
}
