/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
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
 * @author aleja
 */
@Named(value = "tipoIndicadorBean")
@ViewScoped
public class TipoIndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
  
    private String nombreTipoIndicador; 
    private String vigencia;
    private String descripcion;
    
    private IndicadorTipo nuevoIndicadorTipo;
    
    @PostConstruct
    public void initalize(){
        nuevoIndicadorTipo = new IndicadorTipo();
        System.out.println("Inicio Bean Tipo Indicador");     
    }
    
    /**
     * Creates a new instance of convenioBean
     */
    public TipoIndicadorBean() {
    }

    public void crearTipoIndicador() throws IOException
    {
        short numVigencia = 0; 
        if(vigencia.equals("VIGENTE")){
            numVigencia = 1;
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        nuevoIndicadorTipo.setNombre(nombreTipoIndicador);
        nuevoIndicadorTipo.setEstado(numVigencia);
        nuevoIndicadorTipo.setDescripcion(descripcion);
        nuevoIndicadorTipo.setFechaCreacion(new Date());
        nuevoIndicadorTipo.setFechaActualizacion(new Date());
        nuevoIndicadorTipo.setClasificacionCollection(new ArrayList<>());
                
        try{
            tipoIndicadorService.crearTipoIndicador(nuevoIndicadorTipo);          
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN", 
                    "El tipo de indicador " + nombreTipoIndicador + " ha sido agregado correctamente")
            );
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext()
                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-tipo-indicador.xhtml");
        }
        catch(EJBException e){
            
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN", 
                    "El tipo de indicador " + nombreTipoIndicador + " ya existe en los registros")
            );            
            
            
        }                      
    }        
    
    public String getNombreTipoIndicador() {
        return nombreTipoIndicador;
    }

    public void setNombreTipoIndicador(String nombreTipoIndicador) {
        this.nombreTipoIndicador = nombreTipoIndicador;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

    public IndicadorTipo getNuevoIndicadorTipo() {
        return nuevoIndicadorTipo;
    }

    public void setNuevoIndicadorTipo(IndicadorTipo nuevoIndicadorTipo) {
        this.nuevoIndicadorTipo = nuevoIndicadorTipo;
    }
       
}
