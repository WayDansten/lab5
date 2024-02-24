package builders;

import exceptions.WrongInputException;

public abstract class Builder<T> {
    public abstract T build();
}
