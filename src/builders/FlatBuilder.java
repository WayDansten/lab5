package builders;

import exceptions.DataOutOfToleranceRegionException;
import exceptions.WrongDataInputException;
import management.utility.Invoker;
import stored_classes.Coordinates;
import stored_classes.Flat;
import stored_classes.House;
import stored_classes.enums.Furnish;
import stored_classes.enums.Transport;
import stored_classes.enums.View;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class FlatBuilder extends Builder<Flat> {
    @Override
    public Flat build() {
        Coordinates coordinates = new CoordinatesBuilder().build();
        House house = new HouseBuilder().build();
        return new Flat(createName(), coordinates, createArea(), createNumberOfRooms(), createFurnish(), createView(), createTransport(), house);
    }
    public double createArea() {
        double s;
        int MIN_AREA = 0;
        while (true) {
            System.out.println("Введите вещественное число - площадь квартиры S (S > " + MIN_AREA + "):");
            try {
                s = parseDouble(Invoker.getReceiver().next());
                if (s <= MIN_AREA) {
                    throw new DataOutOfToleranceRegionException("Недопустимое значение числа! S > " + MIN_AREA + ".");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый формат данных! S - вещественное число.");
            } catch (DataOutOfToleranceRegionException e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }

    public Integer createNumberOfRooms() {
        int numberOfRooms;
        int MIN_NUMBER_OF_ROOMS = 0;
        while (true) {
            System.out.println("Введите целое число - количество комнат в квартире N (N > " + MIN_NUMBER_OF_ROOMS + "):");
            try {
                numberOfRooms = parseInt(Invoker.getReceiver().next());
                if (numberOfRooms <= MIN_NUMBER_OF_ROOMS) {
                    throw new DataOutOfToleranceRegionException("Недопустимое значение числа! N > " + MIN_NUMBER_OF_ROOMS + ".");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый формат данных! N - целое число.");
            } catch (DataOutOfToleranceRegionException e) {
                System.out.println(e.getMessage());
            }
        }
        return numberOfRooms;
    }

    public String createName() {
        String name;
        while (true) {
            System.out.println("Введите название квартиры (название - не пустая строка)");
            try {
                name = Invoker.getReceiver().next();
                if (name.isEmpty()) {
                    throw new WrongDataInputException("Название квартиры не может быть пустой строкой!");
                }
                break;
            } catch (WrongDataInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    public Furnish createFurnish() {
        Furnish furnish;
        while (true) {
            System.out.println("Введите вид мебели в квартире (перечень приведен ниже):");
            System.out.println("""
                    NONE - мебель отсутствует
                    LITTLE - небольшое количество мебели
                    BAD - низкокачественная мебель
                    FINE - качественная мебель
                    DESIGNER - дизайнерская мебель
                    """);
            try {
                furnish = Furnish.naming.get(Invoker.getReceiver().next());
                if (furnish == null) {
                    throw new WrongDataInputException("Несуществующий вид мебели! Пожалуйста, введите вид мебели из перечня.");
                }
                break;
            } catch (WrongDataInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return furnish;
    }

    public Transport createTransport() {
        Transport transport;
        while (true) {
            System.out.println("Введите степень транспортной доступности квартиры (перечень приведен ниже):");
            System.out.println("""
                    FEW - очень малое количество общественного транспорта
                    LITTLE - небольшое количество общественного транспорта
                    NORMAL - среднее количество общественного транспорта
                    ENOUGH - достаточное количество общественного транспорта
                    """);
            try {
                transport = Transport.naming.get(Invoker.getReceiver().next());
                if (transport == null) {
                    throw new WrongDataInputException("Несуществующая степень транспортной доступности! Пожалуйста, введите степень из перечня.");
                }
                break;
            } catch (WrongDataInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return transport;
    }

    public View createView() {
        View view;
        while (true) {
            System.out.println("Введите, какой у квартиры вид из окна (перечень приведен ниже):");
            System.out.println("""
                    BAD - плохой вид из окна
                    NORMAL - неплохой вид из окна
                    STREET - вид на улицу
                    YARD - вид на двор
                    PARK - вид на парк
                    """);
            try {
                view = View.naming.get(Invoker.getReceiver().next());
                if (view == null) {
                    throw new WrongDataInputException("Несуществующий вид из окна! Пожалуйста, введите вид из перечня.");
                }
                break;
            } catch (WrongDataInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return view;
    }
}
