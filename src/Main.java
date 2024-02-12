import java.io.*;
import java.util.Arrays;
import java.util.Date;
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
               int id = Integer.parseInt(lineData[0]);
               String name = lineData[1];
               int x = Integer.parseInt(lineData[2]);
               int y = Integer.parseInt(lineData[3]);
               Date date = new Date();
               System.out.println(date);
               line = br.readLine();
           }
        }
         catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}