package management.commands;

import exceptions.ErrorInFunctionException;
import management.utility.CollectionManager;

/**
 * Команда, выводящая все элементы коллекции, поле name которых содержит введенную подстроку
 */

public class FilterContainsNameCommand implements Command{
    CollectionManager cm;
    public FilterContainsNameCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException {
        cm.filterContainsName(args[0]);
    }
}
