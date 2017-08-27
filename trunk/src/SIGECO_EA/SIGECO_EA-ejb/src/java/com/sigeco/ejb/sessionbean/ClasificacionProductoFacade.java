/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.ClasificacionProducto;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
import java.util.Date;
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
public class ClasificacionProductoFacade extends AbstractFacade<ClasificacionProducto>
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

    public ClasificacionProductoFacade()
    {
        super(ClasificacionProducto.class);
    }
    //
    
    @Override
    public void create(ClasificacionProducto entity){
        String info[] = {entity.getClasificacion()};
        auditoriaSB.registrarLog("Se creó una clasificación de producto", info);
        super.create(entity);
    }
    
    @Override
    public void edit (ClasificacionProducto entity){
        String info[] = {entity.getClasificacion()};
        auditoriaSB.registrarLog("Se editó una clasificación de producto", info);
        super.edit(entity);   
    }
    
    @Override
    public void remove ( ClasificacionProducto entity) {
        String info[] = {entity.getClasificacion()};
        auditoriaSB.registrarLog("Se eliminó una clasificación de producto", info);
        super.remove(entity);  
    }

    
}
