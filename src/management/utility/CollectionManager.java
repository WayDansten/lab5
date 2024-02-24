package management.utility;

import builders.FlatBuilder;
import stored_classes.Flat;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class CollectionManager {
    TreeSet<Flat> flats = new TreeSet<>();
    Date initDate = new Date();
    public void add(Flat flat) {
        flats.add(flat);
        System.out.println("Квартира успешно добавлена!");
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
}
