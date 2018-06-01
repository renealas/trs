package controladores;

import entidades.Menu;
import entidades.Usuario;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.MenuFacade;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name = "menuControl")
@SessionScoped
public class menuControl implements Serializable {

    @EJB
    private MenuFacade menuFacade;

    private List<Menu> lista;
    private MenuModel model;

    @PostConstruct
    public void init() {
        this.listarMenus();
        model = new DefaultMenuModel();
        this.establecerPermisos();
    }

    public void listarMenus() {
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");        
             lista = us.getIdnivel().getMenuList();
        } catch (Exception e) {
            // mensaje jsf
        }
    }

    public void establecerPermisos() {
        for (Menu m : lista) {
            if (m.getIdparent() == null) {
                if (!(m.getTarget()== null || m.getTarget().equals(""))){
                    DefaultMenuItem item = new DefaultMenuItem(m.getLabel());
                    item.setUrl(m.getTarget());
                    model.addElement(item);
                } else {
                    DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getLabel());
                    for (Menu i : lista) {
                        Menu submenu = i.getIdparent();
                        if (submenu != null) {
                            if (i.getIdparent().getIdmenu() == m.getIdmenu()) {
                                DefaultMenuItem item = new DefaultMenuItem(i.getLabel());
                                item.setUrl(i.getTarget());
                                firstSubmenu.addElement(item);
                            }
                        }
                    }
                    model.addElement(firstSubmenu);
                }
            } 
        }
    }

    public List<Menu> getLista() {
        return lista;
    }

    public void setLista(List<Menu> lista) {
        this.lista = lista;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

}
