/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Usuario;
import com.sigeco.ejb.exceptions.UsuarioException;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author csacanam
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario>
{

    @PersistenceContext(unitName = "SIGECO_EA-ejbPU")
    private EntityManager em;

    @EJB
    AuditoriaSB auditoriaSB;

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public UsuarioFacade()
    {
        super(Usuario.class);
    }

    @Override
    public void create(Usuario usuario)
    {
        String info[] =
        {
            "Tipo Documento: " + usuario.getTipoDocumento(),
            " Documento:" + usuario.getDocumento(), 
            " Nombre: " + usuario.getNombre(), 
            " Email: " + usuario.getEmail(),
            " Teléfono:" + usuario.getTelefono()
        };
        auditoriaSB.registrarLog("Se creó un usuario", info);
        super.create(usuario);
    }

    /**
    * De una lista de strings consecutivos retorna el numero mayor
    */
    public Integer findMaxId()
    {
        List<BigDecimal> resultado = em.createQuery("SELECT CAST(usu.codigoUsuario AS DECIMAL) FROM Usuario usu").getResultList();
        resultado.sort(null);
        return resultado.get(resultado.size() - 1).intValue();
    }
    
    public List<Usuario> filtrarUsuariosPorNombre(String nombre){
        List<Usuario> retorno = em.createQuery("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE :nombre").setParameter("nombre", "%"+nombre.toLowerCase()+"%").getResultList();
        return retorno;
    }
    
    public List<Usuario> filtrarUsuariosPorDocumento(String documento){
        List<Usuario> retorno = em.createQuery("SELECT u FROM Usuario u WHERE u.documento LIKE :documento").setParameter("documento", "%"+documento+"%").getResultList();
        return retorno;
    }
    
    public List<Usuario> filtrarUsuariosPorRol(String rol){
        //List<Usuario> retorno = em.createQuery("SELECT u FROM Usuario u WHERE u.codigo = (SELECT ur. FROM usu ur, ROL_SISTEMA rs WHERE ur.ROL_S_CODIGO = rs.CODIGO AND rs.NOMBRE LIKE '%mini%' );").setParameter("rol", "%"+rol.toLowerCase()+"%").getResultList();
        List<Usuario> retorno = em.createQuery("SELECT u FROM RolSistema r,Usuario u WHERE LOWER (r.nombre) LIKE :rol AND u IN (r.usuarioList) ").setParameter("rol", "%"+rol.toLowerCase()+"%").getResultList();
        return retorno;
    }
}
