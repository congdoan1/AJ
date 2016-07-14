/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.item;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Suzy
 */
@Embeddable
public class ItemPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "itemID", nullable = false, length = 5)
    private String itemID;
    @Basic(optional = false)
    @Column(name = "dateS", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateS;

    public ItemPK() {
    }

    public ItemPK(String itemID, Date dateS) {
        this.itemID = itemID;
        this.dateS = dateS;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Date getDateS() {
        return dateS;
    }

    public void setDateS(Date dateS) {
        this.dateS = dateS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemID != null ? itemID.hashCode() : 0);
        hash += (dateS != null ? dateS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPK)) {
            return false;
        }
        ItemPK other = (ItemPK) object;
        if ((this.itemID == null && other.itemID != null) || (this.itemID != null && !this.itemID.equals(other.itemID))) {
            return false;
        }
        if ((this.dateS == null && other.dateS != null) || (this.dateS != null && !this.dateS.equals(other.dateS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.item.ItemPK[ itemID=" + itemID + ", dateS=" + dateS + " ]";
    }
    
}
