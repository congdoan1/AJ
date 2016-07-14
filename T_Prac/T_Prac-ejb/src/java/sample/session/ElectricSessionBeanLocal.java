/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Suzy
 */
@Local
public interface ElectricSessionBeanLocal {

    List listAll();

    boolean deleteElec(String electid);

    boolean updateElec(String electid, String distributor, int voltage, int power);
    
}
