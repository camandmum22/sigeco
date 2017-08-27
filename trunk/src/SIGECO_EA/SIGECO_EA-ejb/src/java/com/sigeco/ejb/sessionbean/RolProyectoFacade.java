/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.RolProyecto;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author csacanam
 */
@Stateless
public class RolProyectoFacade extends AbstractFacade<RolProyecto>
{
    @PersistenceContext(unitName = "SIGECO_EA-ejbPU")
    private EntityManager em;
    
    @EJB
    private AuditoriaSB auditoriaSB;

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public RolProyectoFacade()
    {
        super(RolProyecto.class);
    }
    
    @Override
    public void create(RolProyecto entity)
    {
        //Información
        String info[] = {"Código rol investigación: " + entity.getCodigo(), 
                         "Nombre" +  entity.getNombre()};
        //Registrar log
        auditoriaSB.registrarLog("Se creó un nuevo rol de investigación ", info);
        //Crear rol de investigación
        super.create(entity);
    }
    
    @Override
    public void edit(RolProyecto entity)
    {
        //Información
        String info[] = {"Código rol investigación: " + entity.getCodigo(), 
                         "Nombre: " +  entity.getNombre()};
        //Registrar log
        auditoriaSB.registrarLog("Se editó un rol de investigación", info);
        //Editar rol de proyecto
        super.edit(entity);
    }
    
    @Override
    public void remove(RolProyecto entity)
    {
        //Información
        String info[] = { "Código rol investigación: " + entity.getCodigo(),
                          "Nombre: " +  entity.getNombre()};
        //Registrar log
        auditoriaSB.registrarLog("Se elimino una rol de investigación", info);
        //Eliminar rol del proyecto
        super.remove(entity);
    }
}
