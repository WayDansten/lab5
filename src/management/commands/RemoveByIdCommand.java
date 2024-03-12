package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;

import static java.lang.Integer.parseInt;

/**
 * Команда, удаляющая элемент из коллекции по его id
 */

public class RemoveByIdCommand implements Command {
    CollectionManager cm;
    public RemoveByIdCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        String input = args[0];
        try {
            cm.removeById(parseInt(input));
        } catch (NumberFormatException e) {
            System.err.println("Недопустимый тип данных!");
            if (Invoker.getInstance().getInScriptState()) {
                throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
            }
        }
    }
}
