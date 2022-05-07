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
@Named(value = "editarTipoIndicadorBean")
@ViewScoped
public class EditarTipoIndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
  
    private IndicadorTipo it;
    private String id; 
    private String nombreTipoIndicador; 
    private String vigencia;
    private String descripcion;    
    
    @PostConstruct
    public void initalize(){
        System.out.println("Inicio Bean Editar Tipo Indicador");
    }
    
    /**
     * Creates a new instance of convenioBean
     */
    public EditarTipoIndicadorBean() {
    }

    public void cargarDatos(){        
        it = tipoIndicadorService.buscarTipoIndicadorID(Integer.valueOf(id));
        nombreTipoIndicador = it.getNombre();
        descripcion = it.getDescripcion();
        
        if(it.getEstado() == 1){
            vigencia = "VIGENTE";
        }else{
            vigencia = "NOVIGENTE";
        }
        
    }
    
    public void actualizarDatos() throws IOException{        
        
        short numVigencia = 0; 
        if(vigencia.equals("VIGENTE")){
            numVigencia = 1;
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        it.setNombre(nombreTipoIndicador);
        it.setEstado(numVigencia);
        it.setDescripcion(descripcion);
        it.setFechaActualizacion(new Date());
        
        try{
            tipoIndicadorService.actualizarTipoIndicador(it);          
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN", 
                    "El tipo de indicador " + nombreTipoIndicador + " ha sido actualizado correctamente")
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
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    
}
