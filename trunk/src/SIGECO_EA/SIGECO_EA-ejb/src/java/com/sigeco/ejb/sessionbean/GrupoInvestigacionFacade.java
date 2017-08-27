/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.GrupoInvestigacion;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
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
public class GrupoInvestigacionFacade extends AbstractFacade<GrupoInvestigacion>
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

    public GrupoInvestigacionFacade()
    {
        super(GrupoInvestigacion.class);
    }
    
    @Override
    public void create(GrupoInvestigacion entity){
        String info[] = {"Código: "+entity.getCodigo(), "Nombre: "+entity.getNombre()};
        auditoriaSB.registrarLog("Se creó un grupo de investigación", info);
        super.create(entity);
    }
    
    @Override
    public void edit (GrupoInvestigacion entity){
        String info[] = {"Código: "+entity.getCodigo(), "Nombre: "+entity.getNombre()};
        auditoriaSB.registrarLog("Se editó un grupo de investigación", info);
        super.edit(entity);   
    }
    
    @Override
    public void remove ( GrupoInvestigacion entity) {
        String info[] = {"Código: "+entity.getCodigo(), "Nombre: "+entity.getNombre()};
        auditoriaSB.registrarLog("Se eliminó un grupo de investigación", info);
        super.remove(entity);  
    }
    
}
