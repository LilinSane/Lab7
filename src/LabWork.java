import java.io.Serializable;
import java.util.Date;
/**
 * LabWork class
*/
public class LabWork implements Comparable, Serializable {

    private long id = 0;//(long) ((1.1 + Math.random()) * 100000);
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate = new Date();
    private int minimalPoint; //Значение поля должно быть больше 0
    private double maximumPoint; //Значение поля должно быть больше 0
    private Double averagePoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Person author; //Поле может быть null

    public LabWork() {
    }

    public LabWork(long id, String name, Coordinates coordinates, Date creationDate, int minimalPoint, double maximumPoint, Double averagePoint, Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.maximumPoint = maximumPoint;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.author = author;
    }

    /**
     *
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
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
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @param coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     *
     * @return Date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return int
     */
    public int getMinimalPoint() {
        return minimalPoint;
    }

    /**
     *
     * @param minimalPoint
     */
    public void setMinimalPoint(int minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    /**
     *
     * @return double
     */
    public double getMaximumPoint() {
        return maximumPoint;
    }

    /**
     *
     * @param maximumPoint
     */
    public void setMaximumPoint(double maximumPoint) {
        this.maximumPoint = maximumPoint;
    }

    /**
     *
     * @return Double
     */
    public Double getAveragePoint() {
        return averagePoint;
    }

    /**
     *
     * @param averagePoint
     */
    public void setAveragePoint(double averagePoint) {
        this.averagePoint = averagePoint;
    }

    /**
     *
     * @return Difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @param difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     *
     * @return Person
     */
    public Person getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(Person author) {
        this.author = author;
    }

    public int compareTo(Object o, boolean flag) {
        LabWork labWork = (LabWork) o;
        if(flag == true){
            return (int) (this.getMaximumPoint() - labWork.getMaximumPoint());
        }
        else {
            return (int) (this.getMinimalPoint() - labWork.getMinimalPoint());
        }

    }


    @Override
    public int compareTo(Object o) {
        LabWork labWork = (LabWork) o;
        return (int) (this.getId() - labWork.getId());
    }
}