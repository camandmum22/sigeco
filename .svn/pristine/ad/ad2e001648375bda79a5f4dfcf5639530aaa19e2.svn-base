/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.csvreader.CsvReader;
import com.sigeco.ejb.entities.Facultad;
import com.sigeco.ejb.entities.GrupoInvestigacion;
import com.sigeco.ejb.entities.Investigador;
import com.sigeco.ejb.entities.Modalidad;
import com.sigeco.ejb.entities.PcsRubro;
import com.sigeco.ejb.entities.RolSistema;
import com.sigeco.ejb.entities.Tarifa;
import com.sigeco.ejb.entities.TarifaPK;
import com.sigeco.ejb.entities.Usuario;
import com.sigeco.ejb.exceptions.UsuarioException;
import com.sigeco.ejb.sessionbean.InvestigadorFacade;
import com.sigeco.ejb.sessionbean.ModalidadFacade;
import com.sigeco.ejb.sessionbean.TarifaFacade;
import com.sigeco.ejb.sessionbean.UsuarioFacade;
import com.sigeco.ejb.utilities.CipherAlgorithms;
import com.sigeco.ejb.utilities.Validaciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * <p>
 * @author csacanam
 */
@Stateful
public class UsuarioSB
{

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private TarifaFacade tarifaFacade;
    
    @EJB
    private GrupoInvestigacionSB grupoInvestigacionSB;
    
    @EJB
    private InvestigadorFacade investigadorFacade;
    
    @EJB
    private ModalidadFacade modalidadFacade;
    
    public UsuarioFacade getUsuarioFacade()
    {
        return usuarioFacade;
    }

    public void setUsuarioFacade( UsuarioFacade usuarioFacade )
    {
        this.usuarioFacade = usuarioFacade;
    }

    public TarifaFacade getTarifaFacade()
    {
        return tarifaFacade;
    }

    public void setTarifaFacade( TarifaFacade tarifaFacade )
    {
        this.tarifaFacade = tarifaFacade;
    }

    /**
     * Valida si la contraseña del usuario indentificado con el documento es la
     * registrada en el sistema
     * <p>
     * @param documento   El documento del usuario
     * @param contrasenia La contrasenia que se quiere validar
     * <p>
     * @return El usuario o null si no es valida o el usuario no existe
     */
    public Usuario isValidPassword( String documento, String contrasenia ) throws Exception
    {
//        Query query = usuarioFacade.getEntityManager().createNamedQuery("Usuario.findByDocumento", Usuario.class).setParameter("documento", documento);
//        List<Usuario> lista = query.getResultList();

        List<Usuario> lista = usuarioFacade.findBy( "Documento", documento );

        if( !lista.isEmpty() )
        {
            Usuario usuario = lista.get( 0 );

            if( CipherAlgorithms.decryptAES( usuario.getPass() ).equals( contrasenia ) )
            {
                return usuario;
            }
        }

        return null;
    }

    /**
     * Devuleve el objeto Usuario asociado a un documento, si no existe ningun
     * usuario asociado a ese documento devulve null
     * <p>
     * @param documento El documento del usuario
     * <p>
     * @return El usuario o null si no es valida o el usuario no existe
     */
    public Usuario darUsuarioConDocumento( String documento )
    {
        Usuario u = null;
        List<Usuario> usus = usuarioFacade.findBy( "Documento", documento );
        if( !usus.isEmpty() && usus.get( 0 ) != null )
        {
            u = usus.get( 0 );
        }
        return u;
    }
    
    /**
     * Metodo que registra las tarifas por hora de un usuario por cada  modalidad 
     * (tipo de vinculación) que tenga, mediante un archivo de Excel. 
     * @param file Archivo de entrada
     * @throws IOException 
     */
    public void leerArchivoModalidadesTarifas ( InputStream file) throws IOException{
        CsvReader input = new CsvReader( new BufferedReader( new InputStreamReader( file ) ) );
        input.readHeaders();
    
        
         while( input.readRecord() ){
                
             try {
             String documentoUsuario = input.get( "Documento" ) ;
             String nombreModalidad = input.get("Modalidad");
             BigInteger valorTarifa = new BigInteger( input.get( "Valor" ) );
             asociarTarifaUsuario(documentoUsuario, nombreModalidad, valorTarifa);
             
             } 
             
             catch ( Exception e ){
                 
             }

            }
    }
    
    /**
     * Metodo que adiciona una tarifa a un usuario en una modalidad
     * - Si el usuario no existe ( dado un documento del usuario ) no se asocia ninguna tarifa
     * - Si el usuario ya tiene la modalidad, el valor de la tarifa se actualiza
     * - Si el usuario no tiene la modalidad , se crea una nueva tarifa 
     * @param usuarioId El documento del usuario
     * @param nombreModalidad Nombre de la modalidad
     * @param valorTarifa  Valor de la tarifa ( por hora )
     */
    public void asociarTarifaUsuario ( String usuarioId , String nombreModalidad , BigInteger valorTarifa){
        
        Usuario usu = darUsuarioConDocumento(usuarioId);
        if ( usu != null){
         List<Tarifa> listaTarifas = usu.getTarifaList();         
         List<Modalidad> modalidadList = modalidadFacade.findBy("Nombre", nombreModalidad);
         
         if ( !modalidadList.isEmpty()){
             Modalidad mod = modalidadList.get(0);
             boolean tieneModalidad = false;
             for ( Tarifa tarifa: listaTarifas){
                 tarifa.setModalidad(modalidadFacade.find(tarifa.getTarifaPK().getModCodigo()));
                 if ( tarifa.getModalidad().equals(mod)){
                     tieneModalidad = true;
                     tarifa.setValor(valorTarifa);
                     break;
                 }
             }
             
             if ( tieneModalidad){
                 usu.setTarifaList(listaTarifas);
                 usuarioFacade.edit(usu);
             }
             
             else {
                 Tarifa tarifa = new Tarifa(new TarifaPK(usu.getCodigo(), mod.getCodigo()), valorTarifa);
                 listaTarifas.add(tarifa);
                 usu.setTarifaList(listaTarifas);
                 usuarioFacade.edit(usu);
             }
         }
        }
    }
    
    
    /**
     * Permite conocer si el usuario asociado a un documento en Investigador
     * <p>
     * @param documento El documento del usuario
     * <p>
     * @return El usuario o null si no es valida o el usuario no existe
     */
    public boolean esInvestigadorVersion2( String documento )
    {
        boolean investigador = false;
        Usuario u = darUsuarioConDocumento(documento);
        if (u!=null && u.getInvestigadorList()!= null && !u.getInvestigadorList().isEmpty() ){
            investigador = true;
        }
        return investigador;
    }
    
    /**
     * Permite obtener todos los ususarios que son investigadores
     * @return Lista de todos los ususarios que son investigadores
     */
    public List<Usuario> getInvestigadores()
    {
        List<Usuario> investigadores = new ArrayList<Usuario>();
        for (Usuario usuario : usuarioFacade.findAll()) {
            if(usuario!= null && usuario.getInvestigadorList()!= null && !usuario.getInvestigadorList().isEmpty()){
                investigadores.add(usuario);
            }
        }
        return investigadores;
    }
    
    /*
     * Permite obtener los investigadores de un grupo de investigacion
    * @param codigo codigo del Grupo de investigación
     * @return Lista de los investigadores  de un grupo de investigacion
    */
    public List<Usuario> getInvestigadoresDeGrupo(String codigo)
    {
        List<Usuario> investigadores = new ArrayList<Usuario>();
        GrupoInvestigacion grupo = grupoInvestigacionSB.getGrupoConCodigo(codigo);
        if(grupo != null && grupo.getInvestigadorList()!= null && !grupo.getInvestigadorList().isEmpty()){
            for (Investigador investigador : grupo.getInvestigadorList()) {
                if(investigador!= null && investigador.getUsuario()!=null && investigador.getUsuario().getCodigo()!=null){
                    investigadores.add(investigador.getUsuario());
                }
            }
        }
        return investigadores;
    }    
    
    /**
     * Permite verificar si el usuario asociado a un domuento corresponde al
     * Lider de un Grupo de Investigacion
     * <p>
     * @param documento El documento del usuario
     * @return lider. 
     */
    public boolean esLiderDeGrupoDeInvestigacion( String documento )
    {
        boolean lider = false;
        Usuario u = darUsuarioConDocumento(documento);
        if( u!= null && u.getInvestigadorList() != null && !u.getInvestigadorList().isEmpty() )
        {
            for( Investigador in : u.getInvestigadorList() )
            {
                String esLider = in.getEslider().toUpperCase();
                if( esLider != null && ( esLider.startsWith( "Y" ) || esLider.startsWith( "S" ) ) )
                {
                    lider = true;
                }
            }
        }
        return lider;
    }

    /**
     * Permite verificar si el usuario asociado a un domuento corresponde al
     * Decano
     * <p>
     * @param documento El documento del usuario
     * @return esDecano
     */
    public boolean esDecano( String documento )
    {
        boolean esDecano = false;
        Usuario u = darUsuarioConDocumento(documento);
        if( u != null && u.getFacCodigo() != null && u.getFacCodigo().getCodigo() != null )
        {
            esDecano = true;
        }
        return esDecano;
    }

    /**
     * Devuelve la facultad del usuario asociado a un domuento que corresponde
     * al Decano
     * <p>
     * @param documento El documento del usuario
     * @return f-Facultad a la que pertenece el decano.
     */
    public Facultad darFacultadDecano( String documento )
    {
        Facultad f = null;
        if(esDecano(documento)){
            Usuario u = darUsuarioConDocumento(documento);
            f = u.getFacCodigo();
        }
        return f;
    }

    /**
     * Devuelve el grupo de investigacion asociado a un documento que corresponde
     * a un Lider de Investigacion
     * <p>
     * @param documento El documento del usuario
     */
    public GrupoInvestigacion darGrupoDeInvestigacionLiderDeInvestigacion( String documento )
    {
        Usuario u = darUsuarioConDocumento(documento);
        if( esLiderDeGrupoDeInvestigacion(documento) )
        {
            for( Investigador in : u.getInvestigadorList() )
            {
                String esLider = in.getEslider().toUpperCase();
                if( esLider != null && ( esLider.startsWith( "Y" ) || esLider.startsWith( "S" ) ) )
                {
                    return in.getGrupoInvestigacion();
                }
            }
        }
        return null;
    }

    /**
     * Metodo que verifica si el usuario es decano es de la OAI. Nota: El decano
     * o la OAI no pueden crear proyectos de investigación.
     */
    public boolean esInvestigador( String documento )
    {
        boolean esInvestigador = false;
        List<Usuario> usus = usuarioFacade.findBy( "Documento", documento );
        if( !usus.isEmpty() )
        {
            long codigoRolInvestigador = 2;
            Usuario usuario = usus.get( 0 );

            esInvestigador = usuario.getRolSistemaList().contains( new RolSistema( codigoRolInvestigador ) );
        }
        return esInvestigador;

    }

    public void editarUsuario( String tipoDocumento, String documento, String nombre,
                               String email, String telefono, String otraInstitucion, String pass/*
     * , List<RolSistema> rolSistemaList,
     * List<Investigador> investigadorList, List<Tarifa> tarifaList
     */ ) throws UsuarioException, Exception
    {

        if( Validaciones.esVacio( tipoDocumento ) || Validaciones.esVacio( documento )
            || Validaciones.esVacio( nombre ) || Validaciones.esVacio( email )
            || Validaciones.esVacio( telefono ) || Validaciones.esVacio( otraInstitucion )
            || Validaciones.esVacio( pass ) )
        {
            throw new UsuarioException( "Ingrese todos los datos." );
        }

        List<Usuario> usuario = usuarioFacade.findBy( "Documento", documento );
        Usuario usu = usuario.get( 0 );

        usu.setTipoDocumento( tipoDocumento );
        usu.setDocumento( documento );
        usu.setNombre( nombre );
        usu.setEmail( email );
        usu.setTelefono( telefono );
        usu.setOtraInstitucion( otraInstitucion );
        usu.setPass( CipherAlgorithms.encryptAES( pass ) );
        usuarioFacade.edit( usu );
    }
}
