package sample.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Suzy
 */
public class DBUtils implements Serializable {
    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Sirius";
            Connection con = DriverManager.getConnection(url, "sa", "");
            return con;
        } catch (Exception e) {
        }
        return null;
    }
}
