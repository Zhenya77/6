package commands;


import controller.CommandWithObject;

public class Insert implements CommandWithObject {
    String name = "insert";
    String whyFailed = "";
    Integer arg;

    /**
     * creates a new dragon if this id is free to use
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

    @Override
    public boolean check(int arg) {
        return false;
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }
}

