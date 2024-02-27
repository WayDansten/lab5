package management.commands;

import management.utility.CollectionManager;
import management.utility.Parser;

public class UpdateCommand implements Command {
    /**
     * Обновляет поля элемента коллекции с указанным id
     */
    CollectionManager cm;
    public UpdateCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String input = args[0];
        cm.update(Parser.parseId(input));
    }
}
