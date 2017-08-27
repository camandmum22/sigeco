/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table(name = "LINEA_INVESTIGACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaInvestigacion.findAll", query = "SELECT l FROM LineaInvestigacion l"),
    @NamedQuery(name = "LineaInvestigacion.findByCodigo", query = "SELECT l FROM LineaInvestigacion l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "LineaInvestigacion.findByNombre", query = "SELECT l FROM LineaInvestigacion l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "LineaInvestigacion.findByDescripcion", query = "SELECT l FROM LineaInvestigacion l WHERE l.descripcion = :descripcion"),
    @NamedQuery(name = "LineaInvestigacion.findByVencimientoAcreditacion", query = "SELECT l FROM LineaInvestigacion l WHERE l.vencimientoAcreditacion = :vencimientoAcreditacion")})
public class LineaInvestigacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "VENCIMIENTO_ACREDITACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimientoAcreditacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineaInvestigacion", fetch = FetchType.LAZY)
    private List<LineaArea> lineaAreaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineaInvestigacion", fetch = FetchType.LAZY)
    private List<GrupoLinea> grupoLineaList;

    public LineaInvestigacion() {
    }

    public LineaInvestigacion(String codigo) {
        this.codigo = codigo;
    }

    public LineaInvestigacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getVencimientoAcreditacion() {
        return vencimientoAcreditacion;
    }

    public void setVencimientoAcreditacion(Date vencimientoAcreditacion) {
        this.vencimientoAcreditacion = vencimientoAcreditacion;
    }

    @XmlTransient
    public List<LineaArea> getLineaAreaList() {
        return lineaAreaList;
    }

    public void setLineaAreaList(List<LineaArea> lineaAreaList) {
        this.lineaAreaList = lineaAreaList;
    }

    @XmlTransient
    public List<GrupoLinea> getGrupoLineaList() {
        return grupoLineaList;
    }

    public void setGrupoLineaList(List<GrupoLinea> grupoLineaList) {
        this.grupoLineaList = grupoLineaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaInvestigacion)) {
            return false;
        }
        LineaInvestigacion other = (LineaInvestigacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.LineaInvestigacion[ codigo=" + codigo + " ]";
    }
    
}
