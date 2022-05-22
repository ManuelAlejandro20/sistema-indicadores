/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.dtos.ClasificacionDTO;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.models.ClasificacionLazyDataModel;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
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
import java.util.List;

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
    transient private TipoIndicadorService tipoIndicadorService;    

    private String nombreSeleccionado;
    private String estadoSeleccionado;
    private IndicadorTipo tipoSeleccionado;
    private String descripcionSeleccionada;
    private String anioCreacionSeleccionado;
    private String anioActualizacionSeleccionado;

    private List<IndicadorTipo> listaIndicadorTipo;
    
    private Boolean filtros;
    private String mensajeFiltros;

    private ClasificacionDTO clasificacionSeleccionadoDTO;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @PostConstruct
    public void initalize() {
        System.out.println("Inicio Bean Ver Tipo Indicador");
        listaIndicadorTipo = tipoIndicadorService.obtenerIndicadorTipos();
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

    public IndicadorTipo getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(IndicadorTipo tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public String getAnioCreacionSeleccionado() {
        return anioCreacionSeleccionado;
    }

    public void setAnioCreacionSeleccionado(String anioCreacionSeleccionado) {
        this.anioCreacionSeleccionado = anioCreacionSeleccionado;
    }

    public String getAnioActualizacionSeleccionado() {
        return anioActualizacionSeleccionado;
    }

    public void setAnioActualizacionSeleccionado(String anioActualizacionSeleccionado) {
        this.anioActualizacionSeleccionado = anioActualizacionSeleccionado;
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

    public List<IndicadorTipo> getListaIndicadorTipo() {
        return listaIndicadorTipo;
    }

    public void setListaIndicadorTipo(List<IndicadorTipo> listaIndicadorTipo) {
        this.listaIndicadorTipo = listaIndicadorTipo;
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
            tipoSeleccionado = null;
            descripcionSeleccionada = null;
            anioCreacionSeleccionado = null;
            anioActualizacionSeleccionado = null;
            onSeleccionNombreListener();
            onSeleccionTipoListener();            
            onSeleccionEstadoListener();
            onSeleccionDescripcionListener();
            onSeleccionAnioCreacionListener();
            onSeleccionAnioActualizacionListener();                        
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
    
    public void onSeleccionTipoListener() {
        try {
            if (tipoSeleccionado != null) {
                model.setTipo(tipoSeleccionado.getNombre());
            } else {
                model.setTipo(null);
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
    
    public void onSeleccionAnioCreacionListener(){        
        try {
            if (anioCreacionSeleccionado != null) {
                model.setFechaCreacion(formatter.parse("01-01-" + anioCreacionSeleccionado));
            } else {
                model.setFechaCreacion(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(IndicadorTipo.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro fecha creacion. Contacte al administrador.")
                    );
        } catch (ParseException ex){
            model.setFechaCreacion(null);
        }
    }            
    
    public void onSeleccionAnioActualizacionListener(){        
        try {
            if (anioActualizacionSeleccionado != null) {
                model.setFechaActualizacion(formatter.parse("01-01-" + anioActualizacionSeleccionado));
            } else {
                model.setFechaActualizacion(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(IndicadorTipo.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro fecha actualizacion. Contacte al administrador.")
                    );
        } catch (ParseException ex){
            model.setFechaCreacion(null);
        }
    }            
    
}
