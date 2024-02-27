package management.commands;

import management.utility.CollectionManager;

public class ShowCommand implements Command{
    /**
     * Выводит коллекцию в строковом представлении
     */
    CollectionManager cm;
    public ShowCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.show();
    }
}
