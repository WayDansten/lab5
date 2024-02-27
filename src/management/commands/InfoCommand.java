package management.commands;

import management.utility.CollectionManager;

public class InfoCommand implements Command {
    /**
     * Выводит информацию о коллекции
     */
    CollectionManager cm;
    public InfoCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.info();
    }
}
