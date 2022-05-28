

import java.io.Serializable;
import java.util.Date;

/**
 * Person class
 */
public class Person implements Serializable {

    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private float height; //Значение поля должно быть больше 0
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Location location; //Поле не может быть null

    public Person() {
    }

    public Person(String name, Date birthday, float height, Long weight, Location location) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.location = location;
    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Date
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return float
     */
    public float getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     *
     * @return Long
     */
    public Long getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(long weight) {
        this.weight = weight;
    }

    /**
     *
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }


}