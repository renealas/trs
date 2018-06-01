package controladores;

import entidades.Asistencia;
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
import modelo.AsistenciaFacade;
import modelo.CultoFacade;
import modelo.IglesiaFacade;

@ManagedBean(name = "asistenciaControl")
@SessionScoped
public class asistenciaControl implements Serializable {

    @EJB
    private AsistenciaFacade asistenciaFacade;

    @EJB
    private CultoFacade cultoFacade;
    
    @EJB
    private IglesiaFacade iglesiaFacade;

    private List<Asistencia> lstAsistencia;
    private List<Asistencia> lstAsistenciaAdmin;
    private List<Asistencia> lstAsistenciaFiltrada;
    private List<Culto> lstCulto;
    private List<Culto> lstCultoFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;
    private Asistencia asistenciaSelected;
    private String accion;
    private Usuario user;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user = (Usuario) sessionMap.get("usuario");
        listarAsistencias();
        listarAsistenciasAdmin();
        asistenciaSelected = new Asistencia();
        asistenciaSelected.setIdusuario(user);
        asistenciaSelected.setIdiglesia(user.getIdiglesia());
        listarIglesias();
        listarCultos();
       }

    public void listarCultos() {
        lstCulto = cultoFacade.listarCultosPorIglesia(asistenciaSelected.getIdiglesia());
    }

    public void listarIglesias() {
        lstIglesia = iglesiaFacade.findAll();
    }
    
    public String limpiarObjeto() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        asistenciaSelected = new Asistencia();
        user = (Usuario) sessionMap.get("usuario");
        asistenciaSelected.setIdusuario(user);
        asistenciaSelected.setIdiglesia(user.getIdiglesia());
        listarIglesias();
        listarCultos();
        return "frmAsistencia.xhtml?faces-redirect=true";
    }

    public void borrarAsistencia() {
        try {
            asistenciaFacade.remove(asistenciaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstAsistencia = asistenciaFacade.listarAsistenciaPorIglesia(user.getIdiglesia());
            } else {
                lstAsistenciaAdmin = asistenciaFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asistencia Borrada", "Informacion"));
            asistenciaSelected = new Asistencia();
        } catch (Exception e) {
            System.out.println("Error al borrar la Asistencia " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarAsistencia();
        } else {
            return crearAsistencia();
        }
    }

    public String crearAsistencia() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                asistenciaSelected.setIdiglesia(user.getIdiglesia());
            }
            asistenciaFacade.create(asistenciaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstAsistencia = asistenciaFacade.listarAsistenciaPorIglesia(user.getIdiglesia());
            } else {
                lstAsistenciaAdmin = asistenciaFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asistencia Creada", "Informacion"));
            asistenciaSelected = new Asistencia();
        } catch (Exception e) {
            System.out.println("Error al crear Asistencia " + e);
        }
        return "lstAsistencia.xhtml?faces-redirect=true";
    }

    public String modificarAsistencia() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                asistenciaSelected.setIdiglesia(user.getIdiglesia());
            }
            asistenciaFacade.edit(asistenciaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstAsistencia = asistenciaFacade.listarAsistenciaPorIglesia(user.getIdiglesia());
            } else {
                lstAsistenciaAdmin = asistenciaFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Asistencia Modificada", "Informacion"));
            asistenciaSelected = new Asistencia();
        } catch (Exception e) {
            System.out.println("Error al modificar Asistencia " + e);
        }
        return "lstAsistencia.xhtml?faces-redirect=true";
    }

    private void listarAsistencias() {
        lstAsistencia = asistenciaFacade.listarAsistenciaPorIglesia(user.getIdiglesia());
    }

    private void listarAsistenciasAdmin() {
        lstAsistenciaAdmin = asistenciaFacade.findAll();
    }

    public List<Asistencia> getLstAsistencia() {
        return lstAsistencia;
    }

    public void setLstAsistencia(List<Asistencia> lstAsistencia) {
        this.lstAsistencia = lstAsistencia;
    }

    public List<Asistencia> getLstAsistenciaFiltrada() {
        return lstAsistenciaFiltrada;
    }

    public void setLstAsistenciaFiltrada(List<Asistencia> lstAsistenciaFiltrada) {
        this.lstAsistenciaFiltrada = lstAsistenciaFiltrada;
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

    public Asistencia getAsistenciaSelected() {
        return asistenciaSelected;
    }

    public void setAsistenciaSelected(Asistencia asistenciaSelected) {
        this.asistenciaSelected = asistenciaSelected;
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

    public List<Asistencia> getLstAsistenciaAdmin() {
        return lstAsistenciaAdmin;
    }

    public void setLstAsistenciaAdmin(List<Asistencia> lstAsistenciaAdmin) {
        this.lstAsistenciaAdmin = lstAsistenciaAdmin;
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
