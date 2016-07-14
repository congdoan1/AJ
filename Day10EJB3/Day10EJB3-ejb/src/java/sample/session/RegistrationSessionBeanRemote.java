/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.List;
import javax.ejb.Remote;
import sample.registration.RegistrationDTO;

/**
 *
 * @author Suzy
 */
@Remote
public interface RegistrationSessionBeanRemote {

    boolean checkLogin(String username, String password);

    List<RegistrationDTO> searchByLastname(String name);

    boolean deleteUser(String username);

    boolean updateUser(String username, String password, boolean roles);
    
}
