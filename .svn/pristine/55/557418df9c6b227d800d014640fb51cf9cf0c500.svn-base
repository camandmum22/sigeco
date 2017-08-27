/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Solicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author csacanam
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud>
{
    @PersistenceContext(unitName = "SIGECO_EA-ejbPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public SolicitudFacade()
    {
        super(Solicitud.class);
    }
    
}
