package controller;

import dragon.Dragon;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class DragonCollection implements Serializable {
    public static Hashtable<Integer, Dragon> collection;
    public static LocalDate dateCreation;


    public static void setCollection(Hashtable<Integer, Dragon> collection) {
        DragonCollection.collection = collection;
    }


    public static LocalDate getDateCreation() {
        return dateCreation;
    }

    public static void setDateCreation(LocalDate dateCreation) {
        DragonCollection.dateCreation = dateCreation;
    }

    public static void clear() {
        collection.clear();
    }

    /**
     * create new dragon with set id and put it into collection
     *
     * @param ind
     * @param dragon
     */
    public static void insert(Integer ind, Dragon dragon) {
        if (isKeyFree(ind))
            collection.put(ind, dragon);
    }

    /**
     * update info about dragon with this id
     *
     * @param ind
     * @param dragon
     */
    public static void update(Integer ind, Dragon dragon) {
        if (!isKeyFree(ind))
            collection.put(ind, dragon);
    }

    /**
     * remove dragon from the collection using id
     *
     * @param ind
     */
    public static void remove(Integer ind) {
        Enumeration keys = collection.keys();
        while (keys.hasMoreElements()) {
            Integer k = (Integer) keys.nextElement();
            Dragon v = collection.get(k);
            if (!isKeyFree(ind)) collection.remove(k);

        }
    }

    /**
     * @param ind
     * @return false if dragon with this id exist, false otherwise
     */
    public static boolean isKeyFree(Integer ind) {
        try {
            for (Map.Entry<Integer, Dragon> entry : collection.entrySet())
                if (entry.getKey() == ind) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;

        }
    }

    public static int getSize() {
        return collection.size();
    }

    /**
     * @return collection size and date of creation
     */
    public static String getInfo() {
        return "Тип коллекции: Hashtable;\nKоличество элементов коллекции: " + getSize() + ";\nДата создания кол"
                + "лекции: " + getDateCreation() + ".";
    }
}
