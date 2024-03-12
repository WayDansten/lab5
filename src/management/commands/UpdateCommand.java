package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;

import static java.lang.Integer.parseInt;

/**
 * Команда, обновляющая поля элемента коллекции с указанным id
 */

public class UpdateCommand implements Command {
    CollectionManager cm;
    public UpdateCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        try {
            cm.update(parseInt(args[0]));
        } catch (NumberFormatException e) {
            System.err.println("Недопустимый тип данных!");
            if (Invoker.getInstance().getInScriptState()) {
                throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
            }
        }
    }
}
