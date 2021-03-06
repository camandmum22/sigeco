/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto>
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

    public ProductoFacade()
    {
        super(Producto.class);
    }
   
    @Override
    public void create(Producto entity){
        
        String[] info = {"Código Producto: " + entity.getCodigo(), "Nombre del producto: " + entity.getNombre(), "Código clasificación del producto: " + entity.getClaProCodigo().getCodigo()};
                
        auditoriaSB.registrarLog("Creación producto", info);
        
        super.create(entity);

    }
    @Override
    public void edit (Producto entity){
         String[] info = {"Código Producto: " + entity.getCodigo(), "Nombre del producto: " + entity.getNombre(), "Código clasificación del producto: " + entity.getClaProCodigo().getCodigo()};
                
        auditoriaSB.registrarLog("Edición producto", info);
        
        super.edit(entity);
    }
    @Override
    public void remove (Producto entity){
         String[] info = {"Código Producto: " + entity.getCodigo(), "Nombre del producto: " + entity.getNombre(), "Código clasificación del producto: " + entity.getClaProCodigo().getCodigo()};
                
        auditoriaSB.registrarLog("Borrado producto", info);
        
        super.remove(entity);
    }
    
    
    
}
