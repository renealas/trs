/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rene Alas
 */
@Entity
@Table(name = "bautismos", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Bautismos.findAll", query = "SELECT b FROM Bautismos b")})
public class Bautismos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBAUTISMOS")
    private Integer idbautismos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BAUTISMOS")
    private int bautismos;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Bautismos() {
    }

    public Bautismos(Integer idbautismos) {
        this.idbautismos = idbautismos;
    }

    public Bautismos(Integer idbautismos, Date fecha, int bautismos) {
        this.idbautismos = idbautismos;
        this.fecha = fecha;
        this.bautismos = bautismos;
    }

    public Integer getIdbautismos() {
        return idbautismos;
    }

    public void setIdbautismos(Integer idbautismos) {
        this.idbautismos = idbautismos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getBautismos() {
        return bautismos;
    }

    public void setBautismos(int bautismos) {
        this.bautismos = bautismos;
    }

    public Iglesia getIdiglesia() {
        return idiglesia;
    }

    public void setIdiglesia(Iglesia idiglesia) {
        this.idiglesia = idiglesia;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbautismos != null ? idbautismos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bautismos)) {
            return false;
        }
        Bautismos other = (Bautismos) object;
        if ((this.idbautismos == null && other.idbautismos != null) || (this.idbautismos != null && !this.idbautismos.equals(other.idbautismos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bautismos[ idbautismos=" + idbautismos + " ]";
    }
    
}
