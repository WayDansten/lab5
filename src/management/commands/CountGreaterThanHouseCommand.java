package management.commands;

import exceptions.WrongDataInputException;
import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;
import stored_classes.enums.Furnish;

import static java.lang.Long.parseLong;

public class CountGreaterThanHouseCommand implements Command {
    CollectionManager cm;
    public CountGreaterThanHouseCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String data = args[0];
        long year;
        while (true) {
            try {
                year = parseLong(data);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый тип данных! Пожалуйста, введите целое число:");
                data = Invoker.getReceiver().next();
            }
        }
        cm.countGreaterThanHouse(year);
    }
}
