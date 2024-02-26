package builders;

import exceptions.DataOutOfToleranceRegionException;
import management.utility.Invoker;
import stored_classes.Coordinates;

import static java.lang.Integer.parseInt;

public class CoordinatesBuilder extends Builder<Coordinates> {
    /**
     * Класс-сборщик для класса Coordinates
     */
    /**
     * Собирает новый экземпляр класса Coordinates
     * @return Новый экземпляр класса Coordinates
     */
    @Override
    public Coordinates build(){
        return new Coordinates(createXCoordinate(), createYCoordinate());
    }

    /**
     * Создает координату по X в интерактивном режиме
     * @return Координата по X
     */
    public int createXCoordinate(){
        int x;
        int MAX_X = 599;
        while (true) {
            System.out.println("Введите целое число - координату по X (X < " + (MAX_X + 1) + "):");
            try {
                x = parseInt(Invoker.getReceiver().next());
                if (x > MAX_X) {
                    throw new DataOutOfToleranceRegionException("Недопустимое значение числа! X < " + (MAX_X + 1) + ".");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый формат данных! X - целое число.");
            } catch (DataOutOfToleranceRegionException e) {
                System.out.println(e.getMessage());
            }
        }
        return x;
    }

    /**
     * Создает координату по Y в интерактивном режиме
     * @return Координата по Y
     */
    public int createYCoordinate(){
        int y;
        while (true) {
            System.out.println("Введите целое число - координату по Y:");
            try {
                y = parseInt(Invoker.getReceiver().next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый формат данных! Y - целое число.");
            }
        }
        return y;
    }
}
