/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author germandavidlozano
 */
@Entity
@Table(name = "PAGINA_XHTML")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaginaXhtml.findAll", query = "SELECT p FROM PaginaXhtml p"),
    @NamedQuery(name = "PaginaXhtml.findByCodigo", query = "SELECT p FROM PaginaXhtml p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "PaginaXhtml.findByNombrePag", query = "SELECT p FROM PaginaXhtml p WHERE p.nombrePag = :nombrePag")})
public class PaginaXhtml implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="PAGINA_XHTML_GENERATOR", sequenceName="SQ_PAGINA_XHTML", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAGINA_XHTML_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_PAG")
    private String nombrePag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "URL")
    private String url;
    @JoinColumn(name = "MEN_CODIGO", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menCodigo;

    public PaginaXhtml() {
    }

    public PaginaXhtml(Long codigo) {
        this.codigo = codigo;
    }

    public PaginaXhtml(Long codigo, String nombrePag) {
        this.codigo = codigo;
        this.nombrePag = nombrePag;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombrePag() {
        return nombrePag;
    }

    public void setNombrePag(String nombrePag) {
        this.nombrePag = nombrePag;
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
        if (!(object instanceof PaginaXhtml)) {
            return false;
        }
        PaginaXhtml other = (PaginaXhtml) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sigeco.ejb.entities.PaginaXhtml[ codigo=" + codigo + " ]";
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
