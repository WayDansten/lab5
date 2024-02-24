package management.commands;

import management.utility.CollectionManager;

public class HelpCommand implements Command {
    CollectionManager cm;
    public HelpCommand(CollectionManager cm) {
        this.cm = cm;
    }
    @Override
    public void execute(String... args) {
        System.out.println("""
                help - выводит справку по всем доступным командам
                info - выводит информацию о коллекции (тип, дата инициализации, кол-во элементов)
                show - выводит все элементы коллекции в строковом представлении
                add - добавляет новый элемент в коллекцию
                update id - обновляет значение элемента коллекции, id которого равен заданному
                remove_by_id id - удаляет элемент из коллекции по его id
                clear - очищает коллекцию
                save - сохраняет коллекцию в файл
                execute_script file_name - считывает и запускает скрипт из указанного файла
                exit - завершает работу в программы (без сохранения в файл)
                remove_greater - удаляет из коллекции все элементы, превышающие заданный (по значению поля area)
                remove_lower - удаляет из коллекции все элементы, меньшие заданного (по значению поля area)
                history - выводит последние 10 команд без аргументов
                count_greater_than_house house - выводит количество элементов, значение поля house которых больше заданного
                filter_contains_name name - выводит элементы, значение поля name которых содержит введенную подстроку
                filter_less_than_furnish furnish - выводит элементы, значение поля furnish которых меньше введенного
                """);
    }
}
