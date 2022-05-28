import java.io.Serializable;

/**
 * Location class
 */
public class Location implements Serializable {
    private Double x; //Поле не может быть null
    private long y;
    private Integer z; //Поле не может быть null

    public Location() {
    }

    public Location(Double x, long y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
     * @return long
     */
    public long getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     *
     * @return Integer
     */
    public Integer getZ() {
        return z;
    }

    /**
     *
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }


}