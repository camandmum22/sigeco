/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table( name = "PROYECTO_INVESTIGACION" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "ProyectoInvestigacion.findAll", query = "SELECT p FROM ProyectoInvestigacion p" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByCodigo", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.codigo = :codigo" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByTitulo", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.titulo = :titulo" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByResumenEjecutivo", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.resumenEjecutivo = :resumenEjecutivo" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByFechainicio", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.fechainicio = :fechainicio" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByFechafin", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.fechafin = :fechafin" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByResultados", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.resultados = :resultados" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByProblema", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.problema = :problema" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByPresupuestoEsperado", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.presupuestoEsperado = :presupuestoEsperado" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByAprobacionEtica", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.aprobacionEtica = :aprobacionEtica" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByCenco", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.cenco = :cenco" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByPresupuestoAsignado", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.presupuestoAsignado = :presupuestoAsignado" ),
    @NamedQuery( name = "ProyectoInvestigacion.findByObservaciones", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.observaciones = :observaciones" ), 
    @NamedQuery( name = "ProyectoInvestigacion.findByAdicionales", query = "SELECT p FROM ProyectoInvestigacion p WHERE p.adicionales = :adicionales")
} )
public class ProyectoInvestigacion implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator( name = "PROYECTO_INVESTIGACION_GENERATOR", sequenceName = "SQ_PROYECTO_INVESTIGACION", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PROYECTO_INVESTIGACION_GENERATOR" )
    @Basic( optional = false )
    @NotNull
    @Column( name = "CODIGO" )
    private Long codigo;

    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 50 )
    @Column( name = "TITULO" )
    private String titulo;

    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 500 )
    @Column( name = "RESUMEN_EJECUTIVO" )
    private String resumenEjecutivo;

    @Basic( optional = false )
    @NotNull
    @Column( name = "FECHAINICIO" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date fechainicio;

    @Basic( optional = false )
    @NotNull
    @Column( name = "FECHAFIN" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date fechafin;

    @Size( max = 500 )
    @Column( name = "RESULTADOS" )
    private String resultados;

    @Size( max = 50 )
    @Column( name = "PROBLEMA" )
    private String problema;

    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 8 )
    @Column( name = "PRESUPUESTO_ESPERADO" )
    private String presupuestoEsperado;

    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 1 )
    @Column( name = "APROBACION_ETICA" )
    private String aprobacionEtica;

    @Size( max = 20 )
    @Column( name = "CENCO" )
    private String cenco;

    @Column( name = "PRESUPUESTO_ASIGNADO" )
    private BigInteger presupuestoAsignado;

    @Lob
    @Column( name = "DOC_ETICA" )
    private Serializable docEtica;

    @Size( max = 500 )
    @Column( name = "OBSERVACIONES" )
    private String observaciones;

    @Lob
    @Column( name = "ADICIONALES" )
    private Serializable adicionales;
    
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "proyectoInvestigacion", fetch = FetchType.LAZY )
    private List<ProConSol> proConSolList;

    @JoinColumn( name = "EST_CODIGO", referencedColumnName = "CODIGO" )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private Estado estCodigo;

    @JoinColumns( 
    {
        @JoinColumn( name = "GRU_LIN_CODIGO", referencedColumnName = "GRU_INV_CODIGO" ),
        @JoinColumn( name = "GRU_LIN_CODIGO1", referencedColumnName = "LIN_INV_CODIGO" )
    } )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private GrupoLinea grupoLinea;

    @JoinColumn( name = "TIP_INV_CODIGO", referencedColumnName = "CODIGO" )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private TipoInvestigacion tipInvCodigo;

    @JoinColumn( name = "TIP_PRO_CODIGO", referencedColumnName = "CODIGO" )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private TipoProyecto tipProCodigo;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "proInvCodigo", fetch = FetchType.LAZY )
    private List<Entregable> entregableList;

    public ProyectoInvestigacion()
    {
    }

    public ProyectoInvestigacion( Long codigo )
    {
        this.codigo = codigo;
    }

    public ProyectoInvestigacion( Long codigo, String titulo, String resumenEjecutivo, Date fechainicio, Date fechafin, String presupuestoEsperado, String aprobacionEtica )
    {
        this.codigo = codigo;
        this.titulo = titulo;
        this.resumenEjecutivo = resumenEjecutivo;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.presupuestoEsperado = presupuestoEsperado;
        this.aprobacionEtica = aprobacionEtica;
    }

    public Long getCodigo()
    {
        return codigo;
    }

    public void setCodigo( Long codigo )
    {
        this.codigo = codigo;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo( String titulo )
    {
        this.titulo = titulo;
    }

    public String getResumenEjecutivo()
    {
        return resumenEjecutivo;
    }

    public void setResumenEjecutivo( String resumenEjecutivo )
    {
        this.resumenEjecutivo = resumenEjecutivo;
    }

    public Date getFechainicio()
    {
        return fechainicio;
    }

    public void setFechainicio( Date fechainicio )
    {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin()
    {
        return fechafin;
    }

    public void setFechafin( Date fechafin )
    {
        this.fechafin = fechafin;
    }

    public String getResultados()
    {
        return resultados;
    }

    public void setResultados( String resultados )
    {
        this.resultados = resultados;
    }

    public String getProblema()
    {
        return problema;
    }

    public void setProblema( String problema )
    {
        this.problema = problema;
    }

    public String getPresupuestoEsperado()
    {
        return presupuestoEsperado;
    }

    public void setPresupuestoEsperado( String presupuestoEsperado )
    {
        this.presupuestoEsperado = presupuestoEsperado;
    }

    public String getAprobacionEtica()
    {
        return aprobacionEtica;
    }

    public void setAprobacionEtica( String aprobacionEtica )
    {
        this.aprobacionEtica = aprobacionEtica;
    }

    public String getCenco()
    {
        return cenco;
    }

    public void setCenco( String cenco )
    {
        this.cenco = cenco;
    }

    public BigInteger getPresupuestoAsignado()
    {
        return presupuestoAsignado;
    }

    public void setPresupuestoAsignado( BigInteger presupuestoAsignado )
    {
        this.presupuestoAsignado = presupuestoAsignado;
    }

    public Serializable getDocEtica()
    {
        return docEtica;
    }

    public void setDocEtica( Serializable docEtica )
    {
        this.docEtica = docEtica;
    }

    public String getObservaciones()
    {
        return observaciones;
    }

    public void setObservaciones( String observaciones )
    {
        this.observaciones = observaciones;
    }
    
    public Serializable getAdicionales()
    {
        return adicionales;
    }

    public void setAdicionales( Serializable adicionales )
    {
        this.adicionales = adicionales;
    }

    @XmlTransient
    public List<ProConSol> getProConSolList()
    {
        return proConSolList;
    }

    public void setProConSolList( List<ProConSol> proConSolList )
    {
        this.proConSolList = proConSolList;
    }

    public Estado getEstCodigo()
    {
        return estCodigo;
    }

    public void setEstCodigo( Estado estCodigo )
    {
        this.estCodigo = estCodigo;
    }

    public GrupoLinea getGrupoLinea()
    {
        return grupoLinea;
    }

    public void setGrupoLinea( GrupoLinea grupoLinea )
    {
        this.grupoLinea = grupoLinea;
    }

    public TipoInvestigacion getTipInvCodigo()
    {
        return tipInvCodigo;
    }

    public void setTipInvCodigo( TipoInvestigacion tipInvCodigo )
    {
        this.tipInvCodigo = tipInvCodigo;
    }

    public TipoProyecto getTipProCodigo()
    {
        return tipProCodigo;
    }

    public void setTipProCodigo( TipoProyecto tipProCodigo )
    {
        this.tipProCodigo = tipProCodigo;
    }

    @XmlTransient
    public List<Entregable> getEntregableList()
    {
        return entregableList;
    }

    public void setEntregableList( List<Entregable> entregableList )
    {
        this.entregableList = entregableList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( codigo != null ? codigo.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if( !( object instanceof ProyectoInvestigacion ) )
        {
            return false;
        }
        ProyectoInvestigacion other = (ProyectoInvestigacion)object;
        if( ( this.codigo == null && other.codigo != null ) || ( this.codigo != null && !this.codigo.equals( other.codigo ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sigeco.ejb.entities.ProyectoInvestigacion[ codigo=" + codigo + " ]";
    }

}
