package sample.obj;

import java.io.Serializable;
import java.util.HashMap;
import sample.book.BookDTO;

/**
 *
 * @author Suzy
 */
public class CartObj implements Serializable {
    private String username;
    private HashMap<BookDTO, Integer> listItems;

    public CartObj() {
        this.username = "toan";
        this.listItems = new HashMap<BookDTO, Integer>();
    }

    public CartObj(String username) {
        this.username = username;
        this.listItems = new HashMap<BookDTO, Integer>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<BookDTO, Integer> getListItems() {
        return listItems;
    }

    public void setListItems(HashMap<BookDTO, Integer> listItems) {
        this.listItems = listItems;
    }
    
    public void addItemToCart(BookDTO book) {
        int quantity = 1;
        if (listItems.containsKey(book)) {
            quantity = listItems.get(book) + 1;
        }
        listItems.put(book, quantity);
    }
    
    public void removeItemFromCart(BookDTO book) {
        if (listItems.containsKey(book)) {
            listItems.remove(book);
        }
    }
}
