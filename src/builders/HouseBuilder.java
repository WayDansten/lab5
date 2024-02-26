package builders;

import exceptions.DataOutOfToleranceRegionException;
import management.utility.Invoker;
import stored_classes.House;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class HouseBuilder extends Builder<House> {
    /**
     * Класс-сборщик для класса House
     */
    /**
     * Собирает новый экземпляр класса House
     * @return Новый экземпляр класса House
     */
    @Override
    public House build() {
        return new House(createName(), createYear(), createNumberOfFloors(), createNumberOfLifts());
    }

    /**
     * Создаем имя
     * @return
     */
    public String createName() {
        String name;
        System.out.println("Введите название дома (оставьте строку пустой для значения null)");
        name = Invoker.getReceiver().next();
        if (name.isEmpty()) {
            name = null;
        }
        return name;
    }

    public long createYear() {
        long year;
        int MIN_YEAR = 0;
        while (true) {
            System.out.println("Введите целое число - год постройки дома Y (N > " + MIN_YEAR + "):");
            try {
                year = parseLong(Invoker.getReceiver().next());
                if (year <= MIN_YEAR) {
                    throw new DataOutOfToleranceRegionException("Недопустимое значение числа! Y > " + MIN_YEAR + ".");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый формат данных! Y - целое число.");
            } catch (DataOutOfToleranceRegionException e) {
                System.out.println(e.getMessage());
            }
        }
        return year;
    }

    public long createNumberOfFloors() {
        long numberOfFloors;
        int MIN_NUMBER_OF_FLOORS = 0;
        while (true) {
            System.out.println("Введите целое число - количество этажей в доме N (N > " + MIN_NUMBER_OF_FLOORS + "):");
            try {
                numberOfFloors = parseLong(Invoker.getReceiver().next());
                if (numberOfFloors <= MIN_NUMBER_OF_FLOORS) {
                    throw new DataOutOfToleranceRegionException("Недопустимое значение числа! N > " + MIN_NUMBER_OF_FLOORS + ".");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый формат данных! N - целое число.");
            } catch (DataOutOfToleranceRegionException e) {
                System.out.println(e.getMessage());
            }
        }
        return numberOfFloors;
    }

    public Integer createNumberOfLifts() {
        int numberOfLifts;
        int MIN_NUMBER_OF_LIFTS = 0;
        while (true) {
            System.out.println("Введите целое число - количество лифтов в доме N (N > " + MIN_NUMBER_OF_LIFTS + "):");
            try {
                numberOfLifts = parseInt(Invoker.getReceiver().next());
                if (numberOfLifts <= MIN_NUMBER_OF_LIFTS) {
                    throw new DataOutOfToleranceRegionException("Недопустимое значение числа! N > " + MIN_NUMBER_OF_LIFTS + ".");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый формат данных! N - целое число.");
            } catch (DataOutOfToleranceRegionException e) {
                System.out.println(e.getMessage());
            }
        }
        return numberOfLifts;
    }

}
