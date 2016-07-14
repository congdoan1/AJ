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
import sample.movie.TMovies;
import sample.movie.TMoviesPK;

/**
 *
 * @author Suzy
 */
@Stateless
public class MovieSessionBean implements MovieSessionBeanLocal, MovieSessionBeanRemote {
    @PersistenceContext(unitName = "T_Prac2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List searchYear(int yFrom, int yTo) {
        String jpql = "TMovies.findByYearBetween";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("yFrom", yFrom);
        query.setParameter("yTo", yTo);
        List result = query.getResultList();
        return result;
    }

    @Override
    public boolean deleteMovie(String title, int year) {
        TMoviesPK pk = new TMoviesPK(title, year);
        TMovies mv = em.find(TMovies.class, pk);
        if (mv != null) {
            em.remove(mv);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMovie(String title, int year, int duration, String genre, String studio) {
        TMoviesPK pk = new TMoviesPK(title, year);
        TMovies mv = em.find(TMovies.class, pk);
        if (mv != null) {
            mv.setDuration(duration);
            mv.setGenre(genre);
            mv.setStudio(studio);
            em.merge(mv);
            return true;
        }
        return false;
    }
    
    
}
