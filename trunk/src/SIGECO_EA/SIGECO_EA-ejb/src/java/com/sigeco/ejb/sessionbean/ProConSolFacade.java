/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Convocatoria;
import com.sigeco.ejb.entities.ProConSol;
import com.sigeco.ejb.entities.ProyectoInvestigacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author csacanam
 */
@Stateless
public class ProConSolFacade extends AbstractFacade<ProConSol>
{

    @PersistenceContext( unitName = "SIGECO_EA-ejbPU" )
    private EntityManager em;

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }

    public ProConSolFacade()
    {
        super( ProConSol.class );
    }

    /**
     * Permite consultar en la base de datos el proconsol de un proyecto en una convocatoria
     * @param pro es el proyecto de investigación
     * @param conv es la convocatoria a la cual pertenece el proyecto de investigación
     * @return List<Proconsol> 
     */
    public List<ProConSol> buscarProconsol( ProyectoInvestigacion pro, Convocatoria conv )
    {
        Query q = em.createQuery( "select proconsol from ProConSol proconsol where proconsol.proyectoInvestigacion.codigo = :pro AND proconsol.convocatoria.codigo = :con" );
        q.setParameter( "pro", pro.getCodigo() );
        if( conv==null){
            if( conv.getCodigo()==null){
                System.out.println( "codigo null" );
            }else
                System.out.println( "conv null" );
        }
        q.setParameter( "con", conv.getCodigo() );
        
        return q.getResultList();
    }

}
