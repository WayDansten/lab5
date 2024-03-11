package management.commands;

import exceptions.ErrorInFunctionException;
import exceptions.WrongInputException;
import management.utility.CollectionManager;
import management.utility.Invoker;
import stored_classes.enums.Furnish;

/**
 * Выводит все элементы коллекции, значение поля furnish (константы Furnish.quality) меньше указанного
 */

public class FilterLessThanFurnishCommand implements Command{
    CollectionManager cm;
    public FilterLessThanFurnishCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) throws ErrorInFunctionException{
        Furnish furnish = Furnish.naming.get(args[0]);;
        while (true) {
            try {
                if (furnish == null) {
                    throw new WrongInputException("Несуществующий вид мебели! Пожалуйста, повторите ввод");
                }
                break;
            } catch (WrongInputException e) {
                System.err.println(e.getMessage());
                if (Invoker.getInstance().getInScriptState()) {
                    throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
                }
                furnish = Furnish.naming.get(Invoker.getInstance().getIoManager().getReceiver().next());
            }
        }
        cm.filterLessThanFurnish(furnish.getQuality());
    }
}
