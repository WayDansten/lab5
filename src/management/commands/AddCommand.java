package management.commands;

import builders.FlatBuilder;
import management.utility.CollectionManager;
import management.utility.Invoker;

/**
 * Команда добавления элемента в коллекцию
 */

public class AddCommand implements Command {
    CollectionManager cm;
    public AddCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.add(new FlatBuilder(Invoker.getInstance().getIoManager().getReceiver()).build());
        System.out.println("Квартира успешно добавлена!");
    }
}
