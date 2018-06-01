/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Departamentos;
import entidades.Municipio;
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
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public List<Municipio> listarMunicipiosPorDepartamento(Departamentos idDepartamento) {
        Query q = em.createQuery("SELECT M FROM Municipio M WHERE M.iddepartamento = :idDepartamento");
        q.setParameter("idDepartamento", idDepartamento);
        if(q.getResultList().isEmpty())
        {return null;
        }else{
            return q.getResultList();
        }
    }
}
