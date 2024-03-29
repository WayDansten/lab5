import exceptions.ErrorInFunctionException;
import management.utility.Invoker;
import stored_classes.Flat;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static management.utility.Parser.parseFlat;

public class Main {
    public static void main(String[] args){
        String filePath;
        if (args.length == 0) {
            filePath = "";
        } else {
            filePath = args[0];
        }
        while (true) {
            try {
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath), 1024)) {
                    Invoker.getInstance().getCollectionManager().fillCollection(bis);
                    break;
                }
            } catch (IOException e) {
                System.err.println("Файл не найден или недостаточно прав! Пожалуйста, введите путь до файла заново:");
                filePath = Invoker.getInstance().getIoManager().getReceiver().next();
            }
        }
        Invoker.getInstance().getIoManager().setInteractiveMode();
        try {
            Invoker.getInstance().launch();
        } catch (ErrorInFunctionException e) {
            System.err.println("При исполнении скрипта произошла ошибка!");
        }
    }
}