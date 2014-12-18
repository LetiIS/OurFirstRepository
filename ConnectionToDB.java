package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author abelash
 */
public class ConnectionToDB
{
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "tarakan55555";
    private static final String classname = "org.postgresql.Driver";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(classname);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getClassname() {
        return classname;
    }
    
    
}
