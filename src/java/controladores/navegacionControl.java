package controladores;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "navegacionControl")
@SessionScoped
public class navegacionControl implements Serializable {
    
    private String includedPage;
    

public String goNav() {
   FacesContext context = FacesContext.getCurrentInstance();
   String selectedPageViewId = context.getExternalContext().getRequestParameterMap().get("pageViewId");
   if (selectedPageViewId.equalsIgnoreCase("zona")){
        includedPage = "lstZona.xhtml?redirect=true";
    }
   else{
       includedPage = "index.xhtml?redirect=true";
   }
   return includedPage;
}

    public String getIncludedPage() {
        return includedPage;
    }

    public void setIncludedPage(String includedPage) {
        this.includedPage = includedPage;
    }

}
