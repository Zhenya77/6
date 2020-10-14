package commands;


import controller.CommandWithoutArg;
import controller.DragonCollection;

public class Info implements CommandWithoutArg {
    String name = "info";

    /**
     * show information about this collection
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        return (DragonCollection.getInfo());
    }

    @Override
    public String getName() {
        return name;
    }
}
