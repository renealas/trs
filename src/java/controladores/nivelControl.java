package controladores;

import entidades.Nivel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.NivelFacade;
import org.apache.commons.lang3.text.WordUtils;

@ManagedBean(name = "nivelControl")
@SessionScoped
public class nivelControl implements Serializable {

    @EJB
    private NivelFacade nivelFacade;
    
    private List<Nivel> lstNivel;
    private List<Nivel> lstNivelFiltrada;
    private Nivel nivelSelected;
    private String accion;
    
    @PostConstruct
    public void init() {
        listarNiveles();
        nivelSelected = new Nivel();
    }

     public String limpiarObjeto(){
        nivelSelected = new Nivel();
        return "frmNivel.xhtml?faces-redirect=true";
    }
    
    public void borrarNivel(){
        try {
            nivelFacade.remove(nivelSelected);
            lstNivel = nivelFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nivel Borrado", "Informacion"));
            nivelSelected = new Nivel();     
        } catch (Exception e) {
            System.out.println("Error al borrar el Nivel " + e);
        }
    }

     public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return
            modificarNivel();
        } else {
            return
            crearNivel();
        }
    }

    public String crearNivel() {
        try {
            nivelSelected.setDescripcion(WordUtils.capitalizeFully(nivelSelected.getDescripcion()));
            nivelFacade.create(nivelSelected);
            lstNivel = nivelFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nivel Creado", "Informacion"));
            nivelSelected = new Nivel();
        } catch (Exception e) {
            System.out.println("Error al crear Nivel " + e);
        }
        return "lstNivel.xhtml?faces-redirect=true";
    }

    public String modificarNivel() {
        try {
            nivelSelected.setDescripcion(WordUtils.capitalizeFully(nivelSelected.getDescripcion()));
            nivelFacade.edit(nivelSelected);
            lstNivel = nivelFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nivel Modificado", "Informacion"));
            nivelSelected = new Nivel();
        } catch (Exception e) {
            System.out.println("Error al modificar el Nivel " + e);
        }
        return "lstNivel.xhtml?faces-redirect=true";
    }
    
    private void listarNiveles() {
      lstNivel = nivelFacade.findAll();
      
    }  

    public List<Nivel> getLstNivel() {
        return lstNivel;
    }

    public void setLstNivel(List<Nivel> lstNivel) {
        this.lstNivel = lstNivel;
    }

    public List<Nivel> getLstNivelFiltrada() {
        return lstNivelFiltrada;
    }

    public void setLstNivelFiltrada(List<Nivel> lstNivelFiltrada) {
        this.lstNivelFiltrada = lstNivelFiltrada;
    }

    public Nivel getNivelSelected() {
        return nivelSelected;
    }

    public void setNivelSelected(Nivel nivelSelected) {
        this.nivelSelected = nivelSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
}
