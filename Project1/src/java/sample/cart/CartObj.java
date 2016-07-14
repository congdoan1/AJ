package sample.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import sample.product.ProductDTO;

/**
 *
 * @author Suzy
 */
public class CartObj implements Serializable {
    private String custID;
    private HashMap<ProductDTO, Integer> items;

    public CartObj() {
        custID = "0001";
        this.items = new HashMap<ProductDTO, Integer>();
    }

    public CartObj(String custID) {
        this.custID = custID;
        this.items = new HashMap<ProductDTO, Integer>();
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public HashMap<ProductDTO, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<ProductDTO, Integer> items) {
        this.items = items;
    }
    
    public void addItemToCart(ProductDTO product, int quantity) {
        int qtity = quantity;
        if (items.containsKey(product)) {
            qtity = items.get(product) + quantity;
        }
        items.put(product, qtity);
    }
    
    public void removeItemFromCart(ProductDTO product) {
        if (items.containsKey(product)) {
            items.remove(product);
        }
    }
    
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
}
