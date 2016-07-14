package sample.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.user.TUsers;

/**
 *
 * @author Suzy
 */
@Stateless
public class UserSessionBean implements UserSessionBeanLocal, UserSessionBeanRemote {

    @PersistenceContext(unitName = "T_Prac4-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean checkLogin(String username, String password) {
        String jpql = "SELECT t FROM TUsers t WHERE t.username = :username"
                + " AND t.password = :password";
        Query query = em.createQuery(jpql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public boolean checkRole(String username) {
        TUsers ch = em.find(TUsers.class, username);
        if (ch != null) {
            String role = ch.getRole();
            if (role.equals("admin")) {
                return true;
            }
        }
        return false;
    }

}
