package management.commands;

import management.utility.CollectionManager;

public class ClearCommand implements Command{
    /**
     * Команда очищения коллекции
     */
    CollectionManager cm;
    public ClearCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.clear();
    }
}
