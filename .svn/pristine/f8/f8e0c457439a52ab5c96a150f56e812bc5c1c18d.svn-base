/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "FACULTAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findByCodigo", query = "SELECT f FROM Facultad f WHERE f.codigo = :codigo"),
    @NamedQuery(name = "Facultad.findByNombre", query = "SELECT f FROM Facultad f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Facultad.findBySiglas", query = "SELECT f FROM Facultad f WHERE f.siglas = :siglas")})
public class Facultad implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name="FACULTAD_GENERATOR", sequenceName="SQ_FACULTAD", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACULTAD_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigInteger codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SIGLAS")
    private String siglas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultad", fetch = FetchType.LAZY)
    private List<FacultadConvocatoria> facultadConvocatoriaList;
    @OneToOne(mappedBy = "facCodigo", fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facCodigo", fetch = FetchType.LAZY)
    private List<GrupoInvestigacion> grupoInvestigacionList;
    @JoinColumn(name = "USU_CODIGO", referencedColumnName = "CODIGO")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuCodigo;

    public Facultad() {
    }

    public Facultad(BigInteger codigo) {
        this.codigo = codigo;
    }

    public Facultad(BigInteger codigo, String nombre, String siglas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
    }

    public BigInteger getCodigo() {
        return codigo;
    }

    public void setCodigo(BigInteger codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    @XmlTransient
    public List<FacultadConvocatoria> getFacultadConvocatoriaList() {
        return facultadConvocatoriaList;
    }

    public void setFacultadConvocatoriaList(List<FacultadConvocatoria> facultadConvocatoriaList) {
        this.facultadConvocatoriaList = facultadConvocatoriaList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<GrupoInvestigacion> getGrupoInvestigacionList() {
        return grupoInvestigacionList;
    }

    public void setGrupoInvestigacionList(List<GrupoInvestigacion> grupoInvestigacionList) {
        this.grupoInvestigacionList = grupoInvestigacionList;
    }

    public Usuario getUsuCodigo() {
        return usuCodigo;
    }

    public void setUsuCodigo(Usuario usuCodigo) {
        this.usuCodigo = usuCodigo;
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
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Facultad[ codigo=" + codigo + " ]";
    }
    
}
