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
public class InvestigadorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "GRU_INV_CODIGO")
    private String gruInvCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_CODIGO")
    private long usuCodigo;

    public InvestigadorPK() {
    }

    public InvestigadorPK(String gruInvCodigo, long usuCodigo) {
        this.gruInvCodigo = gruInvCodigo;
        this.usuCodigo = usuCodigo;
    }

    public String getGruInvCodigo() {
        return gruInvCodigo;
    }

    public void setGruInvCodigo(String gruInvCodigo) {
        this.gruInvCodigo = gruInvCodigo;
    }

    public long getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(long usuCodigo) {
        this.usuCodigo = usuCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruInvCodigo != null ? gruInvCodigo.hashCode() : 0);
        hash += (int) usuCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvestigadorPK)) {
            return false;
        }
        InvestigadorPK other = (InvestigadorPK) object;
        if ((this.gruInvCodigo == null && other.gruInvCodigo != null) || (this.gruInvCodigo != null && !this.gruInvCodigo.equals(other.gruInvCodigo))) {
            return false;
        }
        if (this.usuCodigo != other.usuCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.InvestigadorPK[ gruInvCodigo=" + gruInvCodigo + ", usuCodigo=" + usuCodigo + " ]";
    }
    
}
