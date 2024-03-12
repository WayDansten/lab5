package management.commands;

import management.utility.CollectionManager;

/**
 * Команда, выводящая информацию о коллекции
 */

public class InfoCommand implements Command {
    CollectionManager cm;
    public InfoCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.info();
    }
}
