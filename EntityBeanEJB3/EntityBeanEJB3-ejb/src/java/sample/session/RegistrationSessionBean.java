package sample.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.entity.Registration;

/**
 *
 * @author Suzy
 */
@Stateless
public class RegistrationSessionBean implements RegistrationSessionBeanLocal, RegistrationSessionBeanRemote {
    @PersistenceContext(unitName = "EntityBeanEJB3-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean insertUser(String username, String password, String lastname, boolean roles) {
        Registration reg = new Registration(username, password);
        reg.setLastname(lastname);
        reg.setIsAdmin(roles);
        
        persist(reg);
        
        return true;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Query query = em.createNamedQuery("Registration.checkLogin");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List result = query.getResultList();
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public List loadAll() {
        Query query = em.createNamedQuery("Registration.findAll");
        List result = query.getResultList();
        
        return result;
    }
    
    
}
