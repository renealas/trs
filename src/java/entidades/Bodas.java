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
@Table(name = "bodas", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Bodas.findAll", query = "SELECT b FROM Bodas b")})
public class Bodas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBODAS")
    private Integer idbodas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BODAS")
    private int bodas;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Bodas() {
    }

    public Bodas(Integer idbodas) {
        this.idbodas = idbodas;
    }

    public Bodas(Integer idbodas, Date fecha, int bodas) {
        this.idbodas = idbodas;
        this.fecha = fecha;
        this.bodas = bodas;
    }

    public Integer getIdbodas() {
        return idbodas;
    }

    public void setIdbodas(Integer idbodas) {
        this.idbodas = idbodas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getBodas() {
        return bodas;
    }

    public void setBodas(int bodas) {
        this.bodas = bodas;
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
        hash += (idbodas != null ? idbodas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodas)) {
            return false;
        }
        Bodas other = (Bodas) object;
        if ((this.idbodas == null && other.idbodas != null) || (this.idbodas != null && !this.idbodas.equals(other.idbodas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Bodas[ idbodas=" + idbodas + " ]";
    }
    
}
