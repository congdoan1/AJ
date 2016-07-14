/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Suzy
 */
@Remote
public interface ChildrenSessionBeanRemote {

    List loadAll();

    boolean deleteChildren(String cID);

    boolean updateChildren(String cID, Date dateOfBirth, String placeOfBirth, int weight, int height);

    List searchChildren(Date dFrom, Date dTo);
    
}
