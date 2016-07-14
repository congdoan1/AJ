package sample.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Suzy
 */
public class DBUtils implements Serializable {

    public static Connection makeConnection() {
        try {
            //Load Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Create Connection String
            String url = "jdbc:sqlserver://localhost:1433;databaseName=AJDay1";
            //Create Connection
            Connection con = DriverManager.getConnection(url, "sa", "");
            return con;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
