/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Suzy
 */
@Local
public interface MovieSessionBeanLocal {

    List searchYear(int yFrom, int yTo);

    boolean deleteMovie(String title, int year);

    boolean updateMovie(String title, int year, int duration, String genre, String studio);
    
}
