package sample.customer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class CustomerDAO implements Serializable {
    public boolean checkLogin(String username, String password) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        if (con != null) {
            String sql = "SELECT * FROM customer WHERE custID = ? AND password = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stm != null) {
                        stm.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public boolean addUser(CustomerDTO dto) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        
        if (con != null) {
            String sql = "INSERT INTO customer(custID, [password], custName, lastName, middleName, [address], phone, custLevel)"
                    + " VALUES(?,?,?,?,?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getCustID());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getCustName());
                stm.setString(4, dto.getLastName());
                stm.setString(5, dto.getMiddleName());
                stm.setString(6, dto.getAddress());
                stm.setString(7, dto.getPhone());
                stm.setInt(8, dto.getLevel());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (stm != null) {
                        stm.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
}
