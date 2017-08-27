/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.AreaEstrategica;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author csacanam
 */
@Stateless
public class AreaEstrategicaFacade extends AbstractFacade<AreaEstrategica>
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


    public AreaEstrategicaFacade()
    {
        super(AreaEstrategica.class);
    }
    
    @Override
    public void create(AreaEstrategica entity)
    {
        String info[] = {"Nombre: "+entity.getNombre()};
        auditoriaSB.registrarLog("Se creó un área estratégica", info);
        super.create(entity);
    }
    
    @Override
    public void edit (AreaEstrategica entity){
        String info[] = {"Nombre: "+entity.getNombre()};
        auditoriaSB.registrarLog("Se editó un área estratégica", info);
        super.edit(entity);   
    }
    
    @Override
    public void remove ( AreaEstrategica entity) {
        String info[] = {"Nombre: "+entity.getNombre()};
        auditoriaSB.registrarLog("Se eliminó un área estratégica", info);
        super.remove(entity);  
    }


}
