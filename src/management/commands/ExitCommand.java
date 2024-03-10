package management.commands;

import management.utility.CollectionManager;
import management.utility.Invoker;

/**
 * Завершает работу приложения
 */

public class ExitCommand implements Command{
    CollectionManager cm;
    public ExitCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        System.out.println("Завершение работы приложения.");
        Invoker.getInstance().setActuatorState(false);
    }
}
