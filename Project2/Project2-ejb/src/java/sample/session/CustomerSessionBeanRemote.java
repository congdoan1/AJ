/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.sql.SQLException;
import javax.ejb.Remote;
import sample.customer.CustomerDTO;

/**
 *
 * @author Suzy
 */
@Remote
public interface CustomerSessionBeanRemote {

    boolean checkLogin(String username, String password) throws SQLException;

    boolean addUser(CustomerDTO dto) throws SQLException;
    
}
