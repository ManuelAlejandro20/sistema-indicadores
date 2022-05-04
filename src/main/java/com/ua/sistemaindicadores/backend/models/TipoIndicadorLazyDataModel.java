/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.models;

import com.ua.sistemaindicadores.backend.daos.AbstractDAO;
import com.ua.sistemaindicadores.backend.daos.IndicadorTipoDAO;
import org.primefaces.model.LazyDataModel;
import com.ua.sistemaindicadores.backend.dtos.TipoIndicadorDTO;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.SortOrder;

/**
 *
 * @author aleja
 */
@Dependent
public class TipoIndicadorLazyDataModel extends LazyDataModel<TipoIndicadorDTO>{

    private static final long serialVersionUID = 1L;
    
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;    
    private String nombre;
    private String descripcion;
    private Short estado;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    
    @Override
    public Object getRowKey(TipoIndicadorDTO object) {
        return object.getId();

    }

    @Override
    public TipoIndicadorDTO getRowData(String rowKey) {
        try {
            return tipoIndicadorService.obtenerTipoIndicadorDTO(Integer.parseInt(rowKey));
        } catch (EJBException ex) {
            Logger.getLogger(TipoIndicadorLazyDataModel.class.getName())
                    .log(Level.SEVERE, "Ocurrio un error al cargar TipoIndicadorDTO para rowKey: " + rowKey, ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al recuperar un registro. Contacte al administrador"));
            return null;
        }
    }    
    
    @Override
    public List<TipoIndicadorDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            if(nombre != null)
            {
                filters.put(IndicadorTipoDAO.KEY_NOMBRE, nombre);
            }else
            {
                filters.remove(IndicadorTipoDAO.KEY_NOMBRE);
            }
            if(descripcion != null)
            {
                filters.put(IndicadorTipoDAO.KEY_DESCRIPCION, descripcion);
            }else
            {
                filters.remove(IndicadorTipoDAO.KEY_DESCRIPCION);
            }
            if(estado != null)
            {
                filters.put(IndicadorTipoDAO.KEY_ESTADO, estado);
            }else
            {
                filters.remove(IndicadorTipoDAO.KEY_ESTADO);
            }
            if(fechaCreacion != null)
            {
                filters.put(IndicadorTipoDAO.KEY_FCREACION, fechaCreacion);
            }else
            {
                filters.remove(IndicadorTipoDAO.KEY_FCREACION);
            }
            if(fechaActualizacion != null)
            {
                filters.put(IndicadorTipoDAO.KEY_FACT, fechaActualizacion);
            }else
            {
                filters.remove(IndicadorTipoDAO.KEY_FACT);
            }
            
            String order = null;
            if (sortOrder != null) {
                switch (sortOrder) {
                    case ASCENDING:
                        order = AbstractDAO.SORT_ASCENDING;
                        break;
                    case DESCENDING:
                        order = AbstractDAO.SORT_DESCENDING;
                        break;
                    case UNSORTED:
                        order = null;
                        break;
                    default:
                        break;
                }
            }

            this.setRowCount(tipoIndicadorService.contarTipoIndicadores(filters));
            return tipoIndicadorService.cargarTipoIndicadores(first, pageSize, sortField, order, filters);
        } catch (EJBException ex) {

            Logger.getLogger(TipoIndicadorLazyDataModel.class.getName())
                    .log(Level.SEVERE, "Ocurrio un error al cargar lista la informacion", ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al cargar la lista de registro. Contacte al administrador."));
            return new ArrayList<>();
        }

    }

    public TipoIndicadorService getTipoIndicadorService() {
        return tipoIndicadorService;
    }

    public void setTipoIndicadorService(TipoIndicadorService tipoIndicadorService) {
        this.tipoIndicadorService = tipoIndicadorService;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    
    
}
