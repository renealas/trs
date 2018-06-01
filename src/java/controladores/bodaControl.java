package controladores;

import entidades.Bodas;
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
import modelo.BodasFacade;
import modelo.CultoFacade;
import modelo.IglesiaFacade;

@ManagedBean(name = "bodaControl")
@SessionScoped
public class bodaControl implements Serializable {

    @EJB
    private BodasFacade bodasFacade;

    @EJB
    private CultoFacade cultoFacade;

    @EJB
    private IglesiaFacade iglesiaFacade;

    private List<Bodas> lstBoda;
    private List<Bodas> lstBodaAdmin;
    private List<Bodas> lstBodaFiltrada;
    private List<Culto> lstCulto;
    private List<Culto> lstCultoFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;
    private Bodas bodaSelected;
    private String accion;
    private Usuario user;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user = (Usuario) sessionMap.get("usuario");
        listarBoda();
        listarBodasAdmin();
        bodaSelected = new Bodas();
        listarIglesias();
        listarCultos();
    }

    public void listarCultos() {
        lstCulto = cultoFacade.listarCultosPorIglesia(bodaSelected.getIdiglesia());
    }

    public void listarIglesias() {
        lstIglesia = iglesiaFacade.findAll();
    }

    public String limpiarObjeto() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        bodaSelected = new Bodas();
        user = (Usuario) sessionMap.get("usuario");
        bodaSelected.setIdusuario(user);
        bodaSelected.setIdiglesia(user.getIdiglesia());
        listarIglesias();
        listarCultos();
        return "frmBoda.xhtml?faces-redirect=true";
    }

    public void borrarBoda() {
        try {
            bodasFacade.remove(bodaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstBoda = bodasFacade.listarBodasPorIglesia(user.getIdiglesia());
            } else {
                lstBodaAdmin = bodasFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Boda Borrada", "Informacion"));
            bodaSelected = new Bodas();
        } catch (Exception e) {
            System.out.println("Error al borrar la Boda " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarBoda();
        } else {
            return crearBoda();
        }
    }

    public String crearBoda() {
        try {
             if (user.getIdnivel().getIdnivel() > 2) {
                bodaSelected.setIdiglesia(user.getIdiglesia());
            }
            bodasFacade.create(bodaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
            lstBoda = bodasFacade.listarBodasPorIglesia(user.getIdiglesia());
            }else {
            lstBodaAdmin = bodasFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Boda Creada", "Informacion"));
            bodaSelected = new Bodas();
        } catch (Exception e) {
            System.out.println("Error al crear Boda " + e);
        }
        return "lstBoda.xhtml?faces-redirect=true";
    }

    public String modificarBoda() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                bodaSelected.setIdiglesia(user.getIdiglesia());
            }
            bodasFacade.edit(bodaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
            lstBoda = bodasFacade.listarBodasPorIglesia(user.getIdiglesia());
            }else {
            lstBodaAdmin = bodasFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Boda Modificada", "Informacion"));
            bodaSelected = new Bodas();
        } catch (Exception e) {
            System.out.println("Error al modificar Boda " + e);
        }
        return "lstBoda.xhtml?faces-redirect=true";
    }

    private void listarBoda() {
        lstBoda = bodasFacade.listarBodasPorIglesia(user.getIdiglesia());
    }

    private void listarBodasAdmin() {
        lstBodaAdmin = bodasFacade.findAll();
    }

    public List<Bodas> getLstBoda() {
        return lstBoda;
    }

    public void setLstBoda(List<Bodas> lstBoda) {
        this.lstBoda = lstBoda;
    }

    public List<Bodas> getLstBodaAdmin() {
        return lstBodaAdmin;
    }

    public void setLstBodaAdmin(List<Bodas> lstBodaAdmin) {
        this.lstBodaAdmin = lstBodaAdmin;
    }

    public List<Bodas> getLstBodaFiltrada() {
        return lstBodaFiltrada;
    }

    public void setLstBodaFiltrada(List<Bodas> lstBodaFiltrada) {
        this.lstBodaFiltrada = lstBodaFiltrada;
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

    public Bodas getBodaSelected() {
        return bodaSelected;
    }

    public void setBodaSelected(Bodas bodaSelected) {
        this.bodaSelected = bodaSelected;
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
