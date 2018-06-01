package controladores;

import entidades.Culto;
import entidades.Iglesia;
import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.CultoFacade;
import modelo.IglesiaFacade;

@ManagedBean(name = "cultoControl")
@SessionScoped
public class cultoControl implements Serializable {

    @EJB
    private CultoFacade cultoFacade;
    @EJB
    private IglesiaFacade iglesiaFacade;

    private List<Culto> lstCulto;
    private List<Culto> lstCultoUser;
    private List<Culto> lstCultoFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;
    private Culto cultoSelected;
    private String accion;
    private Usuario user;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user = (Usuario) sessionMap.get("usuario");
        listarCultos();
        listarCultosUsuario();
        cultoSelected = new Culto();
        listarIglesia();
    }

    public void listarIglesia() {
        lstIglesia = iglesiaFacade.findAll();
    }

    public String limpiarObjeto() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        cultoSelected = new Culto();
        user = (Usuario) sessionMap.get("usuario");
        cultoSelected.setIdiglesia(user.getIdiglesia());
        listarCultos();
        listarCultosUsuario();
        return "frmCulto.xhtml?faces-redirect=true";
    }

    public void borrarCulto() {
        try {
            cultoFacade.remove(cultoSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstCultoUser = cultoFacade.listarCultosPorIglesia(user.getIdiglesia());
            } else {
                lstCulto = cultoFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Culto Borrado", "Informacion"));
            cultoSelected = new Culto();
        } catch (Exception e) {
            System.out.println("Error al borrar el Culto " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarCulto();
        } else {
            return crearCulto();
        }
    }

    public String crearCulto() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                cultoSelected.setIdiglesia(user.getIdiglesia());
            }
            cultoSelected.setDescripcion(cultoSelected.getDescripcion().toUpperCase());
            cultoFacade.create(cultoSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstCultoUser = cultoFacade.listarCultosPorIglesia(user.getIdiglesia());
            } else {
                lstCulto = cultoFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Culto Creado", "Informacion"));
            cultoSelected = new Culto();
        } catch (Exception e) {
            System.out.println("Error al crear Culto " + e);
        }
        return "lstCulto.xhtml?faces-redirect=true";
    }

    public String modificarCulto() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                cultoSelected.setIdiglesia(user.getIdiglesia());
            }
            cultoSelected.setDescripcion(cultoSelected.getDescripcion().toUpperCase());
            cultoFacade.edit(cultoSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstCultoUser = cultoFacade.listarCultosPorIglesia(user.getIdiglesia());
            } else {
                lstCulto = cultoFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Culto Modificado", "Informacion"));
            cultoSelected = new Culto();
        } catch (Exception e) {
            System.out.println("Error al modificar el Culto " + e);
        }
        return "lstCulto.xhtml?faces-redirect=true";
    }

    private void listarCultos() {
        lstCulto = cultoFacade.findAll();
    }

    private void listarCultosUsuario() {
        lstCultoUser = cultoFacade.listarCultosPorIglesia(user.getIdiglesia());
    }

    public List<Culto> getLstCulto() {
        return lstCulto;
    }

    public void setLstCulto(List<Culto> lstCulto) {
        this.lstCulto = lstCulto;
    }

    public List<Culto> getLstCultoFiltrada() {
        return lstCultoFiltrada;
    }

    public void setLstCultoFiltrada(List<Culto> lstCultoFiltrada) {
        this.lstCultoFiltrada = lstCultoFiltrada;
    }

    public Culto getCultoSelected() {
        return cultoSelected;
    }

    public void setCultoSelected(Culto cultoSelected) {
        this.cultoSelected = cultoSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Iglesia> getLstIglesia() {
        return lstIglesia;
    }

    public void setLstIglesia(List<Iglesia> lstIglesia) {
        this.lstIglesia = lstIglesia;
    }

    public List<Iglesia> getLstIglesiaFiltrada() {
        return lstIglesiaFiltrada;
    }

    public void setLstIglesiaFiltrada(List<Iglesia> lstIglesiaFiltrada) {
        this.lstIglesiaFiltrada = lstIglesiaFiltrada;
    }

    public List<Culto> getLstCultoUser() {
        return lstCultoUser;
    }

    public void setLstCultoUser(List<Culto> lstCultoUser) {
        this.lstCultoUser = lstCultoUser;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
