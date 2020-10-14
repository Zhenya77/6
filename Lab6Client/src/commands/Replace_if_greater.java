package commands;

import controller.CommandWithObject;
import controller.DragonCollection;
import dragon.Dragon;

public class Replace_if_greater implements CommandWithObject {
    String name = "replace_if_greater";
    String whyFailed;
    int arg;

    /**
     * replace current dragon with new one if parametres values are greater
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        if (DragonCollection.getSize() == 0) return ("Коллекция итак пустая.");
        else {
            Dragon newDragon = (Dragon) o;
            newDragon.setId(arg);
            if (DragonCollection.collection.get(arg).compareTo(newDragon) < 0) {
                newDragon.setId(arg);
                DragonCollection.update(arg, newDragon);
                return ("Дракон с id[" + arg + "] успешно заменен.");
            } else return ("Дракон с id[" + arg + "] оказался больше.");
        }
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


