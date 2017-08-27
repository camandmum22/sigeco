/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table( name = "PRO_CON_SOL" )
@XmlRootElement
@NamedQueries(
                {
            @NamedQuery( name = "ProConSol.findAll", query = "SELECT p FROM ProConSol p" ),
            @NamedQuery( name = "ProConSol.findByConCodigo", query = "SELECT p FROM ProConSol p WHERE p.proConSolPK.conCodigo = :conCodigo" ),
            @NamedQuery( name = "ProConSol.findByProInvCodigo", query = "SELECT p FROM ProConSol p WHERE p.proConSolPK.proInvCodigo = :proInvCodigo" ),
            @NamedQuery( name = "ProConSol.findBySolCodigo", query = "SELECT p FROM ProConSol p WHERE p.proConSolPK.solCodigo = :solCodigo" )
        } )
public class ProConSol implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ProConSolPK proConSolPK;

    @Lob
    @Column( name = "CRONOGRAMA" )
    private Serializable cronograma;

    @JoinColumn( name = "CON_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false )
    @ManyToOne( optional = false, fetch = FetchType.LAZY  )
    private Convocatoria convocatoria;

    @JoinColumn( name = "EST_CODIGO", referencedColumnName = "CODIGO" )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private Estado estCodigo;

    @JoinColumn( name = "PRO_INV_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false )
    @ManyToOne( optional = false, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private ProyectoInvestigacion proyectoInvestigacion;

    @JoinColumn( name = "SOL_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private Solicitud solicitud;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "proConSol", fetch = FetchType.LAZY )
    private List<PcsRubro> pcsRubroList;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "proConSol", fetch = FetchType.LAZY )
    private List<UsuarioPcs> usuarioPcsList;

    public ProConSol()
    {
    }

    public ProConSol( ProConSolPK proConSolPK )
    {
        this.proConSolPK = proConSolPK;
    }

    public ProConSol( long conCodigo, long proInvCodigo, String solCodigo )
    {
        this.proConSolPK = new ProConSolPK( conCodigo, proInvCodigo, solCodigo );
    }

    public ProConSolPK getProConSolPK()
    {
        return proConSolPK;
    }

    public void setProConSolPK( ProConSolPK proConSolPK )
    {
        this.proConSolPK = proConSolPK;
    }

    public Serializable getCronograma()
    {
        return cronograma;
    }

    public void setCronograma( Serializable cronograma )
    {
        this.cronograma = cronograma;
    }

    public Convocatoria getConvocatoria()
    {
        return convocatoria;
    }

    public void setConvocatoria( Convocatoria convocatoria )
    {
        this.convocatoria = convocatoria;
    }

    public Estado getEstCodigo()
    {
        return estCodigo;
    }

    public void setEstCodigo( Estado estCodigo )
    {
        this.estCodigo = estCodigo;
    }

    public ProyectoInvestigacion getProyectoInvestigacion()
    {
        return proyectoInvestigacion;
    }

    public void setProyectoInvestigacion( ProyectoInvestigacion proyectoInvestigacion )
    {
        this.proyectoInvestigacion = proyectoInvestigacion;
    }

    public Solicitud getSolicitud()
    {
        return solicitud;
    }

    public void setSolicitud( Solicitud solicitud )
    {
        this.solicitud = solicitud;
    }

    @XmlTransient
    public List<PcsRubro> getPcsRubroList()
    {
        return pcsRubroList;
    }

    public void setPcsRubroList( List<PcsRubro> pcsRubroList )
    {
        this.pcsRubroList = pcsRubroList;
    }

    @XmlTransient
    public List<UsuarioPcs> getUsuarioPcsList()
    {
        return usuarioPcsList;
    }

    public void setUsuarioPcsList( List<UsuarioPcs> usuarioPcsList )
    {
        this.usuarioPcsList = usuarioPcsList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( proConSolPK != null ? proConSolPK.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if( !( object instanceof ProConSol ) )
        {
            return false;
        }
        ProConSol other = (ProConSol)object;
        if( ( this.proConSolPK == null && other.proConSolPK != null ) || ( this.proConSolPK != null && !this.proConSolPK.equals( other.proConSolPK ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sigeco.ejb.entities.ProConSol[ proConSolPK=" + proConSolPK + " ]";
    }

}
