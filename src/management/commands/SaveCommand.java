package management.commands;

import management.utility.CollectionManager;

import java.io.File;
import java.io.IOException;

/**
 * Сохраняет коллекцию в файл в формате .csv
 */

public class SaveCommand implements Command {
    CollectionManager cm;
    public SaveCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String filePath = args[0];
        try {
            File file = new File(filePath);
            cm.saveCollection(file);
        } catch (IOException e) {
            System.err.println("Файл не найден!");
        }
    }
}
