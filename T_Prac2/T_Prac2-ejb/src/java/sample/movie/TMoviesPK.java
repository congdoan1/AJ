/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.movie;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Suzy
 */
@Embeddable
public class TMoviesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Basic(optional = false)
    @Column(name = "year", nullable = false)
    private int year;

    public TMoviesPK() {
    }

    public TMoviesPK(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (title != null ? title.hashCode() : 0);
        hash += (int) year;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMoviesPK)) {
            return false;
        }
        TMoviesPK other = (TMoviesPK) object;
        if ((this.title == null && other.title != null) || (this.title != null && !this.title.equals(other.title))) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.movie.TMoviesPK[ title=" + title + ", year=" + year + " ]";
    }
    
}
