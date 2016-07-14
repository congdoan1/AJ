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
import sample.electric.TElectrics;

/**
 *
 * @author Suzy
 */
@Stateless
public class ElectricSessionBean implements ElectricSessionBeanLocal, ElectricSessionBeanRemote {
    @PersistenceContext(unitName = "T_Prac3-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List loadAll() {
        String jpql = "TElectrics.findAll";
        Query query = em.createNamedQuery(jpql);
        List result = query.getResultList();
        return result;
    }

    @Override
    public boolean deleteElectric(String electid) {
        TElectrics elec = em.find(TElectrics.class, electid);
        if (elec != null) {
            em.remove(elec);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateElectric(String electid, String distributor, int voltage, int power) {
        TElectrics elec = em.find(TElectrics.class, electid);
        if (elec != null) {
            elec.setDistributor(distributor);
            elec.setVoltage(voltage);
            elec.setPower(power);
            em.merge(elec);
            return true;
        }
        return false;
    }
    
    
    
}
