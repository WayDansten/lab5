package exceptions;

public class WrongInputException extends Exception {
    /**
     * Родительский класс для всех исключений, связанных с неверно введенными пользователем данными
     * @param message Выводимое сообщение об ошибке
     */
    public WrongInputException(String message) {
        super(message);
    }
}
