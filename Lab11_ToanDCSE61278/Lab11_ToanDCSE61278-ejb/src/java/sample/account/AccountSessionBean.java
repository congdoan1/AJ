/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Suzy
 */
@Stateless
public class AccountSessionBean implements AccountSessionBeanLocal, AccountSessionBeanRemote {
    @PersistenceContext(unitName = "Lab11_ToanDCSE61278-ejbPU")
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String getAccountId() {
        Account acc = em.find(Account.class, this);
        return null;
    }

    @Override
    public void create(String accountId) {
        Account acc = em.find(Account.class, accountId);
        if (acc == null) {
            acc = new Account(accountId);
            acc.setBalance(0);
            persist(acc);
        }
    }

    @Override
    public Account findByPrimaryKey(String accountId) {
        return em.find(Account.class, accountId);
    }

    @Override
    public void sell(String amt) {
        //Em khong hieu de
    }
    
    
    
}
