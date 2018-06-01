/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Iglesia;
import entidades.Presentacion;
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
public class PresentacionFacade extends AbstractFacade<Presentacion> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PresentacionFacade() {
        super(Presentacion.class);
    }
    
    public List<Presentacion> listarPresentacionPorIglesia(Iglesia idIglesia) {
        Query q = em.createQuery("SELECT P FROM Presentacion P WHERE P.idiglesia = :idIglesia");
        q.setParameter("idIglesia", idIglesia);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
}
