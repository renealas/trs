/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rene Alas
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "TRSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario buscarUsurio(String usuario, String pass){
        Query q = em.createQuery("SELECT U FROM Usuario U WHERE U.user = :usuario AND U.password = :pass");
        q.setParameter("usuario", usuario);
        q.setParameter("pass", pass);
        if(q.getResultList().isEmpty())
        {
            return null;
        }
        else
        {
            return (Usuario) q.getResultList().get(0);
        }
    }
}
