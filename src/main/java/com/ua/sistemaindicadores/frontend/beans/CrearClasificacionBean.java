/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;  
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author diego
 */
@Named(value = "crearClasificacionBean")
@ViewScoped
public class CrearClasificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    transient private ClasificacionService clasificacionService;
  
    private Integer indicador_tipo_id;
    private String nombreClasificacion;
    private Short estado;
    private String tipo;
    private String descripcion;
    
    private Clasificacion nuevaClasificacion;
        
    @PostConstruct
    public void initalize(){
        nuevaClasificacion = new Clasificacion();
        System.out.println("Inicio Bean Crear Clasificacion");     
    }
    
    /**
     * Creates a new instance of convenioBean
     */
    public CrearClasificacionBean() {
    }

    public void crearClasificacion() throws IOException
    {       
        FacesContext context = FacesContext.getCurrentInstance();
        
        nuevaClasificacion.setIndicadorTipoId(indicador_tipo_id);
        nuevaClasificacion.setNombre(nombreClasificacion);
        nuevaClasificacion.setTipo(tipo);
        nuevaClasificacion.setDescripcion(descripcion);
                
        try{
            clasificacionService.crearClasificacion(nuevaClasificacion);          
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN", 
                    "La clasificacion " + nombreClasificacion + " ha sido agregado correctamente")
            );
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext()
                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-tipo-indicador.xhtml");
        }
        catch(EJBException e){
            
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN", 
                    "La clasificacion " + nombreClasificacion + " ya existe en los registros")
            );            
            
            
        }                      
    }        

    public ClasificacionService getClasificacionService() {
        return clasificacionService;
    }

    public void setClasificacionService(ClasificacionService clasificacionService) {
        this.clasificacionService = clasificacionService;
    }

    public Integer getIndicador_tipo_id() {
        return indicador_tipo_id;
    }

    public void setIndicador_tipo_id(Integer indicador_tipo_id) {
        this.indicador_tipo_id = indicador_tipo_id;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Clasificacion getNuevaClasificacion() {
        return nuevaClasificacion;
    }

    public void setNuevaClasificacion(Clasificacion nuevaClasificacion) {
        this.nuevaClasificacion = nuevaClasificacion;
    }
      
    
}
