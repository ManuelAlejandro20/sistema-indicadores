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
        
    private IndicadorTipo tipoSeleccionado;
    private String estadoSeleccionado;
    private String descripcionSeleccionada;
    private String anioCreacionSeleccionado;
    private String anioActualizacionSeleccionado;
    
    private List<IndicadorTipo> listaIndicadorTipo;
    private List<IndicadorTipo> listaIndicadorTipoVigente;
    private List<IndicadorTipo> listaIndicadorTipoNoVigente;    
    
    private Boolean filtros;
    private String mensajeFiltros;
    
    private TipoIndicadorDTO tipoIndicadorSeleccionadoDTO;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    @PostConstruct
    public void initalize(){
        System.out.println("Inicio Bean Ver Tipo Indicador");     
        listaIndicadorTipo = tipoIndicadorService.obtenerIndicadorTipos();
        listaIndicadorTipoVigente = tipoIndicadorService.obtenerTiposIndicadoresByEstado(Short.valueOf("1"));
        listaIndicadorTipoNoVigente = tipoIndicadorService.obtenerTiposIndicadoresByEstado(Short.valueOf("0"));
        filtros = true;
        mensajeFiltros = "Mostrar filtros";        
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

    public IndicadorTipo getTipoSeleccionado() {
        return tipoSeleccionado;
    }

    public void setTipoSeleccionado(IndicadorTipo tipoSeleccionado) {
        this.tipoSeleccionado = tipoSeleccionado;
    }

    public List<IndicadorTipo> getListaIndicadorTipo() {
        return listaIndicadorTipo;
    }

    public void setListaIndicadorTipo(List<IndicadorTipo> listaIndicadorTipo) {
        this.listaIndicadorTipo = listaIndicadorTipo;
    }

    public List<IndicadorTipo> getListaIndicadorTipoVigente() {
        return listaIndicadorTipoVigente;
    }

    public void setListaIndicadorTipoVigente(List<IndicadorTipo> listaIndicadorTipoVigente) {
        this.listaIndicadorTipoVigente = listaIndicadorTipoVigente;
    }

    public List<IndicadorTipo> getListaIndicadorTipoNoVigente() {
        return listaIndicadorTipoNoVigente;
    }

    public void setListaIndicadorTipoNoVigente(List<IndicadorTipo> listaIndicadorTipoNoVigente) {
        this.listaIndicadorTipoNoVigente = listaIndicadorTipoNoVigente;
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

    public String getAnioCreacionSeleccionado() {
        return anioCreacionSeleccionado;
    }

    public void setAnioCreacionSeleccionado(String anioCreacionSeleccionado) {
        this.anioCreacionSeleccionado = anioCreacionSeleccionado;
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

    public String getAnioActualizacionSeleccionado() {
        return anioActualizacionSeleccionado;
    }

    public void setAnioActualizacionSeleccionado(String anioActualizacionSeleccionado) {
        this.anioActualizacionSeleccionado = anioActualizacionSeleccionado;
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
           tipoSeleccionado = null;
           estadoSeleccionado = null;
           descripcionSeleccionada = null;
           anioCreacionSeleccionado = null;
           anioActualizacionSeleccionado = null;
           onSeleccionNombreListener();
           onSeleccionEstadoListener();
           onSeleccionDescripcionListener();
           onSeleccionAnioCreacionListener();
           onSeleccionAnioActualizacionListener();
            
        } catch (EJBException ex) {
            Logger.getLogger(IndicadorTipo.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema al borrar los filtros. Contacte al administrador.")
                    );
        }
    }    
    
    public void desplegarVigentes(){
        estadoSeleccionado = "1";
        onSeleccionEstadoListener();
    }
    
    public void desplegarNoVigentes(){
        estadoSeleccionado = "0";
        onSeleccionEstadoListener();
    }
        
    //Filtros
    
    public void onSeleccionNombreListener() {
        try {
            if (tipoSeleccionado != null) {
                model.setNombre(tipoSeleccionado.getNombre());
            } else {
                model.setNombre(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(IndicadorTipo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IndicadorTipo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IndicadorTipo.class.getName()).log(Level.SEVERE, null, ex);
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
