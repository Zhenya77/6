package commands;


import controller.CommandWithoutArg;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.HashMap;

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
            StringBuilder answer = new StringBuilder();
            HashMap<String, Dragon> descriptionsOfDragons = new HashMap<>();
            DragonCollection.collection.entrySet().stream().
                    forEach(entry -> descriptionsOfDragons.put(entry.getValue().getDescription(), entry.getValue()));

            return DragonCollection.collection.get(
                        descriptionsOfDragons.keySet().stream().sorted(String::compareTo).findFirst()
                    ).getInfo();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}

