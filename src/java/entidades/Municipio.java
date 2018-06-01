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
@Table(name = "municipio", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMUNICIPIO")
    private Integer idmunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "IDDEPARTAMENTO", referencedColumnName = "IDDEPARTAMENTO")
    @ManyToOne(optional = false)
    private Departamentos iddepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmunicipio")
    private List<Iglesia> iglesiaList;

    public Municipio() {
    }

    public Municipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Municipio(Integer idmunicipio, String descripcion) {
        this.idmunicipio = idmunicipio;
        this.descripcion = descripcion;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Departamentos getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamentos iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public List<Iglesia> getIglesiaList() {
        return iglesiaList;
    }

    public void setIglesiaList(List<Iglesia> iglesiaList) {
        this.iglesiaList = iglesiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmunicipio != null ? idmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idmunicipio == null && other.idmunicipio != null) || (this.idmunicipio != null && !this.idmunicipio.equals(other.idmunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Municipio[ idmunicipio=" + idmunicipio + " ]";
    }
    
}
