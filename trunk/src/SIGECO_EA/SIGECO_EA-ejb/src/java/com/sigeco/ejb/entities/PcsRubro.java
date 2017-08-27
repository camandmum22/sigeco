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
@Table(name = "PCS_RUBRO")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PcsRubro.findAll", query = "SELECT p FROM PcsRubro p"),
    @NamedQuery(name = "PcsRubro.findByCantidad", query = "SELECT p FROM PcsRubro p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PcsRubro.findByRubCodigo", query = "SELECT p FROM PcsRubro p WHERE p.pcsRubroPK.rubCodigo = :rubCodigo"),
    @NamedQuery(name = "PcsRubro.findByProCSConCodigo", query = "SELECT p FROM PcsRubro p WHERE p.pcsRubroPK.proCSConCodigo = :proCSConCodigo"),
    @NamedQuery(name = "PcsRubro.findByProCSCodigo1", query = "SELECT p FROM PcsRubro p WHERE p.pcsRubroPK.proCSCodigo1 = :proCSCodigo1"),
    @NamedQuery(name = "PcsRubro.findByProCSCodigo2", query = "SELECT p FROM PcsRubro p WHERE p.pcsRubroPK.proCSCodigo2 = :proCSCodigo2")
})
public class PcsRubro implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PcsRubroPK pcsRubroPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @JoinColumns(
    {
        @JoinColumn(name = "PRO_C_S_CON_CODIGO", referencedColumnName = "CON_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "PRO_C_S_CODIGO1", referencedColumnName = "PRO_INV_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "PRO_C_S_CODIGO2", referencedColumnName = "SOL_CODIGO", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProConSol proConSol;
    @JoinColumn(name = "RUB_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rubro rubro;

    public PcsRubro()
    {
    }

    public PcsRubro(PcsRubroPK pcsRubroPK)
    {
        this.pcsRubroPK = pcsRubroPK;
    }

    public PcsRubro(PcsRubroPK pcsRubroPK, BigInteger cantidad)
    {
        this.pcsRubroPK = pcsRubroPK;
        this.cantidad = cantidad;
    }

    public PcsRubro(long rubCodigo, long proCSConCodigo, long proCSCodigo1, String proCSCodigo2)
    {
        this.pcsRubroPK = new PcsRubroPK(rubCodigo, proCSConCodigo, proCSCodigo1, proCSCodigo2);
    }

    public PcsRubroPK getPcsRubroPK()
    {
        return pcsRubroPK;
    }

    public void setPcsRubroPK(PcsRubroPK pcsRubroPK)
    {
        this.pcsRubroPK = pcsRubroPK;
    }

    public BigInteger getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad)
    {
        this.cantidad = cantidad;
    }

    public ProConSol getProConSol()
    {
        return proConSol;
    }

    public void setProConSol(ProConSol proConSol)
    {
        this.proConSol = proConSol;
    }

    public Rubro getRubro()
    {
        return rubro;
    }

    public void setRubro(Rubro rubro)
    {
        this.rubro = rubro;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pcsRubroPK != null ? pcsRubroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PcsRubro))
        {
            return false;
        }
        PcsRubro other = (PcsRubro) object;
        if ((this.pcsRubroPK == null && other.pcsRubroPK != null) || (this.pcsRubroPK != null && !this.pcsRubroPK.equals(other.pcsRubroPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sigeco.ejb.entities.PcsRubro[ pcsRubroPK=" + pcsRubroPK + " ]";
    }

}
