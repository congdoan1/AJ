/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import sample.registration.Registration;

/**
 *
 * @author Suzy
 */
@Stateless
public class RegistrationSessionBean implements RegistrationSessionBeanLocal, RegistrationSessionBeanRemote {
    @Resource(name = "ds", mappedName = "java:CRUDDS")
    private DataSource ds;
    
    @PersistenceContext(unitName = "TOANQRIDB-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean checkLogin(String username, String password) {
        Registration reg = em.find(Registration.class, username);
        if (reg != null) {
            return true;
        }
        return false;
    }
    
    
}
