/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Suzy
 */
@Stateless
public class StudentSessionBean implements StudentSessionBeanLocal, StudentSessionBeanRemote {
    @PersistenceContext(unitName = "T_Prac1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List searchByYear(int yFrom, int yTo) {
        String jpql = "TBLStudents.findByYear";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("yFrom", yFrom);
        query.setParameter("yTo", yTo);
        List result = query.getResultList();
        return result;
    }
    
    
}
