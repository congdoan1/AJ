package sample.obj;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class CartDAO implements Serializable {

    public boolean addOrder(CartObj cart) {
        String customerID = cart.getCustomerID();
        String orderID = "O" + String.valueOf(System.currentTimeMillis() % 10000);
        Date date = new Date();
        Timestamp orderDate = new Timestamp(date.getTime());

        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO Orders(orderID, orderDate, customerID) VALUES(?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setTimestamp(2, orderDate);
                stm.setString(3, customerID);
                int row = stm.executeUpdate();

                if (row > 0) {
                    HashMap items = cart.getItems();
                    Iterator iter = items.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry item = (Map.Entry) iter.next();
                        boolean result = addOrderDetail(orderID, item.getKey().toString(), Integer.parseInt(item.getValue().toString()));
                        if (!result) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (SQLException ex) {
                //log("Error when add Order...", ex.getMessage());
            }
        }

        return false;
    }

    private boolean addOrderDetail(String orderID, String bookTitle, int quantity) {

        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO OrderDetails(orderID, bookTitle, quantity) VALUES(?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, bookTitle);
                stm.setInt(3, quantity);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } catch (SQLException e) {
                
            } finally {
                try {
                    if (stm != null) {
                        stm.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                }
            }
        }

        return false;
    }
}
