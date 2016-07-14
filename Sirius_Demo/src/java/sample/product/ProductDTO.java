package sample.product;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class ProductDTO implements Serializable {
    private String code;
    private String name;
    private String description;
    private float price;
    private String category;

    public ProductDTO() {
    }

    public ProductDTO(String code, String name, String description, float price, String category) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
