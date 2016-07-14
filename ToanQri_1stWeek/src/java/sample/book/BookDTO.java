package sample.book;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class BookDTO implements Serializable {
    private String isbn;
    private String title;
    private float price;

    public BookDTO() {
    }

    public BookDTO(String isbn, String title, float price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }
    
    public BookDTO(String isbn) {
        this.isbn = isbn;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return isbn.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        BookDTO dto = (BookDTO) obj;
        return this.isbn.equals(dto.getIsbn()); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
