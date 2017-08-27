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
@Table(name = "CLASIFICACION_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionProducto.findAll", query = "SELECT c FROM ClasificacionProducto c"),
    @NamedQuery(name = "ClasificacionProducto.findByCodigo", query = "SELECT c FROM ClasificacionProducto c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ClasificacionProducto.findByClasificacion", query = "SELECT c FROM ClasificacionProducto c WHERE c.clasificacion = :clasificacion")})
public class ClasificacionProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="CLASIFICACION_PRODUCTO_GENERATOR", sequenceName="SQ_CLASIFICACION_PRODUCTO", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLASIFICACION_PRODUCTO_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLASIFICACION")
    private String clasificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claProCodigo", fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public ClasificacionProducto() {
    }

    public ClasificacionProducto(String codigo) {
        this.codigo = codigo;
    }

    public ClasificacionProducto(String codigo, String clasificacion) {
        this.codigo = codigo;
        this.clasificacion = clasificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
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
        if (!(object instanceof ClasificacionProducto)) {
            return false;
        }
        ClasificacionProducto other = (ClasificacionProducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.ClasificacionProducto[ codigo=" + codigo + " ]";
    }
    
}
