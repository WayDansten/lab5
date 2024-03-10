package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;

import static java.lang.Long.parseLong;

/**
 * Команда, считающая все элементы коллекции, у которых значение поля house.year больше, чем указанное значение
 */

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
                System.err.println("Недопустимый тип данных! Пожалуйста, введите целое число:");
                data = Invoker.getInstance().getIoManager().getReceiver().next();
            }
        }
        cm.countGreaterThanHouse(year);
    }
}
