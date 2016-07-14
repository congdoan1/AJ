/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import sample.book.Books;

/**
 *
 * @author Suzy
 */
@Stateless
public class BookSessionBean implements BookSessionBeanLocal, BookSessionBeanRemote {
    @PersistenceContext(unitName = "ToanQriSR-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List loadAll() {
        String jpql = "Books.findAll";
        Query query = em.createNamedQuery(jpql);
        List result = query.getResultList();
        
        return result;
    }

    @Override
    public boolean deleteBook(String isbn) {
        Books book = em.find(Books.class, isbn);
        if (book != null) {
            em.remove(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBook(String isbn, String title, float price) {
        Books book = em.find(Books.class, isbn);
        if (book != null) {
            book.setTitle(title);
            book.setPrice(new BigDecimal(price));
            
            em.merge(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertBook(String isbn, String title, float price) {
        Books book = em.find(Books.class, isbn);
        if (book == null) {
            book = new Books(isbn, title, new BigDecimal(price));
            
            persist(book);
            
            return true;
        }
        return false;
    }
    
    
    
}
