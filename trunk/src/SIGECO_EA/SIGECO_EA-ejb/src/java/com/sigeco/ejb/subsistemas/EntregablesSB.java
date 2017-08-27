/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.subsistemas;

import com.sigeco.ejb.entities.Entregable;
import com.sigeco.ejb.entities.Estado;
import com.sigeco.ejb.entities.Producto;
import com.sigeco.ejb.entities.ProyectoInvestigacion;
import com.sigeco.ejb.entities.Subproducto;
import com.sigeco.ejb.sessionbean.ClasificacionProductoFacade;
import com.sigeco.ejb.sessionbean.EntregableFacade;
import com.sigeco.ejb.sessionbean.EstadoFacade;
import com.sigeco.ejb.sessionbean.ProductoFacade;
import com.sigeco.ejb.sessionbean.SubproductoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author csacanam
 */
@Stateless
public class EntregablesSB
{
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    ClasificacionProductoFacade clasificacionProductoFacade;
    
    @EJB
    SubproductoFacade subproductoFacade;
    
    @EJB
    ProductoFacade productoFacade;
    
    @EJB
    EntregableFacade entregableFacade;
    
    @EJB
    private EstadoFacade estadoFacade;
    
    //Getters para el CRUD
    public ClasificacionProductoFacade getClasificacionProductoFacade() {
        return clasificacionProductoFacade;
    }
    
     public SubproductoFacade getSubproductoFacade() {
        return subproductoFacade;
    }
     
     public ProductoFacade getProductoFacade(){
         return productoFacade;
     }
     
     public EntregableFacade getEntregableFacade(){
         return entregableFacade;
     }
     
    public List<Subproducto> buscarSubproductosPorProducto( Producto producto )
    {
        List<Subproducto> subproductos = new ArrayList();
        if( producto.getSubproductoList() != null && !producto.getSubproductoList().isEmpty() )
        {
            for( Subproducto sp : producto.getSubproductoList() )
            {
                subproductos.add(sp);
            }
        }
        return subproductos;
    }
    
    public List<Entregable> buscarEntregablesPorProyecto(ProyectoInvestigacion proyecto)
    {
        List<Entregable> entregables = new ArrayList();
        if( proyecto.getEntregableList() != null && !proyecto.getEntregableList().isEmpty() )
        {
            for( Entregable en : proyecto.getEntregableList() )
            {
                entregables.add(en);
            }
        }
        return entregables;
    }
    
    public List<Estado> darEstados()
    {
        List<Estado> estados = estadoFacade.findAll();       
        List<Estado> retorno = new ArrayList<>();
        
        for(int i = 0; i < estados.size(); i++)
        {
            if(estados.get(i).getCodigo() == 10 || estados.get(i).getCodigo() == 11)
            {
                retorno.add(estados.get(i));
            }
        }        
        return retorno;
    }

    /**
     * @return the estadoFacade
     */
    public EstadoFacade getEstadoFacade() {
        return estadoFacade;
    }

    /**
     * @param estadoFacade the estadoFacade to set
     */
    public void setEstadoFacade(EstadoFacade estadoFacade) {
        this.estadoFacade = estadoFacade;
    }
 
}
