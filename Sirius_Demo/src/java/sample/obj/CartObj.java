package sample.obj;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Suzy
 */
public class CartObj implements Serializable {
    private String customerID;
    HashMap<String, Integer> items;

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
    
    
}
