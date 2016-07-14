package sample.stateful;

import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author Suzy
 */
@Local
public interface CartSessionBeanLocal {
    
    String getCustID();
    
    void setCustID(String custID);
    
    HashMap<String, Integer> getItems();

    void addItemToCart(String title);

    void removeItemFromCart(String title);

    void checkOut();
}
