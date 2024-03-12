package management.commands;

import management.utility.CollectionManager;

/**
 * Команда, выводящая коллекцию в строковом представлении
 */

public class ShowCommand implements Command{
    CollectionManager cm;
    public ShowCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.show();
    }
}
