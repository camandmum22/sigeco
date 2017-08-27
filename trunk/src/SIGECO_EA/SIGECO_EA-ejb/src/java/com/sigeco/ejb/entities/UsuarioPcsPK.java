/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author germandavidlozano
 */
@Embeddable
public class UsuarioPcsPK implements Serializable
{
    @Basic( optional = false )
    @NotNull
    @Column( name = "PRO_C_S_CON_CODIGO" )
    private long proCSConCodigo;

    @Basic( optional = false )
    @NotNull
    @Column( name = "PRO_C_S_CODIGO1" )
    private long proCSCodigo1;

    @Basic( optional = false )
    @NotNull
    @Column( name = "TAR_CODIGO1" )
    private long tarCodigo1;

    @Basic( optional = false )
    @NotNull
    @Column( name = "TAR_MOD_CODIGO" )
    private long tarModCodigo;

    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 2 )
    @Column( name = "PRO_C_S_CODIGO2" )
    private String proCSCodigo2;

    public UsuarioPcsPK()
    {
    }

    public UsuarioPcsPK( long proCSConCodigo, long proCSCodigo1, long tarCodigo1, long tarModCodigo, String proCSCodigo2 )
    {
        this.proCSConCodigo = proCSConCodigo;
        this.proCSCodigo1 = proCSCodigo1;
        this.tarCodigo1 = tarCodigo1;
        this.tarModCodigo = tarModCodigo;
        this.proCSCodigo2 = proCSCodigo2;
    }

    public long getProCSConCodigo()
    {
        return proCSConCodigo;
    }

    public void setProCSConCodigo( long proCSConCodigo )
    {
        this.proCSConCodigo = proCSConCodigo;
    }

    public long getProCSCodigo1()
    {
        return proCSCodigo1;
    }

    public void setProCSCodigo1( long proCSCodigo1 )
    {
        this.proCSCodigo1 = proCSCodigo1;
    }

    public long getTarCodigo1()
    {
        return tarCodigo1;
    }

    public void setTarCodigo1( long tarCodigo1 )
    {
        this.tarCodigo1 = tarCodigo1;
    }

    public long getTarModCodigo()
    {
        return tarModCodigo;
    }

    public void setTarModCodigo( long tarModCodigo )
    {
        this.tarModCodigo = tarModCodigo;
    }

    public String getProCSCodigo2()
    {
        return proCSCodigo2;
    }

    public void setProCSCodigo2( String proCSCodigo2 )
    {
        this.proCSCodigo2 = proCSCodigo2;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int)proCSConCodigo;
        hash += (int)proCSCodigo1;
        hash += (int)tarCodigo1;
        hash += (int)tarModCodigo;
        hash += ( proCSCodigo2 != null ? proCSCodigo2.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if( !( object instanceof UsuarioPcsPK ) )
        {
            return false;
        }
        UsuarioPcsPK other = (UsuarioPcsPK)object;
        if( this.proCSConCodigo != other.proCSConCodigo )
        {
            return false;
        }
        if( this.proCSCodigo1 != other.proCSCodigo1 )
        {
            return false;
        }
        if( this.tarCodigo1 != other.tarCodigo1 )
        {
            return false;
        }
        if( this.tarModCodigo != other.tarModCodigo )
        {
            return false;
        }
        if( ( this.proCSCodigo2 == null && other.proCSCodigo2 != null ) || ( this.proCSCodigo2 != null && !this.proCSCodigo2.equals( other.proCSCodigo2 ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sigeco.ejb.entities.UsuarioPcsPK[ proCSConCodigo=" + proCSConCodigo + ", proCSCodigo1=" + proCSCodigo1 + ", tarCodigo1=" + tarCodigo1 + ", tarModCodigo=" + tarModCodigo + ", proCSCodigo2=" + proCSCodigo2 + " ]";
    }

}