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
import sample.product.ProductDTO;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class CartDAO implements Serializable {

    public boolean addOrder(CartObj cart, float total) {
        String customerID = cart.getCustomerID();
        String orderID = "C" + System.currentTimeMillis() % 10000;
        Date date = new Date();
        Timestamp orderDate = new Timestamp(date.getTime());

        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO Orders(orderID, orderDate, customerID, total) VALUES(?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setTimestamp(2, orderDate);
                stm.setString(3, customerID);
                stm.setFloat(4, total);
                int row = stm.executeUpdate();
                if (row > 0) {
                    HashMap items = cart.getItems();
                    Iterator iter = items.entrySet().iterator();
                    boolean result = false;
                    while (iter.hasNext()) {
                        Map.Entry item = (Map.Entry) iter.next();
                        ProductDTO dto = (ProductDTO) item.getKey();
                        result = addOrderDetail(orderID, dto.getProductID(), dto.getProductName(), dto.getQuantityPerUnit(), dto.getPrice(), Integer.parseInt(item.getValue().toString()));
                    }
                    if (result) {
                        return true;
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stm != null) {
                        stm.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }
        return false;
    }

    private boolean addOrderDetail(String orderID, String productID,
            String productName, String quantityPerUnit, float price, int quantity) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO OrderDetails(orderID, productID, productName, quantityPerUnit, price, quantity)"
                    + " VALUES(?,?,?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, productID);
                stm.setString(3, productName);
                stm.setString(4, quantityPerUnit);
                stm.setFloat(5, price);
                stm.setInt(6, quantity);
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
