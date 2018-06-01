/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rene Alas
 */
@Entity
@Table(name = "menu", catalog = "trs", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findByIdmenu", query = "SELECT m FROM Menu m WHERE m.idmenu = :idmenu")
    , @NamedQuery(name = "Menu.findByLabel", query = "SELECT m FROM Menu m WHERE m.label = :label")
    , @NamedQuery(name = "Menu.findByTarget", query = "SELECT m FROM Menu m WHERE m.target = :target")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMENU")
    private Integer idmenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LABEL")
    private String label;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 175)
    @Column(name = "TARGET")
    private String target;
    @ManyToMany(mappedBy = "menuList")
    private List<Nivel> nivelList;
    @OneToMany(mappedBy = "idparent")
    private List<Menu> menuList;
    @JoinColumn(name = "IDPARENT", referencedColumnName = "IDMENU")
    @ManyToOne
    private Menu idparent;

    public Menu() {
    }

    public Menu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Menu(Integer idmenu, String label, String target) {
        this.idmenu = idmenu;
        this.label = label;
        this.target = target;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @XmlTransient
    public List<Nivel> getNivelList() {
        return nivelList;
    }

    public void setNivelList(List<Nivel> nivelList) {
        this.nivelList = nivelList;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getIdparent() {
        return idparent;
    }

    public void setIdparent(Menu idparent) {
        this.idparent = idparent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Menu[ idmenu=" + idmenu + " ]";
    }
    
}
