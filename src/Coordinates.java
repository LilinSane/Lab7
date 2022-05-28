import java.io.Serializable;

/**
 * Coordinates class
 */
public class Coordinates implements Serializable {
    private Double x; //Значение поля должно быть больше -28, Поле не может быть null
    private float y; //Значение поля должно быть больше -886

    public Coordinates() {
    }

    public Coordinates(Double x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return Double
     */
    public Double getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @return float
     */
    public float getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

}