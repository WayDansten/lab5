package builders;

import exceptions.ErrorInFunctionException;
import exceptions.WrongInputException;

/**
 * Абстрактный класс-родитель для всех классов-сборщиков экземпляров других классов
 */

public abstract class Builder<T> {
    public abstract T build() throws ErrorInFunctionException;
}
