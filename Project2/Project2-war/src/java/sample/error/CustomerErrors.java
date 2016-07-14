package sample.error;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class CustomerErrors implements Serializable {
    private String usernameErr;
    private String passwordErr;
    private String confirmNotMatch;
    private String usernameDup;
    private String lastnameErr;
    private String custNameErr;
    private String phoneErr;

    public CustomerErrors() {
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

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getUsernameDup() {
        return usernameDup;
    }

    public void setUsernameDup(String usernameDup) {
        this.usernameDup = usernameDup;
    }

    public String getLastnameErr() {
        return lastnameErr;
    }

    public void setLastnameErr(String lastnameErr) {
        this.lastnameErr = lastnameErr;
    }

    public String getCustNameErr() {
        return custNameErr;
    }

    public void setCustNameErr(String custNameErr) {
        this.custNameErr = custNameErr;
    }

    public String getPhoneErr() {
        return phoneErr;
    }

    public void setPhoneErr(String phoneErr) {
        this.phoneErr = phoneErr;
    }
}
