/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.account.Account;

/**
 *
 * @author Suzy
 */
@Stateless
public class AccountSessionBean implements AccountSessionBeanLocal, AccountSessionBeanRemote {

    @PersistenceContext(unitName = "Day13ID0866-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean insertAccount(String username, float salary) {
        String jpql = "SELECT a FROM Account a WHERE a.username = :username";
        Query query = em.createQuery(jpql);
        query.setParameter("username", username);
        try {
            query.getSingleResult();
            return false;
        } catch (NoResultException ex) {
            Account acc = new Account();
            acc.setUsername(username);
            acc.setSalary(salary);
            
            persist(acc);
            System.out.println(acc.getId());
            return true;
        }
    }

}
