package management.utility;

import builders.FlatBuilder;
import exceptions.ErrorInFunctionException;
import stored_classes.Flat;

import java.io.*;
import java.nio.Buffer;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import static java.lang.Integer.parseInt;
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
                Flat.removeUsedId(id);
                System.out.println("Квартира успешно удалена!");
                break;
            }
        }
        if (!foundId) {
            System.err.println("Квартира с данным id не найдена!");
        }
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        Flat.clearUsedIds();
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
     * Удаляет из коллекции все элементы, значение поля id которых больше, чем значение id у элемента с указанным id
     * @param id id элемента, с которым проводится сравнение
     * @throws ErrorInFunctionException Выбрасывается, если произошло любое другое исключение во время исполнения скрипта
     */
    public void removeGreater(int id) throws ErrorInFunctionException{
        boolean foundFlat = false;
        for (Flat flat: flats) {
            if (flat.getId() == id) {
                foundFlat = true;
                break;
            }
        }
        if (!foundFlat) {
            System.err.println("Квартира с данным id не найдена!");
            if (Invoker.getInstance().getInScriptState()) {
                throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
            }
        } else {
            flats.removeIf(flat -> flat.getId() > id);
            for (int i : Flat.getUsedIds()) {
                if (i > id) {
                    Flat.removeUsedId(i);
                }
            }
        }
    }
    /**
     * Удаляет из коллекции все элементы, значение поля area которых меньше, чем значение area у элемента с указанным id
     * @param id id элемента, с которым проводится сравнение
     * @throws ErrorInFunctionException Выбрасывается, если произошло любое другое исключение во время исполнения скрипта
     */
    public void removeLower(int id) throws ErrorInFunctionException{
        boolean foundFlat = false;
        for (Flat flat: flats) {
            if (flat.getId() == id) {
                foundFlat = true;
                break;
            }
        }
        if (!foundFlat) {
            System.err.println("Квартира с данным id не найдена!");
            if (Invoker.getInstance().getInScriptState()) {
                throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
            }
        } else {
            flats.removeIf(flat -> flat.getId() < id);
            for (int i : Flat.getUsedIds()) {
                if (i < id) {
                    Flat.removeUsedId(i);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Заполняет коллекцию значениями из файла в формате .csv
     * @param bis Буферизированный поток данных из файла
     * @throws IOException Выбрасывается в случае, если файла не существует или не хватае прав для считывания информации из него
     */
    public void fillCollection(BufferedInputStream bis) throws IOException {
        Invoker.getInstance().getIoManager().setFileMode(bis);
        while (Invoker.getInstance().getIoManager().getReceiver().hasNext()) {
            String[] data = Invoker.getInstance().getIoManager().getReceiver().next().split(",");
            try {
                Flat flat = parseFlat(data);
                Flat.addUsedId(parseInt(data[0]));
                flats.add(flat);
            } catch (NumberFormatException e) {
                System.err.println("Некорректная строка данных! Квартира добавлена не будет");
            }
        Validator.validateAll(flats);
        }
    }

    /**
     * Сохраняет коллекцию в файл в формате .csv
     * @param file Файл, в который производится сохранение
     * @throws IOException Выбрасывается, если не хватает прав для записи в файл
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
     * @throws ErrorInFunctionException Выбрасывается, если произошло любое другое исключение во время исполнения скрипта
     */
    public void update(int id) throws ErrorInFunctionException{
        boolean foundFlat = false;
        for (Flat flat : flats) {
            if (flat.getId() == id) {
                foundFlat = true;
                System.out.println("Текущие значения полей данной квартиры:");
                System.out.println(flat);
                System.out.println("Введите новые значения полей. Для того, чтобы оставить поле без изменений, введите предыдущее значение поля:");
                flats.remove(flat);
                flats.add(new FlatBuilder(Invoker.getInstance().getIoManager().getReceiver()).build());
                break;
            }
        }
        if (!foundFlat) {
            System.err.println("Квартира с данным id не найдена!");
            if (Invoker.getInstance().getInScriptState()) {
                throw new ErrorInFunctionException("При исполнении скрипта произошла ошибка!");
            }
        }
    }

    /**
     * Выводит все элементы коллекции, поле name которых содержит введенную подстроку
     * @param searchedString Введенная подстрока
     */
    public void filterContainsName(String searchedString) {
        for (Flat flat : flats) {
            if (flat.getName().contains(searchedString)) {
                System.out.println(flat);
            }
        }
    }
}
