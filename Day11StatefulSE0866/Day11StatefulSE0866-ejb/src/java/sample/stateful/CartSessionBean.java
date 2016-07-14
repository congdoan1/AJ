package sample.stateful;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Suzy
 */
@Stateful
public class CartSessionBean implements CartSessionBeanLocal, CartSessionBeanRemote {
    private String custID;
    private HashMap<String, Integer> items;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PostConstruct
    public void construct() {
        items = new HashMap<String, Integer>();
    }
    
    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    @Override
    public void addItemToCart(String title) {
        int quantity = 1;
        if (items.containsKey(title)) {
            quantity = items.get(title) + 1;
        }
        items.put(title, quantity);
    }

    @Override
    public void removeItemFromCart(String title) {
        if (items.containsKey(title)) {
            items.remove(title);
        }
    }

    @Remove
    public void checkOut() {
        //do yourself
        //cap nhat database
    }
    
    
}
