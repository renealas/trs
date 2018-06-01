/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rene Alas
 */
@Entity
@Table(name = "culto", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Culto.findAll", query = "SELECT c FROM Culto c")})
public class Culto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCULTO")
    private Integer idculto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idculto")
    private List<Conversiones> conversionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idculto")
    private List<Asistencia> asistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idculto")
    private List<Ofrenda> ofrendaList;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;

    public Culto() {
    }

    public Culto(Integer idculto) {
        this.idculto = idculto;
    }

    public Culto(Integer idculto, String descripcion) {
        this.idculto = idculto;
        this.descripcion = descripcion;
    }

    public Integer getIdculto() {
        return idculto;
    }

    public void setIdculto(Integer idculto) {
        this.idculto = idculto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Conversiones> getConversionesList() {
        return conversionesList;
    }

    public void setConversionesList(List<Conversiones> conversionesList) {
        this.conversionesList = conversionesList;
    }

    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    public List<Ofrenda> getOfrendaList() {
        return ofrendaList;
    }

    public void setOfrendaList(List<Ofrenda> ofrendaList) {
        this.ofrendaList = ofrendaList;
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
        hash += (idculto != null ? idculto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Culto)) {
            return false;
        }
        Culto other = (Culto) object;
        if ((this.idculto == null && other.idculto != null) || (this.idculto != null && !this.idculto.equals(other.idculto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Culto[ idculto=" + idculto + " ]";
    }
    
}
