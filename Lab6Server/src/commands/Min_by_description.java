package commands;


import controller.CommandWithoutArg;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Enumeration;
import java.util.Hashtable;

public class Min_by_description implements CommandWithoutArg {
    String name = "min_by_description";

    /**
     * shows dragon from the collection with shortest description
     *
     * @param arg
     */
    @Override
    public String execute(Object arg) {
        if (DragonCollection.getSize() == 0) return ("Коллекция пустая.");
        else {
            String minDescription = "";
            Integer key = -152;
            int tumb = 0;
            Hashtable<Integer, Dragon> dragons = DragonCollection.collection;
            Enumeration keys = dragons.keys();
            while (keys.hasMoreElements()) {
                Integer k = (Integer) keys.nextElement();
                Dragon v = dragons.get(k);
                if (tumb == 0) {
                    minDescription = v.getDescription();
                    key = k;
                    tumb++;
                }
                if (v.getDescription().length() < minDescription.length()) {
                    minDescription = v.getDescription();
                    key = k;
                }
            }
            return (DragonCollection.collection.get(key).getInfo());
        }
    }

    @Override
    public String getName() {
        return name;
    }
}

