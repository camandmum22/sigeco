/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.subsistemas.AuditoriaSB;
import com.sigeco.ejb.entities.Facultad;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author csacanam
 */
@Stateless
public class FacultadFacade extends AbstractFacade<Facultad>
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

    public FacultadFacade()
    {
        super(Facultad.class);
    }

    @Override
    public List<Facultad> findAll()
    {
        auditoriaSB.registrarLog("Se muestran las facultades", new String[0]);
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public void create(Facultad entity)
    {
        String[] info = {"Código Facultad: "+entity.getCodigo(),"Nombre Facultad:"+entity.getNombre()};
        auditoriaSB.registrarLog("Creación Facultad", info);
        super.create(entity);
    }
    
    @Override
    public void edit(Facultad entity)
    {
        String[] info = {"Código Facultad: " + entity.getCodigo(),"Nombre Facultad: "+entity.getNombre()};
        auditoriaSB.registrarLog("Edición Facultad", info);
        super.edit(entity);
    }
    
    @Override
    public void remove(Facultad entity)
    {
        String[] info = {"Código Facultad: " + entity.getCodigo(),"Nombre Facultad: "+entity.getNombre()};
        auditoriaSB.registrarLog("Borrado Facultad", info);
        super.remove(entity);
    }


}
