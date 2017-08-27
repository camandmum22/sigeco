/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table( name = "USUARIO_PCS" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "UsuarioPcs.findAll", query = "SELECT u FROM UsuarioPcs u" ),
    @NamedQuery( name = "UsuarioPcs.findByDedicacion", query = "SELECT u FROM UsuarioPcs u WHERE u.dedicacion = :dedicacion" ),
    @NamedQuery( name = "UsuarioPcs.findByProCSConCodigo", query = "SELECT u FROM UsuarioPcs u WHERE u.usuarioPcsPK.proCSConCodigo = :proCSConCodigo" ),
    @NamedQuery( name = "UsuarioPcs.findByProCSCodigo1", query = "SELECT u FROM UsuarioPcs u WHERE u.usuarioPcsPK.proCSCodigo1 = :proCSCodigo1" ),
    @NamedQuery( name = "UsuarioPcs.findByTarCodigo1", query = "SELECT u FROM UsuarioPcs u WHERE u.usuarioPcsPK.tarCodigo1 = :tarCodigo1" ),
    @NamedQuery( name = "UsuarioPcs.findByTarModCodigo", query = "SELECT u FROM UsuarioPcs u WHERE u.usuarioPcsPK.tarModCodigo = :tarModCodigo" ),
    @NamedQuery( name = "UsuarioPcs.findByProCSCodigo2", query = "SELECT u FROM UsuarioPcs u WHERE u.usuarioPcsPK.proCSCodigo2 = :proCSCodigo2" )
} )
public class UsuarioPcs implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected UsuarioPcsPK usuarioPcsPK;

    @Basic( optional = false )
    @NotNull
    @Column( name = "DEDICACION" )
    private BigInteger dedicacion;

    @JoinColumns( 
    {
        @JoinColumn( name = "PRO_C_S_CON_CODIGO", referencedColumnName = "CON_CODIGO", insertable = false, updatable = false ),
        @JoinColumn( name = "PRO_C_S_CODIGO1", referencedColumnName = "PRO_INV_CODIGO", insertable = false, updatable = false ),
        @JoinColumn( name = "PRO_C_S_CODIGO2", referencedColumnName = "SOL_CODIGO", insertable = false, updatable = false )
    } )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private ProConSol proConSol;

    @JoinColumn( name = "ROL_PRO_CODIGO", referencedColumnName = "CODIGO" )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private RolProyecto rolProCodigo;

    @JoinColumns( 
    {
        @JoinColumn( name = "TAR_CODIGO1", referencedColumnName = "USU_CODIGO", insertable = false, updatable = false ),
        @JoinColumn( name = "TAR_MOD_CODIGO", referencedColumnName = "MOD_CODIGO", insertable = false, updatable = false )
    } )
    @ManyToOne( optional = false, fetch = FetchType.LAZY )
    private Tarifa tarifa;

    public UsuarioPcs()
    {
    }

    public UsuarioPcs( UsuarioPcsPK usuarioPcsPK )
    {
        this.usuarioPcsPK = usuarioPcsPK;
    }

    public UsuarioPcs( UsuarioPcsPK usuarioPcsPK, BigInteger dedicacion )
    {
        this.usuarioPcsPK = usuarioPcsPK;
        this.dedicacion = dedicacion;
    }

    public UsuarioPcs( long proCSConCodigo, long proCSCodigo1, long tarCodigo1, long tarModCodigo, String proCSCodigo2 )
    {
        this.usuarioPcsPK = new UsuarioPcsPK( proCSConCodigo, proCSCodigo1, tarCodigo1, tarModCodigo, proCSCodigo2 );
    }

    public UsuarioPcsPK getUsuarioPcsPK()
    {
        return usuarioPcsPK;
    }

    public void setUsuarioPcsPK( UsuarioPcsPK usuarioPcsPK )
    {
        this.usuarioPcsPK = usuarioPcsPK;
    }

    public BigInteger getDedicacion()
    {
        return dedicacion;
    }

    public void setDedicacion( BigInteger dedicacion )
    {
        this.dedicacion = dedicacion;
    }

    public ProConSol getProConSol()
    {
        return proConSol;
    }

    public void setProConSol( ProConSol proConSol )
    {
        this.proConSol = proConSol;
    }

    public RolProyecto getRolProCodigo()
    {
        return rolProCodigo;
    }

    public void setRolProCodigo( RolProyecto rolProCodigo )
    {
        this.rolProCodigo = rolProCodigo;
    }

    public Tarifa getTarifa()
    {
        return tarifa;
    }

    public void setTarifa( Tarifa tarifa )
    {
        this.tarifa = tarifa;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( usuarioPcsPK != null ? usuarioPcsPK.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if( !( object instanceof UsuarioPcs ) )
        {
            return false;
        }
        UsuarioPcs other = (UsuarioPcs)object;
        if( ( this.usuarioPcsPK == null && other.usuarioPcsPK != null ) || ( this.usuarioPcsPK != null && !this.usuarioPcsPK.equals( other.usuarioPcsPK ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sigeco.ejb.entities.UsuarioPcs[ usuarioPcsPK=" + usuarioPcsPK + " ]";
    }

}
