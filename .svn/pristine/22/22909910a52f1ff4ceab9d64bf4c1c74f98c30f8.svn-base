/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "PERMISOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p"),
    @NamedQuery(name = "Permisos.findByUrl", query = "SELECT p FROM Permisos p WHERE p.url = :url"),
    @NamedQuery(name = "Permisos.findByNombre", query = "SELECT p FROM Permisos p WHERE p.nombre = :nombre")})
public class Permisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "PERMISOS_ROL", joinColumns = {
        @JoinColumn(name = "PER_URL", referencedColumnName = "URL")}, inverseJoinColumns = {
        @JoinColumn(name = "ROL_S_CODIGO", referencedColumnName = "CODIGO")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<RolSistema> rolSistemaList;
    @JoinColumn(name = "ROL_PRO_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private RolProyecto rolProCodigo;

    public Permisos() {
    }

    public Permisos(String url) {
        this.url = url;
    }

    public Permisos(String url, String nombre) {
        this.url = url;
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<RolSistema> getRolSistemaList() {
        return rolSistemaList;
    }

    public void setRolSistemaList(List<RolSistema> rolSistemaList) {
        this.rolSistemaList = rolSistemaList;
    }

    public RolProyecto getRolProCodigo() {
        return rolProCodigo;
    }

    public void setRolProCodigo(RolProyecto rolProCodigo) {
        this.rolProCodigo = rolProCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (url != null ? url.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.url == null && other.url != null) || (this.url != null && !this.url.equals(other.url))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Permisos[ url=" + url + " ]";
    }
    
}
