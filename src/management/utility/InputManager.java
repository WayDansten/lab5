package management.utility;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Класс, отвечающий за ввод
 */

public class InputManager {
    private Scanner receiver = new Scanner(System.in);
    /**
     * Переводит приложение в режим интерактивного ввода с консоли
     */
    public void setInteractiveMode() {
        receiver = new Scanner(System.in);
        receiver.useDelimiter("\n");
    }
    /**
     * Переводит приложение в режим считывания данных из файла
     * @param bis Поток входных данных
     */
    public void setFileMode(BufferedInputStream bis) {
        receiver = new Scanner(bis);
        receiver.useDelimiter("\n");
    }
    public Scanner getReceiver() {
        return receiver;
    }
}
