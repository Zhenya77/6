package utilities;

import com.google.gson.Gson;
import dragon.Dragon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Hashtable;

public class WriterToFile {
    static String filename;

    public static void setFilename(String filename) {
        WriterToFile.filename = filename;
    }

    public static void writeToFile(String data) throws FileNotFoundException {
        try {
            File file = new File(filename);
            PrintWriter printWriter = new PrintWriter("file.txt");
            printWriter.print(data);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * write information about dragon into the file
     *
     * @param dragons
     * @throws FileNotFoundException
     */
    public static void writeDragonToFile(Hashtable<Integer, Dragon> dragons) throws FileNotFoundException {
        Gson gson = new Gson();
        try {
            File file = new File(filename);
            PrintWriter printWriter = new PrintWriter(filename);
            printWriter.println(gson.toJson(dragons));
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
