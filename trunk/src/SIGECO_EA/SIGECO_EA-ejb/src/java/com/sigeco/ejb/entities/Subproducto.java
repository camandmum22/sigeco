/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "SUBPRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subproducto.findAll", query = "SELECT s FROM Subproducto s"),
    @NamedQuery(name = "Subproducto.findByCodigo", query = "SELECT s FROM Subproducto s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "Subproducto.findByCategoria", query = "SELECT s FROM Subproducto s WHERE s.categoria = :categoria"),
    @NamedQuery(name = "Subproducto.findByVentanaObservacion", query = "SELECT s FROM Subproducto s WHERE s.ventanaObservacion = :ventanaObservacion"),
    @NamedQuery(name = "Subproducto.findByRequerimientoCalidad", query = "SELECT s FROM Subproducto s WHERE s.requerimientoCalidad = :requerimientoCalidad"),
    @NamedQuery(name = "Subproducto.findByPesoRelativo1", query = "SELECT s FROM Subproducto s WHERE s.pesoRelativo1 = :pesoRelativo1"),
    @NamedQuery(name = "Subproducto.findByPesoRelativo2", query = "SELECT s FROM Subproducto s WHERE s.pesoRelativo2 = :pesoRelativo2"),
    @NamedQuery(name = "Subproducto.findByVigencia", query = "SELECT s FROM Subproducto s WHERE s.vigencia = :vigencia")})
public class Subproducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="SUBPRODUCTO_GENERATOR", sequenceName="SQ_SUBPRODUCTO", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUBPRODUCTO_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CATEGORIA")
    private String categoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VENTANA_OBSERVACION")
    private BigInteger ventanaObservacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "REQUERIMIENTO_CALIDAD")
    private String requerimientoCalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PESO_RELATIVO_1")
    private String pesoRelativo1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PESO_RELATIVO_2")
    private String pesoRelativo2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subpCodigo", fetch = FetchType.LAZY)
    private List<Entregable> entregableList;
    @JoinColumn(name = "PROD_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto prodCodigo;

    public Subproducto() {
    }

    public Subproducto(Integer codigo) {
        this.codigo = codigo;
    }

    public Subproducto(Integer codigo, String categoria, BigInteger ventanaObservacion, String requerimientoCalidad, String pesoRelativo1, String pesoRelativo2, Date vigencia) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.ventanaObservacion = ventanaObservacion;
        this.requerimientoCalidad = requerimientoCalidad;
        this.pesoRelativo1 = pesoRelativo1;
        this.pesoRelativo2 = pesoRelativo2;
        this.vigencia = vigencia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigInteger getVentanaObservacion() {
        return ventanaObservacion;
    }

    public void setVentanaObservacion(BigInteger ventanaObservacion) {
        this.ventanaObservacion = ventanaObservacion;
    }

    public String getRequerimientoCalidad() {
        return requerimientoCalidad;
    }

    public void setRequerimientoCalidad(String requerimientoCalidad) {
        this.requerimientoCalidad = requerimientoCalidad;
    }

    public String getPesoRelativo1() {
        return pesoRelativo1;
    }

    public void setPesoRelativo1(String pesoRelativo1) {
        this.pesoRelativo1 = pesoRelativo1;
    }

    public String getPesoRelativo2() {
        return pesoRelativo2;
    }

    public void setPesoRelativo2(String pesoRelativo2) {
        this.pesoRelativo2 = pesoRelativo2;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    @XmlTransient
    public List<Entregable> getEntregableList() {
        return entregableList;
    }

    public void setEntregableList(List<Entregable> entregableList) {
        this.entregableList = entregableList;
    }

    public Producto getProdCodigo() {
        return prodCodigo;
    }

    public void setProdCodigo(Producto prodCodigo) {
        this.prodCodigo = prodCodigo;
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
        if (!(object instanceof Subproducto)) {
            return false;
        }
        Subproducto other = (Subproducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Subproducto[ codigo=" + codigo + " ]";
    }
    
}
