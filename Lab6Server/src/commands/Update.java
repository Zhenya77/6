package commands;

import controller.CommandWithObject;
import controller.DragonCollection;
import dragon.Dragon;

public class Update implements CommandWithObject {
    String name = "update";
    String whyFailed = "";
    int arg;

    /**
     * update dragons parametres with appointed id number
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        Dragon dragon = (Dragon) o;
        dragon.setId(arg);
        DragonCollection.update((Integer) arg, dragon);
        return ("Дракон с id[" + arg + "] успешно обновлен.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean check(int arg) {
        this.arg = arg;
        if (DragonCollection.isKeyFree((Integer) arg)) {
            whyFailed = "Дракона с указанымм id не существует";
            return false;
        } else return true;
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }
}

