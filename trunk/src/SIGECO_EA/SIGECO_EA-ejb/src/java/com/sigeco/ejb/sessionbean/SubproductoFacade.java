/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Subproducto;
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
public class SubproductoFacade extends AbstractFacade<Subproducto>
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

    public SubproductoFacade()
    {
        super(Subproducto.class);
    }
    
    @Override
     public void create(Subproducto entity)
    {
        String info[] = {"Codigo Subproducto: "+entity.getCodigo(),"Codigo Categoría: "+entity.getCategoria(),"Codigo Peso Relativo1: "+entity.getPesoRelativo1(),"Codigo Peso Relativo2: "+entity.getPesoRelativo2(),"Codigo Requerimiento Calidad: "+entity.getRequerimientoCalidad()};
        auditoriaSB.registrarLog("Se creó un nuevo subproducto", info);
        super.create(entity);
    }
     
    @Override
    public void edit(Subproducto entity)
    {
        String info[] = {"Codigo Subproducto: "+entity.getCodigo(),"Codigo Categoría: "+entity.getCategoria(),"Codigo Peso Relativo1: "+entity.getPesoRelativo1(),"Codigo Peso Relativo2: "+entity.getPesoRelativo2(),"Codigo Requerimiento Calidad: "+entity.getRequerimientoCalidad()};
        auditoriaSB.registrarLog("Se modificó un subproducto", info);
        super.edit(entity);
    }
    
    @Override
    public void remove(Subproducto entity)
    {
        String info[] = {"Codigo Subproducto: "+entity.getCodigo(),"Codigo Categoría: "+entity.getCategoria(),"Codigo Peso Relativo1: "+entity.getPesoRelativo1(),"Codigo Peso Relativo2: "+entity.getPesoRelativo2(),"Codigo Requerimiento Calidad: "+entity.getRequerimientoCalidad()};
        auditoriaSB.registrarLog("Se eliminó el subproducto", info);
        super.remove(entity);
    }
   
    
}
