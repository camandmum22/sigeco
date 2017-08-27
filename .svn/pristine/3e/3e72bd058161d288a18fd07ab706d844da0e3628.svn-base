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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m ORDER BY m.nombreMenu ASC"),
    @NamedQuery(name = "Menu.findByCodigo", query = "SELECT m FROM Menu m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Menu.findByNombreMenu", query = "SELECT m FROM Menu m WHERE m.nombreMenu = :nombreMenu")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MENU_GENERATOR", sequenceName="SQ_MENU", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MENU_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_MENU")
    private String nombreMenu;
    @JoinTable(name = "ROL_MENU", joinColumns = {
        @JoinColumn(name = "MEN_CODIGO", referencedColumnName = "CODIGO")}, inverseJoinColumns = {
        @JoinColumn(name = "ROL_S_CODIGO", referencedColumnName = "CODIGO")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<RolSistema> rolSistemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menCodigo", fetch = FetchType.LAZY)
    private List<PaginaXhtml> paginaXhtmlList;
    @OneToMany(mappedBy = "menCodigo", fetch = FetchType.LAZY)
    private List<Menu> menuList;
    @JoinColumn(name = "MEN_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menCodigo;

    public Menu() {
    }

    public Menu(Long codigo) {
        this.codigo = codigo;
    }

    public Menu(Long codigo, String nombreMenu) {
        this.codigo = codigo;
        this.nombreMenu = nombreMenu;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    @XmlTransient
    public List<RolSistema> getRolSistemaList() {
        return rolSistemaList;
    }

    public void setRolSistemaList(List<RolSistema> rolSistemaList) {
        this.rolSistemaList = rolSistemaList;
    }

    @XmlTransient
    public List<PaginaXhtml> getPaginaXhtmlList() {
        return paginaXhtmlList;
    }

    public void setPaginaXhtmlList(List<PaginaXhtml> paginaXhtmlList) {
        this.paginaXhtmlList = paginaXhtmlList;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getMenCodigo() {
        return menCodigo;
    }

    public void setMenCodigo(Menu menCodigo) {
        this.menCodigo = menCodigo;
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Menu[ codigo=" + codigo + " ]";
    }
    
}
