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
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String qPerUnit = rs.getString("quantityPerUnit");
                    float price = rs.getFloat("price");
                    listProducts.add(new ProductDTO(productID, productName, qPerUnit, price));
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
