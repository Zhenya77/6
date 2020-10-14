package commands;


import controller.ClientCommand;
import controller.CommandWithoutArg;
import controller.Commands;

import java.util.ArrayList;

public class History implements CommandWithoutArg , ClientCommand {
    String name = "history";

    /**
     * show list of previously used commands in current session
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        String ans = ("Последние выполненные команды:\n");
        ArrayList<String> history = Commands.getHistory();
        if (history.size() == 0) return ("История пустая.");
        else {
            int numOfCommands = history.size();
            try {
                for (int i = numOfCommands; i > numOfCommands - 6; i--)
                    ans += (history.get(i)) + "\n";
            } catch (IndexOutOfBoundsException e) {
                for (int i = 0; i < numOfCommands; i++)
                    ans += (history.get(i)) + "\n";
            }
        }
        return ans;
    }

    @Override
    public String getName() {
        return name;
    }
}

