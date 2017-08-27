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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ROL_SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolSistema.findAll", query = "SELECT r FROM RolSistema r"),
    @NamedQuery(name = "RolSistema.findByCodigo", query = "SELECT r FROM RolSistema r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "RolSistema.findByNombre", query = "SELECT r FROM RolSistema r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RolSistema.findByDescripcion", query = "SELECT r FROM RolSistema r WHERE r.descripcion = :descripcion")})
public class RolSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="ROL_SISTEMA_GENERATOR", sequenceName="SQ_ROL_SISTEMA", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROL_SISTEMA_GENERATOR")
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
    @JoinTable(name = "USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "ROL_S_CODIGO", referencedColumnName = "CODIGO")}, inverseJoinColumns = {
        @JoinColumn(name = "USU_CODIGO", referencedColumnName = "CODIGO")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;
    @ManyToMany(mappedBy = "rolSistemaList", fetch = FetchType.LAZY)
    private List<Permisos> permisosList;
    @ManyToMany(mappedBy = "rolSistemaList", fetch = FetchType.LAZY)
    private List<Menu> menuList;

    public RolSistema() {
    }

    public RolSistema(Long codigo) {
        this.codigo = codigo;
    }

    public RolSistema(Long codigo, String nombre) {
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
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
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
        if (!(object instanceof RolSistema)) {
            return false;
        }
        RolSistema other = (RolSistema) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.RolSistema[ codigo=" + codigo + " ]";
    }
    
}
