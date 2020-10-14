package commands;


import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Enumeration;
import java.util.InputMismatchException;

public class Remove_lower_key implements Commandable {
    String name = "remove_lower_key";

    /**
     * delete all dragons from the collection with ids lower then appointed
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        try {
            if (DragonCollection.getSize() == 0) return("Коллекция итак пустая.");
            else {
                Enumeration keys = DragonCollection.collection.keys();
                while (keys.hasMoreElements()) {
                    Integer k = (Integer) keys.nextElement();
                    Dragon v = DragonCollection.collection.get(k);
                    if (v.getId() < Integer.parseInt((String) arg)) {
                        DragonCollection.collection.remove(k);
                        return ("Дракон с id:[" + k + "] успешно удален.");
                    }
                }
                return("Драконов с меньшим id не найдено.");
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
            return("Аргумент команды должен быть типа \"int\"");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}