package commands;

import controller.CommandWithoutArg;
import controller.DragonCollection;
import dragon.Dragon;

import java.util.Map;

public class Average_of_age implements CommandWithoutArg {
    String name = "average_of_age";

    /**
     * if collection is nor empty than show average age of all dragons
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        long middleAge = 0;
        if (DragonCollection.getSize() == 0) return ("Коллекция итак пустая.");
        else for (Map.Entry<Integer, Dragon> entry : DragonCollection.collection.entrySet())
            middleAge += entry.getValue().getAge();
        return "Средний возраст дракона: " + middleAge / DragonCollection.getSize();
    }

    @Override
    public String getName() {
        return name;
    }
}

