/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.sql.SQLException;
import java.util.HashMap;
import javax.ejb.Local;
import sample.product.ProductDTO;

/**
 *
 * @author Suzy
 */
@Local
public interface CartSessionBeanLocal {
    
    HashMap<ProductDTO, Integer> getItems();

    void addItemToCart(ProductDTO dto, int quantity);

    void removeItemFromCart(String productID);

    void setCustID(String custID);

    float getTotal();
    
    boolean checkOut() throws SQLException ;

    String getCustID();

}
