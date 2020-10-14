package utilities;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import dragon.Color;
import dragon.Coordinates;
import dragon.Dragon;
import dragon.Person;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Map;

public class Decoder {
    Hashtable<Integer, Dragon> collection;
    Gson gson = new Gson();


    public static Hashtable<Integer, Dragon> loadCollection(String data) {
        if (data == null) System.out.println("Указанный файл не найден. Коллекция пустая.");
        else {
            try {
                Hashtable<Integer, Dragon> collection = new Hashtable<>();
                Type type = new TypeToken<Hashtable>() {
                }.getType();
                Hashtable<Integer, LinkedTreeMap> dragons = new Gson().fromJson(data, type);
                for (Map.Entry<Integer, LinkedTreeMap> entry : dragons.entrySet()) {
                    LinkedTreeMap params = entry.getValue();
                    Dragon dragon = new Dragon();
                    dragon.setName((String) params.get("name"));
                    if (params.get("id") != null) dragon.setId(((Double) params.get("id")).longValue());
                    dragon.setColor(Color.valueOf((String) params.get("color")));
                    // dragon.setCreationDate(LocalDate.parse();
                    LinkedTreeMap<String, Double> partsOfDate = (LinkedTreeMap) params.get("creationDate");
                    int year = partsOfDate.get("year").intValue();
                    int month = partsOfDate.get("month").intValue();
                    int day = partsOfDate.get("day").intValue();
                    dragon.setCreationDate(LocalDate.of(year, month, day));
                    dragon.setAge(((Double) params.get("age")).longValue());
                    dragon.setSpeaking((Boolean) params.get("speaking"));
                    dragon.setDescription((String) params.get("description"));
                    LinkedTreeMap coordParams = (LinkedTreeMap) params.get("coordinates");
                    Coordinates coordinates = new Coordinates();
                    coordinates.setX((Double) coordParams.get("x"));
                    coordinates.setY(((Double) coordParams.get("y")).floatValue());
                    dragon.setCoordinates(coordinates);
                    LinkedTreeMap killerParams = (LinkedTreeMap) params.get("killer");
                    Person killer = new Person();
                    killer.setName((String) killerParams.get("name"));
                    killer.setHeight(((Double) killerParams.get("height")).longValue());
                    killer.setEyeColor(Color.valueOf((String) killerParams.get("eyeColor")));
                    killer.setHairColor(Color.valueOf((String) killerParams.get("hairColor")));
                    dragon.setKiller(killer);
                    collection.put(Integer.parseInt(String.valueOf(entry.getKey())), dragon);
                    System.out.println("Коллекция успешно заполнена.");
                    return collection;
                }
            } catch (NullPointerException e) {
                System.out.println("В файле указаны некорретные данные. Коллекция пустая.");
                return new Hashtable<Integer, Dragon>();
            }
        }
        return new Hashtable<Integer, Dragon>();
    }
}
