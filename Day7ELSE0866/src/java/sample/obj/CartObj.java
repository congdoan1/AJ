package sample.obj;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Suzy
 */
public class CartObj implements Serializable {
    private String customerID;
    private HashMap<String, Integer> items;

    public CartObj() {
        this.customerID = "00000";
        this.items = new HashMap<String, Integer>();
    }

    public CartObj(String customerID) {
        this.customerID = customerID;
         this.items = new HashMap<String, Integer>();
    }
    
    public String getCustomerID() {
        return customerID;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String title) {
        int quantity = 1;
        if (items.containsKey(title)) {
            quantity = items.get(title) + 1;
        }
        items.put(title, quantity);
    }
    
    public void removeItemFromCart(String title) {
        if (items.containsKey(title)) {
            items.remove(title);
        }
    }
}
