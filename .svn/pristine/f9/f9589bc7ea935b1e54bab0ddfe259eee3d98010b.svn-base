/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.sigeco.ejb.entities.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 <p>
 @author csacanam
 */
@Stateful
public class AuthAutSB
{
    @EJB
    private UsuarioSB usuarioSB;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public UsuarioSB getUsuarioSB()
    {
        return usuarioSB;
    }

    public void setUsuarioSB( UsuarioSB usuarioSB )
    {
        this.usuarioSB = usuarioSB;
    }

    public Usuario autenticar( String documento, String contrasenia ) throws Exception
    {
        return usuarioSB.isValidPassword( documento, contrasenia ); 
    }
}
