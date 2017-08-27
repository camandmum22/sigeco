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
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByCodigo", query = "SELECT p FROM Producto p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findBySiglas", query = "SELECT p FROM Producto p WHERE p.siglas = :siglas")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="PRODUCTO_GENERATOR", sequenceName="SQ_PRODUCTO", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTO_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "SIGLAS")
    private String siglas;
    @JoinColumn(name = "CLA_PRO_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClasificacionProducto claProCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodCodigo", fetch = FetchType.LAZY)
    private List<Subproducto> subproductoList;

    public Producto() {
    }

    public Producto(Integer codigo) {
        this.codigo = codigo;
    }

    public Producto(Integer codigo, String nombre, String siglas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siglas = siglas;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
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

    public ClasificacionProducto getClaProCodigo() {
        return claProCodigo;
    }

    public void setClaProCodigo(ClasificacionProducto claProCodigo) {
        this.claProCodigo = claProCodigo;
    }

    @XmlTransient
    public List<Subproducto> getSubproductoList() {
        return subproductoList;
    }

    public void setSubproductoList(List<Subproducto> subproductoList) {
        this.subproductoList = subproductoList;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Producto[ codigo=" + codigo + " ]";
    }
    
}
