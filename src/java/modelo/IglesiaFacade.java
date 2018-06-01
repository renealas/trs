/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Iglesia;
import entidades.Municipio;
import entidades.Usuario;
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
public class IglesiaFacade extends AbstractFacade<Iglesia> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IglesiaFacade() {
        super(Iglesia.class);
    }
  
    public List<Iglesia> listarIglesiasPorMunicipio(Municipio idMunicipio) {
        Query q = em.createQuery("SELECT I FROM Iglesia I WHERE I.idmunicipio = :idMunicipio");
        q.setParameter("idMunicipio", idMunicipio);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
   
}

