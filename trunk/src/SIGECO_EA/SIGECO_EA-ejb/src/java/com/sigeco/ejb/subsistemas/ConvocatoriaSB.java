
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;
import com.sigeco.ejb.entities.ProyectoInvestigacion;
import com.sigeco.ejb.entities.Usuario;
import com.sigeco.ejb.entities.Convocatoria;
import com.sigeco.ejb.entities.FacultadConvocatoria;
import com.sigeco.ejb.entities.Estado;
import com.sigeco.ejb.entities.Facultad;
import com.sigeco.ejb.entities.FacultadConvocatoriaPK;
import com.sigeco.ejb.entities.Fase;
import com.sigeco.ejb.entities.ProConSol;
import com.sigeco.ejb.exceptions.ConvocatoriaException;
import com.sigeco.ejb.exceptions.FaseException;
import com.sigeco.ejb.exceptions.ProyectoException;
import com.sigeco.ejb.exceptions.UsuarioException;
import com.sigeco.ejb.sessionbean.ConvocatoriaFacade;
import com.sigeco.ejb.sessionbean.FacultadConvocatoriaFacade;
import com.sigeco.ejb.sessionbean.FacultadFacade;
import com.sigeco.ejb.sessionbean.FaseFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import static com.sigeco.ejb.utilities.InputChecks.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 <p>
 @author csacanam
 */
@Stateless
public class ConvocatoriaSB
{
    
    @EJB
    ConvocatoriaFacade convocatoriaFacade;
    
    @EJB
    FaseFacade faseFacade;
    
    @EJB
    private FacultadFacade facultadFacade;
    
    @EJB
    private FacultadConvocatoriaFacade facultadConvocatoriaFacade;
    
    @EJB
    EstadosSB estadosSB;
    
    @EJB
    ProyectosSB proyectosSB;
    
    @EJB
    AuditoriaSB auditoriaSB;
    
    @EJB
    UsuarioSB usuariosSB;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     Agrega una fase a una convocatoria determinada.
     <p>
     @param codConv - Código de la convocatoria.
     @param nomFase - Nombre de la fase.
     @param fechaI  - Fecha de inicio de la fase.
     @param fechaF  - Fecha de finalizacion de la fase.
     @throws FaseException
     */
    public void agregarFase( Long codConv, String nomFase, Date fechaI, Date fechaF ) throws FaseException
    {
        if( codConv == null || nomFase == null || fechaI == null || fechaF == null || nomFase.isEmpty() )
        {
            throw new FaseException( "Ingrese todos los datos." );
        }

        //Verificar coherencia de fechas
        if( fechaF.before( fechaI ) )
        {
            throw new FaseException( "La fecha de finalización debe ser posterior a la fecha de inicio." );
        }

        //Verificar que no existen fases en el mismo periodo de tiempo
        Convocatoria convocatoria = convocatoriaFacade.find( codConv );
        List<Fase> listaFases = convocatoria.getFaseList();
        for( Fase fase : listaFases )
        {
            if( ( fase.getFechaIni().after( fechaI ) && fase.getFechaFin().before( fechaF ) )
                || ( fase.getFechaIni().before( fechaI ) && fase.getFechaFin().after( fechaI ) )
                || ( fase.getFechaIni().before( fechaF ) && fase.getFechaFin().after( fechaF ) )
                || fase.getFechaIni().equals( fechaI ) || fase.getFechaIni().equals( fechaF )
                || fase.getFechaFin().equals( fechaI ) || fase.getFechaFin().equals( fechaF ) )
            {
                throw new FaseException( "Las fechas seleccionadas entran en conflicto con fechas de fases existentes." );
            }
        }

        //Crear fase
        Fase fase = new Fase();
        fase.setNombre(nomFase);
        System.out.println(fase.getNombre()+"");
        fase.setFechaIni(fechaI);
        System.out.println(fase.getFechaIni()+"");
        fase.setFechaFin(fechaF);
        System.out.println(fase.getFechaFin()+"");
        fase.setConCodigo(convocatoria);
        System.out.println(fase.getConCodigo().getCodigo()+"");
        faseFacade.create(fase);

        //Agregar la fase a la lista de fases
        listaFases.add( fase );
        convocatoria.setFaseList(listaFases);

        //Guardar la convocatoria       
        convocatoriaFacade.edit( convocatoria );
    }
    
    /**
     * Edita una fase con los nuevos datos
     * @param codConv - Código de la convocatoria a la que pertence la fase
     * @param codFase - Código de la fase a ser editada
     * @param nomFase - Nombre de la fase
     * @param fechaI - Fecha de inicio de la fase
     * @param fechaF - Fecha de finalización de la fase
     * @throws FaseException 
     */
    public void editarFase( Long codConv, Long codFase, String nomFase, Date fechaI, Date fechaF ) throws FaseException
    {
        if( nomFase == null || fechaI == null || fechaF == null || nomFase.isEmpty() )
        {
            throw new FaseException( "No es posible editar la fase. Hacen falta datos." );
        }

        //Verificar coherencia de fechas
        if( fechaF.before( fechaI ) )
        {
            throw new FaseException( "La fecha de finalización debe ser posterior a la fecha de inicio." );
        }

        //Verificar si hay conflictos con las fechas de otras fases.
        Convocatoria conv = convocatoriaFacade.find( codConv );
        List<Fase> fases = conv.getFaseList();
        for( Fase fase : fases )
        {
            if( ( fase.getFechaIni().after( fechaI ) && fase.getFechaFin().before( fechaF ) )
                || ( fase.getFechaIni().before( fechaI ) && fase.getFechaFin().after( fechaI ) )
                || ( fase.getFechaIni().before( fechaF ) && fase.getFechaFin().after( fechaF ) )
                || fase.getFechaIni().equals( fechaI ) || fase.getFechaIni().equals( fechaF )
                || fase.getFechaFin().equals( fechaI ) || fase.getFechaFin().equals( fechaF ) )
            {
                if(!Objects.equals(fase.getCodigo(), codFase) )
                {
                    throw new FaseException( "Las fechas seleccionadas entran en conflicto con fechas de fases existentes." );
                }
            }
        }
        
        Fase fase = faseFacade.find(codFase);
        fase.setNombre( nomFase );
        fase.setFechaIni( fechaI );
        fase.setFechaFin( fechaF );
        faseFacade.edit( fase );
    }
    
    /**
     * 
     * @param codConv
     * @param codFase
     * @throws FaseException 
     */
    public void eliminarFase(Long codConv, Long codFase) throws FaseException{
        
        if(codFase == null || codConv == null)
        {
            throw new FaseException( "No es posible eliminar la fase. Hacen falta datos." );
        }
        
        Convocatoria convocatoria = convocatoriaFacade.find(codConv);
        List<Fase> listaFases = convocatoria.getFaseList();
        
        Fase fase = faseFacade.find(codFase);
        
        listaFases.remove(fase);
        faseFacade.remove(fase);
        System.out.println(listaFases.size());
        convocatoria.setFaseList(listaFases);
        
        convocatoriaFacade.edit(convocatoria);
    }

    /**
     Este método permite saber la fecha en que inicia una convocatoria
     <p>
     @param codigoConvocatoria
     @return Date, correspondiente a la fecha inicial de la convocatoria
             solicitada
     */
    public Date getFechaInicioConvocatoria( long codigoConvocatoria )
    {
        Date anterior = null;
        Date siguiente = null;
        List<Fase> fases = faseFacade.findAll();
        for( Fase fase : fases )
        {
            if( fase.getConCodigo().getCodigo() == codigoConvocatoria && anterior != null )
            {
                siguiente = fase.getFechaIni();
                if( siguiente.before( anterior ) )
                {
                    anterior = siguiente;
                }
            }
            else
            {
                anterior = fase.getFechaIni();
            }
            
        }
        return anterior;
    }

    /**
     Este método permite saber la fecha en que finaliza una convocatoria
     <p>
     @param codigoConvocatoria
     @return Date, correspondiente a la fecha final de la convocatoria
             solicitada
     */
    public Date getFechaFinalConvocatoria( long codigoConvocatoria )
    {
        Date anterior = null;
        Date siguiente = null;
        List<Fase> fases = faseFacade.findAll();
        for( Fase fase : fases )
        {
            if( fase.getConCodigo().getCodigo() == codigoConvocatoria && siguiente != null )
            {
                anterior = fase.getFechaFin();
                if( siguiente.before( anterior ) )
                {
                    siguiente = anterior;
                }
            }
            else
            {
                siguiente = fase.getFechaFin();
            }
            
        }
        return siguiente;
    }
    
    public ConvocatoriaFacade getConvocatoriaFacade()
    {
        return convocatoriaFacade;
    }
    
    public FaseFacade getFaseFacade()
    {
        return faseFacade;
    }
    
    /**
     Este método devuelve la convocatoria actual que esta activa, o null si no hay
     * ninguna activa
     <p>
     @return Convocatoria, correspondiente a la convocatoria actuall o null
             si no hay ninguna activa
     */
    public Convocatoria darConvocatoriaActual(){
        Convocatoria c = null;
        List<Convocatoria> convocatorias = convocatoriaFacade.findAll();
        for (int x = 0; x < convocatorias.size(); x++) {
            if(convocatorias.get(x) != null && convocatorias.get(x).getEstCodigo().getCodigo() != null &&
               convocatorias.get(x).getEstCodigo().getCodigo() == EstadosSB.CONVOCATORIA_ACTIVO){
                c = convocatorias.get(x);
            }
        }
        return c;
    }
    
    
    /**
     Este método devuelve la convocatoria actual que esta activa, o null si no hay
     * ninguna activa
     <p>
     @return Convocatoria, correspondiente a la convocatoria actuall o null
             si no hay ninguna activa
     */
    public List<Convocatoria> darConvocatoriaPasadas(){
       
        List<Convocatoria> convocatorias = convocatoriaFacade.findAll();
        for (int x = 0; x < convocatorias.size(); x++) {
            if(convocatorias.get(x) != null && convocatorias.get(x).getEstCodigo().getCodigo() != null &&
               convocatorias.get(x).getEstCodigo().getCodigo() == EstadosSB.CONVOCATORIA_INACTIVO){
                convocatorias.add(convocatorias.get(x)) ;
            }
        }
        return convocatorias;
    }
 
    /**
    Crea una nueva convocatoria con las siguientes caracteristicas
    -Estado ACTIVO
    -Una sola fase de nombre "Registro"
    -Con el presupuesto(FacultadConvocatoria) igual al de la ultima convocatoria, o 0 si no hay convocatorias anterirores
    -Con una lista vacia de ProConSol
    @param nombre El nombre de la convocatoria, debe ser unico y no nulo
    @param fases Lista de fases a agregar
    @throws ConvocatoriaException Si algunos de los atributos es nulo o vacio
    @throws com.sigeco.ejb.exceptions.FaseException
    */
    public void crearConvocatoria( String nombre, List<Fase> fases ) throws ConvocatoriaException, FaseException
    {       
        if( isTrimEmptyOrNull( nombre ) )
        {
            throw new ConvocatoriaException( "El nombre de la convocatoria no debe ser nulo ni vacio" );
        }
        
        List<Convocatoria> listaConvocatorias = convocatoriaFacade.findBy( "Nombre", nombre );
        
        if( !listaConvocatorias.isEmpty() )
        {
            throw new ConvocatoriaException( "Ya existe una convocatoria con el nombre: " + nombre );
        }
        
        //Verificar si existe una convocatoria en estado Activo
        List<Convocatoria> convocatorias = convocatoriaFacade.findAll();
        for(Convocatoria convocatoria : convocatorias){
            if(convocatoria.getEstCodigo().getCodigo()==EstadosSB.CONVOCATORIA_ACTIVO)
                throw new ConvocatoriaException("Ya existe una convocatoria en estado activo");
        }
        
        //Crea los atributos de la convocatoria
        List<Fase> listaFases = new LinkedList<>();
        List<FacultadConvocatoria> listaPresupuesto = new LinkedList<>();
        Estado estado = new Estado( EstadosSB.CONVOCATORIA_ACTIVO );
        
        //Crea la convocatoria
        Convocatoria convocatoria = new Convocatoria();
        convocatoria.setNombre( nombre );
        convocatoria.setEstCodigo(estado );
        convocatoria.setFacultadConvocatoriaList( listaPresupuesto );
        convocatoria.setFaseList( listaFases );
        convocatoria.setProConSolList( new LinkedList<>() );
        
        convocatoriaFacade.create( convocatoria ); //Crea la convocatoria
        /*
        
         * Codigo para asignar automaticamente el presupuesto de la convocatoria pasada a las facultades de la convocatoria actual
        */
        Convocatoria conv=convocatoriaFacade.getUltimaConvocatoria();
        if(conv!=null){
            List<FacultadConvocatoria> facultadesConv = getFacultadConvocatoriaFacade().findBy("ConCodigo", conv.getCodigo());
            listaPresupuesto = new LinkedList<>();
            for( FacultadConvocatoria facultadConv : facultadesConv)
            {
                if( facultadConv.getConvocatoria()==null || facultadConv.getFacultad()==null){
                    facultadConv.setFacultad(getFacultadFacade().find(facultadConv.getFacultadConvocatoriaPK().getFacCodigo()) );
                    facultadConv.setConvocatoria( convocatoriaFacade.find(facultadConv.getFacultadConvocatoriaPK().getConCodigo()) );
                }
                FacultadConvocatoria facConv = new FacultadConvocatoria(convocatoria.getCodigo(), facultadConv.getFacultad().getCodigo());
                facConv.setPresupuesto(facultadConv.getPresupuesto() );
                facConv.setConvocatoria(convocatoria);
                facConv.setFacultad( facultadConv.getFacultad());
                getFacultadConvocatoriaFacade().create( facConv );
                listaPresupuesto.add( facConv);
            }  
        }else{
            List<Facultad> facultades = getFacultadFacade().findAll();
            for( Facultad facultad : facultades)
            {
                FacultadConvocatoria facConv=new FacultadConvocatoria(convocatoria.getCodigo(), facultad.getCodigo());
                facConv.setConvocatoria( convocatoriaFacade.find( facConv.getFacultadConvocatoriaPK().getConCodigo()) );
                facConv.setFacultad(getFacultadFacade().find( facConv.getFacultadConvocatoriaPK().getFacCodigo()));
                facConv.setPresupuesto( BigInteger.ZERO );
                getFacultadConvocatoriaFacade().create( facConv );
                listaPresupuesto.add( facConv);
            }
        }
        //convocatoria = convocatoriaFacade.findBy( "Nombre", nombre ).get( 0 );//Obtiene la convocatoria(y el codigo de esta)
        for(Fase lafase : fases){
            agregarFase(convocatoria.getCodigo(), lafase.getNombre(), lafase.getFechaIni(), lafase.getFechaFin());
        }
        
        
        //convocatoria.setFaseList( listaFases );
        //convocatoria.setFacultadConvocatoriaList( listaPresupuesto );
        
        //convocatoriaFacade.edit( convocatoria );
    }
    
    public void eliminarConvocatoria(Convocatoria convocatoria) throws ConvocatoriaException, FaseException{
        if(convocatoria.getProConSolList().size()>0){
            throw new ConvocatoriaException("Existen proyectos asociados a esta convocatoria");
        }
        
        List<Fase> fases = convocatoria.getFaseList();
         for(Fase fase : fases){
            eliminarFase(convocatoria.getCodigo(), fase.getCodigo());
        }
        
        List<FacultadConvocatoria> fcList = convocatoria.getFacultadConvocatoriaList();
        
        convocatoria.setFacultadConvocatoriaList(new ArrayList<>());
        convocatoriaFacade.edit(convocatoria);
        
        for(FacultadConvocatoria fc : fcList){                      
            facultadConvocatoriaFacade.remove(fc);
        }
   
        convocatoriaFacade.remove(convocatoria);
    }
    
    /**
     Verifica que:
     - El nombre.trim() de una fase no sea nulo ni vacio
     - La fecha de fin de una fase no se anterior a su fecha de inicio
     <p>
     @param fase La fasea verificar
     @throws FaseException Si no se cumple las verificaciones
     */
    public static void verificarFase( Fase fase ) throws FaseException
    {
        if( fase == null )
        {
            throw new FaseException( "La fase es no debe se nula" );
        }
        
        if( isEmptyOrNull( fase.getNombre() ) || fase.getFechaIni() == null || fase.getFechaFin() == null )
        {
            throw new FaseException( "La fase no tiene todos los datos validos" );
        }

        //Verificar coherencia de fechas
        if( fase.getFechaFin().before( fase.getFechaIni() ) )
        {
            throw new FaseException( "La fecha de finalización debe ser posterior a la fecha de inicio." );
        }
    }
    
    /**
     * Permite a un Lider de Investigacion Aprobar o Rechazar un Proyecto de 
     * Investigacion registrado en su grupo de investigacion
     * <p>
     * @param usuario El documento del usuario que representa al Lider de investigacion
     * @param proyecto El Codigo del ProyectoInvestigacion que se esta evaluando
     * @param aprueba El resultado de la evaluacion: true si aprueba, false en caso contrario
     * @param observaciones Las observaciones para el proyecto
     * @throws Exception Si no se cumple las verificaciones
     */
    public void evaluarProyectoPorLiderDeInvestigacion(String usuario, Long proyecto, boolean aprueba, String observaciones ) throws Exception{
        if(usuario == null ||  usuario.trim().equals("") ||
                proyecto == null || 
                observaciones == null ||  observaciones.trim().equals("")){
            throw new ConvocatoriaException("Alguno de los parametros para evaluar el proyecto por parte del decano es nulo o esta vacio");
        }
        Usuario u = usuariosSB.darUsuarioConDocumento(usuario);
        if(u == null)// Asumo que el usuario es Lider de Investigacion.
            throw new UsuarioException("El documento: "+usuario+" no corresponde a un usuario valido");
        else if(usuariosSB.esLiderDeGrupoDeInvestigacion(usuario)){
            proyectosSB.evaluarProyectoPorLiderDeInvestigacion(usuario, proyecto, aprueba, observaciones);
            
            String info[] =
            {
            "Documento Lider de Investigacion: " + usuario,
            "Codigo Proyecto de Investigacion Evaluado: " + proyecto,
            "Valor Evaluacion: " + aprueba,
            "Observaciones de la Evaluacion: " + observaciones
            };
            auditoriaSB.registrarLog( "Un lider de investigacion evaluo el proyecto de investigacion", info );
        }
        else{
            throw new UsuarioException("El usuario no corresponde a un Lider de Investigacion");
        }
    }
    
    /**
     * Permite a un Decano Aprobar o Rechazar un Proyecto de 
     * Investigacion registrado en su facultad
     * <p>
     * @param usuario El documento del usuario que representa al DEcano
     * @param proyecto El Codigo del ProyectoInvestigacion que se esta evaluando
     * @param aprueba El resultado de la evaluacion: true si aprueba, false en caso contrario
     * @param observaciones Las observaciones para el proyecto
     * @throws Exception Si no se cumple las verificaciones
     */
    public void evaluarProyectoPorDecano(String usuario, Long proyecto, boolean aprueba, String observaciones ) throws Exception{
        if(usuario == null ||  usuario.trim().equals("") ||
                proyecto == null || 
                observaciones == null ||  observaciones.trim().equals("")){
            throw new ConvocatoriaException("Alguno de los parametros para evaluar el proyecto por parte del decano es nulo o esta vacio");
        }
        Usuario u = usuariosSB.darUsuarioConDocumento(usuario);
        if(u == null)// Asumo que el usuario es Lider de Investigacion.
            throw new UsuarioException("El documento: "+usuario+" no corresponde a un usuario valido");
        else if(usuariosSB.esDecano(usuario)){
            proyectosSB.evaluarProyectoPorDecano(usuario, proyecto, aprueba, observaciones);

            String info[] =
            {
            "Documento Decano: " + usuario,
            "Codigo Proyecto de Investigacion Evaluado: " + proyecto,
            "Valor Evaluacion: " + aprueba,
            "Observaciones de la Evaluacion: " + observaciones
            };
            auditoriaSB.registrarLog( "Un lider de investigacion evaluo el proyecto de investigacion", info );
        }
        else{
            throw new UsuarioException("El usuario no corresponde a un Decano");
        }
    }
    
    /**
     * Este metodo permite saber la convocatoria a la que pertenece un proyecto de investigación
     * @param codProyecto El codigo del proyecto
     * @return La convocatoria a la que el proyecto pertenece
     */
    public Convocatoria darConvocatoriaProyecto(long codProyecto){
        ProyectoInvestigacion proyecto= proyectosSB.darProyectoConCodigo(codProyecto);
        List<ProConSol> proconsol=proyecto.getProConSolList();
        if(proconsol.size()>1){
             Date anterior = null;
             Date siguiente = null;
             Convocatoria masAntigua=null;
            for(ProConSol pcs:proconsol){
            Convocatoria conv=pcs.getConvocatoria();
            siguiente=getFechaInicioConvocatoria(pcs.getConvocatoria().getCodigo());
            if(anterior==null){
                anterior=siguiente;
            }
            if(anterior!=siguiente && anterior.after(siguiente)|| anterior.equals(siguiente)){
                anterior=siguiente;
                masAntigua=conv;
            }
        }
            return masAntigua;
        }
        else{
           return proconsol.get(0).getConvocatoria();
        }
    }
    
    /**
     * EDITAR FACULTAD CONVOCATORIA (HACER SQL?)
     * seguramente sobra
     * @param codConv
     * @param codFac
     * @param presupuesto 
     */
    public void editarFacultadConvocatoria(long codConv, BigInteger codFac, BigInteger presupuesto){
        /*
        Convocatoria conv = convocatoriaFacade.find( codConv );
        List<FacultadConvocatoria> facConvs = conv.getFacultadConvocatoriaList();
        for (FacultadConvocatoria facConv : facConvs) {
            if (facConv.getFacultad().getCodigo().equals(codFac)) {
                facConv.setPresupuesto(presupuesto);
                facultadConvocatoriaFacade.edit(facConv);
                break;
            }
        }
        */
    }

    /**
     * @return the facultadFacade
     */
    public FacultadFacade getFacultadFacade()
    {
        return facultadFacade;
    }

    /**
     * @return the facultadConvocatoriaFacade
     */
    public FacultadConvocatoriaFacade getFacultadConvocatoriaFacade()
    {
        return facultadConvocatoriaFacade;
    }

    public List<FacultadConvocatoria> crearConvocatoriasFacultad(Long codigo) {
        List<FacultadConvocatoria> retorno = new LinkedList<FacultadConvocatoria>();
        List<Facultad> facultades = getFacultadFacade().findAll();
        for( Facultad facultad : facultades)
        {
            FacultadConvocatoria facConv=new FacultadConvocatoria(codigo, facultad.getCodigo() );
            facConv.setConvocatoria( convocatoriaFacade.find( facConv.getFacultadConvocatoriaPK().getConCodigo()) );
            facConv.setFacultad(getFacultadFacade().find( facConv.getFacultadConvocatoriaPK().getFacCodigo()));
            facConv.setPresupuesto( BigInteger.ZERO );
            getFacultadConvocatoriaFacade().create( facConv );
            retorno.add( facConv);
        }
        Convocatoria conv = getConvocatoriaFacade().find(codigo);
        conv.setFacultadConvocatoriaList(retorno);
        getConvocatoriaFacade().edit(conv);
        return retorno; 
    }
}
