/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;

    private Integer indicador_tipo_id;
    private String nombreClasificacion;
    private String vigencia;
    private String descripcion;
    private List<IndicadorTipo> listaIndicadorTipo;
    private IndicadorTipo indicadorTipoSeleccionado;

    private Clasificacion nuevaClasificacion;

    private boolean disabled;
    
    @PostConstruct
    public void initalize() {
        nuevaClasificacion = new Clasificacion();
        listaIndicadorTipo = tipoIndicadorService.obtenerIndicadorTipos();
        disabled = false;
        System.out.println("Inicio Bean Crear Clasificacion");
    }

    /**
     * Creates a new instance of convenioBean
     */
    public CrearClasificacionBean() {
    }

    public void crearClasificacion() throws IOException {
        short numVigencia = 0;
        if (vigencia.equals("VIGENTE")) {
            numVigencia = 1;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        
        nuevaClasificacion.setIndicadorTipoId(indicadorTipoSeleccionado);
        nuevaClasificacion.setNombre(nombreClasificacion);
        nuevaClasificacion.setEstado(numVigencia);
        nuevaClasificacion.setTipo(indicadorTipoSeleccionado.getNombre());
        nuevaClasificacion.setDescripcion(descripcion);

        try {
            clasificacionService.crearClasificacion(nuevaClasificacion);
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN",
                    "La clasificacion " + nombreClasificacion + " ha sido agregado correctamente")
            );
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext()
                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-clasificacion.xhtml");
        } catch (EJBException e) {

            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN",
                    "La clasificacion " + nombreClasificacion + " ya existe en los registros")
            );

        }
    }

    public List<IndicadorTipo> getListaIndicadorTipo() {
        return listaIndicadorTipo;
    }

    public void setListaIndicadorTipo(List<IndicadorTipo> listaIndicadorTipo) {
        this.listaIndicadorTipo = listaIndicadorTipo;
    }

    public IndicadorTipo getIndicadorTipoSeleccionado() {
        return indicadorTipoSeleccionado;
    }

    public void setIndicadorTipoSeleccionado(IndicadorTipo indicadorTipoSeleccionado) {
        this.indicadorTipoSeleccionado = indicadorTipoSeleccionado;
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

    public String getEstado() {
        return vigencia;
    }

    public void setEstado(String vigencia) {
        this.vigencia = vigencia;
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

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    public Clasificacion getNuevaClasificacion() {
        return nuevaClasificacion;
    }

    public void setNuevaClasificacion(Clasificacion nuevaClasificacion) {
        this.nuevaClasificacion = nuevaClasificacion;
    }

}
