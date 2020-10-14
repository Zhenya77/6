import connection.App;
import connection.ServerReceiver;
import connection.ServerSender;
import controller.DragonCollection;
import utilities.Decoder;
import utilities.ReaderFromFile;
import utilities.WriterToFile;

import java.io.IOException;
import java.io.Writer;
import java.net.BindException;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    static String filePath;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            filePath = System.getenv("file");
//             filePath = "collection.json";
            System.out.println("Переменная окружения \"file\": \"" + filePath + "\"");
            checkFilepath();
            DragonCollection.dateCreation = LocalDate.now();
            ServerSender sender = new ServerSender(11352);
            ServerReceiver receiver = new ServerReceiver(32719);
            App app = new App(sender, receiver);
            DragonCollection.collection = Decoder.loadCollection(ReaderFromFile.readFromFile(filePath));
            WriterToFile.setFilename(filePath);
            System.out.println("Для сохранения текущего состояния коллекции используйте команду\"save\".");
            receiver.receive();
            app.begin();
            app.run();
        } catch (BindException e) {
            System.out.println("Порт занят.");
        }
    }

    private static void checkFilepath() {
        if (filePath == null) {
            Scanner in = new Scanner(System.in);
            filePath = "";
            while (filePath.equals("")) {
                System.out.print("Введите имя файла:\n~ ");
                filePath = in.nextLine();
            }
        }
    }
}