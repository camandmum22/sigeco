/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.LineaInvestigacion;
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
public class LineaInvestigacionFacade extends AbstractFacade<LineaInvestigacion>
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

    public LineaInvestigacionFacade()
    {
        super(LineaInvestigacion.class);
    }
    
    @Override
    public void create(LineaInvestigacion entity)
    {
        //Información
        String info[] = {entity.getCodigo(), entity.getNombre(), entity.getDescripcion()};
        //Registrar log
        auditoriaSB.registrarLog("Se creó una línea de investigación", info);
        //Crear linea de investigación
        super.create(entity);
    }
    
    @Override
    public void edit(LineaInvestigacion entity)
    {
        //Información
        String info[] = {entity.getCodigo(), entity.getNombre(), entity.getDescripcion()};
        //Registrar log
        auditoriaSB.registrarLog("Se editó una línea de investigación", info);
        //Crear linea de investigación
        super.edit(entity);
    }
    
    @Override
    public void remove(LineaInvestigacion entity)
    {
        //Información
        String info[] = {entity.getCodigo(), entity.getNombre(), entity.getDescripcion()};
        //Registrar log
        auditoriaSB.registrarLog("Se elimino una línea de investigación", info);
        //Crear linea de investigación
        super.remove(entity);
    }
    
}
