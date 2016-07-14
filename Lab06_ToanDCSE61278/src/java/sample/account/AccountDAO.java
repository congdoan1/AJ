package sample.account;

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
public class AccountDAO implements Serializable {

    public AccountDTO checkLogin(String username, String password) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (con != null) {
            String sql = "SELECT * FROM TBL_Account WHERE Username = ? AND Password = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    AccountDTO dto = new AccountDTO(rs.getString("Username"), rs.getString("Password"), rs.getString("Lastname"), rs.getBoolean("isAdmin"));
                    return dto;
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
        return null;
    }

    public List<AccountDTO> searchLikeLastname(String name) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (con != null) {
            String sql = "SELECT * FROM TBL_Account WHERE Lastname LIKE ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                List<AccountDTO> result = new ArrayList<AccountDTO>();
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String lastname = rs.getString("Lastname");
                    boolean roles = rs.getBoolean("isAdmin");
                    result.add(new AccountDTO(username, password, lastname, roles));
                }
                if (result.size() > 0) {
                    return result;
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
        return null;
    }

    public AccountDTO searchByUser(String usrname) {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        if (con != null) {
            String sql = "SELECT * FROM TBL_Account WHERE Username = ?";
            try {
                stm = con.prepareStatement(sql);
                stm.setString(1, usrname);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String lastname = rs.getString("Lastname");
                    boolean roles = rs.getBoolean("isAdmin");
                    AccountDTO dto = new AccountDTO(username, password, lastname, roles);
                    return dto;
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
        return null;
    }
}
