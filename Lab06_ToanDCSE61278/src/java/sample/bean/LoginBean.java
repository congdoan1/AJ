package sample.bean;

import java.io.Serializable;
import sample.account.AccountDAO;
import sample.account.AccountDTO;

/**
 *
 * @author Suzy
 */
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private boolean roles;

    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRoles() {
        return roles;
    }

    public void setRoles(boolean roles) {
        this.roles = roles;
    }
    
    public AccountDTO checkLogin() {
        AccountDAO dao = new AccountDAO();
        AccountDTO result = dao.checkLogin(username, password);
        return result;
    }
}
