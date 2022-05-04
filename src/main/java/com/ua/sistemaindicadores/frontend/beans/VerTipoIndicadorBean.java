/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.dtos.TipoIndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.models.TipoIndicadorLazyDataModel;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;  
import java.util.List;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author aleja
 */
@Named(value = "verTipoIndicadorBean")
@ViewScoped
public class VerTipoIndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private TipoIndicadorLazyDataModel model;    
    
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
        
    private TipoIndicadorDTO tipoIndicadorSeleccionadoDTO;
    
    @PostConstruct
    public void initalize(){
        System.out.println("Inicio Bean Ver Tipo Indicador");     
    }
    
    /**
     * Creates a new instance of convenioBean
     */
    public VerTipoIndicadorBean() {
    }

    public TipoIndicadorLazyDataModel getModel() {
        return model;
    }

    public void setModel(TipoIndicadorLazyDataModel model) {
        this.model = model;
    }

    public TipoIndicadorDTO getTipoIndicadorSeleccionadoDTO() {
        return tipoIndicadorSeleccionadoDTO;
    }

    public void setTipoIndicadorSeleccionadoDTO(TipoIndicadorDTO tipoIndicadorSeleccionadoDTO) {
        this.tipoIndicadorSeleccionadoDTO = tipoIndicadorSeleccionadoDTO;
    }

    

       
}
