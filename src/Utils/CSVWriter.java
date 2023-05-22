package Utils;

import java.io.*;
import java.text.*;
import java.util.*;

public class CSVWriter {
    public static void CSVFile(String numeActiune) {
        Date timestamp = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestampString = dateFormat.format(timestamp);
        try {
            FileWriter fileWriter = new FileWriter("audit.csv", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("%s,%s\n", numeActiune, timestampString);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Eroare la scrierea in fisierul CSV: " + e.getMessage());
        }
    }
}
