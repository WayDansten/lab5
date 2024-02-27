package management.commands;

import management.utility.CollectionManager;
import management.utility.Parser;

/**
 * Удаляет из коллекции все элементы, значение поля area которых больше, чем значение area у элемента с указанным id
 */

public class RemoveGreaterCommand implements Command {
    CollectionManager cm;
    public RemoveGreaterCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String input = args[0];
        cm.removeGreater(Parser.parseId(input));
    }
}
