/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.children.TChildren;

/**
 *
 * @author Suzy
 */
@Stateless
public class ChildrenSessionBean implements ChildrenSessionBeanLocal, ChildrenSessionBeanRemote {
    @PersistenceContext(unitName = "T_Prac4-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List loadAll() {
        String jpql = "TChildren.findAll";
        Query query = em.createNamedQuery(jpql);
        List result = query.getResultList();
        
        return result;
    }

    @Override
    public boolean deleteChildren(String cID) {
        TChildren c = em.find(TChildren.class, cID);
        if (c != null) {
            em.remove(c);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateChildren(String cID, Date dateOfBirth, String placeOfBirth, int weight, int height) {
        TChildren c = em.find(TChildren.class, cID);
        if (c != null) {
            c.setDateOfBirth(dateOfBirth);
            c.setPlaceOfBirth(placeOfBirth);
            c.setWeight(weight);
            c.setHeight(height);
            em.merge(c);
            return true;
        }
        return false;
    }

    @Override
    public List searchChildren(Date dFrom, Date dTo) {
        String jpql = "TChildren.findByDOB";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("dFrom", dFrom);
        query.setParameter("dTo", dTo);
        List result = query.getResultList();
        return result;
    }
    
}
