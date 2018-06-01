package controladores;

import entidades.Culto;
import entidades.Iglesia;
import entidades.Presentacion;
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
import modelo.CultoFacade;
import modelo.IglesiaFacade;
import modelo.PresentacionFacade;

@ManagedBean(name = "presentacionControl")
@SessionScoped
public class presentacionControl implements Serializable {

    @EJB
    private PresentacionFacade presentacionFacade;

    @EJB
    private CultoFacade cultoFacade;
    
    @EJB
    private IglesiaFacade iglesiaFacade;

    private List<Presentacion> lstPresentacion;
    private List<Presentacion> lstPresentacionAdmin;
    private List<Presentacion> lstPresentacionFiltrada;
    private List<Culto> lstCulto;
    private List<Culto> lstCultoFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;
    private Presentacion presentacionSelected;
    private String accion;
    private Usuario user;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user = (Usuario) sessionMap.get("usuario");
        listarPresentacion();
        listarPresentacionAdmin();
        presentacionSelected = new Presentacion();
        listarIglesias();
        listarCultos();        
    }

    public void listarCultos() {
        lstCulto = cultoFacade.listarCultosPorIglesia(user.getIdiglesia());
    }

    public void listarIglesias() {
        lstIglesia = iglesiaFacade.findAll();
    }

    public String limpiarObjeto() {
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        presentacionSelected = new Presentacion();
        user = (Usuario) sessionMap.get("usuario");
        presentacionSelected.setIdusuario(user);
        presentacionSelected.setIdiglesia(user.getIdiglesia());
        listarIglesias();
        listarCultos();
        return "frmPresentacion.xhtml?faces-redirect=true";
    }

    public void borrarPresentacion() {
        try {
            presentacionFacade.remove(presentacionSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstPresentacion = presentacionFacade.listarPresentacionPorIglesia(user.getIdiglesia());
            } else {
                lstPresentacionAdmin = presentacionFacade.findAll();
            }            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Presentacion Borrada", "Informacion"));
            presentacionSelected = new Presentacion();
        } catch (Exception e) {
            System.out.println("Error al borrar la Presentacion " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarPresentacion();
        } else {
            return crearPresentacion();
        }
    }

    public String crearPresentacion() {
        try {
           if (user.getIdnivel().getIdnivel() > 2) {
                presentacionSelected.setIdiglesia(user.getIdiglesia());
            }
            presentacionFacade.create(presentacionSelected);
             if (user.getIdnivel().getIdnivel() > 2) {
                lstPresentacion = presentacionFacade.listarPresentacionPorIglesia(user.getIdiglesia());
            } else {
                lstPresentacionAdmin = presentacionFacade.findAll();
            }            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Presentacion Creada", "Informacion"));
            presentacionSelected = new Presentacion();
        } catch (Exception e) {
            System.out.println("Error al crear Presentacion " + e);
        }
        return "lstPresentacion.xhtml?faces-redirect=true";
    }

    public String modificarPresentacion() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                presentacionSelected.setIdiglesia(user.getIdiglesia());
            }
            presentacionFacade.edit(presentacionSelected);
             if (user.getIdnivel().getIdnivel() > 2) {
                lstPresentacion = presentacionFacade.listarPresentacionPorIglesia(user.getIdiglesia());
            } else {
                lstPresentacionAdmin = presentacionFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "presentacion Modificada", "Informacion"));
            presentacionSelected = new Presentacion();
        } catch (Exception e) {
            System.out.println("Error al modificar Presentacion " + e);
        }
        return "lstPresentacion.xhtml?faces-redirect=true";
    }

    private void listarPresentacion() {
        lstPresentacion = presentacionFacade.listarPresentacionPorIglesia(user.getIdiglesia());
    }
    
    private void listarPresentacionAdmin() {
        lstPresentacionAdmin = presentacionFacade.findAll();
    }

    public List<Presentacion> getLstPresentacion() {
        return lstPresentacion;
    }

    public void setLstPresentacion(List<Presentacion> lstPresentacion) {
        this.lstPresentacion = lstPresentacion;
    }

    public List<Presentacion> getLstPresentacionAdmin() {
        return lstPresentacionAdmin;
    }

    public void setLstPresentacionAdmin(List<Presentacion> lstPresentacionAdmin) {
        this.lstPresentacionAdmin = lstPresentacionAdmin;
    }

    public List<Presentacion> getLstPresentacionFiltrada() {
        return lstPresentacionFiltrada;
    }

    public void setLstPresentacionFiltrada(List<Presentacion> lstPresentacionFiltrada) {
        this.lstPresentacionFiltrada = lstPresentacionFiltrada;
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

    public Presentacion getPresentacionSelected() {
        return presentacionSelected;
    }

    public void setPresentacionSelected(Presentacion presentacionSelected) {
        this.presentacionSelected = presentacionSelected;
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
    
    
}    

