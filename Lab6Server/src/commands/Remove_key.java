package commands;

import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Map;

public class Remove_key implements Commandable {
    String name = "remove_key";
    DragonCollection collection = new DragonCollection();

    /**
     * delete dragon with this id number
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        try {
            if (DragonCollection.getSize() == 0) return("Коллекция итак пустая.");
            else {
                for (Map.Entry<Integer, Dragon> entry : DragonCollection.collection.entrySet())
                    if (entry.getKey() == Integer.parseInt((String) arg)) {
                        collection.remove(entry.getKey());
                        return("Элемент с id[" + arg + "] успешно удален.");
                    }
                 return("Дракон с указанным id не найден.");
            }
        } catch (Exception e) {
            return ("Аргумент команды должен быть типа \"int\"");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}

