package management.commands;

import builders.FlatBuilder;
import management.utility.CollectionManager;

public class AddCommand implements Command {
    CollectionManager cm;
    public AddCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        cm.add(new FlatBuilder().build());
        System.out.println("Квартира успешно добавлена!");
    }
}
