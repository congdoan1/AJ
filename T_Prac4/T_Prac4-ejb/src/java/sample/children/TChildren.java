/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.children;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Suzy
 */
@Entity
@Table(name = "T_Children", catalog = "TOANQRI", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TChildren.findAll", query = "SELECT t FROM TChildren t"),
    @NamedQuery(name = "TChildren.findByCID", query = "SELECT t FROM TChildren t WHERE t.cID = :cID"),
    @NamedQuery(name = "TChildren.findByName", query = "SELECT t FROM TChildren t WHERE t.name = :name"),
    @NamedQuery(name = "TChildren.findByDateOfBirth", query = "SELECT t FROM TChildren t WHERE t.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "TChildren.findByPlaceOfBirth", query = "SELECT t FROM TChildren t WHERE t.placeOfBirth = :placeOfBirth"),
    @NamedQuery(name = "TChildren.findByWeight", query = "SELECT t FROM TChildren t WHERE t.weight = :weight"),
    @NamedQuery(name = "TChildren.findByHeight", query = "SELECT t FROM TChildren t WHERE t.height = :height"),
    @NamedQuery(name = "TChildren.findByDOB", query = "SELECT t FROM TChildren t WHERE t.dateOfBirth BETWEEN :dFrom AND :dTo")
})
public class TChildren implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cID", nullable = false, length = 15)
    private String cID;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Basic(optional = false)
    @Column(name = "dateOfBirth", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Column(name = "placeOfBirth", length = 30)
    private String placeOfBirth;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "height")
    private Integer height;

    public TChildren() {
    }

    public TChildren(String cID) {
        this.cID = cID;
    }

    public TChildren(String cID, String name, Date dateOfBirth) {
        this.cID = cID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getCID() {
        return cID;
    }

    public void setCID(String cID) {
        this.cID = cID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cID != null ? cID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TChildren)) {
            return false;
        }
        TChildren other = (TChildren) object;
        if ((this.cID == null && other.cID != null) || (this.cID != null && !this.cID.equals(other.cID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.children.TChildren[ cID=" + cID + " ]";
    }
    
}
