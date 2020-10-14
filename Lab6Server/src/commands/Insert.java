package commands;


import controller.CommandWithObject;
import controller.DragonCollection;
import dragon.Dragon;

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
        Dragon dragon = (Dragon) o;
        dragon.setId(arg);
        DragonCollection.insert(arg, dragon);
        return ("Дракон залетел в коллекцию.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean check(int arg) {
        this.arg = arg;
        if (DragonCollection.isKeyFree(arg))
            return true;
        else {
            whyFailed = "Указанный id занят.";
            return false;
        }
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }
}

