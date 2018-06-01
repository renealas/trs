package controladores;

import entidades.Departamentos;
import entidades.Pais;
import entidades.Zona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.DepartamentosFacade;
import modelo.PaisFacade;
import modelo.ZonaFacade;
import org.apache.commons.lang3.text.WordUtils;

@ManagedBean(name = "departamentosControl")
@SessionScoped
public class departamentosControl implements Serializable {

    @EJB
    private DepartamentosFacade departamentoFacade;
    @EJB
    private PaisFacade paisFacade;
    @EJB
    private ZonaFacade zonaFacade;

    private List<Departamentos> lstDepartamento;
    private List<Departamentos> lstDepartamentoFiltrada;
    private List<Pais> lstPais;
    private List<Pais> lstPaisFiltrada;
    private List<Zona> lstZona;
    private List<Zona> lstZonaFiltrada;
    private Departamentos departamentoSelected;
    private String accion;

    @PostConstruct
    public void init() {
        listarDepartamentos();
        departamentoSelected = new Departamentos();
        listarPaises();
        listarZonas();
    }

    public void listarZonas() {
        lstZona = zonaFacade.findAll();
    }

    public void listarPaises() {
        lstPais = paisFacade.findAll();
    }

    public String limpiarObjeto() {
        departamentoSelected = new Departamentos();
        return "frmDepartamento.xhtml?faces-redirect=true";
    }

    public void borrarDepartamento() {
        try {
            departamentoFacade.remove(departamentoSelected);
            lstDepartamento = departamentoFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento Borrado", "Informacion"));
            departamentoSelected = new Departamentos();
        } catch (Exception e) {
            System.out.println("Error al borrar el Departamento " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarDepartamento();
        } else {
            return crearDepartamento();
        }
    }

    public String crearDepartamento() {
        try {
            departamentoSelected.setDescripcion(WordUtils.capitalizeFully(departamentoSelected.getDescripcion()));
            departamentoFacade.create(departamentoSelected);
            lstDepartamento = departamentoFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento Creado", "Informacion"));
            departamentoSelected = new Departamentos();
        } catch (Exception e) {
            System.out.println("Error al crear Departamento " + e);
        }
        return "lstDepartamento.xhtml?faces-redirect=true";
    }

    public String modificarDepartamento() {
        try {
            departamentoSelected.setDescripcion(WordUtils.capitalizeFully(departamentoSelected.getDescripcion()));
            departamentoFacade.edit(departamentoSelected);
            lstDepartamento = departamentoFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento Modificado", "Informacion"));
            departamentoSelected = new Departamentos();
        } catch (Exception e) {
            System.out.println("Error al modificar el Departamento " + e);
        }
        return "lstDepartamento.xhtml?faces-redirect=true";
    }

    private void listarDepartamentos() {
        lstDepartamento = departamentoFacade.findAll();

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

    public Departamentos getDepartamentoSelected() {
        return departamentoSelected;
    }

    public void setDepartamentoSelected(Departamentos departamentoSelected) {
        this.departamentoSelected = departamentoSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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
}
