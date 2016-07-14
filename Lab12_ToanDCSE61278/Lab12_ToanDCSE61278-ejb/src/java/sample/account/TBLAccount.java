/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.account;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Suzy
 */
@Entity
@Table(name = "TBL_Account", catalog = "UserDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBLAccount.findAll", query = "SELECT t FROM TBLAccount t"),
    @NamedQuery(name = "TBLAccount.findByUsername", query = "SELECT t FROM TBLAccount t WHERE t.username = :username"),
    @NamedQuery(name = "TBLAccount.findByPassword", query = "SELECT t FROM TBLAccount t WHERE t.password = :password"),
    @NamedQuery(name = "TBLAccount.findByLastname", query = "SELECT t FROM TBLAccount t WHERE t.lastname = :lastname"),
    @NamedQuery(name = "TBLAccount.findByIsAdmin", query = "SELECT t FROM TBLAccount t WHERE t.isAdmin = :isAdmin")})
public class TBLAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Username", nullable = false, length = 20)
    private String username;
    @Basic(optional = false)
    @Column(name = "Password", nullable = false, length = 20)
    private String password;
    @Basic(optional = false)
    @Column(name = "Lastname", nullable = false, length = 40)
    private String lastname;
    @Basic(optional = false)
    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin;

    public TBLAccount() {
    }

    public TBLAccount(String username) {
        this.username = username;
    }

    public TBLAccount(String username, String password, String lastname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBLAccount)) {
            return false;
        }
        TBLAccount other = (TBLAccount) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.account.TBLAccount[ username=" + username + " ]";
    }
    
}
