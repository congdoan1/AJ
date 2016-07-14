package sample.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class ProductDAO implements Serializable {

    public List<ProductDTO> searchByName(String name) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;

        if (con != null) {
            String sql = "SELECT * FROM product WHERE productName LIKE ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                List<ProductDTO> result = new ArrayList<ProductDTO>();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String unit = "";
                    float price = rs.getFloat("price");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    if (quantity > 0) {
                        ProductDTO product = new ProductDTO(productID, productName, unit, price);
                        result.add(product);
                    }
                }
                return result;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
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
        return null;
    }

    public int getQuantity(String productID) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;

        if (con != null) {
            String sql = "SELECT quantity FROM product WHERE productID = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    return quantity;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
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
        return 0;
    }

    public boolean setQuantity(String productID, int quantity) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;

        if (con != null) {
            String sql = "UPDATE product SET quantity = ? WHERE productID = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, productID);
                int row = stm.executeUpdate();
                if (row > 0) {
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
