/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Entregable;
import com.sigeco.ejb.entities.ProyectoInvestigacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author csacanam
 */
@Stateless
public class EntregableFacade extends AbstractFacade<Entregable>
{

    @PersistenceContext(unitName = "SIGECO_EA-ejbPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public EntregableFacade()
    {
        super(Entregable.class);
    }

    public Integer findMaxId()
    {
        return (Integer) em.createQuery("SELECT coalesce(max(e.codigo),0) FROM Entregable e").getSingleResult();
    }

    public Entregable findByParameters(String pNombre, long pCodigoProyecto)
    {
        try
        {
            return (Entregable) em.createQuery("SELECT e FROM Entregable e WHERE e.nombre = :nombre AND e.codigoProyecto = :proyecto").setParameter("nombre", pNombre).setParameter("proyecto", pCodigoProyecto).getSingleResult();
        } catch (Exception e)
        {
            return null;
        }
    }

}
