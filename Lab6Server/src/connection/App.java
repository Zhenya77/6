package connection;


import commands.Save;
import controller.CommandWithObject;
import controller.Commandable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    static ServerReceiver receiver;
    static ServerSender sender;

    public App(ServerSender sender, ServerReceiver receiver) {

        this.receiver = receiver;
        this.sender = sender;
        checkForSaveCommand();
    }

    public static ServerReceiver getReceiver() {
        return receiver;
    }

    public static ServerSender getSender() {
        return sender;
    }

    public void begin() throws IOException, ClassNotFoundException {
        try {
            sender.setPort(receiver.receive());
            System.out.println("Клиент [" + sender.getPort() + "] подключен к серверу.\n");
//         sender.send("Программа подключена к серверу.\n");
        } catch (Exception e) {
            this.begin();
            this.run();
        }

    }

    public void run() throws IOException, ClassNotFoundException {
        try {
            while (true) {
                String commandResult = "";
                int port = Integer.parseInt(receiver.receive());
                if (port != -1)
                    sender.setPort(port);
                else {
                    this.begin();
                    this.run();
                }
                ArrayList commandAndArgument = receiver.receiveCommand();
                Commandable command = (Commandable) commandAndArgument.get(0);
                String arg = (String) commandAndArgument.get(1);
                System.out.println("Получена команда \"" + command.getName() + "\".");
                try {
                    CommandWithObject commandWithObject = (CommandWithObject) command;
                    try {
                        if (commandWithObject.check(Integer.parseInt(arg))) {
                            sender.send("newObject");
                            commandResult = command.execute(receiver.receiveDragon());
                        } else {
                            sender.send("nope");
                            commandResult = commandWithObject.whyFailed();
                        }
                    } catch (Exception e) {
                        commandResult = "Аргумент команды должен быть числом";
                    }
                } catch (Exception e) {
                    commandResult = (String) command.execute(arg);
                }
//                sender.sendCollecton(DragonCollection.collection);
                sender.send(commandResult);
                if (!commandResult.isEmpty())
                    System.out.println("Клиенту [" + sender.getPort() + "] отправлен результат выполнения команды.");
                else System.out.println("");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            this.begin();
            this.run();

        }
    }


    public static void checkForSaveCommand() {
        Thread backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) {
                            new Save().execute(null);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}



