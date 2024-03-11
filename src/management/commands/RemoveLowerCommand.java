package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Parser;

/**
 * Удаляет из коллекции все элементы, значение поля area которых меньше, чем значение area у элемента с указанным id
 */

public class RemoveLowerCommand implements Command{
    CollectionManager cm;
    public RemoveLowerCommand (CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        String input = args[0];
        cm.removeLower(Parser.parseId(input));
    }
}
