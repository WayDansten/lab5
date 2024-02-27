package exceptions;

public class DataOutOfToleranceRegionException extends WrongInputException {
    /**
     * Класс исключений, связанных с данными, выходящими за ОДЗ для полей классов
     * @param message Выводимое сообщение об ошибке
     */
    public DataOutOfToleranceRegionException(String message) {
        super(message);
    }
}
