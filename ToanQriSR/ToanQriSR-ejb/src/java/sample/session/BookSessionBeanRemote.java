/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Suzy
 */
@Remote
public interface BookSessionBeanRemote {

    List loadAll();

    boolean deleteBook(String isbn);

    boolean updateBook(String isbn, String title, float price);

    boolean insertBook(String isbn, String title, float price);
    
}
