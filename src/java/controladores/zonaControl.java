package controladores;

import entidades.Zona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.ZonaFacade;

@ManagedBean(name = "zonaControl")
@SessionScoped
public class zonaControl implements Serializable {

    @EJB
    private ZonaFacade zonaFacade;

    private List<Zona> lstZona;
    private List<Zona> lstZonaFiltrada;
    private Zona zonaSelected;
    private String accion;

    @PostConstruct
    public void init() {
        listarZonas();
        zonaSelected = new Zona();
    }

    public String limpiarObjeto() {
        zonaSelected = new Zona();
         return "frmZona.xhtml?faces-redirect=true";
    }

    public void borrarZona() {
        try {
            zonaFacade.remove(zonaSelected);
            lstZona = zonaFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zona Borrada", "Informacion"));
            zonaSelected = new Zona();
        } catch (Exception e) {
            System.out.println("Error al borrar la Zona " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return
            modificarZona();
        } else {
            return
            crearZona();
        }
    }

    public String crearZona() {
        try {
            zonaSelected.setDescripcion(zonaSelected.getDescripcion().toUpperCase());
            zonaFacade.create(zonaSelected);
            lstZona = zonaFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zona Creada", "Informacion"));
            zonaSelected = new Zona();
        } catch (Exception e) {
            System.out.println("Error al crear Zona " + e);
        }
        return "lstZona.xhtml?faces-redirect=true";
    }

    public String modificarZona() {
        try {
            zonaSelected.setDescripcion(zonaSelected.getDescripcion().toUpperCase());
            zonaFacade.edit(zonaSelected);
            lstZona = zonaFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zona Modificada", "Informacion"));
            zonaSelected = new Zona();
        } catch (Exception e) {
            System.out.println("Error al modificar la Zona " + e);
        }
        return "lstZona.xhtml?faces-redirect=true";
    }

    private void listarZonas() {
        lstZona = zonaFacade.findAll();
       
    }

    public List<Zona> getLstZona() {
        return lstZona;
    }

    public void setLstZona(List<Zona> lstZona) {
        this.lstZona = lstZona;
    }

    public List<Zona> getLstZonaFiltrada() {
        return lstZonaFiltrada;
    }

    public void setLstZonaFiltrada(List<Zona> lstZonaFiltrada) {
        this.lstZonaFiltrada = lstZonaFiltrada;
    }

    public Zona getZonaSelected() {
        return zonaSelected;
    }

    public void setZonaSelected(Zona zonaSelected) {
        this.zonaSelected = zonaSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
