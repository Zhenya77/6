package dragon;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Dragonborn  implements Serializable {
    public static boolean isFromScript;
    Scanner scanner = new Scanner(System.in);
    public String whyFailed = "";
    public  Dragon dragonFromScript;
    /**
     * Creates new human using input information
     *
     * @return human with set parametres
     */
    public Dragon create() {
        Dragon dragon = new Dragon();
        this.setName(dragon);
        Coordinates coordinates = new Coordinates();
        this.setCoordinateX(coordinates);
        this.setCoordinateY(coordinates);
        dragon.setCoordinates(coordinates);
        this.setAge(dragon);
        this.setDescription(dragon);
        this.setSpeaking(dragon);
        this.setColor(dragon);
        Person killer = new Person();
        this.setPersonName(killer);
        this.setHeight(killer);
        this.setEyeColor(killer);
        this.setHairColor(killer);
        dragon.setKiller(killer);
        dragon.setCreationDate(LocalDate.now());
        return dragon;
    }

    /**
     * create dragon using information from the file
     *
     * @param pars
     */
    public void createFromFile(Object pars) {
        try {
            ArrayList<String> params = (ArrayList) pars;
            Dragon dragon = new Dragon();
            this.setName(dragon, params.get(0));
            Coordinates coordinates = new Coordinates();
            this.setCoordinateX(coordinates, params.get(1));
            this.setCoordinateY(coordinates, params.get(2));
            dragon.setCoordinates(coordinates);
            this.setAge(dragon, params.get(3));
            this.setDescription(dragon, params.get(4));
            this.setSpeaking(dragon, params.get(5));
            this.setColor(dragon, params.get(6));
            Person killer = new Person();
            this.setPersonName(killer, params.get(7));
            this.setHeight(killer, params.get(8));
            this.setEyeColor(killer, params.get(9));
            this.setHairColor(killer, params.get(10));
            dragon.setKiller(killer);
            dragon.setCreationDate(LocalDate.now());
            dragonFromScript = dragon;
            isFromScript = true;
        } catch (Exception e) {
            dragonFromScript = null;
//            System.out.println(whyFailed);
//            whyFailed = "";
        }
    }

    /**
     * Set dragon's name
     *
     * @param dragon
     */
    public void setName(Dragon dragon) {
        System.out.println("Введите имя дракона:");
        System.out.print("~ ");
        String name = scanner.nextLine();
        if (name.equals("") || name.equals(null)) this.setName(dragon);
        dragon.setName(name);
    }

    /**
     * set X coordinate for the dragon
     *
     * @param coords
     */

    public void setCoordinateX(Coordinates coords) {
        try {
            System.out.println("Введите координату x:");
            System.out.print("~ ");
            String x = scanner.nextLine();
            if (x.equals("") || x.equals(null)) this.setCoordinateX(coords);
            double xn = Double.parseDouble(x);
            if (xn <= 364) coords.setX(xn);
            else {
                System.out.println("Значение должно быть меньше 364");
                this.setCoordinateX(coords);
            }
            coords.setX(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"double\".");
            this.setCoordinateX(coords);
        }
    }

    /**
     * set Y coordinate for the dragon
     *
     * @param coords
     */

    public void setCoordinateY(Coordinates coords) {
        try {
            System.out.println("Введите координату y:");
            System.out.print("~ ");
            String y = scanner.nextLine();
            if (y.equals("") || y.equals(null)) this.setCoordinateY(coords);
            coords.setY(Float.parseFloat(y));
            float yn = Float.parseFloat(y);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"float\".");
            this.setCoordinateY(coords);
        }
    }

    /**
     * set dragon's age
     *
     * @param dragon
     */
    public void setAge(Dragon dragon) {
        try {
            System.out.println("Введите возраст дракона:");
            System.out.print("~ ");
            String age = scanner.nextLine();
            if (age.equals("")) this.setAge(dragon);
            long agel = Long.parseLong(age);
            if (agel > 0) dragon.setAge(agel);
            else System.out.println("Значение должно быть положительным.");
        } catch (Exception e) {
            System.out.println("Значение должно быть типа:\"long\".");
            this.setAge(dragon);
        }
    }

    /**
     * write your description of a dragon
     *
     * @param dragon
     */

    public void setDescription(Dragon dragon) {
        System.out.println("Введите описание дракона.");
        System.out.print("~ ");
        String des = scanner.nextLine();
        if (des.equals("") || des.equals(null)) this.setDescription(dragon);
        else dragon.setDescription(des);
    }

    /**
     * set dragon's ability to speak (true/false)
     *
     * @param dragon
     */

    public void setSpeaking(Dragon dragon) {
        System.out.println("Введите есть ли у дракона способность разговаривать(true/false):");
        System.out.print("~ ");
        String speaking = scanner.nextLine();
        if (speaking.equals(""))
            dragon.setSpeaking();
        else if (speaking.equals("true") || speaking.equals("false"))
            dragon.setSpeaking(Boolean.parseBoolean(speaking));
        else {
            System.out.println("Значение должно быть типа:\"boolean\"");
            this.setSpeaking(dragon);
        }

    }

    /**
     * set dragon's colour (GREEN, RED, BLUE, ORANGE, BROWN)
     *
     * @param dragon
     */

    public void setColor(Dragon dragon) {
        System.out.println("Введите расцветку дракона(GREEN, RED, BLUE, ORANGE, BROWN):");
        System.out.print("~ ");
        String color = scanner.nextLine().toUpperCase();
        try {
            dragon.setColor(Color.valueOf(color));
        } catch (Exception e) {
            System.out.println("Значение должно соответствовать перечислинным типам. Введите значение:");
            this.setColor(dragon);
        }
    }

    /**
     * set a name for your human
     *
     * @param killer
     */

    public void setPersonName(Person killer) {
        System.out.println("Введите имя убийцы:");
        System.out.print("~ ");
        String name = scanner.nextLine();
        if (name.equals("") || name.equals(null)) this.setPersonName(killer);
        killer.setName(name);
    }

    /**
     * set your human's height
     *
     * @param killer
     */

    public void setHeight(Person killer) {
        try {
            System.out.println("Введите рост убийцы:");
            System.out.print("~ ");
            String height = scanner.nextLine();
            if (height.equals("")) this.setHeight(killer);
            float heightf = Float.parseFloat(height);
            if (heightf > 0) killer.setHeight(heightf);
            else System.out.println("Значение должно быть положительным.");
        } catch (Exception e) {
            System.out.println("Значение должно быть типа:\"float\".");
            this.setHeight(killer);
        }
    }

    /**
     * set your human's eye colour (GREEN, RED, BLUE, ORANGE, BROWN)
     *
     * @param killer
     */

    public void setEyeColor(Person killer) {
        System.out.println("Введите цвет глаз убийцы(GREEN, RED, BLUE, ORANGE, BROWN):");
        System.out.print("~ ");
        String color = scanner.nextLine().toUpperCase();
        try {
            killer.setEyeColor(Color.valueOf(color.toUpperCase()));
        } catch (Exception e) {
            System.out.println("Значение поля должно соответствовать перечислинным типам");
            this.setEyeColor(killer);
        }
    }

    /**
     * set your human's hair colour (GREEN, RED, BLUE, ORANGE, BROWN)
     *
     * @param killer
     */

    public void setHairColor(Person killer) {
        System.out.println("Введите цвет волос убийцы(GREEN, RED, BLUE, ORANGE, BROWN):");
        System.out.print("~ ");
        String color = scanner.nextLine().toUpperCase();
        try {
            killer.setHairColor(Color.valueOf(color.toUpperCase()));
        } catch (Exception e) {
            System.out.println("Значение поля должно соответствовать перечислинным типам.");
            this.setHairColor(killer);
        }
    }

    /**
     * set a name for this dragon
     * if name is empty, than throw throw exption
     *
     * @param dragon
     * @param name
     * @throws Exception
     */
    public void setName(Dragon dragon, String name) throws Exception {
        if (name.equals("") || name.equals(null)) {
            whyFailed += "У дракона должно быть имя.\n";
            throw null;
        } else dragon.setName(name);
    }

    /**
     * set X coordinate for a drangon
     * X cannot be empty or have higher value than 364
     *
     * @param coords
     * @param x
     * @throws Exception
     */
    public void setCoordinateX(Coordinates coords, String x) throws Exception {
        try {
            if (x.equals("") || x.equals(null)) whyFailed += "У дракона должна быть координата x.\n";
            ;
            double xn = Double.parseDouble(x);
            if (xn <= 364) coords.setX(xn);
            else {
                whyFailed += ("Значение координаты х должно быть меньше 364.\n");
                throw null;
            }
            coords.setX(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            whyFailed += ("Значение x должно быть типа:\"double\".\n");
            throw new Exception();
        }
    }
    /**
     * set Y coordinate for a drangon
     * Y if float type and cannot be empty
     *
     * @param coords
     * @param y
     * @throws Exception
     */
    public void setCoordinateY(Coordinates coords, String y) throws Exception {
        try {
            if (y.equals("") || y.equals(null)) {
                whyFailed += "У дракона должна быть коордната y.\n";
                throw null;
            }
            coords.setY(Float.parseFloat(y));
        } catch (InputMismatchException | NumberFormatException e) {
            whyFailed += ("Значение y должно быть типа:\"float\".\n");
            throw new Exception();
        }
    }

    /**
     * set dragon age
     * age cannot be empty and must be positive
     *
     * @param dragon
     * @param age
     */

    public void setAge(Dragon dragon, String age) {
        try {
            if (age.equals("")) {
                whyFailed += "У дракона должен быть возраст.\n";
                throw null;
            }
            long agel = Long.parseLong(age);
            if (agel > 0) dragon.setAge(agel);
            else {
                whyFailed += ("Значение возраста должно быть положительным.\n");
                throw null;
            }
        } catch (InputMismatchException | NumberFormatException e) {
            whyFailed += ("Значение возраста должно быть типа:\"long\"");
            throw null;
        }
    }

    /**
     * write description of your dragon
     * cannot be empty
     *
     * @param dragon
     * @param des
     */
    public void setDescription(Dragon dragon, String des) {
        if (des.equals("") || des.equals(null)) {
            whyFailed += "У дракона должно быть описание";
        } else dragon.setDescription(des);
    }

    /**
     * set your dragon's ability to speak
     * must be true/false
     *
     * @param dragon
     * @param speaking
     */
    public void setSpeaking(Dragon dragon, String speaking) {
        if (speaking.equals(""))
            dragon.setSpeaking();
        else if (speaking.equals("true") || speaking.equals("false"))
            dragon.setSpeaking(Boolean.parseBoolean(speaking));
        else {
            whyFailed += ("Значение способности разговаривать должно быть типа:\"boolean\".\n");
            throw null;
        }

    }

    /**
     * set your dragon's colour
     * must be (GREEN, RED, BLUE, ORANGE, BROWN)
     *
     * @param dragon
     * @param color
     */
    public void setColor(Dragon dragon, String color) {
        try {
            dragon.setColor(Color.valueOf(color.toUpperCase()));
        } catch (Exception e) {
            whyFailed += ("Значение расцетки должно соответствовать перечислинным типам(GREEN, RED, BLUE, ORANGE, BROWN).\n:");
            this.setColor(dragon);
        }
    }

    /**
     * set your human's name
     * human must have a name
     *
     * @param killer
     * @param name
     */
    public void setPersonName(Person killer, String name) {
        if (name.equals("") || name.equals(null)) {
            whyFailed += ("У убийцы должно быть имя.\n:");
            throw null;
        } else killer.setName(name);
    }

    /**
     * set your human's height
     * height is floate and must have positive value
     *
     * @param killer
     * @param height
     */
    public void setHeight(Person killer, String height) {
        try {
            if (height.equals("")) {
                whyFailed += ("У убийцы должен быть рост.\n:");
                throw null;
            }
            float heightf = Float.parseFloat(height);
            if (heightf > 0) killer.setHeight(heightf);
            else {
                whyFailed += ("Значение роста убийцы должно быть положительным.\n");
                throw null;
            }
        } catch (Exception e) {
            whyFailed += ("Значение роста убийцы должно быть типа:\"float\".\n");
            throw null;
        }
    }

    /**
     * set your human's eye colour
     * must be (GREEN, RED, BLUE, ORANGE, BROWN)
     *
     * @param killer
     * @param color
     */
    public void setEyeColor(Person killer, String color) {
        try {
            killer.setEyeColor(Color.valueOf(color.toUpperCase()));
        } catch (Exception e) {
            whyFailed += ("Значение поля цвета глаз убийцы должно соответствовать перечислинным типам(GREEN, RED, BLUE, ORANGE, BROWN)\n");
            throw null;
        }
    }

    /**
     * set your human's hair colour
     * must be (GREEN, RED, BLUE, ORANGE, BROWN)
     *
     * @param killer
     * @param color
     */
    public void setHairColor(Person killer, String color) {
        try {
            killer.setHairColor(Color.valueOf(color.toUpperCase()));
        } catch (Exception e) {
            whyFailed += ("Значение поля цвета волос убийцы должно соответствовать перечислинным типам(GREEN, RED, BLUE, ORANGE, BROWN)\n");
            throw null;
        }
    }
}