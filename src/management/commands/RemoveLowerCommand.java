package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;

import static java.lang.Integer.parseInt;

/**
 * Команда, удаляющая из коллекции все элементы, значение поля id которых меньше, чем значение id у элемента с указанным id
 */

public class RemoveLowerCommand implements Command{
    CollectionManager cm;
    public RemoveLowerCommand (CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        try {
            cm.removeLower(parseInt(args[0]));
        } catch (NumberFormatException e) {
            System.err.println("Недопустимый тип данных!");
            if (Invoker.getInstance().getInScriptState()) {
                throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
            }
        }
    }
}
