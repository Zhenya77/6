package commands;


import controller.CommandWithoutArg;
import controller.DragonCollection;
import utilities.WriterToFile;

import java.io.FileNotFoundException;

public class Save implements CommandWithoutArg {
    String name = "save";

    @Override
    public String execute(Object o) {
        try {
            WriterToFile.writeDragonToFile(DragonCollection.collection);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Коллекция успешно сохранена.");
        return "";
    }


    @Override
    public String getName() {
        return name;
    }
}

