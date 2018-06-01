/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Departamentos;
import entidades.Pais;
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
public class DepartamentosFacade extends AbstractFacade<Departamentos> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentosFacade() {
        super(Departamentos.class);
    }
    
    public List<Departamentos> listarDepartamentosPorPais(Pais idPais) {
        Query q = em.createQuery("SELECT D FROM Departamentos D WHERE D.idpais = :idPais");
        q.setParameter("idPais", idPais);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
}
