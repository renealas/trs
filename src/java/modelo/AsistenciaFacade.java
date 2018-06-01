/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Asistencia;
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
public class AsistenciaFacade extends AbstractFacade<Asistencia> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }
    
    public List<Asistencia> listarAsistenciaPorIglesia(Iglesia idIglesia) {
        Query q = em.createQuery("SELECT A FROM Asistencia A WHERE A.idiglesia = :idIglesia");
        q.setParameter("idIglesia", idIglesia);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
}
