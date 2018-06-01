package controladores;

import entidades.Iglesia;
import entidades.Nivel;
import entidades.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.IglesiaFacade;
import modelo.NivelFacade;
import modelo.UsuarioFacade;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "usuarioControl")
@SessionScoped
public class usuarioControl implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private IglesiaFacade iglesiaFacade;
    @EJB
    private NivelFacade nivelFacade;

    private List<Usuario> lstUsuario;
    private List<Usuario> lstUsuarioFiltrada;
    private List<Iglesia> lstIglesia;
    private List<Iglesia> lstIglesiaFiltrada;
    private List<Nivel> lstNivel;
    private List<Nivel> lstNivelFiltrada;
    private Usuario usuarioSelected;
    private String accion;
    private byte[] content;
    String userName;
    String passcode;

    @PostConstruct
    public void init() {
        listarUsuarios();
        usuarioSelected = new Usuario();
        listarNiveles();
        listarIglesias();
    }

    public void listarNiveles() {
        lstNivel = nivelFacade.findAll();
    }

    public void listarIglesias() {
        lstIglesia = iglesiaFacade.findAll();
    }

    public String limpiarObjeto() {
        usuarioSelected = new Usuario();
        return "frmUsuario.xhtml?faces-redirect=true";
    }

    public void borrarUsuario() {
        try {
            usuarioFacade.remove(usuarioSelected);
            lstUsuario = usuarioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Borrado", "Informacion"));
            usuarioSelected = new Usuario();
        } catch (Exception e) {
            System.out.println("Error al borrar el Usuario " + e);
        }

    }

    public String doAccion() {
        if (accion.equals("MODIFICAR")) {
            return modificarUsuario();
        }
        if (accion.equals("ACTUALIZAR")) {
            return modificarUsuarioLog();
        }else {
            return crearUsuario();
        }
    }

    public String crearUsuario() {
        try {
            String Us1 = usuarioSelected.getNombre().substring(0, 2);
            String Us2 = usuarioSelected.getApellido().substring(0, 2);
            Date indate = usuarioSelected.getFechanac();
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            String reporteFecNac = sdfr.format(indate);
            String Us3 = reporteFecNac.substring(0, 2);
            String Us4 = reporteFecNac.substring(3, 5);
            userName = Us1 + Us3 + Us4 + Us2;
            passcode = Us1 + Us3 + Us4 + Us2;
            usuarioSelected.setNombre(WordUtils.capitalizeFully(usuarioSelected.getNombre()));
            usuarioSelected.setApellido(WordUtils.capitalizeFully(usuarioSelected.getApellido()));
            usuarioSelected.setEscuelateo(WordUtils.capitalizeFully(usuarioSelected.getEscuelateo()));
            usuarioSelected.setConyuge(WordUtils.capitalizeFully(usuarioSelected.getConyuge()));
            usuarioSelected.setNacionalidad(WordUtils.capitalizeFully(usuarioSelected.getNacionalidad()));
            usuarioSelected.setProfesion(usuarioSelected.getProfesion().toUpperCase());
            usuarioSelected.setLugarempleo(WordUtils.capitalizeFully(usuarioSelected.getLugarempleo()));
            usuarioSelected.setUser(userName);
            usuarioSelected.setPassword(DigestUtils.md5Hex(passcode));
            usuarioFacade.create(usuarioSelected);
            lstUsuario = usuarioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Creado", "Informacion"));
            usuarioSelected = new Usuario();
        } catch (Exception e) {
            System.out.println("Error al crear Usuario " + e);
        }
        return "lstUsuario.xhtml?faces-redirect=true";
    }

    public String modificarUsuario() {
        try {
            usuarioSelected.setNombre(WordUtils.capitalizeFully(usuarioSelected.getNombre()));
            usuarioSelected.setApellido(WordUtils.capitalizeFully(usuarioSelected.getApellido()));
            usuarioSelected.setEscuelateo(WordUtils.capitalizeFully(usuarioSelected.getEscuelateo()));
            usuarioSelected.setConyuge(WordUtils.capitalizeFully(usuarioSelected.getConyuge()));
            usuarioSelected.setNacionalidad(WordUtils.capitalizeFully(usuarioSelected.getNacionalidad()));
            usuarioSelected.setProfesion(usuarioSelected.getProfesion().toUpperCase());
            usuarioSelected.setLugarempleo(WordUtils.capitalizeFully(usuarioSelected.getLugarempleo()));
            usuarioSelected.setUser(userName);            
            usuarioFacade.edit(usuarioSelected);
            lstUsuario = usuarioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Modificado", "Informacion"));
            usuarioSelected = new Usuario();
        } catch (Exception e) {
            System.out.println("Error al modificar el Usuario " + e);
        }
        return "lstUsuario.xhtml?faces-redirect=true";
    }
    
    public String modificarUsuarioLog() {
        try {
            usuarioSelected.setNombre(WordUtils.capitalizeFully(usuarioSelected.getNombre()));
            usuarioSelected.setApellido(WordUtils.capitalizeFully(usuarioSelected.getApellido()));
            usuarioSelected.setEscuelateo(WordUtils.capitalizeFully(usuarioSelected.getEscuelateo()));
            usuarioSelected.setConyuge(WordUtils.capitalizeFully(usuarioSelected.getConyuge()));
            usuarioSelected.setNacionalidad(WordUtils.capitalizeFully(usuarioSelected.getNacionalidad()));
            usuarioSelected.setProfesion(usuarioSelected.getProfesion().toUpperCase());
            usuarioSelected.setLugarempleo(WordUtils.capitalizeFully(usuarioSelected.getLugarempleo()));
            usuarioFacade.edit(usuarioSelected);
            lstUsuario = usuarioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Modificado", "Informacion"));
            usuarioSelected = new Usuario();
        } catch (Exception e) {
            System.out.println("Error al modificar el Usuario " + e);
        }
        return "/faces/Catalogos/inicio.xhtml";
    }

    public void resetPasswordByAdmin() {
        try {
            String TabernPass = "tabernaculo";
            usuarioSelected.setPassword(DigestUtils.md5Hex(TabernPass));
            usuarioFacade.edit(usuarioSelected);
            lstUsuario = usuarioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña Cambiada con Exito! Nueva Contraseña: tabernaculo", "Informacion"));
            usuarioSelected = new Usuario();
        } catch (Exception e) {
            System.out.println("Error al cambiar Password del Usuario " + e);
        }
    }

    public String redirigirReset() {
        return "rstUsuario.xhtml?faces-redirect=true";
    }

    public String resetPasswordByUser() {
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            us.setPassword(DigestUtils.md5Hex(usuarioSelected.getPassword()));
            usuarioFacade.edit(us);
            lstUsuario = usuarioFacade.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña Cambiada con Exito", "Informacion"));
            usuarioSelected = new Usuario();
        } catch (Exception e) {
            System.out.println("Error al cambiar Password del Usuario " + e);
        }
        return "/faces/Catalogos/inicio.xhtml";
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        content = uploadedFile.getContents();
        usuarioSelected.setFoto(content);
        usuarioFacade.edit(usuarioSelected);
    }

    private void listarUsuarios() {
        lstUsuario = usuarioFacade.findAll();
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public List<Usuario> getLstUsuarioFiltrada() {
        return lstUsuarioFiltrada;
    }

    public void setLstUsuarioFiltrada(List<Usuario> lstUsuarioFiltrada) {
        this.lstUsuarioFiltrada = lstUsuarioFiltrada;
    }

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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

}
