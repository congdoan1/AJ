package sample.product;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class ProductDTO implements Serializable {
    private String productID;
    private String productName;
    private String unit;
    private float price;

    public ProductDTO() {
    }
    
    public ProductDTO(String productID) {
        this.productID = productID;
    }

    public ProductDTO(String productID, String productName, String unit, float price) {
        this.productID = productID;
        this.productName = productName;
        this.unit = unit;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return this.productID.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        ProductDTO dto = (ProductDTO) obj;
        return this.productID.equals(dto.getProductID()); //To change body of generated methods, choose Tools | Templates.
    }
}
