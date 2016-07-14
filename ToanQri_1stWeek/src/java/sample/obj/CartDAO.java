package sample.obj;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import sample.utils.DBUtils;

/**
 *
 * @author Suzy
 */
public class CartDAO implements Serializable {
    public boolean addOrder(CartObj cart, float total) {
        String code = "[INV" + System.currentTimeMillis() % 100000 + "]";
        String username = cart.getUsername();
        Date date = new Date();
        Timestamp orderDate = new Timestamp(date.getTime());
        
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO BookOrder(code,username,orderDate,total) VALUES(?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, username);
                stm.setTimestamp(3, orderDate);
                stm.setFloat(4, total);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
