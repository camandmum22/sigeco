/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table(name = "GRUPO_INVESTIGACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoInvestigacion.findAll", query = "SELECT g FROM GrupoInvestigacion g"),
    @NamedQuery(name = "GrupoInvestigacion.findByCodigo", query = "SELECT g FROM GrupoInvestigacion g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GrupoInvestigacion.findByNombre", query = "SELECT g FROM GrupoInvestigacion g WHERE g.nombre = :nombre")})
public class GrupoInvestigacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoInvestigacion", fetch = FetchType.LAZY)
    private List<Investigador> investigadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoInvestigacion", fetch = FetchType.LAZY)
    private List<GrupoLinea> grupoLineaList;
    @JoinColumn(name = "FAC_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Facultad facCodigo;

    public GrupoInvestigacion() {
    }

    public GrupoInvestigacion(String codigo) {
        this.codigo = codigo;
    }

    public GrupoInvestigacion(String codigo, String nombre) {
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

    @XmlTransient
    public List<Investigador> getInvestigadorList() {
        return investigadorList;
    }

    public void setInvestigadorList(List<Investigador> investigadorList) {
        this.investigadorList = investigadorList;
    }

    @XmlTransient
    public List<GrupoLinea> getGrupoLineaList() {
        return grupoLineaList;
    }

    public void setGrupoLineaList(List<GrupoLinea> grupoLineaList) {
        this.grupoLineaList = grupoLineaList;
    }

    public Facultad getFacCodigo() {
        return facCodigo;
    }

    public void setFacCodigo(Facultad facCodigo) {
        this.facCodigo = facCodigo;
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
        if (!(object instanceof GrupoInvestigacion)) {
            return false;
        }
        GrupoInvestigacion other = (GrupoInvestigacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.GrupoInvestigacion[ codigo=" + codigo + " ]";
    }
    
}
