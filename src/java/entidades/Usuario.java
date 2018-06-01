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
import javax.persistence.Lob;
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
@Table(name = "usuario", catalog = "trs", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    @Lob
    @Column(name = "FOTO")
    private byte[] foto;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DUI")
    private String dui;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHANAC")
    @Temporal(TemporalType.DATE)
    private Date fechanac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "GRADUADOTEO")
    private String graduadoteo;
    @Size(max = 150)
    @Column(name = "ESCUELATEO")
    private String escuelateo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "ESTADOCIVIL")
    private String estadocivil;
    @Size(max = 300)
    @Column(name = "CONYUGE")
    private String conyuge;
    @Size(max = 50)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 50)
    @Column(name = "CELULAR")
    private String celular;
    @Size(max = 50)
    @Column(name = "NIT")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "ESTADOACAD")
    private String estadoacad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "PROFESION")
    private String profesion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "EMPLEADO")
    private String empleado;
    @Size(max = 150)
    @Column(name = "LUGAREMPLEO")
    private String lugarempleo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "USER")
    private String user;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Bautismos> bautismosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Conversiones> conversionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Presentacion> presentacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Bodas> bodasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Asistencia> asistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Ofrenda> ofrendaList;
    @JoinColumn(name = "IDIGLESIA", referencedColumnName = "IDIGLESIA")
    @ManyToOne(optional = false)
    private Iglesia idiglesia;
    @JoinColumn(name = "IDNIVEL", referencedColumnName = "IDNIVEL")
    @ManyToOne(optional = false)
    private Nivel idnivel;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String nombre, String apellido, String dui, Date fechanac, String graduadoteo, String estadocivil, String nacionalidad, String estadoacad, String profesion, String empleado, String user, String password) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.fechanac = fechanac;
        this.graduadoteo = graduadoteo;
        this.estadocivil = estadocivil;
        this.nacionalidad = nacionalidad;
        this.estadoacad = estadoacad;
        this.profesion = profesion;
        this.empleado = empleado;
        this.user = user;
        this.password = password;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getGraduadoteo() {
        return graduadoteo;
    }

    public void setGraduadoteo(String graduadoteo) {
        this.graduadoteo = graduadoteo;
    }

    public String getEscuelateo() {
        return escuelateo;
    }

    public void setEscuelateo(String escuelateo) {
        this.escuelateo = escuelateo;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getConyuge() {
        return conyuge;
    }

    public void setConyuge(String conyuge) {
        this.conyuge = conyuge;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoacad() {
        return estadoacad;
    }

    public void setEstadoacad(String estadoacad) {
        this.estadoacad = estadoacad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getLugarempleo() {
        return lugarempleo;
    }

    public void setLugarempleo(String lugarempleo) {
        this.lugarempleo = lugarempleo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Bautismos> getBautismosList() {
        return bautismosList;
    }

    public void setBautismosList(List<Bautismos> bautismosList) {
        this.bautismosList = bautismosList;
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

    public Iglesia getIdiglesia() {
        return idiglesia;
    }

    public void setIdiglesia(Iglesia idiglesia) {
        this.idiglesia = idiglesia;
    }

    public Nivel getIdnivel() {
        return idnivel;
    }

    public void setIdnivel(Nivel idnivel) {
        this.idnivel = idnivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ idusuario=" + idusuario + " ]";
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
}
