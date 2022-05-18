/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.models;

import com.ua.sistemaindicadores.backend.daos.AbstractDAO;
import com.ua.sistemaindicadores.backend.daos.ClasificacionDAO;
import org.primefaces.model.LazyDataModel;
import com.ua.sistemaindicadores.backend.dtos.ClasificacionDTO;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
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
 * @author diego
 */
@Dependent
public class ClasificacionLazyDataModel extends LazyDataModel<ClasificacionDTO> {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private ClasificacionService clasificacionService;
    private Integer indicador_tipo_id;
    private String nombre;
    private Short estado;
    private String tipo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    @Override
    public Object getRowKey(ClasificacionDTO object) {
        return object.getId();

    }

    @Override
    public ClasificacionDTO getRowData(String rowKey) {
        try {
            return clasificacionService.obtenerClasificacionDTO(Integer.parseInt(rowKey));
        } catch (EJBException ex) {
            Logger.getLogger(ClasificacionLazyDataModel.class.getName())
                    .log(Level.SEVERE, "Ocurrio un error al cargar ClasificacionDTO para rowKey: " + rowKey, ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al recuperar un registro. Contacte al administrador"));
            return null;
        }
    }

    @Override
    public List<ClasificacionDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            if (nombre != null) {
                filters.put(ClasificacionDAO.KEY_NOMBRE, nombre);
            } else {
                filters.remove(ClasificacionDAO.KEY_NOMBRE);
            }
            if (tipo != null) {
                filters.put(ClasificacionDAO.KEY_TIPO, tipo);
            } else {
                filters.remove(ClasificacionDAO.KEY_TIPO);
            }
            if (estado != null) {
                filters.put(ClasificacionDAO.KEY_ESTADO, estado);
            } else {
                filters.remove(ClasificacionDAO.KEY_ESTADO);
            }
            if (descripcion != null) {
                filters.put(ClasificacionDAO.KEY_DESCRIPCION, descripcion);
            } else {
                filters.remove(ClasificacionDAO.KEY_DESCRIPCION);
            }
                        if(fechaCreacion != null)
            {
                filters.put(ClasificacionDAO.KEY_FCREACION, fechaCreacion);
            }else
            {
                filters.remove(ClasificacionDAO.KEY_FCREACION);
            }
            if(fechaActualizacion != null)
            {
                filters.put(ClasificacionDAO.KEY_FACT, fechaActualizacion);
            }else
            {
                filters.remove(ClasificacionDAO.KEY_FACT);
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

            this.setRowCount(clasificacionService.contarClasificaciones(filters));
            return clasificacionService.cargarClasificaciones(first, pageSize, sortField, order, filters);
        } catch (EJBException ex) {

            Logger.getLogger(ClasificacionLazyDataModel.class.getName())
                    .log(Level.SEVERE, "Ocurrio un error al cargar lista la informacion", ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al cargar la lista de registro. Contacte al administrador."));
            return new ArrayList<>();
        }

    }

    public ClasificacionService getClasificacionService() {
        return clasificacionService;
    }

    public void setClasificacionService(ClasificacionService clasificacionService) {
        this.clasificacionService = clasificacionService;
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

    public Integer getIndicador_tipo_id() {
        return indicador_tipo_id;
    }

    public void setIndicador_tipo_id(Integer indicador_tipo_id) {
        this.indicador_tipo_id = indicador_tipo_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
