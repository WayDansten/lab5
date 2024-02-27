import management.utility.Invoker;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static management.utility.Parser.parseFlat;

public class Main {
    public static void main(String[] args){
        String filePath = args[0];
        Invoker mainInvoker = new Invoker();
        while (true) {
            try {
                Invoker.fileMode(filePath);
                break;
            } catch (IOException e) {
                System.out.println("Файл не найден! Пожалуйста, введите путь до файла заново:");
                filePath = Invoker.getReceiver().next();
            }
        }
        Invoker.interactiveMode();
        mainInvoker.launch();
    }
}