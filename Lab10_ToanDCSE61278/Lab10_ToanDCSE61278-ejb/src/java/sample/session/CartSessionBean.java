/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.session;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

/**
 *
 * @author Suzy
 */
@Stateful
public class CartSessionBean implements CartSessionBeanLocal, CartSessionBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private HashMap<String, Integer> items;

    @PostConstruct
    public void construct() {
        this.items = new HashMap<String, Integer>();
    }

    @Override
    public HashMap<String, Integer> getItems() {
        return this.items;
    }

    @Override
    public void addItemToCart(String title) {
        int quantity = 1;
        if (this.items.containsKey(title)) {
            quantity = this.items.get(title) + 1;
        }
        this.items.put(title, quantity);
    }

    @Override
    public void removeItemFromCart(String title) {
        if (this.items.containsKey(title)) {
            this.items.remove(title);
        }
    }
}
