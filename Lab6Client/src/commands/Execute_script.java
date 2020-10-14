package commands;


import connection.App;
import connection.ClientReceiver;
import connection.ClientSender;
import controller.ClientCommand;
import controller.Commandable;
import controller.Commands;
import dragon.Dragon;
import dragon.Dragonborn;
import utilities.ReaderFromFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Execute_script implements Commandable, ClientCommand {
    String name = "execute_script";
    static private HashMap scripts = new HashMap();

    /**
     * executes script with the name written in command line
     * executes script's name and script's names writen in this script are saved int static variable to prevent collisions
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        ClientReceiver receiver = App.getReceiver();
        ClientSender sender = App.getSender();
        scripts.put((String) arg, 1);
        try {
            String data = ReaderFromFile.readFromFile((String) arg);
            Commands command = new Commands();
            if (data != null) {
                String[] commands = data.split("\n|\r\n");
                for (int i = 0; i < commands.length; i++) {
                    String commandName = commands[i];
                    System.out.println("Команда \"" + commandName + "\":");
                    System.out.print("\t");
                    command.setCommand(commandName);
                    if (command.getMessage() != (null))
                        System.out.println(command.getMessage());
                    if (command.getCommand() != null) {
                        try {
                            try {
                                ClientCommand clientCommand = (ClientCommand) command.getCommand();
                                System.out.println(command.getCommand().execute(command.getArg()).replace("\n", "\n\t"));
                            } catch (ClassCastException e) {
                                if (command.getCommand().getName().equals("exit")) {
                                    sender.sendCommand(command);
                                    command.getCommand().execute(command.getArg());
                                } else {
                                    sender.sendCommand(command);
                                    if (sender.isCommandWithObject())
                                        if (receiver.receive().equals("newObject")) {
                                            ArrayList<String> params = new ArrayList<>();
                                            for (int j = 0; j < 11; j++) {
                                                i++;
                                                params.add(commands[i]);
                                            }
                                            Dragonborn.isFromScript = true;
                                            Dragonborn dragonborn = new Dragonborn();
                                            dragonborn.createFromFile(params);
                                            command.setCommand(commands[i - 11]);
                                            Dragonborn.isFromScript = false;
                                            Dragon newDragon = dragonborn.dragonFromScript;
                                            if (newDragon != null) {
                                                sender.send( new Dragonborn().create());
                                            } else {
                                                System.out.println("Неверно указаны поля дракона: " + dragonborn.whyFailed);
                                                dragonborn.whyFailed = "";
                                            }

                                        }
//                                  }
                                    System.out.println(receiver.receive().replace("\n", "\n\t"));
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Сервер недоступен =(");
                        }
                    }
                }
            }
        } catch (NullPointerException | FileNotFoundException e) {
            return "Указанный файл не найден";
        } finally {
            scripts.clear();
            return "";
        }
    }

    @Override
    public String getName() {
        return name;
    }
}


//                    boolean isValidScript = true;
//                    if (!(commands[i].equals(""))) {
//                        String[] commandAndArg = commands[i].split(" ");
//                        if (commandAndArg[0].equals("execute_script")) {
//                            if (scripts.get(commandAndArg[1]) == null)
//                                scripts.put(commandAndArg[1], 1);
//                            else isValidScript = false;
//                        }
//                        try {
//                            CommandWithObject commandWithObject = (CommandWithObject) invoker.getCommandByName(commandAndArg[0]);
//                            if (commandWithObject != null) {
//                                ArrayList<String> params = new ArrayList<>();
//                                try {
//                                    for (int j = 0; j < 11; j++) {
//                                        i++;
//                                        params.add(commands[i]);
//                                    }
//                                    Dragonborn.isFromScript = true;
//                                    Dragonborn creater = new Dragonborn();
//                                    creater.createFromFile(params);
//                                    System.out.println("\nКоманда \"" + commands[i - 11] + "\":");
//                                    command.(commands[i - 11]);
//                                    Dragonborn.isFromScript = false;
//                                } catch (IndexOutOfBoundsException e) {
//                                    System.out.println("Команда \"" + commandAndArg + "\":");
//                                    System.out.println("Недостаточно параметров");
//                                }
//                            }
//                        } catch (Exception e) {
//                            if (isValidScript) {
//                                System.out.println("Команда \"" + commands[i] + "\":");
//                                command.executeCommand(commands[i]);
//                                System.out.println();
//                            } else {
//                                System.out.println("Команда \"" + commands[i] + "\": невыполнима.\n");
//                            }
//                        }
//                    }
//                }
//            } else System.out.println("Указанный файл не найден.");

