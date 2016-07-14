/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.electric;

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
@Table(name = "T_Electrics", catalog = "TOANQRI", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TElectrics.findAll", query = "SELECT t FROM TElectrics t"),
    @NamedQuery(name = "TElectrics.findByElectid", query = "SELECT t FROM TElectrics t WHERE t.electid = :electid"),
    @NamedQuery(name = "TElectrics.findByDistributor", query = "SELECT t FROM TElectrics t WHERE t.distributor = :distributor"),
    @NamedQuery(name = "TElectrics.findByVoltage", query = "SELECT t FROM TElectrics t WHERE t.voltage = :voltage"),
    @NamedQuery(name = "TElectrics.findByPower", query = "SELECT t FROM TElectrics t WHERE t.power = :power")})
public class TElectrics implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "electid", nullable = false, length = 20)
    private String electid;
    @Basic(optional = false)
    @Column(name = "distributor", nullable = false, length = 20)
    private String distributor;
    @Basic(optional = false)
    @Column(name = "voltage", nullable = false)
    private int voltage;
    @Basic(optional = false)
    @Column(name = "power", nullable = false)
    private int power;

    public TElectrics() {
    }

    public TElectrics(String electid) {
        this.electid = electid;
    }

    public TElectrics(String electid, String distributor, int voltage, int power) {
        this.electid = electid;
        this.distributor = distributor;
        this.voltage = voltage;
        this.power = power;
    }

    public String getElectid() {
        return electid;
    }

    public void setElectid(String electid) {
        this.electid = electid;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (electid != null ? electid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TElectrics)) {
            return false;
        }
        TElectrics other = (TElectrics) object;
        if ((this.electid == null && other.electid != null) || (this.electid != null && !this.electid.equals(other.electid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.electric.TElectrics[ electid=" + electid + " ]";
    }
    
}
