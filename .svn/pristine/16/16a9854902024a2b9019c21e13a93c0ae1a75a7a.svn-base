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
@Table( name = "MODALIDAD" )
@XmlRootElement
@NamedQueries( 
{
    @NamedQuery( name = "Modalidad.findAll", query = "SELECT m FROM Modalidad m" ),
    @NamedQuery( name = "Modalidad.findByCodigo", query = "SELECT m FROM Modalidad m WHERE m.codigo = :codigo" ),
    @NamedQuery( name = "Modalidad.findByNombre", query = "SELECT m FROM Modalidad m WHERE m.nombre = :nombre" )
} )
public class Modalidad implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator( name = "MODALIDAD_GENERATOR", sequenceName = "SQ_MODALIDAD", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "MODALIDAD_GENERATOR" )
    @Basic( optional = false )
    @NotNull
    @Column( name = "CODIGO" )
    private Long codigo;

    @Size( max = 15 )
    @Column( name = "NOMBRE" )
    private String nombre;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "modalidad", fetch = FetchType.LAZY )
    private List<Tarifa> tarifaList;

    public Modalidad()
    {
    }

    public Modalidad( Long codigo )
    {
        this.codigo = codigo;
    }

    public Long getCodigo()
    {
        return codigo;
    }

    public void setCodigo( Long codigo )
    {
        this.codigo = codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Tarifa> getTarifaList()
    {
        return tarifaList;
    }

    public void setTarifaList( List<Tarifa> tarifaList )
    {
        this.tarifaList = tarifaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += ( codigo != null ? codigo.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object )
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if( !( object instanceof Modalidad ) )
        {
            return false;
        }
        Modalidad other = (Modalidad)object;
        if( ( this.codigo == null && other.codigo != null ) || ( this.codigo != null && !this.codigo.equals( other.codigo ) ) )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sigeco.ejb.entities.Modalidad[ codigo=" + codigo + " ]";
    }

}
