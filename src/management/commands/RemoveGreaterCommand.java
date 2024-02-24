package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;
import management.utility.Parser;

import static java.lang.Integer.parseInt;

public class RemoveGreaterCommand implements Command {
    CollectionManager cm;
    public RemoveGreaterCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        String input = args[0];
        cm.removeGreater(Parser.parseId(input));
    }
}
