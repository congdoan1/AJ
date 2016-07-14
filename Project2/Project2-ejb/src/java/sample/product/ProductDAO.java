package sample.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> listProducts;

    public List<ProductDTO> getListProducts() {
        return listProducts;
    }

    public void searchByName(String name, DataSource ds) throws SQLException {
        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        ResultSet rs = null;

        if (con != null) {
            String sql = "SELECT * FROM product WHERE productName LIKE ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                listProducts = new ArrayList<ProductDTO>();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String unit = "";
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    if (quantity > 0) {
                        ProductDTO dto = new ProductDTO(productID, productName, unit, price);
                        listProducts.add(dto);
                    }
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
    }

    public int getQuantity(String productID, DataSource ds) throws SQLException {
        Connection con = DBUtils.makeConnection(ds);
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
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
        return 0;
    }

    public boolean setQuantity(String productID, int quantity, DataSource ds) throws SQLException {
        Connection con = DBUtils.makeConnection(ds);
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
            } finally {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            }
        }
        return false;
    }
}
