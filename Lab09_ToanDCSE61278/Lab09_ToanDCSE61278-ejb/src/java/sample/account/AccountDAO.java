package sample.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class AccountDAO implements Serializable {
    
    public boolean checkLogin(String username, String password, DataSource ds) {
        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        if (con != null) {
            String sql = "SELECT * FROM TBL_Account WHERE Username = ? AND Password = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return false;
    }
    
}
