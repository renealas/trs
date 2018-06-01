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
@Table(name = "zona", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Zona.findAll", query = "SELECT z FROM Zona z")})
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDZONA")
    private Integer idzona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idzona")
    private List<Departamentos> departamentosList;

    public Zona() {
    }

    public Zona(Integer idzona) {
        this.idzona = idzona;
    }

    public Zona(Integer idzona, String descripcion) {
        this.idzona = idzona;
        this.descripcion = descripcion;
    }

    public Integer getIdzona() {
        return idzona;
    }

    public void setIdzona(Integer idzona) {
        this.idzona = idzona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Departamentos> getDepartamentosList() {
        return departamentosList;
    }

    public void setDepartamentosList(List<Departamentos> departamentosList) {
        this.departamentosList = departamentosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idzona != null ? idzona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.idzona == null && other.idzona != null) || (this.idzona != null && !this.idzona.equals(other.idzona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Zona[ idzona=" + idzona + " ]";
    }
    
}
