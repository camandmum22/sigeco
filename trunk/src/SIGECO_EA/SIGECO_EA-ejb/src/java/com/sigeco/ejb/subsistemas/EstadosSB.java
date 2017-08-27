/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.sigeco.ejb.sessionbean.EstadoFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 <p>
 @author csacanam
 */
@Stateless
public class EstadosSB
{
    public static final long CONVOCATORIA_ACTIVO = 1;

    public static final long CONVOCATORIA_INACTIVO = 2;

    public static final long PROYECTO_REGISTRADO = 3;

    public static final long PROYECTO_APROBADO_LIDER = 4;

    public static final long PROYECTO_APROBADO_DECANO = 5;
    
    public static final long PROYECTO_APROBADO_COMITE = 6;
    
    public static final long PROYECTO_RECHAZADO_LIDER = 12;//New

    public static final long PROYECTO_RECHAZADO_DECANO = 13;//New
    
    public static final long PROYECTO_RECHAZADO_COMITE = 14;//New

    public static final long PROCONSOL_PENDIENTE = 7;

    public static final long PROCONSOL_APROBADO = 8;

    public static final long PROCONSOL_RECHAZADO = 9;

    public static final long ENTREGABLE_SOMETIDO = 10;

    public static final long ENTREGABLE_PUBLICADO = 11;
    
    public static final long PROYECTO_SUSPENDIDO = 15;
    
    public static final long PROYECTO_CANCELADO = 16;

    @EJB
    private EstadoFacade estadoFacade;

    // Lógica especializada
    // Getters para el CRUD
    public EstadoFacade getEstadoFacade()
    {
        return estadoFacade;
    }

    public void setEstadoFacade( EstadoFacade estadoFacade )
    {
        this.estadoFacade = estadoFacade;
    }
}
