package management.utility;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class IOManager {
    private Scanner receiver;
    public Scanner getReceiver() {
        return receiver;
    }
    public void setInteractiveMode() {
        receiver = new Scanner(System.in);
        receiver.useDelimiter("\n");
    }
    public void setFileMode(BufferedInputStream bis) {
        receiver = new Scanner(bis);
        receiver.useDelimiter("\n");
    }
}
