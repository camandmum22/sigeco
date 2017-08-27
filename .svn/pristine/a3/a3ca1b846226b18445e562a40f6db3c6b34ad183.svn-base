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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "TIPO_PROYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProyecto.findAll", query = "SELECT t FROM TipoProyecto t"),
    @NamedQuery(name = "TipoProyecto.findByCodigo", query = "SELECT t FROM TipoProyecto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoProyecto.findByNombre", query = "SELECT t FROM TipoProyecto t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoProyecto.findByDescripcion", query = "SELECT t FROM TipoProyecto t WHERE t.descripcion = :descripcion")})
public class TipoProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="TIPO_PROYECTO_GENERATOR", sequenceName="SQ_TIPO_PROYECTO", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PROYECTO_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipProCodigo", fetch = FetchType.LAZY)
    private List<ProyectoInvestigacion> proyectoInvestigacionList;

    public TipoProyecto() {
    }

    public TipoProyecto(Long codigo) {
        this.codigo = codigo;
    }

    public TipoProyecto(Long codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
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
        if (!(object instanceof TipoProyecto)) {
            return false;
        }
        TipoProyecto other = (TipoProyecto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.TipoProyecto[ codigo=" + codigo + " ]";
    }
    
}
