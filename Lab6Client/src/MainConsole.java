
import commands.*;
import connection.App;
import connection.ClientReceiver;
import connection.ClientSender;
import controller.Commands;
import utilities.Console;

import java.io.IOException;
import java.net.BindException;
import java.net.SocketException;
import java.util.NoSuchElementException;

/**
 * Main-класс
 */
public class MainConsole {
    private static String host;
    private static int clientPort;

    /**
     * Стартовая точка программы. Считывает путь к файлу из переменной окружения и запускает обработчик команд.
     *
     * @param args Аргументы командной строки (не спользуются)
     */
    public static void main(String[] args) {
        int serverPort = 32719;
//        args = new String[]{"localhost", "21"};
        setPortAndHost(args);
        Commands.regist(
                new Help(), new Info(), new Show(), new Insert(), new Clear(), new Exit(), new Remove_all_by_description(),
                new Remove_lower_key(), new Remove_key(), new Update(), new Min_by_description(), new Average_of_age(),
                new Replace_if_greater(), new History(), new Execute_script()
        );
        try {
            ClientReceiver receiver = new ClientReceiver(clientPort);
            ClientSender sender = new ClientSender(serverPort, clientPort, host);
            App app = new App(sender, receiver);
            app.begin();
            app.run();
        } catch (BindException e) {
            System.out.println("Этот порт уже занят=(");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Этот порт слишком большой=(");
        } catch (SocketException e) {
            System.out.println("Хост указан неверно");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("Прерывание работы программы");
        }
    }

    public static void setPortAndHost(String[] args) {
        if (args.length > 1) {
            try {
                clientPort = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Порт должен быть числом");
                clientPort = Console.readPort();
            }
            host = args[0];
        } else if (args.length == 1) {
            host = args[0];
            try {
                clientPort = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Порт должен быть числом");
                clientPort = Console.readPort();
            }
        } else {
            host = Console.readHost();
            clientPort = Console.readPort();
        }
    }
}
