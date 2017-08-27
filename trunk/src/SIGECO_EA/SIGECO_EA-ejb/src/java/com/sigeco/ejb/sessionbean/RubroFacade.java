/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Rubro;
import com.sigeco.ejb.exceptions.RubroException;
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
public class RubroFacade extends AbstractFacade<Rubro> {

    @PersistenceContext(unitName = "SIGECO_EA-ejbPU")
    private EntityManager em;

    @EJB
    private AuditoriaSB auditoriaSB;
    
    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public RubroFacade() {
        super(Rubro.class);
    }

    @Override
    public void create(Rubro rubro) {
        String info[] = {"Código: " + rubro.getCodigo(), "Nombre: " + rubro.getNombre(), "Descripción: " + rubro.getDescripcion()};
           auditoriaSB.registrarLog("Se creó un rubro", info);
        super.create(rubro);
            
    }
    
    @Override
    public void edit(Rubro rubro) {
            
        String info[] = {"Código: " + rubro.getCodigo(), "Nombre: " + rubro.getNombre(), "Descripción: " + rubro.getDescripcion()};
           auditoriaSB.registrarLog("Se editó un rubro", info);
        super.edit(rubro);
            
    }
    
    @Override
    public void remove(Rubro rubro) {
            
        String info[] = {"Código: " + rubro.getCodigo(), "Nombre: " + rubro.getNombre(), "Descripción: " + rubro.getDescripcion()};
           auditoriaSB.registrarLog("Se remoció un rubro", info);
        super.remove(rubro);
            
    }
    

}
