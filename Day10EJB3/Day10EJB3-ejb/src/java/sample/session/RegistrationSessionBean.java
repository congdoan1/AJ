package sample.session;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import sample.registration.RegistrationDAO;
import sample.registration.RegistrationDTO;

/**
 *
 * @author Suzy
 */
@Stateless
public class RegistrationSessionBean implements RegistrationSessionBeanLocal, RegistrationSessionBeanRemote {
    @Resource(name = "ds", mappedName = "java:EJB3DS")
    private DataSource ds;

    @Override
    public boolean checkLogin(String username, String password) {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.checkLogin(username, password, ds);
        
        return result;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<RegistrationDTO> searchByLastname(String name) {
        RegistrationDAO dao = new RegistrationDAO();
        dao.searchByLastname(name, ds);
        List<RegistrationDTO> result = dao.getListUsers();
        
        return result;
    }

    @Override
    public boolean deleteUser(String username) {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.deleteUser(username, ds);
        
        return result;
    }

    @Override
    public boolean updateUser(String username, String password, boolean roles) {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.updateUser(username, password, roles, ds);
        
        return result;
    }
    
    
    
}
