/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.TipoInvestigacion;
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
public class TipoInvestigacionFacade extends AbstractFacade<TipoInvestigacion>
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

    public TipoInvestigacionFacade()
    {
        super(TipoInvestigacion.class);
    }
    
    @Override
    public void create(TipoInvestigacion entity){
        String info[] = {"ID: " + entity.getCodigo()+ "Nombre: " + entity.getTipo()};
        auditoriaSB.registrarLog("Creación Tipo Investigación", info);
        super.create(entity);
    }
    
    @Override
    public void edit (TipoInvestigacion entity){
        String info[] = {"ID: " + entity.getCodigo() + "Nombre: " + entity.getTipo()};
        auditoriaSB.registrarLog("Se editó un Tipo de Investigación", info);
        super.edit(entity);   
    }
    
    @Override
    public void remove (TipoInvestigacion entity) {
        String info[] = {"ID: " + entity.getCodigo() + "Nombre: " + entity.getTipo()};
        auditoriaSB.registrarLog("Se eliminó un Tipo de Investigación", info);
        super.remove(entity);  
    }
    
}
