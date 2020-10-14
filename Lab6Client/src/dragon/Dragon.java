package dragon;

import java.io.Serializable;
import java.time.LocalDate;

public class Dragon implements Comparable, Serializable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long age; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private boolean speaking;
    private Color color; //Поле может быть null
    private Person killer; //Поле может быть null

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSpeaking() {
        return speaking;
    }

    public void setSpeaking(Boolean speaking) {
        this.speaking = speaking;
    }
    public void setSpeaking() {
        this.speaking = Boolean.parseBoolean(null);
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Person getKiller() {
        return killer;
    }

    public void setKiller(Person killer) {
        this.killer = killer;
    }

    /**
     *
     * @return all dragon's parametres
     */
    public String getInfo() {
        return "Дракон [id:" + id + "]:\n\t" + "Имя: " + name + "\n\tКоординаты:\n\t\tx: " + coordinates.getX() +
        "\n\t\ty: " + coordinates.getY() +  "\n\tДата cоздания: " + creationDate + "\n\tВозраст: " + age +
        "\n\tОписание: "+ description + "\n\tСпособен разговаривать: " + speaking + "\n\tЦвет: " + color +
        "\n\tУбийца:\n\t\tИмя: " + killer.getName() + "\n\t\tРост: " + killer.getHeight() + "\n\t\tЦвет глаз: " +
        killer.getEyeColor() + "\n\t\tЦвет волос: " + killer.getHairColor();
    }
    public Dragon getDragon(){
        return this;
    }
    @Override
    public int compareTo(Object dragon) {
        return this.getInfo().length() - ((Dragon) dragon).getInfo().length();
    }
}