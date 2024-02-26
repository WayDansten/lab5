package management.utility;

import builders.FlatBuilder;
import stored_classes.Flat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import static management.utility.Parser.parseFlat;

public class CollectionManager {
    TreeSet<Flat> flats = new TreeSet<>();
    Date initDate = new Date();
    public void add(Flat flat) {
        flats.add(flat);
    }
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
    public void clear() {
        flats.clear();
    }
    public void show() {
        int counter = 1;
        for (Flat flat : flats) {
            System.out.println(counter + ") " + flat);
            counter++;
        }
    }
    public void info() {
        System.out.println("Тип коллекции - " + flats.getClass() + ", Дата инициализации - " + initDate + ", Количество элементов - " + flats.size());
    }
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
    public void fillCollection(File file) throws FileNotFoundException {
        Invoker.fileMode(file);
        Invoker.getReceiver().useDelimiter("\n");
        while (Invoker.getReceiver().hasNext()) {
            flats.add(parseFlat(Invoker.getReceiver().next().split(",")));
        }
        Invoker.interactiveMode();
    }
    public void saveCollection(File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Flat flat : flats) {
                writer.write(Unparser.FlatToCSV(flat));
            }
        }
    }
    public void countGreaterThanHouse(long year) {
        int counter = 0;
        for (Flat flat : flats) {
            if (flat.getHouse().getYear() > year) {
                counter++;
            }
        }
            System.out.println(counter);
    }
    public void filterLessThanFurnish(int quality) {
        for (Flat flat : flats) {
            if (flat.getFurnish().getQuality() < quality) {
                System.out.println(flat);
            }
        }
    }
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
