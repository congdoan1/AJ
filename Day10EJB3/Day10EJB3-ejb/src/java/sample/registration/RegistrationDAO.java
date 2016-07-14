package sample.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password, DataSource ds) {
        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        if (con != null) {
            String sql = "SELECT * FROM Registration WHERE username = ? AND password = ?";
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
    
    
    private List<RegistrationDTO> listUsers;

    public List<RegistrationDTO> getListUsers() {
        return listUsers;
    }
    
    public void searchByLastname(String name, DataSource ds) {
        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        if (con != null) {
            String sql = "SELECT * FROM Registration WHERE lastname LIKE ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                listUsers = new ArrayList<RegistrationDTO>();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean roles = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, roles);
                    listUsers.add(dto);
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
    }
    
    public boolean updateUser(String username, String password, boolean roles, DataSource ds) {
        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        
        if (con != null) {
            String sql = "UPDATE Registration SET password = ?, isAdmin = ? WHERE username = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, roles);
                stm.setString(3, username);
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
    
    public boolean deleteUser(String username, DataSource ds) {
        Connection con = DBUtils.makeConnection(ds);
        PreparedStatement stm = null;
        
        if (con != null) {
            String sql = "DELETE FROM Registration WHERE username = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
