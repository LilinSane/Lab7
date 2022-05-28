import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

        /* if (args.length == 0){
            System.out.println("Названия файла не содержится в командной строке, пожалуйста, запустите программу с именем файла");
        return;
        }

        String filename = args[0];
        try {
            FileReader fileReader = new FileReader(filename);
            fileReader.read();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Файл " + filename + " не удаётся открыть");
            return;
        }

        HashSet<LabWork> labWorks = JsonReader.fromDataFile(filename);
        Date ini_date = new Date();
        if (labWorks == null){
            System.out.println("В файле отсутствуют элементы");
            return;
        }


        ServerHandler.file_check(labWorks);*/

        HashSet<LabWork> hashSet = new HashSet<>();
        Set<LabWork> labWorks = Collections.synchronizedSet(hashSet);
        HashSet<UserProperties> propertiesHashSet = new HashSet<>();
        Set<UserProperties> users = Collections.synchronizedSet(propertiesHashSet);
        Date ini_date = new Date();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        UserProperties admin = new UserProperties("s334633", "kcg537");
        DBManager dbManager = new DBManager();
        String tableQuery = "" +
                "CREATE TABLE IF NOT EXISTS LabWorks" +
                "(" +
                "id INT PRIMARY KEY, " +
                "lab_name VARCHAR(30) NOT NULL, " +
                "x_coord REAL NOT NULL, " +
                "y_coord REAL NOT NULL, " +
                "creation_date DATE, " +
                "minimal_point INT NOT NULL, " +
                "maximalmal_point REAL NOT NULL, " +
                "averagePoint REAL NOT NULL, " +
                "difficulty VARCHAR(30) NOT NULL, " +
                "per_name VARCHAR(30) NOT NULL, " +
                "per_birthday DATE, " +
                "per_height REAL NOT NULL, " +
                "per_weight INT NOT NULL, " +
                "x_loc REAL NOT NULL, " +
                "y_loc INT NOT NULL, " +
                "z_loc INT NOT NULL, " +
                "usr_login VARCHAR(30) NOT NULL " +
                ")";
        String sequenceQuery = "" +
                "CREATE SEQUENCE IF NOT EXISTS lab_id_sequence" +
                "     START WITH 100000" +
                "     INCREMENT BY 1" +
                "     MINVALUE 100000" +
                "     MAXVALUE 999999;";
        String usersTableQuery = "" +
                "CREATE TABLE IF NOT EXISTS users " +
                "(" +
                "id SERIAL PRIMARY KEY, " +
                "login VARCHAR(30), " +
                "password VARCHAR(30)," +
                "pwhash VARCHAR(32) " +
                ")";
        String getLabDataQuery = "SELECT * FROM labworks";
        String getUserDataQuery = "SELECT * FROM users";
        ResultSet data = null;
        try {
            dbManager.connectToDB("jdbc:postgresql://pg:5432/studs", admin);
            dbManager.createDDLQuery(tableQuery);
            dbManager.createDDLQuery(sequenceQuery);
            dbManager.createDDLQuery(usersTableQuery);
            data = dbManager.createSELECTQuery(getLabDataQuery);
            while (data.next()){
                LabWork labWork = DataController.createLabworkFromSQL(data.getLong(1),data.getString(2) , data.getDouble(3), data.getFloat(4), data.getDate(5),data.getInt(6), data.getDouble(7),data.getDouble(8),data.getString(9), data.getString(10), data.getDate(11), data.getFloat(12), data.getLong(13), data.getDouble(14), data.getLong(15), data.getInt(16));
                labWorks.add(labWork);
            }
            data = dbManager.createSELECTQuery(getUserDataQuery);
            while (data.next()){
                UserProperties user = DataController.createUserFromSQL(data.getString(2), data.getString(3));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ServerHandler serverHandler = new ServerHandler();
        serverHandler.start(labWorks, users, reader, ini_date, dbManager);

        //AppCommands.save(labWorks, filename);

    }


}
