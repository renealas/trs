package controladores;

import entidades.Departamentos;
import entidades.Municipio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.DepartamentosFacade;
import modelo.MunicipioFacade;
import org.apache.commons.lang3.text.WordUtils;

@ManagedBean(name = "municipioControl")
@SessionScoped
public class municipioControl implements Serializable {

    @EJB
    private MunicipioFacade municipioFacade;
    @EJB
    private DepartamentosFacade departamentoFacade;
    
    private List<Municipio> lstMunicipio;
    private List<Municipio> lstMunicipioFiltrada;
    private List<Departamentos> lstDepartamento;
    private List<Departamentos> lstDepartamentoFiltrada;
    private Municipio municipioSelected;
    private String accion;
    
    @PostConstruct
    public void init() {
        listarMunicipios();
        municipioSelected = new Municipio();
        listarDepartamentos();
    }
    
    public void listarDepartamentos(){
        lstDepartamento = departamentoFacade.findAll();
    }

     public String limpiarObjeto(){
        municipioSelected = new Municipio();
        return "frmMunicipio.xhtml?faces-redirect=true";
    }
    
    public void borrarMunicipio(){
        try {
            municipioFacade.remove(municipioSelected);
            lstMunicipio = municipioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Municipio Borrado", "Informacion"));
            municipioSelected = new Municipio();     
        } catch (Exception e) {
            System.out.println("Error al borrar el Municipio " + e);
        }
    }

     public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return
            modificarMunicipio();
        } else {
            return
            crearMunicipio();
        }
    }

    public String crearMunicipio() {
        try {
            municipioSelected.setDescripcion(WordUtils.capitalizeFully(municipioSelected.getDescripcion()));
            municipioFacade.create(municipioSelected);
            lstMunicipio = municipioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Municipio Creado", "Informacion"));
            municipioSelected = new Municipio();
        } catch (Exception e) {
            System.out.println("Error al crear Municipio " + e);
        }
        return "lstMunicipio.xhtml?faces-redirect=true";
    }

    public String modificarMunicipio() {
        try {
            municipioSelected.setDescripcion(WordUtils.capitalizeFully(municipioSelected.getDescripcion()));
            municipioFacade.edit(municipioSelected);
            lstMunicipio = municipioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Municipio Modificado", "Informacion"));
            municipioSelected = new Municipio();
        } catch (Exception e) {
            System.out.println("Error al modificar el Municipio " + e);
        }
        return "lstMunicipio.xhtml?faces-redirect=true";
    }
    
    private void listarMunicipios() {
      lstMunicipio = municipioFacade.findAll();
  
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

    public Municipio getMunicipioSelected() {
        return municipioSelected;
    }

    public void setMunicipioSelected(Municipio municipioSelected) {
        this.municipioSelected = municipioSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Departamentos> getLstDepartamento() {
        return lstDepartamento;
    }

    public void setLstDepartamento(List<Departamentos> lstDepartamento) {
        this.lstDepartamento = lstDepartamento;
    }

    public List<Departamentos> getLstDepartamentoFiltrada() {
        return lstDepartamentoFiltrada;
    }

    public void setLstDepartamentoFiltrada(List<Departamentos> lstDepartamentoFiltrada) {
        this.lstDepartamentoFiltrada = lstDepartamentoFiltrada;
    }
    
    
}
