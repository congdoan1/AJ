package sample.registration;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class AccountDTO implements Serializable {
    private String username;
    private String password;
    private String fullname;
    private boolean roles;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String fullname, boolean roles) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.roles = roles;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isRoles() {
        return roles;
    }

    public void setRoles(boolean roles) {
        this.roles = roles;
    }
    
    
}
