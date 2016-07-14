package sample.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class CartDAO implements Serializable {
    public boolean addOrder(CartObj cart, float total) {
        String orderID = "INV" + System.currentTimeMillis() % 100000;
        Date date = new Date();
        Timestamp orderDate = new Timestamp(date.getTime());
        
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO tbl_Order(orderID, orderDate, custID, total) VALUES(?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setTimestamp(2, orderDate);
                stm.setString(3, cart.getCustID());
                stm.setFloat(4, total);
                int row = stm.executeUpdate();
                if (row > 0) {
                    HashMap items = cart.getItems();
                    Iterator iter = items.entrySet().iterator();
                    boolean bAdd = false;
                    while (iter.hasNext()) {
                        Map.Entry item = (Map.Entry) iter.next();
                        ProductDTO product = (ProductDTO) item.getKey();
                        int quantity = Integer.parseInt(item.getValue().toString());
                        bAdd = addOrderDetail(product, quantity, orderID);
                        if (!bAdd) {
                            return false;
                        }
                    }
                    return bAdd;
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
    private boolean addOrderDetail(ProductDTO product, int quantity, String orderID) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO orderDetail(productID, quantity, unitPrice, unitItem, total, orderID)"
                    + " VALUES(?,?,?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, product.getProductID());
                stm.setInt(2, quantity);
                stm.setFloat(3, product.getPrice());
                stm.setString(4, product.getUnit());
                stm.setFloat(5, product.getPrice() * quantity);
                stm.setString(6, orderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    ProductDAO dao = new ProductDAO();
                    dao.setQuantity(product.getProductID(), dao.getQuantity(product.getProductID()) - quantity);
                    return true;
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
}
