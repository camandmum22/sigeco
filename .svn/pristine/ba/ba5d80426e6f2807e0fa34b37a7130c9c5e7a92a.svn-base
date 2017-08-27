/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.sigeco.ejb.entities.Convocatoria;
import com.sigeco.ejb.entities.Estado;
import com.sigeco.ejb.entities.Facultad;
import com.sigeco.ejb.entities.GrupoInvestigacion;
import com.sigeco.ejb.entities.GrupoLinea;
import com.sigeco.ejb.entities.LineaInvestigacion;
import com.sigeco.ejb.entities.PcsRubro;
import com.sigeco.ejb.entities.ProConSol;
import com.sigeco.ejb.entities.ProConSolPK;
import com.sigeco.ejb.entities.ProyectoInvestigacion;
import com.sigeco.ejb.entities.Rubro;
import com.sigeco.ejb.entities.Solicitud;
import com.sigeco.ejb.entities.Usuario;
import com.sigeco.ejb.entities.UsuarioPcs;
import com.sigeco.ejb.entities.UsuarioPcsPK;
import com.sigeco.ejb.exceptions.ProyectoException;
import com.sigeco.ejb.sessionbean.GrupoLineaFacade;
import com.sigeco.ejb.sessionbean.InvestigadorFacade;
import com.sigeco.ejb.sessionbean.LineaInvestigacionFacade;
import com.sigeco.ejb.sessionbean.ModalidadFacade;
import com.sigeco.ejb.sessionbean.PcsRubroFacade;
import com.sigeco.ejb.sessionbean.ProConSolFacade;
import com.sigeco.ejb.sessionbean.ProyectoInvestigacionFacade;
import com.sigeco.ejb.sessionbean.RolProyectoFacade;
import com.sigeco.ejb.sessionbean.RubroFacade;
import com.sigeco.ejb.sessionbean.TipoInvestigacionFacade;
import com.sigeco.ejb.sessionbean.TipoProyectoFacade;
import com.sigeco.ejb.sessionbean.UsuarioPcsFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import static com.sigeco.ejb.utilities.InputChecks.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.LinkedList;
import javax.ejb.EJBException;

//Librerías de iTextPDF--------------------
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.JFileChooser;
//-----------------------------------------

/**
 *
 * @author csacanam
 */
@Stateless
public class ProyectosSB {

    @EJB
    private TipoInvestigacionFacade tipoInvestigacionFacade;

    @EJB
    private RolProyectoFacade rolProyectoFacade;

    @EJB
    private ProyectoInvestigacionFacade proyectoFacade;

    @EJB
    private TipoProyectoFacade tipoProyectoFacade;

    @EJB
    private LineaInvestigacionFacade lineaInvestigacionFacade;

    @EJB
    private GrupoLineaFacade grupoLineaFacade;

    @EJB
    private InvestigadorFacade investigadoresFacade;

    @EJB
    private UsuarioSB usuarioSB;

    @EJB
    private GrupoInvestigacionSB grupoInvestigacionSB;

    @EJB
    private ProConSolFacade proConSolFacade;

    @EJB
    private ConvocatoriaSB convocatoriaSB;

    @EJB
    private UsuarioPcsFacade usuarioPcsFacade;

    @EJB
    private SolicitudesSB solicitudesSB;

    @EJB
    private EstadosSB estadosSB;

    @EJB
    private ModalidadFacade modalidadFacade;

    @EJB
    private PresupuestoSB presupuestoSB;

    @EJB
    private PcsRubroFacade pcsRubroFacade;

    @EJB
    private RubroFacade rubroFacade;

    /**
     * Retorna el lider de un proyecto de investigacion
     * <p>
     * @param proConSol El proyecto de investigacion del cual se desea quien es
     * su lider
     * <p>
     * @return El lidel del proyecto de investigación
     */
    public Usuario darLiderProyecto(ProConSol proConSol) {
        Usuario lider = null;

        for (UsuarioPcs colaborador : proConSol.getUsuarioPcsList()) {
            if (colaborador.getRolProCodigo().equals(rolProyectoFacade.findBy("Nombre", "IP"))) {
                lider = colaborador.getTarifa().getUsuario();
            }
        }

        return lider;
    }

    public Usuario darLiderPorCodigoProyectoConvActual(long codigoProyecto) {
        Convocatoria conv = convocatoriaSB.darConvocatoriaActual();
        ProConSolPK pk = new ProConSolPK();
        pk.setConCodigo(conv.getCodigo());
        pk.setProInvCodigo(codigoProyecto);
        pk.setSolCodigo("PN");
        ProConSol proconsol = new ProConSol();
        proconsol = proConSolFacade.find(pk);
        Usuario lider = darLiderProyecto(proconsol);
        return lider;
    }
    
    /**
     * Este metodo permite obtener el lider de un proyecto de investigacion aprobado por el decano
     * @param proyectoId el codigo del proyecto del cual se requiere su investigador principal
     * @return Usuario, correspondiente al investigador principal de un determinado proyecto
     */
    public Usuario darliderProyectoAprobadoDecano(long proyectoId){
        Usuario respuesta=null;
        ProyectoInvestigacion pi=proyectoFacade.find(proyectoId);
        List<ProConSol> pcsl=pi.getProConSolList();
        ProConSol proconsol=null;
        for(ProConSol pcs: pcsl){
            if(pcs.getConvocatoria().getCodigo() == convocatoriaSB.darConvocatoriaActual().getCodigo()){
                proconsol=pcs;
                break;
            }
        }
        List<UsuarioPcs> upcsl=proconsol.getUsuarioPcsList();
        UsuarioPcs Uproconsol=null;
        for(UsuarioPcs upcs: upcsl){
            String n=upcs.getRolProCodigo().getNombre();
            if(upcs.getRolProCodigo().getDescripcion().equalsIgnoreCase("Investigador Principal")){
                Uproconsol=upcs;
                break;
            }
        }
        respuesta=Uproconsol.getTarifa().getUsuario();
        return respuesta;
    }
    

    /**
     * Deja como colaboradores de un proyecto a los que se encuentren en la
     * lista Si el proyecto ya posee colaboradores, estos son borrados y solo se
     * dejan a los especificados en colaboradores
     * <p>
     * @param proConSol El proyecto al cual se le van asociar los colaboradores,
     * ya debe existir en el sistema
     * @param equipoInvestigadores La lista de colaboradores. Todos sus
     * datos(tarifa, dedicacion) no deben ser nulos y deben existir en el
     * sistema. La lista de colaboladores debe tener unicamente al usuario lider
     * como investigador principal.
     * <p>
     * @throws ProyectoException Si se incumple alguna restriccion sobre los
     * parametros
     */
    private void cambiarEquipoInvestigadoresProyecto(ProConSol proConSol, List<UsuarioPcs> equipoInvestigadores) throws ProyectoException {
        //Comprueba la entrada
        if (proConSol == null || equipoInvestigadores == null) {
            throw new ProyectoException("Los parametros no deben ser nulos");
        }

        int investigadoresPrincipales = 0;
        for (UsuarioPcs colaborador : equipoInvestigadores) {
            if (colaborador.getRolProCodigo().equals(rolProyectoFacade.findBy("Nombre", "IP").get(0))) {
                investigadoresPrincipales++;
            }
        }

        if (investigadoresPrincipales != 1) {
            throw new ProyectoException("Existe mas de un investigador principal en la lista de colaboradores");
        }

        //Configura los colaboladores
        for (UsuarioPcs investigador : equipoInvestigadores) {
            UsuarioPcsPK usuarioPcsPK = new UsuarioPcsPK(proConSol.getProConSolPK().getConCodigo(),
                    proConSol.getProConSolPK().getProInvCodigo(),
                    investigador.getTarifa().getUsuario().getCodigo(),
                    investigador.getTarifa().getModalidad().getCodigo(),
                    proConSol.getProConSolPK().getSolCodigo());
            investigador.setUsuarioPcsPK(usuarioPcsPK);
        }

        proConSol.setUsuarioPcsList(equipoInvestigadores);
        proConSolFacade.edit(proConSol);
    }

    /**
     * Crea un nuevo proyecto y lo registra en la convocatoria actual mediante
     * una solicitud de nuevo proyecto La solicitud es creada con estado
     * PENDIENTE
     * <p>
     * @param proyectoNuevo El proyecto que se va a registrar
     * @param investigadorLider La tarifa del lider del proyecto. La tarifa debe
     * estar registrada en el sistema
     * <p>
     * @throws ProyectoException Si alguno de los parametros es nulo Si el
     * usuario no tiene permisos para crear un proyecto Si el titulo, el nombre
     * de ejecutivo o las fechas de inicio o fin del proyecto son vacias o
     * nulas.
     */
    public void crearProyecto(ProyectoInvestigacion proyectoNuevo, UsuarioPcs investigadorLider) throws ProyectoException {
        //Comprueba todas las excepciones
        if (investigadorLider == null || proyectoNuevo == null) {
            throw new ProyectoException("El proyecto de investigacion y el usuario no deben ser nulos");
        }

        if (!usuarioSB.esInvestigador(investigadorLider.getTarifa().getUsuario().getDocumento())) {
            throw new ProyectoException("El usuario no tiene permiso para realizar esta acción");
        }

        if (isAnyTrimEmptyorNull(proyectoNuevo.getTitulo(), proyectoNuevo.getResumenEjecutivo())
                || proyectoNuevo.getFechafin() == null || proyectoNuevo.getFechainicio() == null
                || new BigDecimal(proyectoNuevo.getPresupuestoEsperado()).doubleValue() <= 0
                || proyectoNuevo.getTipInvCodigo() == null || proyectoNuevo.getTipProCodigo() == null
                || proyectoNuevo.getGrupoLinea() == null) {
            throw new ProyectoException("Debe llenar todos los campos");
        }

        if (proyectoNuevo.getAprobacionEtica().equals("Y") && proyectoNuevo.getDocEtica() == null) {
            throw new ProyectoException("Se debe adjuntar el documento de etica");
        }

        //Crea el proyecto
        proyectoNuevo.setEstCodigo(estadosSB.getEstadoFacade().find(EstadosSB.PROYECTO_REGISTRADO));
        proyectoFacade.create(proyectoNuevo);
        proConSolFacade.getEntityManager().persist(proyectoNuevo);

        //Crea el proconsol
        ProConSol proConSol = new ProConSol(convocatoriaSB.darConvocatoriaActual().getCodigo(),
                proyectoNuevo.getCodigo(),
                solicitudesSB.getSolicitudFacade().find(SolicitudesSB.SOLICITUD_REGISTRO).getCodigo());
        proConSol.setSolicitud(solicitudesSB.getSolicitudFacade().find(SolicitudesSB.SOLICITUD_REGISTRO));
        proConSol.setEstCodigo(estadosSB.getEstadoFacade().find(EstadosSB.PROCONSOL_PENDIENTE));
        proConSolFacade.create(proConSol);
        proConSolFacade.getEntityManager().persist(proConSol);

        //Agrega el lider a la lista de colaboradores de proConSol
        List<UsuarioPcs> listaEquipoInvestigacion = new LinkedList<>();
        listaEquipoInvestigacion.add(investigadorLider);
        cambiarEquipoInvestigadoresProyecto(proConSol, listaEquipoInvestigacion);
    }

    /**
     * Método que permite editar un proyecto con la información básica
     *
     * @param codigo
     * @param titulo
     * @param resumenEjecutivo
     * @param fechaInicio
     * @param fechaFin
     * @param aprobacionEtica
     * <p>
     * @throws ProyectoException
     */
    public void editarProyecto(long codigo, String titulo, String resumenEjecutivo, Date fechaInicio, Date fechaFin, BigDecimal presupuestoEsperado, String aprobacionEtica, InputStream file) throws ProyectoException, IOException {

        if (titulo == null || resumenEjecutivo == null || fechaInicio == null || fechaFin == null || presupuestoEsperado == null) {
            throw new ProyectoException("El proyecto debe tener estos datos completos.");
        } else {
            ProyectoInvestigacion proyecto = proyectoFacade.find(codigo);
            if (proyecto != null) {
                proyecto.setTitulo(titulo);
                proyecto.setResumenEjecutivo(resumenEjecutivo);
                proyecto.setPresupuestoEsperado(presupuestoEsperado.toString());
                proyecto.setFechainicio(fechaInicio);
                proyecto.setFechafin(fechaFin);
                if (file != null) {
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    byte[] buffer = new byte[10240];
                    for (int length = 0; (length = file.read(buffer)) > 0;) {
                        output.write(buffer, 0, length);
                    }
                    byte[] b = output.toByteArray();
                    file.close();
                    proyecto.setAdicionales(b);
                }

                proyectoFacade.edit(proyecto);
            } else {
                throw new ProyectoException("No existe proyecto de investigación con ese código");
            }
        }
    }

    /**
     * Devuleve el objeto ProyectoInvestigacion asociado a un codigo, si no
     * existe ningun proyecto asociado a ese codigo devulve null
     * <p>
     * @param codigo El codigo del ProyectoInvestigacion
     * <p>
     * @return El proyecto o null si no es valido o el proyecto no existe
     */
    //No estoy seguro si uso bien el findBy en la forma 2
    public ProyectoInvestigacion darProyectoConCodigo(Long codigo) {
        ProyectoInvestigacion p = null;
        if (codigo != null) {
            p = proyectoFacade.find(codigo);
        }
        return p;
    }

    /**
     * Permite a un Lider de Investigacion Aprobar o Rechazar un Proyecto de
     * Investigacion registrado en su grupo de investigacion
     * <p>
     * @param usuario El documento del usuario que representa al Lider de
     * investigacion
     * @param proyecto El Codigo del ProyectoInvestigacion que se esta evaluando
     * @param aprueba El resultado de la evaluacion: true si aprueba, false en
     * caso contrario
     * @param observaciones Las observaciones para el proyecto
     * <p>
     * @throws Exception Si no se cumple las verificaciones
     */
    public void evaluarProyectoPorLiderDeInvestigacion(String usuario, Long proyecto, boolean aprueba, String observaciones) throws Exception {
        ProyectoInvestigacion p = darProyectoConCodigo(proyecto);
        if (p == null) {
            throw new ProyectoException("El codigo: " + proyecto + " no corresponde a un proyecto de investigacion valido");
        }
        Estado e = new Estado();
        if (aprueba) {
            e.setCodigo(EstadosSB.PROYECTO_APROBADO_LIDER);
        } else {
            e.setCodigo(EstadosSB.PROYECTO_RECHAZADO_LIDER);
        }
        p.setEstCodigo(e);
        p.setObservaciones(observaciones);

        proyectoFacade.edit(p);
    }

    /**
     * Permite a un Decano Aprobar o Rechazar un Proyecto de Investigacion
     * registrado en su facultad
     * <p>
     * @param usuario El documento del usuario que representa al Lider de
     * investigacion
     * @param proyecto El Codigo del ProyectoInvestigacion que se esta evaluando
     * @param aprueba El resultado de la evaluacion: true si aprueba, false en
     * caso contrario
     * @param observaciones Las observaciones para el proyecto
     * <p>
     * @throws Exception Si no se cumple las verificaciones
     */
    public void evaluarProyectoPorDecano(String usuario, Long proyecto, boolean aprueba, String observaciones) throws Exception {
        ProyectoInvestigacion p = darProyectoConCodigo(proyecto);
        if (p == null) {
            throw new ProyectoException("El codigo: " + proyecto + " no corresponde a un proyecto de investigacion valido");
        }
        Estado e = new Estado();
        if (aprueba) {
            e.setCodigo(EstadosSB.PROYECTO_APROBADO_DECANO);
        } else {
            e.setCodigo(EstadosSB.PROYECTO_APROBADO_LIDER);//Cuando está rechazado por el decano, se deja en aprobado por líder de investigación.
        }
        p.setEstCodigo(e);
        //p.setObservaciones(observaciones);

        proyectoFacade.edit(p);
    }

    /**
     * Permite aprobar o rechazar un proyecto por parte del comité de
     * investigación
     *
     * @param codPro Código del proyecto a aprobar
     * @param aprobado True si el proyecto es aprobado, false si es rechazado.
     * @param observaciones Observaciones realizadas al momento de aprobar o
     * rechazar un proyecto.
     */
    public void evaluarProyectoPorComiteInvestigacion(Long codPro, boolean aprobado, String observaciones) {

        ProyectoInvestigacion proyecto = proyectoFacade.find(codPro);
        if (proyecto.getEstCodigo().getCodigo() == EstadosSB.PROYECTO_APROBADO_DECANO) {
            if (aprobado) {
                proyecto.setEstCodigo(new Estado(EstadosSB.PROYECTO_APROBADO_COMITE));
                proyecto.setObservaciones(observaciones);
            } else {
                proyecto.setEstCodigo(new Estado(EstadosSB.PROYECTO_RECHAZADO_COMITE));
                proyecto.setObservaciones(observaciones);
            }
        }
        proyectoFacade.edit(proyecto);
    }

    /**
     * Retorna los proyectos de la convocatoria actual pertenecientes a una
     * facultad
     *
     * @param facultad Facultad para buscar sus proyectos en la convocatoria
     * actual
     * <p>
     * @return lista de proyectos pertenecientes a la convocatoria actual y a la
     * facultad
     */
    public List<ProyectoInvestigacion> darProyectosFacultadEnConvActual(Facultad facultad) {

        //Lista a retornar
        List<ProyectoInvestigacion> proFac = new ArrayList<>();

        //Proyectos de la convocatoria actual
        List<ProyectoInvestigacion> proAct = darProyectosConvActual();
        for (ProyectoInvestigacion pro : proAct) {
            //Facultad de un proyecto en la convocatoria actul
            Facultad facPro = pro.getGrupoLinea().getGrupoInvestigacion().getFacCodigo();
            //Si la facultad es igual a la que está por parámetro, agregarla a la lista
            if (facPro.getCodigo() == facultad.getCodigo()) {
                proFac.add(pro);
            }
        }
        return proFac;
    }

    /**
     * Permite obtener las líneas de investigación de un grupo de investigación
     *
     * @param grupoInvestigacion Grupo de investigación
     * <p>
     * @return Líneas de investigación del grupo de investigación
     */
    public List<LineaInvestigacion> buscarLineasPorGrupo(GrupoInvestigacion grupoInvestigacion) {
        List<LineaInvestigacion> lineasInvestigacion = new ArrayList();

        if (grupoInvestigacion != null) {
            Query query = grupoLineaFacade.getEntityManager().createNamedQuery("GrupoLinea.findByGruInvCodigo");
            query.setParameter("gruInvCodigo", grupoInvestigacion.getCodigo());
            List<GrupoLinea> gruposLinea = query.getResultList();

            for (GrupoLinea grupoLinea : gruposLinea) {
                lineasInvestigacion.add(grupoLinea.getLineaInvestigacion());
            }
        }

        return lineasInvestigacion;

    }

    /**
     * Permite buscar las convocatorias donde ha estado presente un proyecto de
     * investigación
     * <p>
     * @param proyInv Proyecto de investigación al que se le desean buscar las
     * convocatorias
     * <p>
     * @return Lista de convocatorias en las que ha estado presente el proyecto
     * de investigación
     */
    public List<Convocatoria> buscarConvocatoriasPorProyecto(ProyectoInvestigacion proyInv) {
        List<Convocatoria> convocatorias = new ArrayList<>();

        if (proyInv != null) {
            Query query = proConSolFacade.getEntityManager().createNamedQuery("ProConSol.findByProInvCodigo");
            query.setParameter("proInvCodigo", proyInv.getCodigo());
            List<ProConSol> proConSolList = query.getResultList();

            for (ProConSol proConSol : proConSolList) {
                convocatorias.add(proConSol.getConvocatoria());
            }

        }

        return convocatorias;
    }

    /**
     * Permite obtener los proyectos de investigación de una facultad
     *
     * @param facultad Facultad
     * <p>
     * @return Proyectos de investigación de una facultad
     */
    public List<ProyectoInvestigacion> darProyectosDeFacultad(Facultad facultad) {
        List<ProyectoInvestigacion> proyectos = new ArrayList();
        if (facultad.getGrupoInvestigacionList() != null && !facultad.getGrupoInvestigacionList().isEmpty()) {
            for (GrupoInvestigacion gi : facultad.getGrupoInvestigacionList()) {
                List<ProyectoInvestigacion> lista = darProyectosDeGrupoDeInvestigacion(gi);
                proyectos.addAll(lista);
            }
        }
        return proyectos;
    }

    /**
     * Permite obtener los proyectos de investigación de un grupo de
     * investigación
     *
     * @param grupo Grupo de investigación
     * <p>
     * @return Proyectos de investigación de un grupo de investigación
     */
    public List<ProyectoInvestigacion> darProyectosDeGrupoDeInvestigacion(GrupoInvestigacion grupo) {
        if (grupo != null) {
            return proyectoFacade.getProyectosPorGrupo(grupo.getCodigo());
        }
        return null;
    }

    /**
     * Permite obtener los proyectos de investigación de una facultad
     *
     * @param facultad Facultad a buscar
     * <p>
     * @return Proyectos de investigación de una facultad
     */
    public List<ProyectoInvestigacion> darProyectosFacultad(Facultad facultad) {
        if (facultad != null) {
            return proyectoFacade.getProyectosPorFacultad(facultad.getCodigo());
        }
        return null;
    }

    /**
     * Permite obtener los proyectos de investigación que fueron aprobados por
     * un Lider de investigacion
     *
     * @return Proyectos de investigación aprobados por un Lider de
     * investigacion
     */
    public List<ProyectoInvestigacion> darProyectosAprobadosPorLiderDeInvestigacion() {
        List<ProyectoInvestigacion> proyectos = new ArrayList();
        List<ProyectoInvestigacion> todos = darProyectosConvActual();//proyectoFacade.findAll()
        for (ProyectoInvestigacion pi : todos) {
            if (pi != null && pi.getEstCodigo() != null && pi.getEstCodigo().getCodigo() != null
                    && (pi.getEstCodigo().getCodigo().equals(EstadosSB.PROYECTO_APROBADO_LIDER)
                    || pi.getEstCodigo().getCodigo() == EstadosSB.PROYECTO_APROBADO_LIDER)) {
                proyectos.add(pi);
            }
        }
        return proyectos;
    }

    /**
     * Permite obtener los proyectos de investigación que fueron aprobados por
     * los decanos.
     *
     * @return Proyectos de investigación aprobados por un decano
     */
    public List<ProyectoInvestigacion> darProyectosAprobadosPorDecanos() {
        List<ProyectoInvestigacion> proyectos = new ArrayList();
        List<ProyectoInvestigacion> todos = darProyectosConvActual();//proyectoFacade.findAll()
        for (ProyectoInvestigacion pi : todos) {
            if (pi != null && pi.getEstCodigo() != null && pi.getEstCodigo().getCodigo() != null
                    && (pi.getEstCodigo().getCodigo().equals(EstadosSB.PROYECTO_APROBADO_DECANO)
                    || pi.getEstCodigo().getCodigo() == EstadosSB.PROYECTO_APROBADO_DECANO)) {
                proyectos.add(pi);
            }
        }
        return proyectos;
    }

    /**
     * Permite obtener los proyectos de investigación que fueron aprobados por
     * un decano filtrados por facultad
     *
     * @return Proyectos de investigación aprobados por un decano filtrados por
     * facultad
     */
    public HashMap<GrupoInvestigacion, List<ProyectoInvestigacion>> darProyectosAprobadosPorDecanosFiltradosPorGrupoDeInvestigacion() {
        List<GrupoInvestigacion> grupos = grupoInvestigacionSB.getGruposDeInvestigacion();
        List<ProyectoInvestigacion> aprobados = darProyectosAprobadosPorDecanos();
        HashMap<GrupoInvestigacion, List<ProyectoInvestigacion>> filtro = new HashMap<GrupoInvestigacion, List<ProyectoInvestigacion>>();

        for (GrupoInvestigacion g : grupos) {
            if (g != null && g.getCodigo() != null) {
                filtro.put(g, new ArrayList());
            }
        }

        for (ProyectoInvestigacion aprobado : aprobados) {
            if (aprobado.getGrupoLinea() != null && aprobado.getGrupoLinea().getGrupoInvestigacion() != null) {
                GrupoInvestigacion grupo = aprobado.getGrupoLinea().getGrupoInvestigacion();
                List<ProyectoInvestigacion> proyectos = filtro.get(grupo);
                proyectos.add(aprobado);
                filtro.put(grupo, proyectos);
            }
        }
        return filtro;
    }

    /**
     * Permite obtener los proyectos de investigación que fueron aprobados por
     * un Lider de investigacion y pertenecen a una facultad determinada
     *
     * @param facultad Facultad
     * <p>
     * @return Proyectos de investigación aprobados por un Lider de
     * investigacion y pertenecen a una facultad determinada
     */
    public List<ProyectoInvestigacion> darProyectosAprobadosPorLiderConFacultad(Facultad facultad) {
        List<ProyectoInvestigacion> proyectos = new ArrayList();
        List<ProyectoInvestigacion> aprobados = darProyectosAprobadosPorLiderDeInvestigacion();
        for (ProyectoInvestigacion a : aprobados) {
            Facultad facultadA = hallarFacultadDeProyectoDeInvestigacion(a);
            if (facultadA != null && (facultad.getCodigo().equals(facultadA.getCodigo())
                    || facultad.getSiglas().equals(facultadA.getSiglas()))) {
                proyectos.add(a);
            }
        }
        return proyectos;
    }

    /**
     * Permite obtener la facultad que corresponde a un Proyecto de
     * Investigacion
     *
     * @param proyecto Proyecto de Investigacion
     * <p>
     * @return la Facultad correspondiente a un Proyecto de Investigacion
     */
    public Facultad hallarFacultadDeProyectoDeInvestigacion(ProyectoInvestigacion proyecto) {
        Facultad facultad = null;
        if (proyecto.getGrupoLinea() != null && proyecto.getGrupoLinea().getGrupoInvestigacion() != null
                && proyecto.getGrupoLinea().getGrupoInvestigacion().getFacCodigo() != null) {
            facultad = proyecto.getGrupoLinea().getGrupoInvestigacion().getFacCodigo();
        }
        return facultad;
    }

    /**
     * Permite obtener los proyectos de investigación de una convocatoria actual
     *
     * @return proyectos
     */
    public List<ProyectoInvestigacion> darProyectosConvActual() {

        List<ProyectoInvestigacion> proyectos = new ArrayList();
        Convocatoria conv = convocatoriaSB.darConvocatoriaActual();
        for (ProConSol pcs : conv.getProConSolList()) {
            proyectos.add(pcs.getProyectoInvestigacion());
        }
        return proyectos;
    }

    /**
     * Permite dar los proyectos de una convocatoria pasada
     *
     * @return proyectos Los proyectos de una convocatoria pasada
     */
    public List<ProyectoInvestigacion> darProyectosConvPasada() {
        List<ProyectoInvestigacion> proyectos = new ArrayList();
        Convocatoria convActual = convocatoriaSB.darConvocatoriaActual();
        Date fechaIniConvActual = convocatoriaSB.getFechaInicioConvocatoria(convActual.getCodigo());
        List<Convocatoria> convPas = convocatoriaSB.darConvocatoriaPasadas();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaIniConvActual);
        int año = cal.get(Calendar.YEAR);
        for (int i = 0; i > convPas.size(); i++) {
            Date temp = convocatoriaSB.getFechaInicioConvocatoria(convPas.get(i).getCodigo());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(temp);
            int añoTemp = cal2.get(Calendar.YEAR);
            if (añoTemp == año - 1) {
                List<ProConSol> pro = convPas.get(i).getProConSolList();
                for (int j = 0; j > pro.size(); j++) {
                    proyectos.add(pro.get(i).getProyectoInvestigacion());
                }
            }
        }
        return proyectos;
    }

    /**
     * Permite dar los proyectos de un investigador
     *
     * @param documento Es el documento del investigador
     * <p>
     * @return proyectos
     */
    public List<ProyectoInvestigacion> darProyectosInvestigador(String documento) {
        List<ProyectoInvestigacion> proyectos = new ArrayList<ProyectoInvestigacion>();
        Usuario investigador = usuarioSB.darUsuarioConDocumento(documento);
        if (investigador != null && investigador.getCodigo() != null) {
            List<UsuarioPcs> usuariosPCS = usuarioPcsFacade.findAll();
            for (UsuarioPcs upcs : usuariosPCS) {
                if (upcs.getTarifa().getUsuario().getCodigo() == investigador.getCodigo() && upcs.getRolProCodigo().getCodigo() == investigador.getCodigo()) {
                    proyectos.add(upcs.getProConSol().getProyectoInvestigacion());
                }
            }
        }
        return proyectos;
    }

    /**
     * Permite dar los proyectos de un líder de proyecto
     *
     * @param codigo Es el código del líder de proyecto
     * <p>
     * @return proyectos
     */
    public List<ProyectoInvestigacion> darProyectosLiderProyecto(Long codigo) {
        List<ProyectoInvestigacion> proyectos = new ArrayList<>();
        List<UsuarioPcs> usuariosPCS = usuarioPcsFacade.findAll();
        List<UsuarioPcs> temp = new ArrayList<>();
        for (int i = 0; i > usuariosPCS.size(); i++) {
            if (usuariosPCS.get(i).getTarifa().getUsuario().getCodigo() == codigo && usuariosPCS.get(i).getRolProCodigo().getCodigo() == 1) {
                temp.add(usuariosPCS.get(i));
            }
        }
        for (UsuarioPcs x : temp) {
            proyectos.add(x.getProConSol().getProyectoInvestigacion());
        }
        return proyectos;
    }

    /**
     * Permite obtener la lista de proyectos aprobados por los decanos 
     * de cada facultad
     * @return Proyectos aprobados por los decanos
     */
    public List<ProyectoInvestigacion> darProyectosAprobadosPorDecano() {
        return proyectoFacade.getProyectosPorFacultadAprobados();
    }
    
    /**
     * Permite dar los proyectos de un líder de proyecto en una convocatoria
     * pasada
     *
     * @param codLider es el código del Líder de proyecto
     * @param codConv es el código de la convocatoria
     * <p>
     * @return proyectos
     */
    public List<ProyectoInvestigacion> proyectosConvPasadaLider(Long codLider, Long codConv) {
        List<ProyectoInvestigacion> proyecLider = darProyectosLiderProyecto(codLider);
        List<ProyectoInvestigacion> proyecConvPas = darProyectosConvPasada();
        List<ProyectoInvestigacion> proyectos = new ArrayList<>();
        for (int i = 0; i > proyecLider.size(); i++) {
            ProyectoInvestigacion proyCompLid = proyecLider.get(i);
            for (int j = 0; j > proyecConvPas.size(); j++) {
                ProyectoInvestigacion proyCompConv = proyecConvPas.get(i);
                if (proyCompLid.getCodigo() == proyCompConv.getCodigo()) {
                    proyectos.add(proyCompLid);
                }
            }
        }
        return proyectos;
    }

    /**
     * Permite asociar un rubro a un proyecto de investigación
     * <p>
     * @param proyecto Proyecto de investigación al que se le va a asignar un
     * rubro
     * @param convocatoria Convocatoria en la que está el proyecto de
     * investigación
     * @param rubro Rubro Rubro que se va a asociar al proyecto
     * @param cantidad Cantidad de dinero asociada a un rubro en un proyecto
     * <p>
     * @return Verdadero si se pudo agregar. Falso en caso contrario
     */
    public boolean asociarRubroAProyecto(ProyectoInvestigacion proyecto, Convocatoria convocatoria, Rubro rubro, BigInteger cantidad) {
        Query query = proConSolFacade.getEntityManager().createNamedQuery("ProConSol.findByProInvCodigo");
        query.setParameter("proInvCodigo", proyecto.getCodigo());

        List<ProConSol> proConSolTemp = query.getResultList();

        for (ProConSol proConSol : proConSolTemp) {
            if (proConSol.getProConSolPK().getConCodigo() == convocatoria.getCodigo()) {
                Solicitud solicitud = proConSol.getSolicitud();

                PcsRubro pcsRubro = new PcsRubro(rubro.getCodigo(), convocatoria.getCodigo(), proyecto.getCodigo(), solicitud.getCodigo());

                pcsRubro.setRubro(rubro);
                pcsRubro.setCantidad(cantidad);
                pcsRubro.setProConSol(proConSol);
                presupuestoSB.getPcsRubroFacade().create(pcsRubro);

                return true;
            }
        }

        return false;
    }

    public boolean desasociarRubroAProyecto(ProyectoInvestigacion proyecto, Convocatoria convocatoria, Rubro rubro) {

        List<ProConSol> proTemp = proConSolFacade.findBy("ProInvCodigo", proyecto.getCodigo());
        for (ProConSol proTemp1 : proTemp) {
            if (proTemp1.getProConSolPK().getConCodigo() == convocatoria.getCodigo()) {
                Solicitud solicitud = proTemp1.getSolicitud();
                List<PcsRubro> proTemp2 = pcsRubroFacade.findBy("ProCSCodigo1", proTemp1.getProConSolPK().getProInvCodigo());
                for (PcsRubro pcsRubroTemp : proTemp2) {
                    Rubro rub = rubroFacade.find(rubro.getCodigo());
                    if (pcsRubroTemp.getRubro() == null) {
                        pcsRubroTemp.setRubro(presupuestoSB.getRubroFacade().find(pcsRubroTemp.getPcsRubroPK().getRubCodigo()));
                    }
                    if (pcsRubroTemp.getRubro().getCodigo().equals(rub.getCodigo())) {
                        presupuestoSB.getPcsRubroFacade().remove(pcsRubroTemp);
                        return true;
                    }
                }

            }

        }

        return false;
    }

    /**
     * Permite obtener la lista de rubros de un proyecto de investigación
     *
     * @param proyecto Proyecto de investigación del cual se listan los rubros
     * @return Lista de rubros de un proyecto de investigación
     */
    public List<Rubro> obtenerRubrosProyectoInv(ProyectoInvestigacion proyecto) {

        List<Rubro> rubros = new ArrayList();
        if (proyecto != null) {
            Query query = proConSolFacade.getEntityManager().createNamedQuery("ProConSol.findByProInvCodigo");
            query.setParameter("proInvCodigo", proyecto.getCodigo());
            List<ProConSol> proTemp = query.getResultList();
            for (ProConSol pr : proTemp) {
                for (PcsRubro ro : pr.getPcsRubroList()) {
                    rubros.add(ro.getRubro());
                }
            }

        }

        return rubros;
    }

    public List<ProyectoInvestigacion> darProyectosAprobadosDecano() {
        List<ProyectoInvestigacion> aprobados = new ArrayList<>();
        /*
         * darProyectosConvActual().stream().filter((p) ->
         * (p.getEstCodigo().getCodigo()==EstadosSB.PROYECTO_APROBADO_DECANO)).forEach((p)
         * -> {
         * aprobados.add(p);
         * });
         */

        for (ProyectoInvestigacion p : darProyectosConvActual()) {
            if (p.getEstCodigo().getCodigo() == EstadosSB.PROYECTO_APROBADO_DECANO) {
                aprobados.add(p);
            }
        }
        return aprobados;
    }

    /**
     * Permite obtener todos los proyectos de Investigacion que pertenencen a un
     * Grupo de Investigacion
     * <p>
     * @param codigo codigo del Grupo de investigación
     * <p>
     * @return La lista de los proyectos de Investigacion que pertenencen a un
     * Grupo de Investigacion
     */
    public List<ProyectoInvestigacion> darProyectosFiltroConGrupo(String codigo) {
        List<ProyectoInvestigacion> proyectos = new ArrayList<ProyectoInvestigacion>();
        GrupoInvestigacion grupo = grupoInvestigacionSB.getGrupoConCodigo(codigo);
        if (grupo != null && grupo.getGrupoLineaList() != null && !grupo.getGrupoLineaList().isEmpty()) {
            for (GrupoLinea gl : grupo.getGrupoLineaList()) {
                if (gl != null && gl.getProyectoInvestigacionList() != null && !gl.getProyectoInvestigacionList().isEmpty()) {
                    for (ProyectoInvestigacion proyecto : gl.getProyectoInvestigacionList()) {
                        if (proyecto != null && proyecto.getCodigo() != null) {
                            proyectos.add(proyecto);
                        }
                    }
                }
            }
        }
        return proyectos;
    }

    /**
     * Permite obtener todos los proyectos de Investigacion que pertenencen a un
     * Investigador
     * <p>
     * @param documento documento del Investigador
     * <p>
     * @return La lista de los proyectos de Investigacion que pertenencen a un
     * Investigador
     */
    public List<ProyectoInvestigacion> darProyectosFiltroConInvestigador(String documento) {
        List<ProyectoInvestigacion> proyectos = new ArrayList<ProyectoInvestigacion>();
        Usuario investigador = usuarioSB.esInvestigador(documento) ? usuarioSB.darUsuarioConDocumento(documento) : null;
        if (investigador != null) {
            proyectos.addAll(darProyectosInvestigador(documento));
        }
        return proyectos;
    }

    /**
     * Escribe un archivo PDF con los siguientes datos en la ruta especificada
     * por el usuario en su ordenador.
     *
     * @param titulo-Título que va a llevar el documento.
     */
    public void generarReporteProyectosAprobadosPorDecanos(String titulo) {
        // Creación del documento:-----------------------------------------------
        Document documento = new Document();
        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.

        try {
            FileOutputStream ficheroPdf = new FileOutputStream("C:/Users/Public/Downloads/"+titulo+".pdf");

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);

            // Se abre el documento.
            documento.open();

            //Fecha de realización del reporte:
            Calendar cal = Calendar.getInstance();
            Date fecha = new Date(cal.getTimeInMillis());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaReporte = "Santiago de Cali, " + formato.format(fecha);
            //---------------------------------------------------------------
            //Lista de los proyectos:----------------------------------------
            HashMap<GrupoInvestigacion, List<ProyectoInvestigacion>> proyectosAprobadosPorDecanoFiltradosPorGrupoInvestigacion = this.darProyectosAprobadosPorDecanosFiltradosPorGrupoDeInvestigacion();
            //---------------------------------------------------------------
            documento.add(new Paragraph(fechaReporte));

            documento.add(new Paragraph(titulo,
                    FontFactory.getFont("arial", // fuente
                            22, // tamaño
                            Font.ITALIC, // estilo
                            BaseColor.BLACK)));             // color

            //------------Tabla de proyectos por grupo de investigación:---------------//
            List<GrupoInvestigacion> grupos = grupoInvestigacionSB.getGruposDeInvestigacion();
            for (GrupoInvestigacion g : grupos) {
                if (g != null && g.getCodigo() != null) {
                    String nombreGrupoInvestigacion = g.getNombre();
                    List<ProyectoInvestigacion> proyectosAprobados
                            = proyectosAprobadosPorDecanoFiltradosPorGrupoInvestigacion.get(g);
                    if (proyectosAprobados != null) {
                        PdfPTable tablaProyectosPorGrupoInvestigacionAprobadosPorDecano = new PdfPTable(4);
                        tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell("Grupo de investigación");
                        tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell("Nombre del proyecto");
                        tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell("Presupuesto asignado");
                        tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell("Investigador principal");
                        for (ProyectoInvestigacion p : proyectosAprobados) {
                            tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell(nombreGrupoInvestigacion);
                            tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell(p.getTitulo());
                            tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell(p.getPresupuestoEsperado());//Este campo debería ser el presupuesto asignado.
                            tablaProyectosPorGrupoInvestigacionAprobadosPorDecano.addCell("Investigador principal pendiente");
                        }
                        documento.add(tablaProyectosPorGrupoInvestigacionAprobadosPorDecano);

                    }
                }
            }

            //------------------------------------------------------------------------//
            documento.close();

        } catch (FileNotFoundException | DocumentException e) {

            e.printStackTrace();

        }
    }

    //GETTERS AND SETTERS--------------------------------------------------------
    public TipoInvestigacionFacade getTipoInvestigacionFacade() {
        return tipoInvestigacionFacade;
    }

    public void setTipoInvestigacionFacade(TipoInvestigacionFacade tipoInvestigacionFacade) {
        this.tipoInvestigacionFacade = tipoInvestigacionFacade;
    }

    public RolProyectoFacade getRolProyectoFacade() {
        return rolProyectoFacade;
    }

    public void setRolProyectoFacade(RolProyectoFacade rolProyectoFacade) {
        this.rolProyectoFacade = rolProyectoFacade;
    }

    public ProyectoInvestigacionFacade getProyectoFacade() {
        return proyectoFacade;
    }

    public void setProyectoFacade(ProyectoInvestigacionFacade proyectoFacade) {
        this.proyectoFacade = proyectoFacade;
    }

    public TipoProyectoFacade getTipoProyectoFacade() {
        return tipoProyectoFacade;
    }

    public void setTipoProyectoFacade(TipoProyectoFacade tipoProyectoFacade) {
        this.tipoProyectoFacade = tipoProyectoFacade;
    }

    public LineaInvestigacionFacade getLineaInvestigacionFacade() {
        return lineaInvestigacionFacade;
    }

    public void setLineaInvestigacionFacade(LineaInvestigacionFacade lineaInvestigacionFacade) {
        this.lineaInvestigacionFacade = lineaInvestigacionFacade;
    }

    public GrupoLineaFacade getGrupoLineaFacade() {
        return grupoLineaFacade;
    }

    public void setGrupoLineaFacade(GrupoLineaFacade grupoLineaFacade) {
        this.grupoLineaFacade = grupoLineaFacade;
    }

    public InvestigadorFacade getInvestigadoresFacade() {
        return investigadoresFacade;
    }

    public void setInvestigadoresFacade(InvestigadorFacade investigadoresFacade) {
        this.investigadoresFacade = investigadoresFacade;
    }

    public UsuarioSB getUsuarioSB() {
        return usuarioSB;
    }

    public void setUsuarioSB(UsuarioSB usuarioSB) {
        this.usuarioSB = usuarioSB;
    }

    public GrupoInvestigacionSB getGrupoInvestigavionSB() {
        return grupoInvestigacionSB;
    }

    public void setGrupoInvestigavionSB(GrupoInvestigacionSB grupoInvestigavionSB) {
        this.grupoInvestigacionSB = grupoInvestigavionSB;
    }

    public ProConSolFacade getProConSolFacade() {
        return proConSolFacade;
    }

    public void setProConSolFacade(ProConSolFacade proConSolFacade) {
        this.proConSolFacade = proConSolFacade;
    }

    public ConvocatoriaSB getConvocatoriaSB() {
        return convocatoriaSB;
    }

    public void setConvocatoriaSB(ConvocatoriaSB convocatoriaSB) {
        this.convocatoriaSB = convocatoriaSB;
    }

    public UsuarioPcsFacade getUsuarioPcsFacade() {
        return usuarioPcsFacade;
    }

    public void setUsuarioPcsFacade(UsuarioPcsFacade usuarioPcsFacade) {
        this.usuarioPcsFacade = usuarioPcsFacade;
    }

    public SolicitudesSB getSolicitudesSB() {
        return solicitudesSB;
    }

    public void setSolicitudesSB(SolicitudesSB solicitudesSB) {
        this.solicitudesSB = solicitudesSB;
    }

    public EstadosSB getEstadosSB() {
        return estadosSB;
    }

    public void setEstadosSB(EstadosSB estadosSB) {
        this.estadosSB = estadosSB;
    }

    public ModalidadFacade getModalidadFacade() {
        return modalidadFacade;
    }

    public void setModalidadFacade(ModalidadFacade modalidadFacade) {
        this.modalidadFacade = modalidadFacade;
    }

    public PresupuestoSB getPresupuestoSB() {
        return presupuestoSB;
    }

    public void setPresupuestoSB(PresupuestoSB presupuestoSB) {
        this.presupuestoSB = presupuestoSB;
    }

}
