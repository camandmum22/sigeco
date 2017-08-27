/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table(name = "LINEA_AREA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaArea.findAll", query = "SELECT l FROM LineaArea l"),
    @NamedQuery(name = "LineaArea.findByLinInvCodigo", query = "SELECT l FROM LineaArea l WHERE l.lineaAreaPK.linInvCodigo = :linInvCodigo"),
    @NamedQuery(name = "LineaArea.findByAreEstCodigo", query = "SELECT l FROM LineaArea l WHERE l.lineaAreaPK.areEstCodigo = :areEstCodigo"),
    @NamedQuery(name = "LineaArea.findByTipo", query = "SELECT l FROM LineaArea l WHERE l.tipo = :tipo")})
public class LineaArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineaAreaPK lineaAreaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "ARE_EST_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreaEstrategica areaEstrategica;
    @JoinColumn(name = "LIN_INV_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LineaInvestigacion lineaInvestigacion;

    public LineaArea() {
    }

    public LineaArea(LineaAreaPK lineaAreaPK) {
        this.lineaAreaPK = lineaAreaPK;
    }

    public LineaArea(LineaAreaPK lineaAreaPK, String tipo) {
        this.lineaAreaPK = lineaAreaPK;
        this.tipo = tipo;
    }

    public LineaArea(String linInvCodigo, long areEstCodigo) {
        this.lineaAreaPK = new LineaAreaPK(linInvCodigo, areEstCodigo);
    }

    public LineaAreaPK getLineaAreaPK() {
        return lineaAreaPK;
    }

    public void setLineaAreaPK(LineaAreaPK lineaAreaPK) {
        this.lineaAreaPK = lineaAreaPK;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public AreaEstrategica getAreaEstrategica() {
        return areaEstrategica;
    }

    public void setAreaEstrategica(AreaEstrategica areaEstrategica) {
        this.areaEstrategica = areaEstrategica;
    }

    public LineaInvestigacion getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
        this.lineaInvestigacion = lineaInvestigacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineaAreaPK != null ? lineaAreaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaArea)) {
            return false;
        }
        LineaArea other = (LineaArea) object;
        if ((this.lineaAreaPK == null && other.lineaAreaPK != null) || (this.lineaAreaPK != null && !this.lineaAreaPK.equals(other.lineaAreaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.LineaArea[ lineaAreaPK=" + lineaAreaPK + " ]";
    }
    
}
