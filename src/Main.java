import management.utility.Invoker;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static management.utility.Parser.parseFlat;

public class Main {
    public static void main(String[] args){
        String filePath = args[0];
        while (true) {
            try {
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath), 1024)) {
                    Invoker.getInstance().getCollectionManager().fillCollection(bis);
                    break;
                }
            } catch (IOException e) {
                System.err.println("Файл не найден! Пожалуйста, введите путь до файла заново:");
                filePath = Invoker.getInstance().getIoManager().getReceiver().next();
            }
        }
        Invoker.getInstance().getIoManager().setInteractiveMode();
        Invoker.getInstance().launch();
    }
}