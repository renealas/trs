package modelo;

import entidades.Iglesia;
import entidades.Ofrenda;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OfrendaFacade extends AbstractFacade<Ofrenda> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfrendaFacade() {
        super(Ofrenda.class);
    }
    
    public List<Ofrenda> listarOfrendaPorIglesia(Iglesia idIglesia) {
        Query q = em.createQuery("SELECT O FROM Ofrenda O WHERE O.idiglesia = :idIglesia");
        q.setParameter("idIglesia", idIglesia);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
}
