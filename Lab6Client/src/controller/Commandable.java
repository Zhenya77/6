package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public interface Commandable extends Serializable {
    public String execute(Object o) throws IOException, ClassNotFoundException;
    public String getName();
}
