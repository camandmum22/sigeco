/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Menu;
import com.sigeco.ejb.entities.PaginaXhtml;
import com.sigeco.ejb.entities.RolSistema;
import com.sigeco.ejb.entities.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author csacanam
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu>
{
    @PersistenceContext(unitName = "SIGECO_EA-ejbPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public MenuFacade()
    {
        super(Menu.class);
    }

    public List<Menu> buscarModuloUsuario(Usuario usuario) {
        Query q = em.createQuery(  "select usu from Usuario usu where usu.codigo = :codigoUsuario ");
        q.setParameter("codigoUsuario", usuario.getCodigo());
        usuario = (Usuario) q.getSingleResult();
        List<RolSistema> rolSistemas = usuario.getRolSistemaList();
        List<Menu> retorno = new ArrayList<Menu>();
        for (Iterator<RolSistema> iteratorRoles = rolSistemas.iterator(); iteratorRoles.hasNext();) {
            RolSistema rol = iteratorRoles.next();
            retorno.addAll(rol.getMenuList());
            for (Iterator<Menu> iteratorMenus = retorno.iterator(); iteratorMenus.hasNext();) {
                Menu menu = iteratorMenus.next();
                List<PaginaXhtml> paginas = menu.getPaginaXhtmlList();
                for (int i = 0; i < paginas.size(); i++) {
                    PaginaXhtml get = paginas.get(i);
                    System.out.println(get.getUrl());
                }
            }
        }
        return retorno;
    }   
}
