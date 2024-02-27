package management.commands;

import management.utility.CollectionManager;

public class ExecuteScriptCommand implements Command {
    /**
     * Исполняет скрипт с командами
     */
    CollectionManager cm;
    public ExecuteScriptCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {

    }
}
