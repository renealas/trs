/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rene Alas
 */
@Entity
@Table(name = "iglesia", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Iglesia.findAll", query = "SELECT i FROM Iglesia i")})
public class Iglesia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDIGLESIA")
    private Integer idiglesia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 150)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 300)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 150)
    @Column(name = "FACEBOOK")
    private String facebook;
    @Size(max = 150)
    @Column(name = "TWITTER")
    private String twitter;
    @Size(max = 150)
    @Column(name = "PAGINAWEB")
    private String paginaweb;
    @Column(name = "FUNDACION")
    @Temporal(TemporalType.DATE)
    private Date fundacion;
    @Size(max = 150)
    @Column(name = "COMP")
    private String comp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Bautismos> bautismosList;
    @JoinColumn(name = "IDMUNICIPIO", referencedColumnName = "IDMUNICIPIO")
    @ManyToOne(optional = false)
    private Municipio idmunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Conversiones> conversionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Presentacion> presentacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Bodas> bodasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Asistencia> asistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Ofrenda> ofrendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiglesia")
    private List<Culto> cultoList;

    public Iglesia() {
    }

    public Iglesia(Integer idiglesia) {
        this.idiglesia = idiglesia;
    }

    public Iglesia(Integer idiglesia, String descripcion) {
        this.idiglesia = idiglesia;
        this.descripcion = descripcion;
    }

    public Integer getIdiglesia() {
        return idiglesia;
    }

    public void setIdiglesia(Integer idiglesia) {
        this.idiglesia = idiglesia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPaginaweb() {
        return paginaweb;
    }

    public void setPaginaweb(String paginaweb) {
        this.paginaweb = paginaweb;
    }

    public Date getFundacion() {
        return fundacion;
    }

    public void setFundacion(Date fundacion) {
        this.fundacion = fundacion;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public List<Bautismos> getBautismosList() {
        return bautismosList;
    }

    public void setBautismosList(List<Bautismos> bautismosList) {
        this.bautismosList = bautismosList;
    }

    public Municipio getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Municipio idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public List<Conversiones> getConversionesList() {
        return conversionesList;
    }

    public void setConversionesList(List<Conversiones> conversionesList) {
        this.conversionesList = conversionesList;
    }

    public List<Presentacion> getPresentacionList() {
        return presentacionList;
    }

    public void setPresentacionList(List<Presentacion> presentacionList) {
        this.presentacionList = presentacionList;
    }

    public List<Bodas> getBodasList() {
        return bodasList;
    }

    public void setBodasList(List<Bodas> bodasList) {
        this.bodasList = bodasList;
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

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Culto> getCultoList() {
        return cultoList;
    }

    public void setCultoList(List<Culto> cultoList) {
        this.cultoList = cultoList;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idiglesia != null ? idiglesia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Iglesia)) {
            return false;
        }
        Iglesia other = (Iglesia) object;
        if ((this.idiglesia == null && other.idiglesia != null) || (this.idiglesia != null && !this.idiglesia.equals(other.idiglesia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Iglesia[ idiglesia=" + idiglesia + " ]";
    }
    
}
