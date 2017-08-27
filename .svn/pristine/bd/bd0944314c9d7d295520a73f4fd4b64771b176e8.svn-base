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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author germandavidlozano
 */
@Embeddable
public class FacultadConvocatoriaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CON_CODIGO")
    private long conCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_CODIGO")
    private BigInteger facCodigo;

    public FacultadConvocatoriaPK() {
    }

    public FacultadConvocatoriaPK(long conCodigo, BigInteger facCodigo) {
        this.conCodigo = conCodigo;
        this.facCodigo = facCodigo;
    }

    public long getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(long conCodigo) {
        this.conCodigo = conCodigo;
    }

    public BigInteger getFacCodigo() {
        return facCodigo;
    }

    public void setFacCodigo(BigInteger facCodigo) {
        this.facCodigo = facCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) conCodigo;
        hash += (facCodigo != null ? facCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadConvocatoriaPK)) {
            return false;
        }
        FacultadConvocatoriaPK other = (FacultadConvocatoriaPK) object;
        if (this.conCodigo != other.conCodigo) {
            return false;
        }
        if ((this.facCodigo == null && other.facCodigo != null) || (this.facCodigo != null && !this.facCodigo.equals(other.facCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.FacultadConvocatoriaPK[ conCodigo=" + conCodigo + ", facCodigo=" + facCodigo + " ]";
    }
    
}
