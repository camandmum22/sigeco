/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.Fase;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
import java.math.BigDecimal;
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
public class FaseFacade extends AbstractFacade<Fase>
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
    public FaseFacade()
    {
        super(Fase.class);
    }
    
    @Override
    public void create(Fase entity){
        String info[] = {"Código: "+entity.getCodigo(), "Nombre: "+entity.getNombre(),
                        "Fecha de Inicio: "+entity.getFechaIni(), "Fecha finalización: "+entity.getFechaFin()};
        auditoriaSB.registrarLog("Se creó una fase", info);
        super.create(entity);
    }
    
    @Override
    public void edit(Fase entity){
        String info[] = {"Código: "+entity.getCodigo(), "Nombre: "+entity.getNombre(),
                        "Fecha de Inicio: "+entity.getFechaIni(), "Fecha finalización: "+entity.getFechaFin()};
        auditoriaSB.registrarLog("Se editó una fase", info);
        super.edit(entity);
    }
    
    @Override
    public void remove(Fase entity){
        String info[] = {"Código: "+entity.getCodigo(), "Nombre: "+entity.getNombre(),
                        "Fecha de Inicio: "+entity.getFechaIni(), "Fecha finalización: "+entity.getFechaFin()};
        auditoriaSB.registrarLog("Se eliminó una fase", info);
        super.remove(entity);
    }
    
    public Integer findMaxId(){
        List<BigDecimal> result = em.createQuery("SELECT CAST(fas.codigoFase AS DECIMAL) FROM Fase fas").getResultList();
        result.sort(null);
        return result.get(result.size()-1).intValue();
    }
    
}
