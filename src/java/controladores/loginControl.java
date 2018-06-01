package controladores;

import entidades.Usuario;
import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UsuarioFacade;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean(name = "loginControl")
@SessionScoped
public class loginControl implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuarioValidado;
    private String txtUsuario;
    private String txtPass;
    private String password;
    private Boolean menurender;
    private Integer anio;
    String accion;
    String page = FacesContext.getCurrentInstance().getViewRoot().getViewId();

    @PostConstruct
    public void init() {
        this.anio = Calendar.getInstance().get(Calendar.YEAR);
    }

    public String validarIngreso() {
        password = DigestUtils.md5Hex(txtPass);
        usuarioValidado = usuarioFacade.buscarUsurio(txtUsuario, password);
        if (usuarioValidado != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioValidado);
            return "/Catalogos/inicio.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Credenciales Incorrectas."));
            return null;
        }
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
            usuarioValidado = null;
            txtUsuario = null;
            txtPass = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/TRS");
        } catch (Exception E) {
            System.out.println("Error al cerrar la sesion" + E);
        }
    }

    public Usuario getUsuarioValidado() {
        return usuarioValidado;
    }

    public void setUsuarioValidado(Usuario usuarioValidado) {
        this.usuarioValidado = usuarioValidado;
    }

    public String getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(String txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public String getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(String txtPass) {
        this.txtPass = txtPass;
    }

    public Boolean getMenurender() {
        return menurender;
    }

    public void setMenurender(Boolean menurender) {
        this.menurender = menurender;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
