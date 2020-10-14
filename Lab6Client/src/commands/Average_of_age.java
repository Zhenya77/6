package commands;

import controller.CommandWithoutArg;

public class Average_of_age implements CommandWithoutArg {
    String name = "average_of_age";

    /**
     * if collection is nor empty than show average age of all dragons
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }
}

