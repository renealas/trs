package controladores;

import entidades.Usuario;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import modelo.UsuarioFacade;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "imagenesControl")
@ApplicationScoped
public class imagenesControl {

    @EJB
    private UsuarioFacade usuarioFacade;
    
     public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            int userId = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("userId"));
            Usuario us = (Usuario) usuarioFacade.find(userId);
            return new DefaultStreamedContent(new ByteArrayInputStream(us.getFoto()));
        }
    }
    
}
