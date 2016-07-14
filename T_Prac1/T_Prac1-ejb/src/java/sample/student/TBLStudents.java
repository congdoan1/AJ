/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.student;

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
@Table(name = "TBL_Students", catalog = "TOANQRI", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBLStudents.findAll", query = "SELECT t FROM TBLStudents t"),
    @NamedQuery(name = "TBLStudents.findByEnrollment", query = "SELECT t FROM TBLStudents t WHERE t.enrollment = :enrollment"),
    @NamedQuery(name = "TBLStudents.findByFullname", query = "SELECT t FROM TBLStudents t WHERE t.fullname = :fullname"),
    @NamedQuery(name = "TBLStudents.findBySex", query = "SELECT t FROM TBLStudents t WHERE t.sex = :sex"),
    @NamedQuery(name = "TBLStudents.findByYearOfBirth", query = "SELECT t FROM TBLStudents t WHERE t.yearOfBirth = :yearOfBirth"),
    @NamedQuery(name = "TBLStudents.findByPlaceOfBirth", query = "SELECT t FROM TBLStudents t WHERE t.placeOfBirth = :placeOfBirth"),
    @NamedQuery(name = "TBLStudents.findByPassword", query = "SELECT t FROM TBLStudents t WHERE t.password = :password"),
    @NamedQuery(name = "TBLStudents.findByGpa", query = "SELECT t FROM TBLStudents t WHERE t.gpa = :gpa"),
    @NamedQuery(name = "TBLStudents.findByYear", query = "SELECT t FROM TBLStudents t WHERE t.yearOfBirth BETWEEN :yFrom AND :yTo")
})
public class TBLStudents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "enrollment", nullable = false, length = 20)
    private String enrollment;
    @Basic(optional = false)
    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;
    @Basic(optional = false)
    @Column(name = "sex", nullable = false)
    private boolean sex;
    @Basic(optional = false)
    @Column(name = "yearOfBirth", nullable = false)
    private int yearOfBirth;
    @Basic(optional = false)
    @Column(name = "placeOfBirth", nullable = false, length = 50)
    private String placeOfBirth;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 250)
    private String password;
    @Basic(optional = false)
    @Column(name = "gpa", nullable = false)
    private double gpa;

    public TBLStudents() {
    }

    public TBLStudents(String enrollment) {
        this.enrollment = enrollment;
    }

    public TBLStudents(String enrollment, String fullname, boolean sex, int yearOfBirth, String placeOfBirth, String password, double gpa) {
        this.enrollment = enrollment;
        this.fullname = fullname;
        this.sex = sex;
        this.yearOfBirth = yearOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.password = password;
        this.gpa = gpa;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enrollment != null ? enrollment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBLStudents)) {
            return false;
        }
        TBLStudents other = (TBLStudents) object;
        if ((this.enrollment == null && other.enrollment != null) || (this.enrollment != null && !this.enrollment.equals(other.enrollment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.student.TBLStudents[ enrollment=" + enrollment + " ]";
    }

}
