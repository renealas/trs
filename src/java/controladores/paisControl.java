package controladores;

import entidades.Pais;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.PaisFacade;
import org.apache.commons.lang3.text.WordUtils;

@ManagedBean(name = "paisControl")
@SessionScoped
public class paisControl implements Serializable {

    @EJB
    private PaisFacade paisFacade;

    private List<Pais> lstPais;
    private List<Pais> lstPaisFiltrada;
    private Pais paisSelected;
    private String accion;

    @PostConstruct
    public void init() {
        listarPaises();
        paisSelected = new Pais();
    }
    
    public String limpiarObjeto() {
        paisSelected = new Pais();
        return "frmPais.xhtml?faces-redirect=true";
    }

    public void borrarPais() {
        try {
            paisFacade.remove(paisSelected);
            lstPais = paisFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais Borrado", "Informacion"));
            paisSelected = new Pais();
        } catch (Exception e) {
            System.out.println("Error al borrar el Pais " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return
            modificarPais();
        } else {
            return
            crearPais();
        }
    }

    public String crearPais() {
        try {
            paisSelected.setDescripcion(WordUtils.capitalizeFully(paisSelected.getDescripcion()));
            paisFacade.create(paisSelected);
            lstPais = paisFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais Creado", "Informacion"));
            paisSelected = new Pais();
        } catch (Exception e) {
            System.out.println("Error al crear Pais " + e);
        }
        return "lstPais.xhtml?faces-redirect=true";
    }

    public String modificarPais() {
        try {
            paisSelected.setDescripcion(WordUtils.capitalizeFully(paisSelected.getDescripcion()));
            paisFacade.edit(paisSelected);
            lstPais = paisFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais Modificado", "Informacion"));
            paisSelected = new Pais();
        } catch (Exception e) {
            System.out.println("Error al modificar el Pais " + e);
        }
        return "lstPais.xhtml?faces-redirect=true";
    }

    private void listarPaises() {
        lstPais = paisFacade.findAll();
        
    }

    public List<Pais> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<Pais> lstPais) {
        this.lstPais = lstPais;
    }

    public List<Pais> getLstPaisFiltrada() {
        return lstPaisFiltrada;
    }

    public void setLstPaisFiltrada(List<Pais> lstPaisFiltrada) {
        this.lstPaisFiltrada = lstPaisFiltrada;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
