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
@Table(name = "presentacion", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Presentacion.findAll", query = "SELECT p FROM Presentacion p")})
public class Presentacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRESENTACION")
    private Integer idpresentacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NINOSPRESENT")
    private int ninospresent;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;

    public Presentacion() {
    }

    public Presentacion(Integer idpresentacion) {
        this.idpresentacion = idpresentacion;
    }

    public Presentacion(Integer idpresentacion, Date fecha, int ninospresent) {
        this.idpresentacion = idpresentacion;
        this.fecha = fecha;
        this.ninospresent = ninospresent;
    }

    public Integer getIdpresentacion() {
        return idpresentacion;
    }

    public void setIdpresentacion(Integer idpresentacion) {
        this.idpresentacion = idpresentacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNinospresent() {
        return ninospresent;
    }

    public void setNinospresent(int ninospresent) {
        this.ninospresent = ninospresent;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Iglesia getIdiglesia() {
        return idiglesia;
    }

    public void setIdiglesia(Iglesia idiglesia) {
        this.idiglesia = idiglesia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpresentacion != null ? idpresentacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presentacion)) {
            return false;
        }
        Presentacion other = (Presentacion) object;
        if ((this.idpresentacion == null && other.idpresentacion != null) || (this.idpresentacion != null && !this.idpresentacion.equals(other.idpresentacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Presentacion[ idpresentacion=" + idpresentacion + " ]";
    }
    
}
