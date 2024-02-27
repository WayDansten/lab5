package management.utility;

import builders.FlatBuilder;
import stored_classes.Flat;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import static management.utility.Parser.parseFlat;

/**
 * Класс, управляющий коллекцией
 */

public class CollectionManager {
    TreeSet<Flat> flats = new TreeSet<>();
    Date initDate = new Date();

    /**
     * Добавляет элемент в коллекцию
     * @param flat Добавляемый элемент
     */
    public void add(Flat flat) {
        flats.add(flat);
    }

    /**
     * Удаляет элемент из коллекции по его id
     * @param id id удаляемого элемента
     */
    public void removeById(int id) {
        boolean foundId = false;
        for (Flat flat : flats) {
            if (flat.getId() == id) {
                foundId = true;
                flats.remove(flat);
                System.out.println("Квартира успешно удалена!");
                break;
            }
        }
        if (!foundId) {
            System.out.println("Квартира с данным id не найдена!");
        }
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        flats.clear();
    }

    /**
     * Выводит всю коллекцию в строковом представлении с порядковыми номерами элементов
     */
    public void show() {
        int counter = 1;
        for (Flat flat : flats) {
            System.out.println(counter + ") " + flat);
            counter++;
        }
    }

    /**
     * Выводит информацию о коллекции (тип, дата инициализации, кол-во элементов)
     */
    public void info() {
        System.out.println("Тип коллекции - " + flats.getClass() + ", Дата инициализации - " + initDate + ", Количество элементов - " + flats.size());
    }

    /**
     * Удаляет из коллекции все элементы, значение поля area которых больше, чем значение area у элемента с указанным id
     * @param id id элемента, с которым проводится сравнение
     */
    public void removeGreater(int id) {
        double comparedArea = 0;
        for (Flat flat: flats) {
            if (flat.getId() == id) {
                comparedArea = flat.getArea();
            }
            if (comparedArea != 0 && flat.getArea() > comparedArea) {
                flats.remove(flat);
            }
        }
        if (comparedArea == 0) {
            System.out.println("Квартира с данным id не найдена!");
        }
    }
    /**
     * Удаляет из коллекции все элементы, значение поля area которых меньше, чем значение area у элемента с указанным id
     * @param id id элемента, с которым проводится сравнение
     */
    public void removeLower(int id) {
        double comparedArea = 0;
        for (Flat flat: flats) {
            if (flat.getId() == id) {
                comparedArea = flat.getArea();
                break;
            }
        }
        if (comparedArea == 0) {
            System.out.println("Квартира с данным id не найдена!");
        } else {
            for (Flat flat : flats) {
                if (flat.getArea() < comparedArea) {
                    flats.remove(flat);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Заполняет коллекцию значениями из файла в формате .csv
     * @param filePath Путь к файлу со значениями
     */
    public void fillCollection(String filePath) throws IOException {
        Invoker.fileMode(filePath);
        Invoker.getReceiver().useDelimiter("\n");
        while (Invoker.getReceiver().hasNext()) {
            flats.add(parseFlat(Invoker.getReceiver().next().split(",")));
        }
    }

    /**
     * Сохраняет коллекцию в файл в формате .csv
     * @param file Файл, в который производится сохранение
     * @throws IOException Выбрасывается, если файл не найден
     */
    public void saveCollection(File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Flat flat : flats) {
                writer.write(Unparser.FlatToCSV(flat));
            }
        }
    }

    /**
     * Выводит количество элементов коллекции, значение поля year которых больше, чем указанное значение year
     * @param year Значение, с которым производится сравнение
     */
    public void countGreaterThanHouse(long year) {
        int counter = 0;
        for (Flat flat : flats) {
            if (flat.getHouse().getYear() > year) {
                counter++;
            }
        }
            System.out.println(counter);
    }
    /**
     * Выводит все элементы коллекции, значение поля furnish которых больше, чем указанное значение furnish (сравнение производится по целочисленным константам Furnish.quality)
     * @param quality Значение, с которым производится сравнение
     */
    public void filterLessThanFurnish(int quality) {
        for (Flat flat : flats) {
            if (flat.getFurnish().getQuality() < quality) {
                System.out.println(flat);
            }
        }
    }

    /**
     * Обновляет поля элемента коллекции с указанным id в интерактивном режиме
     * @param id id элемента, с которым производится сравнение
     */
    public void update(int id) {
        boolean foundFlat = false;
        for (Flat flat : flats) {
            if (flat.getId() == id) {
                foundFlat = true;
                System.out.println("Текущие значения полей данной квартиры:");
                System.out.println(flat);
                System.out.println("Введите новые значения полей. Для того, чтобы оставить поле без изменений, введите предыдущее значение поля:");
                flats.remove(flat);
                flats.add(new FlatBuilder().build());
                break;
            }
        }
        if (!foundFlat) {
            System.out.println("Квартира с данным id не найдена!");
        }
    }
}
