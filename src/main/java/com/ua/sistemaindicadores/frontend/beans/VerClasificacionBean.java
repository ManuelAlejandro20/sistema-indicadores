/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.dtos.ClasificacionDTO;
import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.models.ClasificacionLazyDataModel;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import javax.faces.context.ExternalContext;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Inject
    transient private ClasificacionService clasificacionService;    
    
    private List<Clasificacion> listaClasificaciones;
    private Clasificacion clasSeleccionada;    
    
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

    //---- Variables pertenecientes a la interfaz visualizacion
    private String mensajeVerClasifiaciones;
    
    @PostConstruct
    public void initalize() {
        System.out.println("Inicio Bean Ver Tipo Indicador");
        listaIndicadorTipo = tipoIndicadorService.obtenerIndicadorTipos();
        listaClasificaciones = clasificacionService.obtenerClasificaciones();
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

    public Clasificacion getClasSeleccionada() {
        return clasSeleccionada;
    }

    public void setClasSeleccionada(Clasificacion clasSeleccionada) {
        this.clasSeleccionada = clasSeleccionada;
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

    public List<Clasificacion> getListaClasificaciones() {
        return listaClasificaciones;
    }

    public void setListaClasificaciones(List<Clasificacion> listaClasificaciones) {
        this.listaClasificaciones = listaClasificaciones;
    }        

    public String getMensajeVerClasifiaciones() {
        return mensajeVerClasifiaciones;
    }

    public void setMensajeVerClasifiaciones(String mensajeVerClasifiaciones) {
        this.mensajeVerClasifiaciones = mensajeVerClasifiaciones;
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
            clasSeleccionada = null;
            tipoSeleccionado = null;
            estadoSeleccionado = null;            
            descripcionSeleccionada = null;
            anioCreacionSeleccionado = null;
            anioActualizacionSeleccionado = null;                       
            onSeleccionClasificacionListener();
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
    
    public void desplegarTipoIndicador() {
        //Si el tipoSeleccionado es nulo (ya se porque la página carga por primera vez o porque se borran los filtros)
        //capturara el parametro de la url, si no es nulo significa que ya cargo el parametro tipo y el filtro tipo
        //fue fijado
        if(tipoSeleccionado == null){
            Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            tipoSeleccionado = tipoIndicadorService.buscarTipoIndicadorNombre(params.get("n"));
            if(tipoSeleccionado != null){
                mensajeVerClasifiaciones = "Clasificaciones de " + tipoSeleccionado.getNombre();                        
            }else{
                estadoSeleccionado = "-1";
                mensajeVerClasifiaciones = "No hay datos sobre el tipo de indicador";                                        
            }
            onSeleccionTipoListener();
            onSeleccionEstadoListener();
        }
    }
   

    //Filtros
    public void onSeleccionClasificacionListener() {
        try {
            if (clasSeleccionada != null) {
                model.setNombre(clasSeleccionada.getNombre());
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
                listaClasificaciones = new ArrayList<>(tipoSeleccionado.getClasificacionCollection());                
            } else {
                model.setTipo(null);
                listaClasificaciones = clasificacionService.obtenerClasificaciones();                
            }
            clasSeleccionada = null;
            onSeleccionClasificacionListener();            
        } catch (EJBException ex) {
            Logger.getLogger(Clasificacion.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro tipo. Contacte al administrador.")
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

    public void onSeleccionAnioCreacionListener() {
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
        } catch (ParseException ex) {
            model.setFechaCreacion(null);
        }
    }

    public void onSeleccionAnioActualizacionListener() {
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
        } catch (ParseException ex) {
            model.setFechaCreacion(null);
        }
    }

}
