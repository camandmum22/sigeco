/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table(name = "ENTREGABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entregable.findAll", query = "SELECT e FROM Entregable e"),
    @NamedQuery(name = "Entregable.findByCodigo", query = "SELECT e FROM Entregable e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Entregable.findByNombre", query = "SELECT e FROM Entregable e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Entregable.findByFechaestimada", query = "SELECT e FROM Entregable e WHERE e.fechaestimada = :fechaestimada"),
    @NamedQuery(name = "Entregable.findByFechareal", query = "SELECT e FROM Entregable e WHERE e.fechareal = :fechareal")})
public class Entregable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="ENTREGABLE_GENERATOR", sequenceName="SQ_ENTREGABLE", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENTREGABLE_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FECHAESTIMADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaestimada;
    @Column(name = "FECHAREAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareal;
    @JoinColumn(name = "EST_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado estCodigo;
    @JoinColumn(name = "PRO_INV_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProyectoInvestigacion proInvCodigo;
    @JoinColumn(name = "SUBP_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Subproducto subpCodigo;

    public Entregable() {
    }

    public Entregable(Long codigo) {
        this.codigo = codigo;
    }

    public Entregable(Long codigo, String nombre) {
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

    public Date getFechaestimada() {
        return fechaestimada;
    }

    public void setFechaestimada(Date fechaestimada) {
        this.fechaestimada = fechaestimada;
    }

    public Date getFechareal() {
        return fechareal;
    }

    public void setFechareal(Date fechareal) {
        this.fechareal = fechareal;
    }

    public Estado getEstCodigo() {
        return estCodigo;
    }

    public void setEstCodigo(Estado estCodigo) {
        this.estCodigo = estCodigo;
    }

    public ProyectoInvestigacion getProInvCodigo() {
        return proInvCodigo;
    }

    public void setProInvCodigo(ProyectoInvestigacion proInvCodigo) {
        this.proInvCodigo = proInvCodigo;
    }

    public Subproducto getSubpCodigo() {
        return subpCodigo;
    }

    public void setSubpCodigo(Subproducto subpCodigo) {
        this.subpCodigo = subpCodigo;
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
        if (!(object instanceof Entregable)) {
            return false;
        }
        Entregable other = (Entregable) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Entregable[ codigo=" + codigo + " ]";
    }
    
}
