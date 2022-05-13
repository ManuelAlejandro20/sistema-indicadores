/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.dtos.ClasificacionDTO;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.models.ClasificacionLazyDataModel;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.text.ParseException;

/**
 *
 * @author diego
 */
@Named(value = "verClasificacionBean")
@ViewScoped
public class VerClasificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClasificacionLazyDataModel model;
    @Inject
    transient private ClasificacionService clasificacionService;

    private String nombreSeleccionado;
    private String estadoSeleccionado;
    private String descripcionSeleccionada;

    private Boolean filtros;
    private String mensajeFiltros;

    private ClasificacionDTO clasificacionSeleccionadoDTO;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @PostConstruct
    public void initalize() {
        System.out.println("Inicio Bean Ver Tipo Indicador");
        filtros = true;
        mensajeFiltros = "Mostrar filtros";
    }

    /**
     * Creates a new instance of convenioBean
     */
    public VerClasificacionBean() {
    }

    public ClasificacionLazyDataModel getModel() {
        return model;
    }

    public void setModel(ClasificacionLazyDataModel model) {
        this.model = model;
    }

    public ClasificacionDTO getClasificacionSeleccionadoDTO() {
        return clasificacionSeleccionadoDTO;
    }

    public void setClasificacionSeleccionadoDTO(ClasificacionDTO clasificacionSeleccionadoDTO) {
        this.clasificacionSeleccionadoDTO = clasificacionSeleccionadoDTO;
    }

    public String getNombreSeleccionado() {
        return nombreSeleccionado;
    }

    public void setNombreSeleccionado(String nombreSeleccionado) {
        this.nombreSeleccionado = nombreSeleccionado;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public String getDescripcionSeleccionada() {
        return descripcionSeleccionada;
    }

    public void setDescripcionSeleccionada(String descripcionSeleccionada) {
        this.descripcionSeleccionada = descripcionSeleccionada;
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

    public void eventofiltros() {
        if (filtros) {
            mensajeFiltros = "Ocultar filtros";
            filtros = false;
        } else {
            limpiarFiltros();
            mensajeFiltros = "Mostrar filtros";
            filtros = true;
        }
    }

    public void limpiarFiltros() {
        try {
            nombreSeleccionado = null;
            estadoSeleccionado = null;
            descripcionSeleccionada = null;
            onSeleccionNombreListener();
            onSeleccionEstadoListener();
            onSeleccionDescripcionListener();

        } catch (EJBException ex) {
            Logger.getLogger(Clasificacion.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema al borrar los filtros. Contacte al administrador.")
                    );
        }
    }

    //Filtros
    public void onSeleccionNombreListener() {
        try {
            if (nombreSeleccionado != null) {
                model.setNombre(nombreSeleccionado);
            } else {
                model.setNombre(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Clasificacion.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro nombre. Contacte al administrador.")
                    );
        }
    }

    public void onSeleccionEstadoListener() {
        try {
            if (estadoSeleccionado != null) {
                model.setEstado(Short.valueOf(estadoSeleccionado));
            } else {
                model.setEstado(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Clasificacion.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro estado. Contacte al administrador.")
                    );
        }
    }

    public void onSeleccionDescripcionListener() {
        try {
            if (descripcionSeleccionada != null) {
                model.setDescripcion(descripcionSeleccionada);
            } else {
                model.setDescripcion(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Clasificacion.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro descripcion. Contacte al administrador.")
                    );
        }
    }
}
