import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;
import stored_classes.Flat;

public class Main {
    public static void main(String[] args) {
        TreeSet<Flat> flats = new TreeSet<Flat>();
        String filePath = String.join("", args);
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             InputStreamReader isr = new InputStreamReader(bis);
             BufferedReader br = new BufferedReader(isr);)
        {
           String line;
           String[] lineData;
           line = br.readLine();
           while (line != null) {
               lineData = line.split(",");

               line = br.readLine();
           }
        }
         catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}