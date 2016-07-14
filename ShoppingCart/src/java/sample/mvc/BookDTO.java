package sample.mvc;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class BookDTO implements Serializable {
    private String title;
    private int quantity;

    public BookDTO() {
    }

    public BookDTO(String title) {
        this.title = title;
        this.quantity = 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
