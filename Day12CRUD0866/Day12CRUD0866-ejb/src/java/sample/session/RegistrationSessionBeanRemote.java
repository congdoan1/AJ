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
public interface RegistrationSessionBeanRemote {

    boolean checkLogin(String username, String password);

    List searchLikeLastname(String name);

    boolean updatePassRoles(String username, String password, boolean roles);

    boolean deleteUser(String username);

    boolean insertUser(String username, String password, String lastname, boolean roles);
    
}
