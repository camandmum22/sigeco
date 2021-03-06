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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table(name = "TARIFA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarifa.findAll", query = "SELECT t FROM Tarifa t"),
    @NamedQuery(name = "Tarifa.findByValor", query = "SELECT t FROM Tarifa t WHERE t.valor = :valor"),
    @NamedQuery(name = "Tarifa.findByUsuCodigo", query = "SELECT t FROM Tarifa t WHERE t.tarifaPK.usuCodigo = :usuCodigo"),
    @NamedQuery(name = "Tarifa.findByModCodigo", query = "SELECT t FROM Tarifa t WHERE t.tarifaPK.modCodigo = :modCodigo")})
public class Tarifa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TarifaPK tarifaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private BigInteger valor;
    @JoinColumn(name = "MOD_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modalidad modalidad;
    @JoinColumn(name = "USU_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarifa", fetch = FetchType.LAZY)
    private List<UsuarioPcs> usuarioPcsList;

    public Tarifa() {
    }

    public Tarifa(TarifaPK tarifaPK) {
        this.tarifaPK = tarifaPK;
    }

    public Tarifa(TarifaPK tarifaPK, BigInteger valor) {
        this.tarifaPK = tarifaPK;
        this.valor = valor;
    }

    public Tarifa(long usuCodigo, long modCodigo) {
        this.tarifaPK = new TarifaPK(usuCodigo, modCodigo);
    }

    public TarifaPK getTarifaPK() {
        return tarifaPK;
    }

    public void setTarifaPK(TarifaPK tarifaPK) {
        this.tarifaPK = tarifaPK;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<UsuarioPcs> getUsuarioPcsList() {
        return usuarioPcsList;
    }

    public void setUsuarioPcsList(List<UsuarioPcs> usuarioPcsList) {
        this.usuarioPcsList = usuarioPcsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarifaPK != null ? tarifaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarifa)) {
            return false;
        }
        Tarifa other = (Tarifa) object;
        if ((this.tarifaPK == null && other.tarifaPK != null) || (this.tarifaPK != null && !this.tarifaPK.equals(other.tarifaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Tarifa[ tarifaPK=" + tarifaPK + " ]";
    }
    
}
