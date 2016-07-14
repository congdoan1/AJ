/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Remote;
import sample.product.ProductDTO;

/**
 *
 * @author Suzy
 */
@Remote
public interface ProductSessionBeanRemote {

    List<ProductDTO> searchByName(String name) throws SQLException ;

    int getQuantity(String productID) throws SQLException;
    
}
