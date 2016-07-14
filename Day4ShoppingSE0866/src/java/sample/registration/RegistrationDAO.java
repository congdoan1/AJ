package sample.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.util.DBUtils;

/**
 *
 * @author Suzy
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password) {
        Connection con = DBUtils.makeConnection();
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
    
    public void searchLikeLastname(String name) {
        Connection con = DBUtils.makeConnection();
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
    
    public boolean deleteUser(String username) {
        Connection con = DBUtils.makeConnection();
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
    
    public boolean updatePassRole(String username, String password, boolean roles) {
        Connection con = DBUtils.makeConnection();
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
    
    
    
    public boolean addNewUser(String username, String password, String lastname, boolean roles) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;        
        if (con != null) {
            String sql = "INSERT INTO Registration(username, password, lastname, isAdmin) VALUES(?,?,?,?)";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, roles);
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
