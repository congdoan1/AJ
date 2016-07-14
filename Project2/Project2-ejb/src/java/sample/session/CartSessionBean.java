package sample.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.sql.DataSource;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
@Stateful
public class CartSessionBean implements CartSessionBeanLocal, CartSessionBeanRemote {

    @Resource(name = "ds", mappedName = "java:PROJECT")
    private DataSource ds;

    private String custID;
    private HashMap<ProductDTO, Integer> items;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    public void construct() {
        items = new HashMap<ProductDTO, Integer>();
    }

    public HashMap<ProductDTO, Integer> getItems() {
        return this.items;
    }

    @Override
    public void addItemToCart(ProductDTO dto, int quantity) {
        if (this.items.containsKey(dto)) {
            quantity += this.items.get(dto);
        }
        this.items.put(dto, quantity);
    }

    @Override
    public void removeItemFromCart(String productID) {
        ProductDTO dto = new ProductDTO(productID);
        if (items.containsKey(dto)) {
            items.remove(dto);
        }
    }

    @Override
    public void setCustID(String custID) {
        this.custID = custID;
    }

    @Override
    public float getTotal() {
        Iterator iter = items.entrySet().iterator();
        float total = 0;
        while (iter.hasNext()) {
            Map.Entry item = (Map.Entry) iter.next();
            float price = ((ProductDTO) item.getKey()).getPrice() * Integer.parseInt(item.getValue().toString());
            total += price;
        }
        return total;
    }

    @Remove
    public boolean checkOut() throws SQLException {
        String orderID = "INV" + System.currentTimeMillis() % 100000;
        Date date = new Date();
        Timestamp orderDate = new Timestamp(date.getTime());

        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO tbl_Order(orderID, orderDate, custID, total) VALUES(?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setTimestamp(2, orderDate);
                stm.setString(3, this.custID);
                stm.setFloat(4, this.getTotal());
                int row = stm.executeUpdate();
                if (row > 0) {
                    Iterator iter = this.getItems().entrySet().iterator();
                    boolean bAdd = false;
                    while (iter.hasNext()) {
                        Map.Entry item = (Map.Entry) iter.next();
                        ProductDTO dto = (ProductDTO) item.getKey();
                        int quantity = Integer.parseInt(item.getValue().toString());
                        bAdd = addOrderDetail(dto, quantity, orderID, ds);
                        if (!bAdd) {
                            return false;
                        }
                    }
                    return bAdd;
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

    private boolean addOrderDetail(ProductDTO dto, int quantity, String orderID, DataSource ds) throws SQLException {
        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        if (con != null) {
            String sql = "INSERT INTO orderDetail(productID, quantity, unitPrice, unitItem, total, orderID)"
                    + " VALUES(?,?,?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getProductID());
                stm.setInt(2, quantity);
                stm.setFloat(3, dto.getPrice());
                stm.setString(4, dto.getUnit());
                stm.setFloat(5, dto.getPrice() * quantity);
                stm.setString(6, orderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    ProductDAO dao = new ProductDAO();
                    dao.setQuantity(dto.getProductID(), dao.getQuantity(dto.getProductID(), ds) - quantity, ds);
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

    @Override
    public String getCustID() {
        return this.custID;
    }
}
