/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.sigeco.ejb.entities.Usuario;
import com.sigeco.ejb.utilities.CustomLogger;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author csacanam
 */
@Stateless
public class AuditoriaSB
{

    private Usuario usuarioActual;
    
    public AuditoriaSB()
    {
        
        try
        {
            CustomLogger.setup(AuditoriaSB.class);
        } catch (IOException ex)
        {
            Logger.getLogger(AuditoriaSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Permite registar un log
     * @param titulo Titulo del log. Ej: Se registra un proyecto de investigación
     * @param info Información detallada del log: Ej: [Código proyecto: 1, Nombre proyecto: "Sistemas informáticos"]
     */
    public void registrarLog(String titulo, String[]info)
    {
        StringBuilder sb = new StringBuilder();
        
        //Poner fecha
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        sb.append(dateFormat.format(date)).append(" | ");
        
        //Poner usuario
        if(usuarioActual!=null)
        {
            sb.append("Usuario: ").append(usuarioActual.getNombre()).append(" (").append(usuarioActual.getDocumento()).append(" ) ");
        }
        
        //Poner título
        sb.append(titulo).append(" | ").append("INFO[ ");

        //Poner detalle de la información
        if(info.length > 0)
        {
            for(String infoDetalle : info)
            {
                sb.append(infoDetalle).append("; ");
            }
            sb.append(" ]");  
        }


        //Hacer el log
        //Logger log = Logger.getLogger(clase.getName());
        //log.info(sb.toString());
        CustomLogger.logger.log(Level.INFO,sb.toString());
        
    }

    public Usuario getUsuarioActual()
    {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual)
    {
        this.usuarioActual = usuarioActual;
    }
    
    
}
