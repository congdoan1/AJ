/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Suzy
 */
@Local
public interface ItemSessionBeanLocal {

    boolean insertItem(String itemID, Date dateS, String description, long amt);

    List searchByDate(Date dFrom, Date dTo);

    boolean deleteItem(String itemID, Date dateS);

    boolean updateItem(String itemID, Date dateS, String description, long amt);
    
}
