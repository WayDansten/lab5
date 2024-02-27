package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;

import static java.lang.Integer.parseInt;

public class RemoveByIdCommand implements Command {
    /**
     * Удаляет элемент из коллекции по его id
     */
    CollectionManager cm;
    public RemoveByIdCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String input = args[0];
        cm.removeById(Parser.parseId(input));
    }
}
