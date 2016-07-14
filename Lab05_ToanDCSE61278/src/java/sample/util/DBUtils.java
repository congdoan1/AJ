package sample.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Suzy
 */
public class DBUtils implements Serializable {

    public static Connection makeConnection() {
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
