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
import sample.item.Item;
import sample.item.ItemPK;

/**
 *
 * @author Suzy
 */
@Stateless
public class ItemSessionBean implements ItemSessionBeanLocal, ItemSessionBeanRemote {
    @PersistenceContext(unitName = "Day13ID0866-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean insertItem(String itemID, Date dateS, String description, long amt) {
        if (dateS == null) {
            dateS = new Date();
        }
        ItemPK itemPk = new ItemPK(itemID, dateS);
        Item pk = em.find(Item.class, itemPk);
        if (pk == null) {
            pk = new Item(itemPk);
            pk.setDescription(description);
            pk.setAmt(amt);
            persist(pk);
            return true;
        }
        return false;
    }

    @Override
    public List searchByDate(Date dFrom, Date dTo) {
        String jpql = "Item.findByDate";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("dFrom", dFrom);
        query.setParameter("dTo", dTo);
        List result = query.getResultList();
        return result;
    }

    @Override
    public boolean deleteItem(String itemID, Date dateS) {
        ItemPK pk = new ItemPK(itemID, dateS);
        Item item = em.find(Item.class, pk);
        if (item != null) {
            em.remove(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateItem(String itemID, Date dateS, String description, long amt) {
        ItemPK pk = new ItemPK(itemID, dateS);
        Item item = em.find(Item.class, pk);
        if (item != null) {
            item.setDescription(description);
            item.setAmt(amt);
            em.merge(item);
            return true;
        }
        return false;
    }
    
    
}
