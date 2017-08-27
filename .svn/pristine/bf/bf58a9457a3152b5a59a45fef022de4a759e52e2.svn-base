/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.sigeco.ejb.entities.Usuario;
import com.sigeco.ejb.entities.Investigador;
import com.sigeco.ejb.entities.Facultad;
import com.sigeco.ejb.entities.FacultadConvocatoria;
import com.sigeco.ejb.entities.GrupoInvestigacion;
import com.sigeco.ejb.entities.GrupoLinea;
import com.sigeco.ejb.entities.ProyectoInvestigacion;
import com.sigeco.ejb.sessionbean.AreaEstrategicaFacade;
import com.sigeco.ejb.sessionbean.FacultadConvocatoriaFacade;
import com.sigeco.ejb.sessionbean.FacultadFacade;
import com.sigeco.ejb.sessionbean.GrupoInvestigacionFacade;
import com.sigeco.ejb.sessionbean.GrupoLineaFacade;
import com.sigeco.ejb.sessionbean.LineaInvestigacionFacade;
import com.sigeco.ejb.sessionbean.UsuarioFacade;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author csacanam
 */
@Stateless
public class GrupoInvestigacionSB {

    @EJB
    AreaEstrategicaFacade areaEstrategicaFacade;

    @EJB
    FacultadFacade facultadFacade;
    
    @EJB
    FacultadConvocatoriaFacade facultadConvocatoriaFacade;

    @EJB
    GrupoInvestigacionFacade grupoInvestigacionFacade;

    @EJB
    LineaInvestigacionFacade lineaInvestigacionFacade;
    
    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private GrupoLineaFacade grupoLineaFacade;

    @EJB
    private ProyectosSB proyectosSB;
    
    @EJB
    private UsuarioSB usuarioSB;
    
    @EJB
    private ConvocatoriaSB convocatoriaSB;

    // Lógica especializada
    
    /**
     * Calcula el presupuesto de una facultad sumando los presupuestos asignados
     * de cada proyecto pertenciente a dicha facultad.
     * @param facultad Facultad a calcularle el presupuesto
     * @return presupuesto de una facultad
     */
    public BigInteger calcularPresupuesto(Facultad facultad) {

        //Proyectos de una facultad en la convocatoria actual
        List<ProyectoInvestigacion> proList = proyectosSB.darProyectosFacultadEnConvActual(facultad);
        //Variable para guardar el presupuesto de la facultad
        BigInteger presupuesto = BigInteger.valueOf(new Long(0));
        for (ProyectoInvestigacion pro : proList) {
            //Sumar el presupuesto de cada proyecto
            presupuesto = presupuesto.add(pro.getPresupuestoAsignado());           
        }
        return presupuesto;
    }
    
    /**
     * Aprueba el presupuesto de una facultad dada la facultad y su presupuesto
     * @param facultad Facultad a ser aprobada
     * @param presupuesto Presupuesto de la facultad a ser aprobado
     */
    public void aprobarPresupuestoPorComiteInvestigacion(Facultad facultad, BigInteger presupuesto){
        //Busca las facultadesConvocatoria con el código de la facultad dada.
        List<FacultadConvocatoria> fcList = facultadConvocatoriaFacade.findBy("FacCodigo", facultad.getCodigo());
        //Código de la convocatoria actual
        Long convAct = convocatoriaSB.darConvocatoriaActual().getCodigo();
        for(FacultadConvocatoria fc : fcList){
            //Recorre el arreglo para buscar las facultadesConvocatoria con el 
            //código de la convocatoria actual
            if(Objects.equals(fc.getConvocatoria().getCodigo(), convAct)){
                //Si cumple, agrega modifica el presupuesto de la facultad en esa
                //convocatoria
                fc.setPresupuesto(presupuesto); 
                facultadConvocatoriaFacade.edit(fc);
            }
        }         
    }

    /**
     * Permite obrtener todos los grupo linea asociados a un grupo de
     * investigación
     *
     * @param grupoInv Grupo de investigación
     * @return Lista de grupos línea que están asociados a un grupo de
     * investigación
     */
    public List<GrupoLinea> getGrupoLineaFromGrupoInv(GrupoInvestigacion grupoInv) {
        Query gruposLinea = grupoLineaFacade.getEntityManager().createNamedQuery("GrupoLinea.findByCodigoGrupo").setParameter("codigoGrupo", grupoInv.getCodigo());
        return gruposLinea.getResultList();
    }
     /**
     * Permite obtener todos los grupos de investigación
     * @return Lista de todos los grupos de investigación
     */
    public List<GrupoInvestigacion> getGruposDeInvestigacion()
    {
        List<GrupoInvestigacion> grupos = new ArrayList<GrupoInvestigacion>();
        for (GrupoInvestigacion grupo : grupoInvestigacionFacade.findAll()) {
            if(grupo!= null && grupo.getInvestigadorList()!= null && !grupo.getInvestigadorList().isEmpty()){
                grupos.add(grupo);
            }
        }
        return grupos;
    }
    
     /**
     * Permite obtener un grupo de investigación de acuerdo a su codigo
     * @param codigo codigo del Grupo de investigación
     * @return GrupoInvestigacion grupo de investigación
     */
    public GrupoInvestigacion getGrupoConCodigo(String codigo){
        if(codigo!=null &&  !codigo.isEmpty()){
            List<GrupoInvestigacion> grupos = grupoInvestigacionFacade.findBy("Codigo", codigo);
            if(grupos!= null && !grupos.isEmpty()){
                return grupos.get(0);
            } 
        }
        return null;
    }
    
    /**
     * Permite agregar un usuario investigador a un grupo de investigación con su documento
     * @param documentoLider documento del lider de investigacion del grupo
     * @param documentoInvestigador documento del usuario investigador
     * @return boolean si se agrego o no el usuario
     */
    public boolean agregarInvestigadorAGrupo(String documentoLider, String documentoInvestigador){
        boolean agregado = false;
        if(usuarioSB.esLiderDeGrupoDeInvestigacion(documentoLider) && usuarioSB.esInvestigador(documentoInvestigador)){
            Usuario lider = usuarioSB.darUsuarioConDocumento(documentoLider);
            Usuario investigador = usuarioSB.darUsuarioConDocumento(documentoInvestigador);
            GrupoInvestigacion grupo = usuarioSB.darGrupoDeInvestigacionLiderDeInvestigacion(documentoLider);
            if(lider!= null && investigador !=null && grupo !=null && investigador.getCodigo() !=null && grupo.getCodigo() !=null){
                Investigador i = new Investigador(grupo.getCodigo(), investigador.getCodigo());
                List<Investigador> invGrupo = grupo.getInvestigadorList();
                List<Investigador> invInvestigador = investigador.getInvestigadorList();
                invGrupo.add(i); invInvestigador.add(i);
                grupo.setInvestigadorList(invGrupo);
                investigador.setInvestigadorList(invInvestigador);
                grupoInvestigacionFacade.edit(grupo);
                usuarioFacade.edit(investigador);
                agregado = true;
            }
        }
        return agregado;
    }
    

    // Getters para el CRUD
    public AreaEstrategicaFacade getAreaEstrategicaFacade() {
        return areaEstrategicaFacade;
    }

    public FacultadFacade getFacultadFacade() {
        return facultadFacade;
    }

    public FacultadConvocatoriaFacade getFacultadConvocatoriaFacade(){
        return facultadConvocatoriaFacade;
    }
      
    public GrupoInvestigacionFacade getGrupoInvestigacionFacade() {
        return grupoInvestigacionFacade;
    }

    public LineaInvestigacionFacade getLineaInvestigacionFacade() {
        return lineaInvestigacionFacade;
    }

    public GrupoLineaFacade getGrupoLineaFacade() {
        return grupoLineaFacade;
    }

}
