package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;

public class HistoryCommand implements Command {
    /**
     * Выводит имена последних 10 команд
     */
    CollectionManager cm;
    public HistoryCommand (CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        System.out.println(Invoker.getCommandHistory());
    }
}
