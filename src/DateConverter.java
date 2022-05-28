import java.util.Date;

public class DateConverter {

    public static java.sql.Date convertToSql(Date utilDate){
        return new java.sql.Date(utilDate.getTime());
    }

}
