package commands;


import controller.CommandWithoutArg;

public class Exit implements CommandWithoutArg {
    String name = "exit";

    /**
     * exit your program
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        new Save().execute(null);
        return  "";
    }

    @Override
    public String getName() {
        return name;
    }
}