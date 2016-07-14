/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.sql.SQLException;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import sample.customer.CustomerDAO;
import sample.customer.CustomerDTO;

/**
 *
 * @author Suzy
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanLocal, CustomerSessionBeanRemote {
    @Resource(name = "ds", mappedName = "java:PROJECT")
    private DataSource ds;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        boolean result = dao.checkLogin(username, password, ds);
        
        return result;
    }

    @Override
    public boolean addUser(CustomerDTO dto) throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        boolean result = dao.addUser(dto, ds);
        
        return result;
    }
}
