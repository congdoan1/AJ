/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.HashMap;
import javax.ejb.Remote;

/**
 *
 * @author Suzy
 */
@Remote
public interface CartSessionBeanRemote {

    HashMap<String, Integer> getItems();

    void addItemToCart(String title);

    void removeItemFromCart(String title);
    
}
