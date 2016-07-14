/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Suzy
 */
@Remote
public interface ElectricSessionBeanRemote {

    List loadAll();

    boolean deleteElectric(String electid);

    boolean updateElectric(String electid, String distributor, int voltage, int power);
    
}
