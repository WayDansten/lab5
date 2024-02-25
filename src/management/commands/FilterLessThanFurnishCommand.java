package management.commands;

import exceptions.WrongDataInputException;
import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;
import stored_classes.enums.Furnish;

public class FilterLessThanFurnishCommand implements Command{
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
                    throw new WrongDataInputException("Несуществующий вид мебели! Пожалуйста, повторите ввод");
                }
                break;
            } catch (WrongDataInputException e) {
                System.out.println(e.getMessage());
                furnish = Furnish.naming.get(Invoker.getReceiver().next());
            }
        }
        cm.filterLessThanFurnish(furnish.getQuality());
    }
}
