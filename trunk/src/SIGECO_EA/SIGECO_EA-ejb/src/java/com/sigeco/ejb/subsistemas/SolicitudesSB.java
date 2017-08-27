/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.csvreader.CsvReader;
import com.sigeco.ejb.entities.Convocatoria;
import com.sigeco.ejb.entities.Estado;
import com.sigeco.ejb.entities.PcsRubro;
import com.sigeco.ejb.entities.ProConSol;
import com.sigeco.ejb.entities.ProConSolPK;
import com.sigeco.ejb.entities.ProyectoInvestigacion;
import com.sigeco.ejb.entities.Rubro;
import com.sigeco.ejb.entities.Solicitud;
import com.sigeco.ejb.sessionbean.ConvocatoriaFacade;
import com.sigeco.ejb.sessionbean.EstadoFacade;
import com.sigeco.ejb.sessionbean.PcsRubroFacade;
import com.sigeco.ejb.sessionbean.ProConSolFacade;
import com.sigeco.ejb.sessionbean.ProyectoInvestigacionFacade;
import com.sigeco.ejb.sessionbean.RubroFacade;
import com.sigeco.ejb.sessionbean.SolicitudFacade;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.EJBException;

/**
 *
 * @author csacanam
 */
@Stateless
public class SolicitudesSB
{

    //Solcitud de proyecto nuevo (PN) 
    public final static String SOLICITUD_REGISTRO = "PN";

    //solicitud de adición a un proyecto
    public final static String SOLICITUD_ADICION = "A";

    //solicitud de prorroga de un proyecto
    public final static String SOLICITUD_PRORROGA = "P";

    @EJB
    private SolicitudFacade solicitudFacade;

    @EJB
    private RubroFacade rubroFacade;

    @EJB
    private ProyectoInvestigacionFacade proyectoInvestigacionFacade;

    @EJB
    private ConvocatoriaFacade convocatoriaFacade;

    @EJB
    private EstadoFacade estadoFacade;

    @EJB
    private PcsRubroFacade pcsRubroFacade;

    @EJB
    private ProConSolFacade proConSolFacade;

    @EJB
    ConvocatoriaSB convocatoriaSB;

    @EJB
    EstadosSB estadosSB;

    @EJB
    ProyectosSB proyectosSB;

    @EJB
    PresupuestoSB presupuestoSB;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public SolicitudesSB()
    {

    }

    public RubroFacade getRubroFacade()
    {
        return rubroFacade;
    }

    public void setRubroFacade( RubroFacade rubroFacade )
    {
        this.rubroFacade = rubroFacade;
    }

    /**
     * Este método permite registrar una solicitud de prórroga
     * <p>
     * @param input              El flujo correspondiente al archivo subido
     * @param codigoProyecto     El código de un proyecto de investigación
     * @param codigoConvocatoria El código de una convocatoria
     * <p>
     * @throws IOException  Cuando no hay algún problema con el stram
     * @throws EJBException Se lanza cuando se intenta guardar una solicitud
     *                      existente en la BD
     */
    public void guardarSolicitud( InputStream input, long codigoProyecto, long codigoConvocatoria ) throws IOException, EJBException
    {
        ProConSolPK pk = new ProConSolPK();
        ProyectoInvestigacion p = proyectoInvestigacionFacade.find( codigoProyecto );
        Convocatoria c = convocatoriaSB.darConvocatoriaProyecto( codigoProyecto );
        Solicitud s = solicitudFacade.find( SOLICITUD_PRORROGA );
        pk.setConCodigo( c.getCodigo() );
        pk.setProInvCodigo( p.getCodigo() );
        pk.setSolCodigo( s.getCodigo() );
        long l = 7;
        Estado e = estadoFacade.find( l );
        ProConSol pcs = new ProConSol();
        pcs.setProConSolPK( pk );
        pcs.setEstCodigo( e );
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        for( int length = 0; ( length = input.read( buffer ) ) > 0; ){
            output.write( buffer, 0, length );
        }
        byte[] b = output.toByteArray();
        pcs.setCronograma( b );
        proConSolFacade.create( pcs );
    }

    /**
     * Permite guardar la solicitud de adición para un proyecto de investigación
     * en una convocatoria anterior
     * <p>
     * @param codProyecto es el código del proyecto de investigación 
     * @param input es el archivo de excel que contiene los rubros
     * <p>
     * @throws IOException
     */
    public void guardarSolicitudAdicion( long codProyecto, InputStream input ) throws IOException
    {
        ProyectoInvestigacion proy = proyectoInvestigacionFacade.find( codProyecto );
        Convocatoria c = convocatoriaSB.darConvocatoriaProyecto( codProyecto );
        System.err.println( "codConv " + c.getCodigo() );

        if( proy != null ){

            //Crear un ProConSol y un ProConSolPK para un proyecto de una convocatoria pasada con solicitud adición
            ProConSol pcs = new ProConSol();
            ProConSolPK pcspk = new ProConSolPK();

            //Definir los atributos de ProConSolP´K
            pcspk.setConCodigo( c.getCodigo() );
            pcspk.setProInvCodigo( proy.getCodigo() );
            pcspk.setSolCodigo( getSolicitudFacade().find( SolicitudesSB.SOLICITUD_ADICION ).getCodigo() );

            //Definir el atributo ProConSolPK y el estado de ProConSol
            pcs.setProConSolPK( pcspk );
            pcs.setEstCodigo( estadosSB.getEstadoFacade().find( EstadosSB.PROCONSOL_PENDIENTE ) );

            //Registrar el ProConSol
            proConSolFacade.create( pcs );

            leerArchivoRubros( input, proy, c );

        }

    }

    /**
     * Permite leer el archivo de rubros de una solicitud de adición
     * <p>
     * @param file     es el archivo de excel que contiene los rubros
     * @param proyecto es el proyecto de investigación que se le solicita adicón
     * @param conv     es la convocatoria a la cual pertenece el proyecto
     * <p>
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void leerArchivoRubros( InputStream file, ProyectoInvestigacion proyecto, Convocatoria conv ) throws FileNotFoundException, IOException
    {

        CsvReader input = new CsvReader( new BufferedReader( new InputStreamReader( file ) ) );
        input.readHeaders();
        //Proconsol de un proyecto en una convocatoria
        List<ProConSol> proconsol = proConSolFacade.buscarProconsol( proyecto, conv );

        for( ProConSol proconsol1 : proconsol ){

            System.err.println( "encontró proconsol" );

            List<PcsRubro> pcsRubros = proconsol1.getPcsRubroList();

            while( input.readRecord() ){
                //buscar el codigo del rubro y guardar la cantidad en pcs
                Long codigo = Long.parseLong( input.get( "codigo" ) );
                //String nombre = input.get( "nombre" );
                //String desc = input.get( "descripcion" );
                BigInteger cantidad = new BigInteger( input.get( "cantidad" ) );

                for( PcsRubro pcsRubro : pcsRubros ){
                    if( codigo == pcsRubro.getRubro().getCodigo() ){
                        pcsRubro.setCantidad( cantidad );
                        pcsRubroFacade.edit( pcsRubro );
                    }

                }

            }
        }
    }

    /**
     * @return the solicitudFacade
     */
    public SolicitudFacade getSolicitudFacade()
    {
        return solicitudFacade;
    }

    /**
     * @param solicitudFacade the solicitudFacade to set
     */
    public void setSolicitudFacade( SolicitudFacade solicitudFacade )
    {
        this.solicitudFacade = solicitudFacade;
    }

    /**
     * @return the proyectoInvestigacionFacade
     */
    public ProyectoInvestigacionFacade getProyectoInvestigacionFacade()
    {
        return proyectoInvestigacionFacade;
    }

    /**
     * @param proyectoInvestigacionFacade the proyectoInvestigacionFacade to set
     */
    public void setProyectoInvestigacionFacade( ProyectoInvestigacionFacade proyectoInvestigacionFacade )
    {
        this.proyectoInvestigacionFacade = proyectoInvestigacionFacade;
    }

    /**
     * @return the convocatoriaFacade
     */
    public ConvocatoriaFacade getConvocatoriaFacade()
    {
        return convocatoriaFacade;
    }

    /**
     * @param convocatoriaFacade the convocatoriaFacade to set
     */
    public void setConvocatoriaFacade( ConvocatoriaFacade convocatoriaFacade )
    {
        this.convocatoriaFacade = convocatoriaFacade;
    }

    /**
     * @return the estadoFacade
     */
    public EstadoFacade getEstadoFacade()
    {
        return estadoFacade;
    }

    /**
     * @param estadoFacade the estadoFacade to set
     */
    public void setEstadoFacade( EstadoFacade estadoFacade )
    {
        this.estadoFacade = estadoFacade;
    }

}
