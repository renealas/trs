package controladores;

import entidades.Conversiones;
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
import modelo.ConversionesFacade;
import modelo.CultoFacade;
import modelo.IglesiaFacade;

@ManagedBean(name = "conversionControl")
@SessionScoped
public class conversionControl implements Serializable {

    @EJB
    private ConversionesFacade conversionesFacade;

    @EJB
    private CultoFacade cultoFacade;

    @EJB
    private IglesiaFacade iglesiaFacade;

    private List<Conversiones> lstConversion;
    private List<Conversiones> lstConversionAdmin;
    private List<Conversiones> lstConversionFiltrada;
    private List<Culto> lstCulto;
    private List<Culto> lstCultoFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;
    private Conversiones conversionSelected;
    private String accion;
    private Usuario user;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user = (Usuario) sessionMap.get("usuario");
        listarConversion();
        listarConversionesAdmin();
        conversionSelected = new Conversiones();
        listarIglesias();
        listarCultos();
    }

    public void listarCultos() {
        lstCulto = cultoFacade.listarCultosPorIglesia(conversionSelected.getIdiglesia());
    }

    public void listarIglesias() {
        lstIglesia = iglesiaFacade.findAll();
    }

    public String limpiarObjeto() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        conversionSelected = new Conversiones();
        user = (Usuario) sessionMap.get("usuario");
        conversionSelected.setIdusuario(user);
        conversionSelected.setIdiglesia(user.getIdiglesia());
        listarIglesias();
        listarCultos();
        return "frmConversion.xhtml?faces-redirect=true";
    }

    public void borrarConversion() {
        try {
            conversionesFacade.remove(conversionSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstConversion = conversionesFacade.listarConversionesPorIglesia(user.getIdiglesia());
            } else {
                lstConversionAdmin = conversionesFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Conversion Borrada", "Informacion"));
            conversionSelected = new Conversiones();
        } catch (Exception e) {
            System.out.println("Error al borrar la Conversion " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarConversion();
        } else {
            return crearConversion();
        }
    }

    public String crearConversion() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                conversionSelected.setIdiglesia(user.getIdiglesia());
            }
            conversionesFacade.create(conversionSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstConversion = conversionesFacade.listarConversionesPorIglesia(user.getIdiglesia());
            } else {
                lstConversionAdmin = conversionesFacade.findAll();
            }            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Conversion Creada", "Informacion"));
            conversionSelected = new Conversiones();
        } catch (Exception e) {
            System.out.println("Error al crear Conversion " + e);
        }
        return "lstConversion.xhtml?faces-redirect=true";
    }

    public String modificarConversion() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                conversionSelected.setIdiglesia(user.getIdiglesia());
            }
            conversionesFacade.edit(conversionSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstConversion = conversionesFacade.listarConversionesPorIglesia(user.getIdiglesia());
            } else {
                lstConversionAdmin = conversionesFacade.findAll();
            } 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Conversion Modificada", "Informacion"));
            conversionSelected = new Conversiones();
        } catch (Exception e) {
            System.out.println("Error al modificar Conversion " + e);
        }
        return "lstConversion.xhtml?faces-redirect=true";
    }

    private void listarConversion() {
        lstConversion = conversionesFacade.listarConversionesPorIglesia(user.getIdiglesia());
    }

    private void listarConversionesAdmin() {
        lstConversionAdmin = conversionesFacade.findAll();
    }

    public List<Conversiones> getLstConversion() {
        return lstConversion;
    }

    public void setLstConversion(List<Conversiones> lstConversion) {
        this.lstConversion = lstConversion;
    }

    public List<Conversiones> getLstConversionAdmin() {
        return lstConversionAdmin;
    }

    public void setLstConversionAdmin(List<Conversiones> lstConversionAdmin) {
        this.lstConversionAdmin = lstConversionAdmin;
    }

    public List<Conversiones> getLstConversionFiltrada() {
        return lstConversionFiltrada;
    }

    public void setLstConversionFiltrada(List<Conversiones> lstConversionFiltrada) {
        this.lstConversionFiltrada = lstConversionFiltrada;
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

    public Conversiones getConversionSelected() {
        return conversionSelected;
    }

    public void setConversionSelected(Conversiones conversionSelected) {
        this.conversionSelected = conversionSelected;
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
