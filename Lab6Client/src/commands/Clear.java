package commands;


import controller.CommandWithoutArg;
import controller.DragonCollection;

public class Clear implements CommandWithoutArg {
    String name = "clear";

    /**
     * if collection is not empty than delete all dragons
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}