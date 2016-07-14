/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.registration.Registration;

/**
 *
 * @author Suzy
 */
@Stateless
public class RegistrationSessionBean implements RegistrationSessionBeanLocal, RegistrationSessionBeanRemote {
    @PersistenceContext(unitName = "Day12CRUD0866-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean checkLogin(String username, String password) {
        //tao query instance
        String jpql = "SELECT r FROM Registration r WHERE r.username = :username AND r.password = :password";
        Query query = em.createQuery(jpql);
        //set param cho cau lenh truy van
        query.setParameter("username", username);
        query.setParameter("password", password);
        //thuc hien cau lenh truy van
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public List searchLikeLastname(String name) {
        String jpql = "Registration.findByLikeLastname";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("lastname", "%" + name + "%");
        List result = query.getResultList();
        
        return result;
    }

    @Override
    public boolean updatePassRoles(String username, String password, boolean roles) {
        Registration reg = em.find(Registration.class, username);
        if (reg != null) {
            reg.setPassword(password);
            reg.setIsAdmin(roles);
            
            em.merge(reg);
            
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        Registration reg = em.find(Registration.class, username);
        if (reg != null) {
            em.remove(reg);
            
            return true;
        }
        return false;
    }

    @Override
    public boolean insertUser(String username, String password, String lastname, boolean roles) {
        Registration reg = em.find(Registration.class, username);
        
        if (reg == null) {
            reg = new Registration(username);
            reg.setPassword(password);
            reg.setLastname(lastname);
            reg.setIsAdmin(roles);
            
            persist(reg);
            
            return true;
        }
        return false;
    }
    
    
    
}
