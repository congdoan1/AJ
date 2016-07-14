package sample.session;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import sample.product.ProductDAO;
import sample.product.ProductDTO;

/**
 *
 * @author Suzy
 */
@Stateless
public class ProductSessionBean implements ProductSessionBeanLocal, ProductSessionBeanRemote {
    @Resource(name = "ds", mappedName = "java:PROJECT")
    private DataSource ds;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<ProductDTO> searchByName(String name) throws SQLException {
        ProductDAO dao = new ProductDAO();
        dao.searchByName(name, ds);
        List<ProductDTO> result = dao.getListProducts();
        
        return result;
    }

    @Override
    public int getQuantity(String productID) throws SQLException {
        ProductDAO dao = new ProductDAO();
        int result = dao.getQuantity(productID, ds);
        
        return result;
    }
}
