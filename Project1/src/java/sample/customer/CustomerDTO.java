package sample.customer;

import java.io.Serializable;

/**
 *
 * @author Suzy
 */
public class CustomerDTO implements Serializable {
    private String custID;
    private String password;
    private String custName;
    private String lastName;
    private String middleName;
    private String address;
    private String phone;
    int level;

    public CustomerDTO() {
    }

    public CustomerDTO(String custID, String password, String custName, String lastName, String middleName, String address, String phone) {
        this.custID = custID;
        this.password = password;
        this.custName = custName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.phone = phone;
        this.level = 1;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
}
