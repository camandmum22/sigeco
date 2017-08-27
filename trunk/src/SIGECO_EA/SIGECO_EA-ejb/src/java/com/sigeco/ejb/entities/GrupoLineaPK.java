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
public class GrupoLineaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "GRU_INV_CODIGO")
    private String gruInvCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "LIN_INV_CODIGO")
    private String linInvCodigo;

    public GrupoLineaPK() {
    }

    public GrupoLineaPK(String gruInvCodigo, String linInvCodigo) {
        this.gruInvCodigo = gruInvCodigo;
        this.linInvCodigo = linInvCodigo;
    }

    public String getGruInvCodigo() {
        return gruInvCodigo;
    }

    public void setGruInvCodigo(String gruInvCodigo) {
        this.gruInvCodigo = gruInvCodigo;
    }

    public String getLinInvCodigo() {
        return linInvCodigo;
    }

    public void setLinInvCodigo(String linInvCodigo) {
        this.linInvCodigo = linInvCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruInvCodigo != null ? gruInvCodigo.hashCode() : 0);
        hash += (linInvCodigo != null ? linInvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoLineaPK)) {
            return false;
        }
        GrupoLineaPK other = (GrupoLineaPK) object;
        if ((this.gruInvCodigo == null && other.gruInvCodigo != null) || (this.gruInvCodigo != null && !this.gruInvCodigo.equals(other.gruInvCodigo))) {
            return false;
        }
        if ((this.linInvCodigo == null && other.linInvCodigo != null) || (this.linInvCodigo != null && !this.linInvCodigo.equals(other.linInvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.GrupoLineaPK[ gruInvCodigo=" + gruInvCodigo + ", linInvCodigo=" + linInvCodigo + " ]";
    }
    
}
