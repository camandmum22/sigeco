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
@Table(name = "TIPO_INVESTIGACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoInvestigacion.findAll", query = "SELECT t FROM TipoInvestigacion t"),
    @NamedQuery(name = "TipoInvestigacion.findByCodigo", query = "SELECT t FROM TipoInvestigacion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoInvestigacion.findByTipo", query = "SELECT t FROM TipoInvestigacion t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TipoInvestigacion.findByDescripcion", query = "SELECT t FROM TipoInvestigacion t WHERE t.descripcion = :descripcion")})
public class TipoInvestigacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipInvCodigo", fetch = FetchType.LAZY)
    private List<ProyectoInvestigacion> proyectoInvestigacionList;

    public TipoInvestigacion() {
    }

    public TipoInvestigacion(String codigo) {
        this.codigo = codigo;
    }

    public TipoInvestigacion(String codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<ProyectoInvestigacion> getProyectoInvestigacionList() {
        return proyectoInvestigacionList;
    }

    public void setProyectoInvestigacionList(List<ProyectoInvestigacion> proyectoInvestigacionList) {
        this.proyectoInvestigacionList = proyectoInvestigacionList;
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
        if (!(object instanceof TipoInvestigacion)) {
            return false;
        }
        TipoInvestigacion other = (TipoInvestigacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.TipoInvestigacion[ codigo=" + codigo + " ]";
    }
    
}
