import java.util.Date;

public class DataController {

    public static LabWork createLabworkFromSQL(long id, String name, double x_cor, float y_cor, Date creationDate, int min, double max, double average, String diff, String per_name, Date birthday, float height, long weight, double x_loc, long y_loc, int z_loc){
        Coordinates coordinates = new Coordinates(x_cor, y_cor);
        Location location = new Location(x_loc, y_loc, z_loc);
        Person person = new Person(per_name, birthday, height, weight, location);
        Difficulty difficulty = null;
        if (diff.toUpperCase().equals("HOPELESS")) {
            difficulty = Difficulty.HOPELESS;
        }
        else if(diff.toUpperCase().equals("EASY")){
            difficulty = Difficulty.EASY;
        }
        else if(diff.toUpperCase().equals("VERY_HARD")){
            difficulty = Difficulty.VERY_HARD;
        }
        LabWork labWork = new LabWork(id, name, coordinates, creationDate, min, max, average, difficulty, person);
        return labWork;
    }

    public static UserProperties createUserFromSQL(String login, String password){
        return new UserProperties(login, password);
    }

}
