package sample.obj;

import java.io.Serializable;
import java.util.HashMap;
import sample.product.ProductDTO;

/**
 *
 * @author Suzy
 */
public class CartObj implements Serializable {
    private String customerID;
    private HashMap<ProductDTO, Integer> items;

    public CartObj() {
        this.customerID = "00000";
        this.items = new HashMap<ProductDTO, Integer>();
    }

    public CartObj(String customerID) {
        this.customerID = customerID;
        this.items = new HashMap<ProductDTO, Integer>();
    }
    
    public String getCustomerID() {
        return customerID;
    }

    public HashMap<ProductDTO, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(ProductDTO dto) {
        int quantity = 1;
        if (items.containsKey(dto)) {
            quantity = items.get(dto) + 1;
        }
        items.put(dto, quantity);
    }
    
}
