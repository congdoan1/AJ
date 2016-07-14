package sample.bean;

import java.io.Serializable;
import sample.registration.RegistrationDAO;

/**
 *
 * @author Suzy
 */
public class LoginBean implements Serializable {
    private String username;
    private String password;

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
    
    public boolean checkLogin() {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.checkLogin(username, password);
        
        return result;
    }
}
