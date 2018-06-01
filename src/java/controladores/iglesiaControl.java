package controladores;

import entidades.Iglesia;
import entidades.Municipio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.IglesiaFacade;
import modelo.MunicipioFacade;
import org.apache.commons.lang3.text.WordUtils;

@ManagedBean(name = "iglesiaControl")
@SessionScoped
public class iglesiaControl implements Serializable {

    @EJB
    private IglesiaFacade iglesiaFacade;
    @EJB
    private MunicipioFacade municipioFacade;

    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;
    private List<Municipio> lstMunicipio;
    private List<Municipio> lstMunicipioFiltrada;
    private Iglesia iglesiaSelected;
    private String accion;

    @PostConstruct
    public void init() {
        listarIglesias();
        iglesiaSelected = new Iglesia();
        listarMunicipios();
    }

    public void listarMunicipios() {
        lstMunicipio = municipioFacade.findAll();
    }

    public String limpiarObjeto() {
        iglesiaSelected = new Iglesia();
        return "frmIglesia.xhtml?faces-redirect=true";
    }

    public void borrarIglesia() {
        try {
            iglesiaFacade.remove(iglesiaSelected);
            lstIglesia = iglesiaFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iglesia Borrada", "Informacion"));
            iglesiaSelected = new Iglesia();
        } catch (Exception e) {
            System.out.println("Error al borrar la Iglesia " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarIglesia();
        } 
         if (accion.equals("ACTUALIZAR")) {
            return modificarIglesiaLog();
        }else {
            return crearIglesia();
        }
    }

    public String crearIglesia() {
        try {
            iglesiaSelected.setDescripcion(WordUtils.capitalizeFully(iglesiaSelected.getDescripcion()));
            iglesiaFacade.create(iglesiaSelected);
            lstIglesia = iglesiaFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iglesia Creada", "Informacion"));
            iglesiaSelected = new Iglesia();
        } catch (Exception e) {
            System.out.println("Error al crear la Iglesia " + e);
        }
        return "lstIglesia.xhtml?faces-redirect=true";
    }

    public String modificarIglesia() {
        try {
            iglesiaSelected.setDescripcion(WordUtils.capitalizeFully(iglesiaSelected.getDescripcion()));
            iglesiaFacade.edit(iglesiaSelected);
            lstIglesia = iglesiaFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iglesia Modificada", "Informacion"));
            iglesiaSelected = new Iglesia();
        } catch (Exception e) {
            System.out.println("Error al modificar la Iglesia " + e);
        }
        return "lstIglesia.xhtml?faces-redirect=true";
    }

    public String modificarIglesiaLog() {
        try {
            iglesiaFacade.edit(iglesiaSelected);
            lstIglesia = iglesiaFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iglesia Modificada", "Informacion"));
            iglesiaSelected = new Iglesia();
        } catch (Exception e) {
            System.out.println("Error al modificar la Iglesia " + e);
        }
        return "/faces/Catalogos/inicio.xhtml";
    }

    private void listarIglesias() {
        lstIglesia = iglesiaFacade.findAll();
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

    public List<Municipio> getLstMunicipio() {
        return lstMunicipio;
    }

    public void setLstMunicipio(List<Municipio> lstMunicipio) {
        this.lstMunicipio = lstMunicipio;
    }

    public List<Municipio> getLstMunicipioFiltrada() {
        return lstMunicipioFiltrada;
    }

    public void setLstMunicipioFiltrada(List<Municipio> lstMunicipioFiltrada) {
        this.lstMunicipioFiltrada = lstMunicipioFiltrada;
    }

    public Iglesia getIglesiaSelected() {
        return iglesiaSelected;
    }

    public void setIglesiaSelected(Iglesia iglesiaSelected) {
        this.iglesiaSelected = iglesiaSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
