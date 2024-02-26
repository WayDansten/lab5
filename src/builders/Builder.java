package builders;

import exceptions.WrongInputException;

public abstract class Builder<T> {
    /**
     * Абстрактный класс-родитель для всех классов-сборщиков экземпляров других классов
     */
    public abstract T build();
}
