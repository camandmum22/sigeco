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
public class LineaAreaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "LIN_INV_CODIGO")
    private String linInvCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ARE_EST_CODIGO")
    private long areEstCodigo;

    public LineaAreaPK() {
    }

    public LineaAreaPK(String linInvCodigo, long areEstCodigo) {
        this.linInvCodigo = linInvCodigo;
        this.areEstCodigo = areEstCodigo;
    }

    public String getLinInvCodigo() {
        return linInvCodigo;
    }

    public void setLinInvCodigo(String linInvCodigo) {
        this.linInvCodigo = linInvCodigo;
    }

    public long getAreEstCodigo() {
        return areEstCodigo;
    }

    public void setAreEstCodigo(long areEstCodigo) {
        this.areEstCodigo = areEstCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linInvCodigo != null ? linInvCodigo.hashCode() : 0);
        hash += (int) areEstCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaAreaPK)) {
            return false;
        }
        LineaAreaPK other = (LineaAreaPK) object;
        if ((this.linInvCodigo == null && other.linInvCodigo != null) || (this.linInvCodigo != null && !this.linInvCodigo.equals(other.linInvCodigo))) {
            return false;
        }
        if (this.areEstCodigo != other.areEstCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.LineaAreaPK[ linInvCodigo=" + linInvCodigo + ", areEstCodigo=" + areEstCodigo + " ]";
    }
    
}
