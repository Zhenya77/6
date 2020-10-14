package connection;

import controller.ClientCommand;
import controller.Commands;
import dragon.*;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    private static ClientReceiver receiver;
    private static ClientSender sender;
    boolean was = false;
    private Commands command = new Commands();
    private Scanner in = new Scanner(System.in);
    private CommandsToSend commandsToSend = new CommandsToSend();

    public App(ClientSender sender, ClientReceiver receiver) {
        this.receiver = receiver;
        this.sender = sender;
        System.out.println("Работа приложения запущена.");
    }


    public static ClientReceiver getReceiver() {
        return receiver;
    }

    public static ClientSender getSender() {
        return sender;
    }

    public void begin() throws IOException {
        sender.send("-1");
        try {
            int tumb = 0;
            sender.sendClientPort();
            //sender.send(filename);
//            String received = receiver.receive();
//            if (received != null) {
//                System.out.print(received);
//            }
            was = true;
        } catch (SocketTimeoutException e) {
            was = false;
        }
    }

    public void run() throws IOException {
        while (true) {
            System.out.print("$ ");
            String commandName = in.nextLine();
            command.setCommand(commandName);
            if (command.getMessage() != (null))
                System.out.println(command.getMessage());
            if (command.getCommand() != null) {
                try {
                    if (!was) this.begin();
                    if (!(commandsToSend.getCommandsToSend().equals("")))
                        this.begin();
                    try {
                        ClientCommand clientCommand = (ClientCommand) command.getCommand();
                        System.out.println(command.getCommand().execute(command.getArg()));
                    } catch (ClassCastException e) {
                        if (command.getCommand().getName().equals("exit")) {
                            sender.sendCommand(command);
                            command.getCommand().execute(command.getArg());
                        } else {
                            sender.sendCommand(command);
                            if (sender.isCommandWithObject())
                                if (receiver.receive().equals("newObject")) {
                                    sender.send(new Dragonborn().create());
                                }
                            System.out.println(receiver.receive());
                        }
                        if (!(commandsToSend.getCommandsToSend().equals(""))) {
//                        System.out.println("\nКоманды ранее отправленные на сервер:");
                            commandsToSend.addCommandsToSend(commandName);
//                        commandsToSend.sendCommands();
                            commandsToSend.removeCommandsToSend();
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();

                } catch (SocketTimeoutException e) {
                    System.out.println("Сервер недоступен =(");
                    commandsToSend.addCommandsToSend(commandName + "\n");
                    //if (was == false)
                    this.run();

                }
            }
        }
    }
}