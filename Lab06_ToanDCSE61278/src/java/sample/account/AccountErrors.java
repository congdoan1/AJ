package sample.account;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class AccountErrors implements Serializable {
    private String usernameErr;
    private String passwordErr;

    public AccountErrors() {
    }

    public String getUsernameErr() {
        return usernameErr;
    }

    public void setUsernameErr(String usernameErr) {
        this.usernameErr = usernameErr;
    }

    public String getPasswordErr() {
        return passwordErr;
    }

    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }
   
}
