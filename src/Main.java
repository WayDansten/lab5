import management.utility.Invoker;

import java.io.File;
import java.io.FileNotFoundException;

import static management.utility.Parser.parseFlat;

public class Main {
    public static void main(String[] args){
        String filePath = args[0];
        Invoker mainInvoker = new Invoker();
        while (true) {
            try {
                File file = new File(filePath);
                mainInvoker.getCollectionManager().fillCollection(file);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден! Пожалуйста, введите путь до файла заново:");
                filePath = Invoker.getReceiver().next();
            }
        }
        Invoker.interactiveMode();
        mainInvoker.launch();
    }
}