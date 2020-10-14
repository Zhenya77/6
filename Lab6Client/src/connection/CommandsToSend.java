package connection;


import controller.Commands;

import java.io.IOException;

public class CommandsToSend {
    String commandsToSend = "";

    public String getCommandsToSend() {
        return commandsToSend;
    }

    public void removeCommandsToSend() {
        this.commandsToSend ="";
    }
    public void addCommandsToSend(String commandsToSend) {
        this.commandsToSend += commandsToSend;
    }

    public void sendCommands() {
        try {
            ClientSender sender = App.getSender();
            ClientReceiver receiver = App.getReceiver();
            String data = commandsToSend;
            Commands command = new Commands();
            String res = "";
            //System.out.println("data " + data);
            if (!(data.equals(null))) {
                String[] commands = data.split("\n|\r\n");
                //System.out.println("command.lenght " + commands.length);
                for (int i = 0; i < commands.length; i++) {
                    command.setCommand(commands[i]);
                    if (command.getCommand() != null) {
                        sender.sendCommand(command);
                        if (sender.isCommandWithObject())
                            if (receiver.receive().equals("newObject")) {
//                                CommandShell commandShell = new CommandShell(Long.parseLong(command.getArg()), SpaceMarineGenerator.generate());
//                                sender.send(commandShell);
                            }
                        receiver.receiveCollection();
                        String received = receiver.receive();
                        if (received != null) {
                            res += "Выполнение команды \"" + commands[i] + "\":\n" + received + "\n\n";
                            ;
                        }
                    }
                    if (command.getMessage() != (null)) {
                        String[] sentence = command.getMessage().split(",");
                        res += "Командa \"" + commands[i] + "\": " + sentence[0] + ".\n\n";
                    }
                }
            }
            System.out.print(res);
        } catch (NullPointerException | IOException | ClassNotFoundException e) {
        }

    }
}
