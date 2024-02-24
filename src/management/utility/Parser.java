package management.utility;

import static java.lang.Integer.parseInt;

public class Parser {
    public static int parseId(String input) {
        int id;
        while (true) {
            try {
                id = parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый тип данных! Пожалуйста, введите id заново (id - целое число):");
                input = Invoker.getReceiver().next();
            }
        }
        return id;
    }
}
