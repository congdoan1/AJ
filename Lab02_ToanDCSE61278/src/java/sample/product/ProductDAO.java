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
    
    public boolean save(String code, String name, String description, float price, String category) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        
        if (con != null) {
            String sql = "INSERT INTO Product VALUES(?,?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, name);
                stm.setString(3, description);
                stm.setFloat(4, price);
                stm.setString(5, category);
                int r = stm.executeUpdate();
                if (r > 0) {
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
    
    private List<ProductDTO> listProducts;

    public List<ProductDTO> getListProducts() {
        return listProducts;
    }
    
    
    public void loadAll() {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;

        if (con != null) {
            String sql = "SELECT * FROM Product";
            try {
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                listProducts = new ArrayList<ProductDTO>();
                while (rs.next()) {
                    String code = rs.getString("Code");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    String category = rs.getString("Category");
                    listProducts.add(new ProductDTO(code, name, description, price, category));
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
    }
}
