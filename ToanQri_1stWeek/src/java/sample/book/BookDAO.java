package sample.book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Suzy
 */
public class BookDAO implements Serializable {
    private List<BookDTO> listBooks;

    public List<BookDTO> getListBooks() {
        return listBooks;
    }
    
    public void loadAll() {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (con != null) {
            String sql = "SELECT * FROM Books";
            try {
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                listBooks = new ArrayList<BookDTO>();
                while (rs.next()) {
                    String isbn = rs.getString("isbn");
                    String title = rs.getString("title");
                    float price = rs.getFloat("price");
                    listBooks.add(new BookDTO(isbn, title, price));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
}
