/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table(name = "INVESTIGADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Investigador.findAll", query = "SELECT i FROM Investigador i"),
    @NamedQuery(name = "Investigador.findByGruInvCodigo", query = "SELECT i FROM Investigador i WHERE i.investigadorPK.gruInvCodigo = :gruInvCodigo"),
    @NamedQuery(name = "Investigador.findByUsuCodigo", query = "SELECT i FROM Investigador i WHERE i.investigadorPK.usuCodigo = :usuCodigo"),
    @NamedQuery(name = "Investigador.findByEslider", query = "SELECT i FROM Investigador i WHERE i.eslider = :eslider")})
public class Investigador implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvestigadorPK investigadorPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESLIDER")
    private String eslider;
    @JoinColumn(name = "GRU_INV_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrupoInvestigacion grupoInvestigacion;
    @JoinColumn(name = "USU_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Investigador() {
    }

    public Investigador(InvestigadorPK investigadorPK) {
        this.investigadorPK = investigadorPK;
    }

    public Investigador(InvestigadorPK investigadorPK, String eslider) {
        this.investigadorPK = investigadorPK;
        this.eslider = eslider;
    }

    public Investigador(String gruInvCodigo, long usuCodigo) {
        this.investigadorPK = new InvestigadorPK(gruInvCodigo, usuCodigo);
    }

    public InvestigadorPK getInvestigadorPK() {
        return investigadorPK;
    }

    public void setInvestigadorPK(InvestigadorPK investigadorPK) {
        this.investigadorPK = investigadorPK;
    }

    public String getEslider() {
        return eslider;
    }

    public void setEslider(String eslider) {
        this.eslider = eslider;
    }

    public GrupoInvestigacion getGrupoInvestigacion() {
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (investigadorPK != null ? investigadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Investigador)) {
            return false;
        }
        Investigador other = (Investigador) object;
        if ((this.investigadorPK == null && other.investigadorPK != null) || (this.investigadorPK != null && !this.investigadorPK.equals(other.investigadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.Investigador[ investigadorPK=" + investigadorPK + " ]";
    }
    
}
