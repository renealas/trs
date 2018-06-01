/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Culto;
import entidades.Iglesia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rene Alas
 */
@Stateless
public class CultoFacade extends AbstractFacade<Culto> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CultoFacade() {
        super(Culto.class);
    }
   
    public List<Culto> listarCultosPorIglesia(Iglesia idIglesia) {
        Query q = em.createQuery("SELECT C FROM Culto C WHERE C.idiglesia = :idIglesia");
        q.setParameter("idIglesia", idIglesia);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
}
