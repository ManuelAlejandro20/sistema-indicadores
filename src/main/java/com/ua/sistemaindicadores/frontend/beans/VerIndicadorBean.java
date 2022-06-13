/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.models.IndicadorLazyDataModel;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author aleja
 */
@Named(value = "verIndicadorBean")
@ViewScoped
public class VerIndicadorBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
 
    @Inject
    private IndicadorLazyDataModel model;    
    
    private IndicadorDTO indicadorSeleccionadoDTO;
    
    private Boolean filtros;
    private boolean siguiente;
    private String mensajeFiltros;    
    
    @PostConstruct
    public void initalize(){
        System.out.println("Inicio Bean Ver Indicador");     
        filtros = true;
        mensajeFiltros = "Mostrar filtros";        
    }
    
    public VerIndicadorBean() {
    }

    public Boolean getFiltros() {
        return filtros;
    }

    public void setFiltros(Boolean filtros) {
        this.filtros = filtros;
    }

    public String getMensajeFiltros() {
        return mensajeFiltros;
    }

    public void setMensajeFiltros(String mensajeFiltros) {
        this.mensajeFiltros = mensajeFiltros;
    }

    public boolean getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(boolean siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
    public void eventofiltros() {
        if (filtros) {            
            mensajeFiltros = "Ocultar filtros";
            filtros = false;
        } else {
            //limpiarFiltros();
            mensajeFiltros = "Mostrar filtros";
            filtros = true;
        }
    }
    
//    public void limpiarFiltros() {
//        try {
//
//            
//        } catch (EJBException ex) {
//            Logger.getLogger(IndicadorTipo.class.getName()).log(Level.SEVERE, null, ex);
//            FacesContext.getCurrentInstance()
//                    .addMessage(
//                            "mensaje",
//                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema al borrar los filtros. Contacte al administrador.")
//                    );
//        }
//    }        
    
    public String flujoProceso(FlowEvent event) {
        if (siguiente) {
            siguiente = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }    

    public IndicadorDTO getIndicadorSeleccionadoDTO() {
        return indicadorSeleccionadoDTO;
    }

    public void setIndicadorSeleccionadoDTO(IndicadorDTO indicadorSeleccionadoDTO) {
        this.indicadorSeleccionadoDTO = indicadorSeleccionadoDTO;
    }

    public IndicadorLazyDataModel getModel() {
        return model;
    }

    public void setModel(IndicadorLazyDataModel model) {
        this.model = model;
    }
    
    
    
    
}
