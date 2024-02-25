package management.utility;

import stored_classes.Coordinates;
import stored_classes.Flat;
import stored_classes.House;
import stored_classes.enums.Furnish;
import stored_classes.enums.Transport;
import stored_classes.enums.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Parser {
    public static int parseId(String data) {
        int id;
        while (true) {
            try {
                id = parseInt(data);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Недопустимый тип данных! Пожалуйста, введите id заново (id - целое число):");
                data = Invoker.getReceiver().next();
            }
        }
        return id;
    }
    public static Flat parseFlat(String[] data) {
        return new Flat(parseInt(data[0]), data[1], new Coordinates(parseInt(data[2]), parseInt(data[3])), parseDate(data[4]),
                parseDouble(data[5]), parseInt(data[6]), Furnish.naming.get(data[7]), View.naming.get(data[8]), Transport.naming.get(data[9]),
                new House(data[10], parseLong(data[11]), parseLong(data[12]), parseInt(data[13])));
    }
    public static Date parseDate(String data) {
        SimpleDateFormat template = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            return template.parse(data);
        } catch (ParseException e) {
            System.out.println("Недопустимый формат даты!");
            return null;
        }
    }
}
