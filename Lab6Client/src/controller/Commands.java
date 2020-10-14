package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * class invoker realizes managing of commands
 *
 * @author Diana
 */
public class Commands implements Serializable {


    private static Map<String, Commandable> commands = new TreeMap<>();
    private static ArrayList<String> history = new ArrayList<>();

    public static void setCommands(Map<String, Commandable> commands) {
        Commands.commands = commands;
    }

    public static ArrayList<String> getHistory() {
        return history;
    }
    public Commandable getCommandByName(String name) {
        return commands.get(name);
    }
    private Commandable command;
    private String arg;
    private String message;

    public String getMessage() {
        return message;
    }

    public static Map<String, Commandable> getCommands() {
        return commands;
    }


    public Commandable getCommand() {
        return command;
    }


    public String getArg() {
        return arg;
    }


    public static void regist(Commandable... commands) {
        for (Commandable command : commands) {
            Commands.commands.put(command.getName(), command);
        }
    }


    public void setCommand(String commandName) {
        String[] nameAndArgument = commandName.split(" ");
        if (!commandName.equals("")) {
            if (commands.get(nameAndArgument[0]) == null) {
                this.message = ("Такой команды не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
                this.command = null;
                this.arg = null;
            } else {
                try {
                    CommandWithoutArg commandWithoutArg = (CommandWithoutArg) commands.get(nameAndArgument[0]);
                    try {
                        String s = nameAndArgument[1];
                        this.command = null;
                        this.arg = null;
                        this.message = ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                    } catch (Exception e) {
                        this.command = commands.get(nameAndArgument[0]);
                        this.arg = null;
                        this.message = null;
                        if (!nameAndArgument[0].equals("history"))
                            history.add((nameAndArgument[0]));
                    }
                } catch (Exception e) {
                    try {
                        String s = nameAndArgument[2];
                        this.message = ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        this.arg = null;
                        this.command = null;
                    } catch (IndexOutOfBoundsException e1) {
                        try {
                            this.command = commands.get(nameAndArgument[0]);
                            this.arg = nameAndArgument[1];
                            this.message = null;
                            if (!nameAndArgument[0].equals("history"))
                                history.add((nameAndArgument[0]));
                        } catch (IndexOutOfBoundsException e2) {
                            this.command = null;
                            this.arg = null;
                            this.message = "Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.";
                        }
                    }
                }
            }
        } else {
            this.arg = null;
            this.command = null;
            this.message = null;
        }
    }
}

