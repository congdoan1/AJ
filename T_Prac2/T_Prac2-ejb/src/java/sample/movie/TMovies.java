/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.movie;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Suzy
 */
@Entity
@Table(name = "T_Movies", catalog = "TOANQRI", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMovies.findAll", 
            query = "SELECT t FROM TMovies t"),
    @NamedQuery(name = "TMovies.findByTitle", 
            query = "SELECT t FROM TMovies t WHERE t.tMoviesPK.title = :title"),
    @NamedQuery(name = "TMovies.findByYear", 
            query = "SELECT t FROM TMovies t WHERE t.tMoviesPK.year = :year"),
    @NamedQuery(name = "TMovies.findByDuration", 
            query = "SELECT t FROM TMovies t WHERE t.duration = :duration"),
    @NamedQuery(name = "TMovies.findByGenre", 
            query = "SELECT t FROM TMovies t WHERE t.genre = :genre"),
    @NamedQuery(name = "TMovies.findByStudio", 
            query = "SELECT t FROM TMovies t WHERE t.studio = :studio"),
    @NamedQuery(name = "TMovies.findByYearBetween", 
            query = "SELECT t FROM TMovies t WHERE t.tMoviesPK.year BETWEEN :yFrom AND :yTo")
})
public class TMovies implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TMoviesPK tMoviesPK;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "genre", length = 20)
    private String genre;
    @Column(name = "studio", length = 50)
    private String studio;

    public TMovies() {
    }

    public TMovies(TMoviesPK tMoviesPK) {
        this.tMoviesPK = tMoviesPK;
    }

    public TMovies(String title, int year) {
        this.tMoviesPK = new TMoviesPK(title, year);
    }

    public TMoviesPK getTMoviesPK() {
        return tMoviesPK;
    }

    public void setTMoviesPK(TMoviesPK tMoviesPK) {
        this.tMoviesPK = tMoviesPK;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tMoviesPK != null ? tMoviesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMovies)) {
            return false;
        }
        TMovies other = (TMovies) object;
        if ((this.tMoviesPK == null && other.tMoviesPK != null) || (this.tMoviesPK != null && !this.tMoviesPK.equals(other.tMoviesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.movie.TMovies[ tMoviesPK=" + tMoviesPK + " ]";
    }
    
}
