/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Tarifa;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 <p>
 @author csacanam
 */
@Stateless
public class TarifaFacade extends AbstractFacade<Tarifa>
{
    @PersistenceContext( unitName = "SIGECO_EA-ejbPU" )
    private EntityManager em;

    @EJB
    AuditoriaSB auditoriaSB;

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public TarifaFacade()
    {
        super( Tarifa.class );
    }

    @Override
    public void create( Tarifa entity )
    {
        String[] info =
        {
            "Código Usuario: " + entity.getUsuario().getCodigo(),
            "Código Modalidad: " + entity.getModalidad().getCodigo(),
            "Valor: " + entity.getValor()
        };

        auditoriaSB.registrarLog( "Se creó una nueva tarifa", info );
        super.create( entity );
    }

    @Override
    public void edit( Tarifa entity )
    {
        String[] info =
        {
            "Código Usuario: " + entity.getUsuario().getCodigo(),
            "Código Modalidad: " + entity.getModalidad().getCodigo(),
            "Valor: " + entity.getValor()
        };

        auditoriaSB.registrarLog( "Se editó una tarifa", info );
        super.edit( entity );
    }

    @Override
    public void remove( Tarifa entity )
    {
        Tarifa tarifa = find( entity.getTarifaPK() );

        String[] info =
        {
            "Código Usuario: " + tarifa.getUsuario().getCodigo(),
            "Código Modalidad: " + tarifa.getModalidad().getCodigo(),
            "Valor: " + tarifa.getValor()
        };

        auditoriaSB.registrarLog( "Se eliminó un área estratégica", info );
        super.remove( entity );
    }
}
