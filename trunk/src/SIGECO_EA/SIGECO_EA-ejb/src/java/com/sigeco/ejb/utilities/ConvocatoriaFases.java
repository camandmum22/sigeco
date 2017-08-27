/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.utilities;

import java.util.Date;

/**
 *
 * @author Ferchs
 */
public class ConvocatoriaFases
{

    private String codigoConvocatoria;
    private String nombreConvocatoria;
    private Date fechaInicio;
    private Date fechaFin;

    /**
     * @return the codigoConvocatoria
     */
    public String getCodigoConvocatoria()
    {
        return codigoConvocatoria;
    }

    /**
     * @param codigoConvocatoria the codigoConvocatoria to set
     */
    public void setCodigoConvocatoria(String codigoConvocatoria)
    {
        this.codigoConvocatoria = codigoConvocatoria;
    }

    /**
     * @return the nombreConvocatoria
     */
    public String getNombreConvocatoria()
    {
        return nombreConvocatoria;
    }

    /**
     * @param nombreConvocatoria the nombreConvocatoria to set
     */
    public void setNombreConvocatoria(String nombreConvocatoria)
    {
        this.nombreConvocatoria = nombreConvocatoria;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio()
    {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin()
    {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin)
    {
        this.fechaFin = fechaFin;
    }
}
