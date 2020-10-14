package commands;



import controller.CommandWithoutArg;
import controller.DragonCollection;

import java.util.Map;

public class Show implements CommandWithoutArg {
    String name = "show";

    /**
     * show all element of this collection
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        if (DragonCollection.getSize() == 0) return ("Коллекция пустая.");
        else {
            StringBuilder answer = new StringBuilder();
            DragonCollection.collection.entrySet().stream().forEach(entry -> answer.append(entry.getValue().getInfo()+"\n"));
            return answer.toString();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
