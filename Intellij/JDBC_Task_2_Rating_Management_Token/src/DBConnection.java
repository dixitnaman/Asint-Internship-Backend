import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/rating_system";
    private static final String USER = "root";
    private static final String PASSWORD = "13062003";

    //connection object, return karega, agar exception aaya, to  exception throw karega
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
