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

/**
 *
 * @author germandavidlozano
 */
@Embeddable
public class TarifaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_CODIGO")
    private long usuCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOD_CODIGO")
    private long modCodigo;

    public TarifaPK() {
    }

    public TarifaPK(long usuCodigo, long modCodigo) {
        this.usuCodigo = usuCodigo;
        this.modCodigo = modCodigo;
    }

    public long getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(long usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    public long getModCodigo() {
        return modCodigo;
    }

    public void setModCodigo(long modCodigo) {
        this.modCodigo = modCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuCodigo;
        hash += (int) modCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarifaPK)) {
            return false;
        }
        TarifaPK other = (TarifaPK) object;
        if (this.usuCodigo != other.usuCodigo) {
            return false;
        }
        if (this.modCodigo != other.modCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.TarifaPK[ usuCodigo=" + usuCodigo + ", modCodigo=" + modCodigo + " ]";
    }
    
}
