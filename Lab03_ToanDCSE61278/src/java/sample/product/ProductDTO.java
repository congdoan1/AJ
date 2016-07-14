package sample.product;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class ProductDTO implements Serializable {
    private String productID;
    private String productName;
    private String quantityPerUnit;
    private float price;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, String quantityPerUnit, float price) {
        this.productID = productID;
        this.productName = productName;
        this.quantityPerUnit = quantityPerUnit;
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

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        ProductDTO dto = (ProductDTO) obj;
        return productID.equals(dto.getProductID()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return productID.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
