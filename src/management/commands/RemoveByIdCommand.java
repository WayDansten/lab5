package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Parser;

import static java.lang.Integer.parseInt;

/**
 * Удаляет элемент из коллекции по его id
 */

public class RemoveByIdCommand implements Command {
    CollectionManager cm;
    public RemoveByIdCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        String input = args[0];
        cm.removeById(Parser.parseId(input));
    }
}
