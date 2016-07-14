package sample.stateful;

import java.util.HashMap;
import javax.ejb.Remote;

/**
 *
 * @author Suzy
 */
@Remote
public interface CartSessionBeanRemote {
    
    String getCustID();
    
    void setCustID(String custID);
    
    HashMap<String, Integer> getItems();

    void addItemToCart(String title);

    void removeItemFromCart(String title);

    void checkOut();
    
}
