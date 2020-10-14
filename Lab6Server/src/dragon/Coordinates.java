package dragon;

import java.io.Serializable;

public class Coordinates  implements Serializable {
    private Double x; //Максимальное значение поля: 364, Поле не может быть null
    private Float y; //Поле не может быть null

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }


}
