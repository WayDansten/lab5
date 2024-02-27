package management.commands;

public interface Command {
    /**
     * Интерфейс для всех команд
     */
    /**
     * Исполняет команду
     * @param args От 0 до N аргументов
     */
    public void execute(String... args);
}
