package management.commands;

import management.utility.CollectionManager;

/**
 * Команда, завершающая работу приложения
 */

public class ExitCommand implements Command{
    CollectionManager cm;
    public ExitCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        System.out.println("Завершение работы приложения.");
    }
}
