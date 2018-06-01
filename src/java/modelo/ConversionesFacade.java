package modelo;

import entidades.Conversiones;
import entidades.Iglesia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ConversionesFacade extends AbstractFacade<Conversiones> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConversionesFacade() {
        super(Conversiones.class);
    }
    
    public List<Conversiones> listarConversionesPorIglesia(Iglesia idIglesia) {
        Query q = em.createQuery("SELECT C FROM Conversiones C WHERE C.idiglesia = :idIglesia");
        q.setParameter("idIglesia", idIglesia);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
}
