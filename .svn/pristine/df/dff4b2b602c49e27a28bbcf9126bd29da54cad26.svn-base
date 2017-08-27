/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.sessionbean;

import com.sigeco.ejb.entities.ProyectoInvestigacion;
import com.sigeco.ejb.subsistemas.AuditoriaSB;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class ProyectoInvestigacionFacade extends AbstractFacade<ProyectoInvestigacion>
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

    public ProyectoInvestigacionFacade()
    {
        super(ProyectoInvestigacion.class);
    }

    @Override
    public void create(ProyectoInvestigacion entity)
    {
        //Información adicional
        String info[] =
        {
            "Título proyecto: " + entity.getTitulo()
        };
        //Registrar log
        auditoriaSB.registrarLog("Se creó un proyecto de investigación", info);
        //Crear estado
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer findMaxId()
    {
        List<BigDecimal> resultado = em.createQuery("SELECT CAST(p.codigoProyecto AS DECIMAL) FROM ProyectoInvestigacion p").getResultList();
        resultado.sort(null);
        if(resultado.size() > 0)
        {
           return resultado.get(resultado.size() - 1).intValue();
        }
        else
        {
            return 0;
        }
    }
    
    public List<ProyectoInvestigacion> getProyectosPorGrupo(String grupo){
        List<ProyectoInvestigacion> retorno = em.createQuery("SELECT p FROM ProyectoInvestigacion p WHERE p.grupoLinea.grupoInvestigacion.codigo=:grupo AND p.estCodigo.codigo=3").setParameter("grupo", grupo).getResultList();
        return retorno;
    }

    public List<ProyectoInvestigacion> getProyectosPorFacultad(BigInteger facultad){
        List<ProyectoInvestigacion> retorno = em.createQuery("SELECT p FROM ProyectoInvestigacion p WHERE p.grupoLinea.grupoInvestigacion.facCodigo=:facultad AND (p.estCodigo.codigo=4 OR p.estCodigo.codigo=6)").setParameter("facultad", facultad).getResultList();
        return retorno;
    }
    
    public List<ProyectoInvestigacion> getProyectosPorFacultadAprobados(){
        List<ProyectoInvestigacion> retorno = em.createQuery("SELECT p FROM ProyectoInvestigacion p WHERE p.estCodigo.codigo=5").getResultList();
        return retorno;
    }
}
