package management.commands;

/**
 * Интерфейс для всех команд
 */

public interface Command {
    /**
     * Исполняет команду
     * @param args От 0 до N аргументов
     */
    public void execute(String... args);
}
