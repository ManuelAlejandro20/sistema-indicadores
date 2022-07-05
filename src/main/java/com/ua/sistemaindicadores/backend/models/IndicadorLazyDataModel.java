/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.models;

import com.ua.sistemaindicadores.backend.daos.AbstractDAO;
import com.ua.sistemaindicadores.backend.daos.IndicadorDAO;
import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.services.IndicadorService;
import org.primefaces.model.LazyDataModel;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
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
public class IndicadorLazyDataModel extends LazyDataModel<IndicadorDTO> {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private IndicadorService indicadorService;
    private Clasificacion clasificacionId;
    private String nombre;
    private Short estado;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaActualizacion;    

    @Override
    public Object getRowKey(IndicadorDTO object) {
        return object.getId();

    }

    @Override
    public IndicadorDTO getRowData(String rowKey) {
        try {
            return indicadorService.obtenerIndicadorDTO(Integer.parseInt(rowKey));
        } catch (EJBException ex) {
            Logger.getLogger(IndicadorLazyDataModel.class.getName())
                    .log(Level.SEVERE, "Ocurrio un error al cargar IndicadorDTO para rowKey: " + rowKey, ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al recuperar un registro. Contacte al administrador"));
            return null;
        }
    }

//    @Override
//    public List<IndicadorDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//        try {
//            if (nombre != null) {
//                filters.put(IndicadorDAO.KEY_NOMBRE, nombre);
//            } else {
//                filters.remove(IndicadorDAO.KEY_NOMBRE);
//            }
//            }
//            if (estado != null) {
//                filters.put(IndicadorDAO.KEY_ESTADO, estado);
//            } else {
//                filters.remove(IndicadorDAO.KEY_ESTADO);
//            }
//            if (descripcion != null) {
//                filters.put(IndicadorDAO.KEY_DESCRIPCION, descripcion);
//            } else {
//                filters.remove(IndicadorDAO.KEY_DESCRIPCION);
//            }
//            if(fechaCreacion != null)
//            {
//                filters.put(IndicadorDAO.KEY_FCREACION, fechaCreacion);
//            }else
//            {
//                filters.remove(IndicadorDAO.KEY_FCREACION);
//            }
//            if(fechaActualizacion != null)
//            {
//                filters.put(IndicadorDAO.KEY_FACT, fechaActualizacion);
//            }else
//            {
//                filters.remove(IndicadorDAO.KEY_FACT);
//            }      
////            if (ClasificacionId != null) {
////                filters.put(IndicadorDAO.KEY_CLASIFICACION_ID, ClasificacionId.getId());
////            } else {
////                filters.remove(IndicadorDAO.KEY_CLASIFICACION_ID);
////            }            
//
//            String order = null;
//            if (sortOrder != null) {
//                switch (sortOrder) {
//                    case ASCENDING:
//                        order = AbstractDAO.SORT_ASCENDING;
//                        break;
//                    case DESCENDING:
//                        order = AbstractDAO.SORT_DESCENDING;
//                        break;
//                    case UNSORTED:
//                        order = null;
//                        break;
//                    default:
//                        break;
//                }
//            }
//
////            this.setRowCount(indicadorService.contarIndicadores(filters));
////            return indicadorService.cargarIndicadores(first, pageSize, sortField, order, filters);
////        } catch (EJBException ex) {
////
////            Logger.getLogger(IndicadorLazyDataModel.class.getName())
////                    .log(Level.SEVERE, "Ocurrio un error al cargar lista la informacion", ex);
////            FacesContext
////                    .getCurrentInstance()
////                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al cargar la lista de registro. Contacte al administrador."));
////            return new ArrayList<>();
////        }
//
//    }

//    public IndicadorService getIndicadorService() {
//        return indicadorService;
//    }
//
//    public void setIndicadorService(IndicadorService indicadorService) {
//        this.indicadorService = indicadorService;
//    }
//
//    public Clasificacion getClasificacionId() {
//        return clasificacionId;
//    }
//
//    public void setClasificacionId(Clasificacion clasificacionId) {
//        this.clasificacionId = clasificacionId;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public Short getEstado() {
//        return estado;
//    }
//
//    public void setEstado(Short estado) {
//        this.estado = estado;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    public Date getFechaCreacion() {
//        return fechaCreacion;
//    }
//
//    public void setFechaCreacion(Date fechaCreacion) {
//        this.fechaCreacion = fechaCreacion;
//    }
//
//    public Date getFechaActualizacion() {
//        return fechaActualizacion;
//    }
//
//    public void setFechaActualizacion(Date fechaActualizacion) {
//        this.fechaActualizacion = fechaActualizacion;
//    }
//
//    public EJBException getEx() {
//        return ex;
//    }
//
//    public void setEx(EJBException ex) {
//        this.ex = ex;
//    }
}
