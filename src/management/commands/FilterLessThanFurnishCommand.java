package management.commands;

import exceptions.WrongDataTypeInputException;
import management.utility.CollectionManager;
import management.utility.Invoker;
import stored_classes.enums.Furnish;

public class FilterLessThanFurnishCommand implements Command{
    /**
     * Выводит все элементы коллекции, значение поля furnish (константы Furnish.quality) меньше указанного
     */
    CollectionManager cm;
    public FilterLessThanFurnishCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        Furnish furnish = Furnish.naming.get(args[0]);;
        while (true) {
            try {
                if (furnish == null) {
                    throw new WrongDataTypeInputException("Несуществующий вид мебели! Пожалуйста, повторите ввод");
                }
                break;
            } catch (WrongDataTypeInputException e) {
                System.out.println(e.getMessage());
                furnish = Furnish.naming.get(Invoker.getReceiver().next());
            }
        }
        cm.filterLessThanFurnish(furnish.getQuality());
    }
}
