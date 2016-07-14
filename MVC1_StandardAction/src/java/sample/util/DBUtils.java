package sample.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Suzy
 */
public class DBUtils implements Serializable {

    public static Connection makeConnection() {
//        try {
//            //Load Driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            //Create Connection String
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=AJDay1";
//            //Create Connection
//            Connection con = DriverManager.getConnection(url, "sa", "");
//            return con;
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        try {
            Context context = new InitialContext();
            Context tomcatCtx = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) tomcatCtx.lookup("DBCon");
            Connection con = ds.getConnection();
            return con;
            
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
