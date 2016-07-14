/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import sample.account.AccountDAO;

/**
 *
 * @author Suzy
 */
@Stateless
public class AccountSessionBean implements AccountSessionBeanLocal, AccountSessionBeanRemote {
    @Resource(name = "ds", mappedName = "java:lab09")
    private DataSource ds;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean checkLogin(String username, String password) {
        
        AccountDAO dao = new AccountDAO();
        boolean result = dao.checkLogin(username, password, ds);
        
        return result;
    }
    
}
