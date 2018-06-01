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
@Table(name = "conversiones", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Conversiones.findAll", query = "SELECT c FROM Conversiones c")})
public class Conversiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCONVERSIONES")
    private Integer idconversiones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONVERSIONES")
    private int conversiones;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "IDCULTO", referencedColumnName = "IDCULTO")
    @ManyToOne(optional = false)
    private Culto idculto;

    public Conversiones() {
    }

    public Conversiones(Integer idconversiones) {
        this.idconversiones = idconversiones;
    }

    public Conversiones(Integer idconversiones, Date fecha, int conversiones) {
        this.idconversiones = idconversiones;
        this.fecha = fecha;
        this.conversiones = conversiones;
    }

    public Integer getIdconversiones() {
        return idconversiones;
    }

    public void setIdconversiones(Integer idconversiones) {
        this.idconversiones = idconversiones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getConversiones() {
        return conversiones;
    }

    public void setConversiones(int conversiones) {
        this.conversiones = conversiones;
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

    public Culto getIdculto() {
        return idculto;
    }

    public void setIdculto(Culto idculto) {
        this.idculto = idculto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconversiones != null ? idconversiones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversiones)) {
            return false;
        }
        Conversiones other = (Conversiones) object;
        if ((this.idconversiones == null && other.idconversiones != null) || (this.idconversiones != null && !this.idconversiones.equals(other.idconversiones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Conversiones[ idconversiones=" + idconversiones + " ]";
    }
    
}
