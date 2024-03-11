package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Parser;

/**
 * Обновляет поля элемента коллекции с указанным id
 */

public class UpdateCommand implements Command {
    CollectionManager cm;
    public UpdateCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        String input = args[0];
        cm.update(Parser.parseId(input));
    }
}
