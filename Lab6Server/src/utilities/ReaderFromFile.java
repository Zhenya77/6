package utilities;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderFromFile {

    public static String readFromFile(String filename) throws FileNotFoundException {
        try {
            String data = "";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
                data += scanner.nextLine().trim() + "\n";
            scanner.close();
            WriterToFile.setFilename(filename);
            return data;
        } catch (FileNotFoundException | NullPointerException e) {
            Scanner scanner = new Scanner(System.in);
            while (filename == (null)) {
                System.out.print("Вы забыли указать имя файла. Укажите имя файла сейчас:\n~ ");
                String newFilename = scanner.nextLine();
                if (!(newFilename.equals(""))) {
                    WriterToFile.setFilename(filename);
                    break;
                }
            }
            return null;
        }
    }
}