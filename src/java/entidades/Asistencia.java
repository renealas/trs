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
@Table(name = "asistencia", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
    , @NamedQuery(name = "Asistencia.findByIdasistencia", query = "SELECT a FROM Asistencia a WHERE a.idasistencia = :idasistencia")
    , @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Asistencia.findByAsistenciaadultos", query = "SELECT a FROM Asistencia a WHERE a.asistenciaadultos = :asistenciaadultos")
    , @NamedQuery(name = "Asistencia.findByAsistencianinos", query = "SELECT a FROM Asistencia a WHERE a.asistencianinos = :asistencianinos")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDASISTENCIA")
    private Integer idasistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASISTENCIAADULTOS")
    private int asistenciaadultos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASISTENCIANINOS")
    private int asistencianinos;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;
    @JoinColumn(name = "IDCULTO", referencedColumnName = "IDCULTO")
    @ManyToOne(optional = false)
    private Culto idculto;

    public Asistencia() {
    }

    public Asistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }

    public Asistencia(Integer idasistencia, Date fecha, int asistenciaadultos, int asistencianinos) {
        this.idasistencia = idasistencia;
        this.fecha = fecha;
        this.asistenciaadultos = asistenciaadultos;
        this.asistencianinos = asistencianinos;
    }

    public Integer getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAsistenciaadultos() {
        return asistenciaadultos;
    }

    public void setAsistenciaadultos(int asistenciaadultos) {
        this.asistenciaadultos = asistenciaadultos;
    }

    public int getAsistencianinos() {
        return asistencianinos;
    }

    public void setAsistencianinos(int asistencianinos) {
        this.asistencianinos = asistencianinos;
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
        hash += (idasistencia != null ? idasistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idasistencia == null && other.idasistencia != null) || (this.idasistencia != null && !this.idasistencia.equals(other.idasistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Asistencia[ idasistencia=" + idasistencia + " ]";
    }
    
}
