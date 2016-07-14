package sample.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Suzy
 */
public class DBUtils implements Serializable {

    public static Connection makeConnection(DataSource ds) {
        if (ds != null) {
            try {
                Connection con = ds.getConnection();
                return con;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
