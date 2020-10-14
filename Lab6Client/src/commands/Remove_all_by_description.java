package commands;

import controller.Commandable;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Enumeration;
import java.util.Hashtable;

public class Remove_all_by_description implements Commandable {
    DragonCollection collection = new DragonCollection();
    String name = "remove_all_by_description";

    /**
     * delete dragon from the collection with this description
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {

        if (collection.getSize() == 0) return ("Коллекция итак пустая.");
        else {
            boolean was = false;
            Hashtable<Integer, Dragon> dragons = DragonCollection.collection;
            Enumeration keys = dragons.keys();
            String ans = "Драконы с id:[ ";
            while (keys.hasMoreElements()) {
                Integer k = (Integer) keys.nextElement();
                Dragon v = dragons.get(k);
                if (v.getDescription().equals(arg)) {
                    was = true;
                    collection.remove(k);
                    ans += k + " ";
                }
            }
            if (was) return ans += "] успешно удалены";
               else return ("Драконов с таким описанием не найдено.");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}