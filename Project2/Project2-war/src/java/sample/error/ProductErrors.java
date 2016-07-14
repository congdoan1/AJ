package sample.error;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class ProductErrors implements Serializable {
    private String qtyEmpty;
    private String qtyErr;

    public ProductErrors() {
    }

    public String getQtyEmpty() {
        return qtyEmpty;
    }

    public void setQtyEmpty(String qtyEmpty) {
        this.qtyEmpty = qtyEmpty;
    }

    public String getQtyErr() {
        return qtyErr;
    }

    public void setQtyErr(String qtyErr) {
        this.qtyErr = qtyErr;
    }
}
