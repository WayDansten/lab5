package management.commands;

import management.utility.CollectionManager;

/**
 * Команда, очищающая коллекцию
 */

public class ClearCommand implements Command{
    CollectionManager cm;
    public ClearCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.clear();
    }
}
