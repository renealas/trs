package controladores;

import entidades.Culto;
import entidades.Iglesia;
import entidades.Ofrenda;
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
import modelo.OfrendaFacade;

@ManagedBean(name = "ofrendaControl")
@SessionScoped
public class ofrendaControl implements Serializable {

    @EJB
    private OfrendaFacade ofrendaFacade;

    @EJB
    private CultoFacade cultoFacade;

    @EJB
    private IglesiaFacade iglesiaFacade;

    private List<Ofrenda> lstOfrenda;
    private List<Ofrenda> lstOfrendaAdmin;
    private List<Ofrenda> lstOfrendaFiltrada;
    private List<Culto> lstCulto;
    private List<Culto> lstCultoFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;

    private Ofrenda ofrendaSelected;
    private String accion;
    private Usuario user;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user = (Usuario) sessionMap.get("usuario");
        listarOfrenda();
        listarOfrendasAdmin();
        ofrendaSelected = new Ofrenda();
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
        ofrendaSelected = new Ofrenda();
        user = (Usuario) sessionMap.get("usuario");
        ofrendaSelected.setIdusuario(user);
        ofrendaSelected.setIdiglesia(user.getIdiglesia());
        listarIglesias();
        listarCultos();
        return "frmOfrenda.xhtml?faces-redirect=true";
    }

    public void borrarOfrenda() {
        try {
            ofrendaFacade.remove(ofrendaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstOfrenda = ofrendaFacade.listarOfrendaPorIglesia(user.getIdiglesia());
            } else {
                lstOfrendaAdmin = ofrendaFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ofrenda Borrada", "Informacion"));
            ofrendaSelected = new Ofrenda();
        } catch (Exception e) {
            System.out.println("Error al borrar la Ofrenda " + e);
        }
    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarOfrenda();
        } else {
            return crearOfrenda();
        }
    }

    public String crearOfrenda() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                ofrendaSelected.setIdiglesia(user.getIdiglesia());
            }
            ofrendaSelected.setResultado1(ofrendaSelected.getBilletes1() * 1);
            ofrendaSelected.setResultado2(ofrendaSelected.getBilletes2() * 2);
            ofrendaSelected.setResultado5(ofrendaSelected.getBilletes5() * 5);
            ofrendaSelected.setResultado10(ofrendaSelected.getBilletes10() * 10);
            ofrendaSelected.setResultado20(ofrendaSelected.getBilletes20() * 20);
            ofrendaSelected.setResultado50(ofrendaSelected.getBilletes50() * 50);
            ofrendaSelected.setResultado100(ofrendaSelected.getBilletes100() * 100);
            ofrendaSelected.setSubtotal(ofrendaSelected.getBilletes1() * 1 + ofrendaSelected.getBilletes2() * 2 + ofrendaSelected.getBilletes5() * 5 + ofrendaSelected.getBilletes10() * 10 + ofrendaSelected.getBilletes20() * 20 + ofrendaSelected.getBilletes50() * 50 + ofrendaSelected.getBilletes100() * 100);
            ofrendaSelected.setTotalpapel(ofrendaSelected.getSubtotal() + ofrendaSelected.getChequeinterno() + ofrendaSelected.getChequeexterno() + ofrendaSelected.getGiros());
            ofrendaSelected.setCantidad1(ofrendaSelected.getTotalpapel());
            ofrendaSelected.setTotal(ofrendaSelected.getCantidad1() + ofrendaSelected.getCantidad2());

            ofrendaFacade.create(ofrendaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstOfrenda = ofrendaFacade.listarOfrendaPorIglesia(user.getIdiglesia());
            } else {
                lstOfrendaAdmin = ofrendaFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ofrenda Creada", "Informacion"));
            ofrendaSelected = new Ofrenda();
        } catch (Exception e) {
            System.out.println("Error al crear Ofrenda " + e);
        }
        return "lstOfrenda.xhtml?faces-redirect=true";
    }

    public String modificarOfrenda() {
        try {
            if (user.getIdnivel().getIdnivel() > 2) {
                ofrendaSelected.setIdiglesia(user.getIdiglesia());
            }
            ofrendaSelected.setResultado1(ofrendaSelected.getBilletes1() * 1);
            ofrendaSelected.setResultado2(ofrendaSelected.getBilletes2() * 2);
            ofrendaSelected.setResultado5(ofrendaSelected.getBilletes5() * 5);
            ofrendaSelected.setResultado10(ofrendaSelected.getBilletes10() * 10);
            ofrendaSelected.setResultado20(ofrendaSelected.getBilletes20() * 20);
            ofrendaSelected.setResultado50(ofrendaSelected.getBilletes50() * 50);
            ofrendaSelected.setResultado100(ofrendaSelected.getBilletes100() * 100);
            ofrendaSelected.setSubtotal(ofrendaSelected.getBilletes1() * 1 + ofrendaSelected.getBilletes2() * 2 + ofrendaSelected.getBilletes5() * 5 + ofrendaSelected.getBilletes10() * 10 + ofrendaSelected.getBilletes20() * 20 + ofrendaSelected.getBilletes50() * 50 + ofrendaSelected.getBilletes100() * 100);
            ofrendaSelected.setTotalpapel(ofrendaSelected.getSubtotal() + ofrendaSelected.getChequeinterno() + ofrendaSelected.getChequeexterno() + ofrendaSelected.getGiros());
            ofrendaSelected.setCantidad1(ofrendaSelected.getTotalpapel());
            ofrendaSelected.setTotal(ofrendaSelected.getCantidad1() + ofrendaSelected.getCantidad2());
            ofrendaFacade.edit(ofrendaSelected);
            if (user.getIdnivel().getIdnivel() > 2) {
                lstOfrenda = ofrendaFacade.listarOfrendaPorIglesia(user.getIdiglesia());
            } else {
                lstOfrendaAdmin = ofrendaFacade.findAll();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ofrenda Modificada", "Informacion"));
            ofrendaSelected = new Ofrenda();
        } catch (Exception e) {
            System.out.println("Error al modificar Ofrenda " + e);
        }
        return "lstOfrenda.xhtml?faces-redirect=true";
    }

    private void listarOfrenda() {
        lstOfrenda = ofrendaFacade.listarOfrendaPorIglesia(user.getIdiglesia());
    }

    private void listarOfrendasAdmin() {
        lstOfrendaAdmin = ofrendaFacade.findAll();
    }

    public List<Ofrenda> getLstOfrenda() {
        return lstOfrenda;
    }

    public void setLstOfrenda(List<Ofrenda> lstOfrenda) {
        this.lstOfrenda = lstOfrenda;
    }

    public List<Ofrenda> getLstOfrendaAdmin() {
        return lstOfrendaAdmin;
    }

    public void setLstOfrendaAdmin(List<Ofrenda> lstOfrendaAdmin) {
        this.lstOfrendaAdmin = lstOfrendaAdmin;
    }

    public List<Ofrenda> getLstOfrendaFiltrada() {
        return lstOfrendaFiltrada;
    }

    public void setLstOfrendaFiltrada(List<Ofrenda> lstOfrendaFiltrada) {
        this.lstOfrendaFiltrada = lstOfrendaFiltrada;
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

    public Ofrenda getOfrendaSelected() {
        return ofrendaSelected;
    }

    public void setOfrendaSelected(Ofrenda ofrendaSelected) {
        this.ofrendaSelected = ofrendaSelected;
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
