/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.TipoProyecto;
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
public class TipoProyectoFacade extends AbstractFacade<TipoProyecto>
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

    public TipoProyectoFacade()
    {
        super(TipoProyecto.class);
    }
    
    @Override
    public void create(TipoProyecto entity){
        String info[] = {"ID: " + entity.getCodigo() + "Nombre: " + entity.getNombre()
                           +"Descripcion: "+entity.getDescripcion()};
        auditoriaSB.registrarLog("Se creó Tipo de Proyecto", info);
        super.create(entity);
    }
    
    @Override
    public void edit (TipoProyecto entity){
        String info[] = {"ID: " + entity.getCodigo() + "Nombre: " + entity.getNombre()
                           +"Descripcion: "+entity.getDescripcion()};
        auditoriaSB.registrarLog("Se editó un Tipo de Proyecto", info);
        super.edit(entity);   
    }
    
    @Override
    public void remove (TipoProyecto entity) {
        String info[] = {"ID: " + entity.getCodigo()+ "Nombre: " + entity.getNombre()
                           +"Descripcion: "+entity.getDescripcion()};
        auditoriaSB.registrarLog("Se eliminó un Tipo de Proyecto", info);
        super.remove(entity);  
    }
}
