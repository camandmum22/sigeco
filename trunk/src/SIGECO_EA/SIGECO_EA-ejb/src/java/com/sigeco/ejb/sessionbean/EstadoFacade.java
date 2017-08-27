/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Estado;
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
public class EstadoFacade extends AbstractFacade<Estado>
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

    public EstadoFacade()
    {
        super(Estado.class);
    }

    @Override
    public void create(Estado entity)
    {
        //Información adicional
        String info[] = {"Código estado: "+entity.getCodigo()};
        //Registrar log
        auditoriaSB.registrarLog("Se creó un estado", info);
        //Crear estado
        super.create(entity);
    }

    @Override
    public void edit(Estado entity)
    {   //Información adicional
        String info[] = {"Código estado: "+entity.getCodigo()};
        //Registrar log
        auditoriaSB.registrarLog("Se editó el estado", info);
        //Editar estado
        super.edit(entity); 
    }

    @Override
    public void remove(Estado entity)
    {
        
         //Información adicional
        String info[] = {"Código estado: "+entity.getCodigo()};
        //Registrar log
        auditoriaSB.registrarLog("Se eliminó el estado", info);
        //Eliminar estado
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
