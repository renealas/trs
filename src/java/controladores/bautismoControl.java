package controladores;

import entidades.Bautismos;
import entidades.Culto;
import entidades.Iglesia;
import entidades.Usuario;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.BautismosFacade;
import modelo.CultoFacade;
import modelo.IglesiaFacade;

@ManagedBean(name = "bautismoControl")
@SessionScoped
public class bautismoControl implements Serializable {

    @EJB
    private BautismosFacade bautismoFacade;

    @EJB
    private CultoFacade cultoFacade;

    @EJB
    private IglesiaFacade iglesiaFacade;

    private List<Bautismos> lstBautismo;
    private List<Bautismos> lstBautismosAdmin;
    private List<Bautismos> lstBautismoFiltrada;
    private List<Culto> lstCulto;
    private List<Culto> lstCultoFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;

    private Bautismos bautismoSelected;
    private String accion;
    private Usuario user;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user = (Usuario) sessionMap.get("usuario");
        listarBautismos();
        listarBautimosAdmin();
        bautismoSelected = new Bautismos();
        listarIglesias();
        listarCultos();
    }

    public void listarCultos() {
        lstCulto = cultoFacade.listarCultosPorIglesia(bautismoSelected.getIdiglesia());
    }

    public void listarIglesias() {
        lstIglesia = iglesiaFacade.findAll();
    }

    public String limpiarObjeto() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        bautismoSelected = new Bautismos();
        user = (Usuario) sessionMap.get("usuario");
        bautismoSelected.setIdusuario(user);
        bautismoSelected.setIdiglesia(user.getIdiglesia());
        listarIglesias();
        return "frmBautismo.xhtml?faces-redirect=true";
    }

    public void borrarBautismos() {
        try {
            bautismoFacade.remove(bautismoSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
            lstBautismo = bautismoFacade.listarBautismoPorIglesia(user.getIdiglesia());
            }else {
                lstBautismosAdmin = bautismoFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bautismo Borrado", "Informacion"));
            bautismoSelected = new Bautismos();
        } catch (Exception e) {
            System.out.println("Error al borrar el Bautismo " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarBautismo();
        } else {
            return crearBautismo();
        }
    }

    public String crearBautismo() {
        try {
             if (user.getIdnivel().getIdnivel() > 2) {
                bautismoSelected.setIdiglesia(user.getIdiglesia());
            }
            bautismoFacade.create(bautismoSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
            lstBautismo = bautismoFacade.listarBautismoPorIglesia(user.getIdiglesia());
             } else {
                lstBautismosAdmin = bautismoFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asistencia Creada", "Informacion"));
            bautismoSelected = new Bautismos();
        } catch (Exception e) {
            System.out.println("Error al crear Bautismo " + e);
        }
        return "lstBautismo.xhtml?faces-redirect=true";
    }

    public String modificarBautismo() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                bautismoSelected.setIdiglesia(user.getIdiglesia());
            }
            bautismoFacade.edit(bautismoSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
            lstBautismo = bautismoFacade.listarBautismoPorIglesia(user.getIdiglesia());
             } else {
                lstBautismosAdmin = bautismoFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bautismo Modificado", "Informacion"));
            bautismoSelected = new Bautismos();
        } catch (Exception e) {
            System.out.println("Error al modificar Bautismo " + e);
        }
        return "lstBautismo.xhtml?faces-redirect=true";
    }

    private void listarBautismos() {
        lstBautismo = bautismoFacade.listarBautismoPorIglesia(user.getIdiglesia());
    }
    
     private void listarBautimosAdmin() {
        lstBautismosAdmin = bautismoFacade.findAll();
    }


    public List<Bautismos> getLstBautismo() {
        return lstBautismo;
    }

    public void setLstBautismo(List<Bautismos> lstBautismo) {
        this.lstBautismo = lstBautismo;
    }

    public List<Bautismos> getLstBautismoFiltrada() {
        return lstBautismoFiltrada;
    }

    public void setLstBautismoFiltrada(List<Bautismos> lstBautismoFiltrada) {
        this.lstBautismoFiltrada = lstBautismoFiltrada;
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

    public Bautismos getBautismoSelected() {
        return bautismoSelected;
    }

    public void setBautismoSelected(Bautismos bautismoSelected) {
        this.bautismoSelected = bautismoSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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

    public List<Bautismos> getLstBautismosAdmin() {
        return lstBautismosAdmin;
    }

    public void setLstBautismosAdmin(List<Bautismos> lstBautismosAdmin) {
        this.lstBautismosAdmin = lstBautismosAdmin;
    }

}
