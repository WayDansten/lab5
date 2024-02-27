package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;

import static java.lang.Integer.parseInt;

public class RemoveLowerCommand implements Command{
    /**
     * Удаляет из коллекции все элементы, значение поля area которых меньше, чем значение area у элемента с указанным id
     */
    CollectionManager cm;
    public RemoveLowerCommand (CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String input = args[0];
        cm.removeLower(Parser.parseId(input));
    }
}
