import java.sql.*;


public class DBManager {
    private static Connection connection;
    private UserProperties admin;

    private void labIdSetter(LabWork labwork) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("" +
                "SELECT id FROM labworks WHERE id = CURRVAL ('lab_id_sequence')");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            labwork.setId(rs.getLong("id"));
            break;
        }

    }

    public void usrTableFiller(UserProperties user) throws SQLException {
        String pwhash = MD5Custom.getHash(user.getPassword());
        boolean isUserExists = false;
        PreparedStatement ups = connection.prepareStatement("SELECT * FROM users");
        ResultSet rs = ups.executeQuery();
        while (rs.next()){
            if (user.getLogin().equals(rs.getString(2))){
                isUserExists = true;
            }
            return;
        }
        PreparedStatement ps = connection.prepareStatement("" +
            "INSERT INTO users (login, password, pwhash) " +
            "VALUES (?, ?, ?);");
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, pwhash);
        ps.executeUpdate();
    }

    public void connectToDB(String url, UserProperties admin) throws SQLException {
        this.admin = admin;
        connection = DriverManager.getConnection(url, admin.getLogin(), admin.getPassword());
    }

    public void createDDLQuery(String query) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.executeUpdate();

    }

    public void createLabInsertQuery(LabWork labWork, UserProperties user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("" +
                "INSERT INTO LabWorks " +
                "VALUES ( " +
                "NEXTVAL ('lab_id_sequence'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ); ");

        ps.setString(1, labWork.getName());
        ps.setDouble(2, labWork.getCoordinates().getX());
        ps.setDouble(3, labWork.getCoordinates().getY());
        ps.setDate(4, DateConverter.convertToSql(labWork.getCreationDate()));
        ps.setInt(5, labWork.getMinimalPoint());
        ps.setDouble(6, labWork.getMaximumPoint());
        ps.setDouble(7, labWork.getAveragePoint());
        ps.setString(8, String.valueOf(labWork.getDifficulty()));
        ps.setString(9, labWork.getAuthor().getName());
        ps.setDate(10, DateConverter.convertToSql(labWork.getAuthor().getBirthday()) );
        ps.setDouble(11, labWork.getAuthor().getHeight());
        ps.setLong(12, labWork.getAuthor().getWeight());
        ps.setDouble(13, labWork.getAuthor().getLocation().getX());
        ps.setLong(14, labWork.getAuthor().getLocation().getY());
        ps.setInt(15, labWork.getAuthor().getLocation().getZ());
        ps.setString(16, user.getLogin());
        ps.executeUpdate();
        usrTableFiller(user);
        labIdSetter(labWork);
    }


    public int createUpdateQuery(LabWork labWork, UserProperties user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE  LabWorks " +
                "SET  " +
                "lab_name = ?, " +
                "x_coord = ?, " +
                "y_coord =?, " +
                "minimal_point = ?, " +
                "maximalmal_point = ?, " +
                "averagePoint = ?, " +
                "difficulty = ?, " +
                "per_name = ?, " +
                "per_birthday = ?, " +
                "per_height = ?, " +
                "per_weight = ?, " +
                "x_loc = ?, " +
                "y_loc = ?, " +
                "z_loc = ? " +
                "WHERE usr_login = ? AND id = ? ");

        ps.setString(1, labWork.getName());
        ps.setDouble(2, labWork.getCoordinates().getX());
        ps.setDouble(3, labWork.getCoordinates().getY());
        ps.setInt(4, labWork.getMinimalPoint());
        ps.setDouble(5, labWork.getMaximumPoint());
        ps.setDouble(6, labWork.getAveragePoint());
        ps.setString(7, String.valueOf(labWork.getDifficulty()));
        ps.setString(8, labWork.getAuthor().getName());
        ps.setDate(9, DateConverter.convertToSql(labWork.getAuthor().getBirthday()) );
        ps.setDouble(10, labWork.getAuthor().getHeight());
        ps.setLong(11, labWork.getAuthor().getWeight());
        ps.setDouble(12, labWork.getAuthor().getLocation().getX());
        ps.setLong(13, labWork.getAuthor().getLocation().getY());
        ps.setInt(14, labWork.getAuthor().getLocation().getZ());
        ps.setString(15, user.getLogin());
        ps.setLong(16, labWork.getId());
        return ps.executeUpdate();
    }
    public int createDeleteQuery(long id, UserProperties user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM labworks WHERE usr_login = ? AND id = ?");
        ps.setString(1, user.getLogin());
        ps.setLong(2, id);
        return ps.executeUpdate();
    }

    public ResultSet createSELECTQuery(String query) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        return ps.executeQuery();
    }

}
/*"" +
                "CREATE TABLE IF NOT EXISTS LabWorks" +
                "(" +
                "Id INT PRIMARY KEY, " +
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
                "z_loc INT NOT NULL " +
                ")"*/