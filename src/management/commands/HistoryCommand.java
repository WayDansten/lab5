package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;

/**
 * Выводит имена последних 10 команд
 */

public class HistoryCommand implements Command {
    CollectionManager cm;
    public HistoryCommand (CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        System.out.println(Invoker.getInstance().getCommandHistory());
    }
}
