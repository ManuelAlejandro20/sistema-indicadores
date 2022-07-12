/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.models;

import com.ua.sistemaindicadores.backend.daos.AbstractDAO;
import com.ua.sistemaindicadores.backend.daos.IndicadorDAO;
import org.primefaces.model.LazyDataModel;
import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.AjustePdei;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.entities.Plazo;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion;
import com.ua.sistemaindicadores.backend.services.IndicadorService;
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
public class IndicadorLazyDataModel extends LazyDataModel<IndicadorDTO> {

    private static final long serialVersionUID = 1L;

    //Columnas excluidas: 
    //id: no filtraremos por id
    
    @Inject
    transient private IndicadorService indicadorService;
    private String numIndicador;
    private String nombreIndicador;
    private Short estado;
    private String descripcionIndicador;
    private String aplicaLineamiento;
    private String aplicaObjetivo;
    private String descripcionObjetivo;
    private String version;
    private String lineaBase;
    private String metas;
    private String porcLogro;
    private String medioVerificacion;
    private String formaCalculo;
    private String fuenteInformacion;
    private String proyectoAsociado;
    private String comentario;
    private String actividadComprometida;
    private String estadoActividad;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private IndicadorTipo indicadorTipo;
    private AjustePdei ajustePdeiId;
    private AnioCumplimiento anioCumplimientoId;
    private Clasificacion clasificacionId;
    private FrecuenciaMedicion frecuenciaMedicionId;
    private Plazo plazoId;
    private UnidadRepresentacion unidadRepresentacionId;    
    private UnidadProveedora unidadProveedora;
    private GeneracionDatos generacionDatosId;
    private List<Date> fechaCreacionRange;
    private List<Date> fechaActualizacionRange;
    
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

    @Override
    public List<IndicadorDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {                
            if (numIndicador != null) {
                filters.put(IndicadorDAO.NUM_INDICADOR, numIndicador);
            } else {
                filters.remove(IndicadorDAO.NUM_INDICADOR);
            }
            if (nombreIndicador != null) {
                filters.put(IndicadorDAO.NOMBRE_INDICADOR, nombreIndicador);
            } else {
                filters.remove(IndicadorDAO.NOMBRE_INDICADOR);
            }
            if (estado != null) {
                filters.put(IndicadorDAO.ESTADO, estado);
            } else {
                filters.remove(IndicadorDAO.ESTADO);
            }
            if (descripcionIndicador != null) {
                filters.put(IndicadorDAO.DESCRIPCION_INDICADOR, descripcionIndicador);
            } else {
                filters.remove(IndicadorDAO.DESCRIPCION_INDICADOR);
            }
            if(aplicaLineamiento != null){
                filters.put(IndicadorDAO.APLICA_LINEAMIENTO, aplicaLineamiento);
            }else{
                filters.remove(IndicadorDAO.APLICA_LINEAMIENTO);
            }
            if(aplicaObjetivo != null){
                filters.put(IndicadorDAO.APLICA_OBJETIVO, aplicaObjetivo);
            }else{
                filters.remove(IndicadorDAO.APLICA_OBJETIVO);
            }      
            if (descripcionObjetivo != null) {
                filters.put(IndicadorDAO.DESCRIPCION_OBJETIVO, descripcionObjetivo);
            } else {
                filters.remove(IndicadorDAO.DESCRIPCION_OBJETIVO);
            }      
            //
            if (version != null) {
                filters.put(IndicadorDAO.VERSION, version);
            } else {
                filters.remove(IndicadorDAO.VERSION);
            }
            if (lineaBase != null) {
                filters.put(IndicadorDAO.LINEA_BASE, lineaBase);
            } else {
                filters.remove(IndicadorDAO.LINEA_BASE);
            }
            if (metas != null) {
                filters.put(IndicadorDAO.METAS, metas);
            } else {
                filters.remove(IndicadorDAO.METAS);
            }
            if (porcLogro != null) {
                filters.put(IndicadorDAO.PORC_LOGRO, porcLogro);
            } else {
                filters.remove(IndicadorDAO.PORC_LOGRO);
            }
            if(medioVerificacion != null){
                filters.put(IndicadorDAO.MEDIO_VERIFICACION, medioVerificacion);
            }else{
                filters.remove(IndicadorDAO.MEDIO_VERIFICACION);
            }
            if(formaCalculo != null){
                filters.put(IndicadorDAO.FORMA_CALCULO, formaCalculo);
            }else{
                filters.remove(IndicadorDAO.FORMA_CALCULO);
            }      
            if (fuenteInformacion != null) {
                filters.put(IndicadorDAO.FUENTE_INFORMACION, fuenteInformacion);
            } else {
                filters.remove(IndicadorDAO.FUENTE_INFORMACION);
            }    
            //
            if (proyectoAsociado != null) {
                filters.put(IndicadorDAO.PROYECTO_ASOCIADO, proyectoAsociado);
            } else {
                filters.remove(IndicadorDAO.PROYECTO_ASOCIADO);
            }
            if (comentario != null) {
                filters.put(IndicadorDAO.COMENTARIO, comentario);
            } else {
                filters.remove(IndicadorDAO.COMENTARIO);
            }
            if (actividadComprometida != null) {
                filters.put(IndicadorDAO.ACTIVIDAD_COMPROMETIDA, actividadComprometida);
            } else {
                filters.remove(IndicadorDAO.ACTIVIDAD_COMPROMETIDA);
            }
            if (estadoActividad != null) {
                filters.put(IndicadorDAO.ESTADO_ACTIVIDAD, estadoActividad);
            } else {
                filters.remove(IndicadorDAO.ESTADO_ACTIVIDAD);
            }
            if(fechaCreacion != null){
                filters.put(IndicadorDAO.FECHA_CREACION, fechaCreacion);
            }else{
                filters.remove(IndicadorDAO.FECHA_CREACION);
            }
            if(fechaActualizacion != null){
                filters.put(IndicadorDAO.FECHA_ACTUALIZACION, fechaActualizacion);
            }else{
                filters.remove(IndicadorDAO.FECHA_ACTUALIZACION);
            }    
            
            if (indicadorTipo != null) {
                filters.put(IndicadorDAO.INDICADOR_TIPO_ID, indicadorTipo.getId());
            } else {
                filters.remove(IndicadorDAO.INDICADOR_TIPO_ID);
            }              
            if (ajustePdeiId != null) {
                filters.put(IndicadorDAO.AJUSTE_PDEI_ID, ajustePdeiId.getId());
            } else {
                filters.remove(IndicadorDAO.AJUSTE_PDEI_ID);
            }  
            if (anioCumplimientoId != null) {
                filters.put(IndicadorDAO.ANIO_CUMPLIMIENTO_ID, anioCumplimientoId.getId());
            } else {
                filters.remove(IndicadorDAO.ANIO_CUMPLIMIENTO_ID);
            }  
            if (clasificacionId != null) {
                filters.put(IndicadorDAO.CLASIFICACION_ID, clasificacionId.getId());
            } else {
                filters.remove(IndicadorDAO.CLASIFICACION_ID);
            }  
            if (frecuenciaMedicionId != null) {
                filters.put(IndicadorDAO.FRECUENCIA_MEDICION_ID, frecuenciaMedicionId.getId());
            } else {
                filters.remove(IndicadorDAO.FRECUENCIA_MEDICION_ID);
            }  
            if (generacionDatosId != null) {
                filters.put(IndicadorDAO.GENERACION_DATOS_ID, generacionDatosId.getId());
            } else {
                filters.remove(IndicadorDAO.GENERACION_DATOS_ID);
            }     
            if (plazoId != null) {
                filters.put(IndicadorDAO.PLAZO_ID, plazoId.getId());
            } else {
                filters.remove(IndicadorDAO.PLAZO_ID);
            }     
            if (unidadProveedora != null) {
                filters.put(IndicadorDAO.UNIDAD_PROVEEDORA_ID, unidadProveedora.getId());
            } else {
                filters.remove(IndicadorDAO.UNIDAD_PROVEEDORA_ID);
            }     
            if (unidadRepresentacionId != null) {
                filters.put(IndicadorDAO.UNIDAD_REPRESENTACION_ID, unidadRepresentacionId.getId());
            } else {
                filters.remove(IndicadorDAO.UNIDAD_REPRESENTACION_ID);
            } 
            if (fechaCreacionRange != null) {
                filters.put(IndicadorDAO.FECHA_CREACION_RANGE, fechaCreacionRange);
            } else {
                filters.remove(IndicadorDAO.FECHA_CREACION_RANGE);
            }  
            if (fechaActualizacionRange != null) {
                filters.put(IndicadorDAO.FECHA_ACTUALIZACION_RANGE, fechaActualizacionRange);
            } else {
                filters.remove(IndicadorDAO.FECHA_ACTUALIZACION_RANGE);
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

            this.setRowCount(indicadorService.contarIndicadores(filters));
            return indicadorService.cargarIndicadores(first, pageSize, sortField, order, filters);
        } catch (EJBException ex) {

            Logger.getLogger(IndicadorLazyDataModel.class.getName())
                    .log(Level.SEVERE, "Ocurrio un error al cargar lista la informacion", ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al cargar la lista de registro. Contacte al administrador."));
            return new ArrayList<>();
        }

    }

    public IndicadorService getIndicadorService() {
        return indicadorService;
    }

    public void setIndicadorService(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    public String getNumIndicador() {
        return numIndicador;
    }

    public void setNumIndicador(String numIndicador) {
        this.numIndicador = numIndicador;
    }

    public String getNombreIndicador() {
        return nombreIndicador;
    }

    public void setNombreIndicador(String nombreIndicador) {
        this.nombreIndicador = nombreIndicador;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public String getAplicaLineamiento() {
        return aplicaLineamiento;
    }

    public void setAplicaLineamiento(String aplicaLineamiento) {
        this.aplicaLineamiento = aplicaLineamiento;
    }

    public String getAplicaObjetivo() {
        return aplicaObjetivo;
    }

    public void setAplicaObjetivo(String aplicaObjetivo) {
        this.aplicaObjetivo = aplicaObjetivo;
    }

    public String getDescripcionObjetivo() {
        return descripcionObjetivo;
    }

    public void setDescripcionObjetivo(String descripcionObjetivo) {
        this.descripcionObjetivo = descripcionObjetivo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLineaBase() {
        return lineaBase;
    }

    public void setLineaBase(String lineaBase) {
        this.lineaBase = lineaBase;
    }

    public String getMetas() {
        return metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }

    public String getPorcLogro() {
        return porcLogro;
    }

    public void setPorcLogro(String porcLogro) {
        this.porcLogro = porcLogro;
    }

    public String getMedioVerificacion() {
        return medioVerificacion;
    }

    public void setMedioVerificacion(String medioVerificacion) {
        this.medioVerificacion = medioVerificacion;
    }

    public String getFormaCalculo() {
        return formaCalculo;
    }

    public void setFormaCalculo(String formaCalculo) {
        this.formaCalculo = formaCalculo;
    }

    public String getFuenteInformacion() {
        return fuenteInformacion;
    }

    public void setFuenteInformacion(String fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    public String getProyectoAsociado() {
        return proyectoAsociado;
    }

    public void setProyectoAsociado(String proyectoAsociado) {
        this.proyectoAsociado = proyectoAsociado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getActividadComprometida() {
        return actividadComprometida;
    }

    public void setActividadComprometida(String actividadComprometida) {
        this.actividadComprometida = actividadComprometida;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
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

    public IndicadorTipo getIndicadorTipo() {
        return indicadorTipo;
    }

    public void setIndicadorTipo(IndicadorTipo indicadorTipo) {
        this.indicadorTipo = indicadorTipo;
    }
            
    public AjustePdei getAjustePdeiId() {
        return ajustePdeiId;
    }

    public void setAjustePdeiId(AjustePdei ajustePdeiId) {
        this.ajustePdeiId = ajustePdeiId;
    }

    public AnioCumplimiento getAnioCumplimientoId() {
        return anioCumplimientoId;
    }

    public void setAnioCumplimientoId(AnioCumplimiento anioCumplimientoId) {
        this.anioCumplimientoId = anioCumplimientoId;
    }

    public Clasificacion getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(Clasificacion clasificacionId) {
        this.clasificacionId = clasificacionId;
    }

    public FrecuenciaMedicion getFrecuenciaMedicionId() {
        return frecuenciaMedicionId;
    }

    public void setFrecuenciaMedicionId(FrecuenciaMedicion frecuenciaMedicionId) {
        this.frecuenciaMedicionId = frecuenciaMedicionId;
    }

    public GeneracionDatos getGeneracionDatosId() {
        return generacionDatosId;
    }

    public void setGeneracionDatosId(GeneracionDatos generacionDatosId) {
        this.generacionDatosId = generacionDatosId;
    }

    public Plazo getPlazoId() {
        return plazoId;
    }

    public void setPlazoId(Plazo plazoId) {
        this.plazoId = plazoId;
    }
    
    public UnidadRepresentacion getUnidadRepresentacionId() {
        return unidadRepresentacionId;
    }

    public void setUnidadRepresentacionId(UnidadRepresentacion unidadRepresentacionId) {
        this.unidadRepresentacionId = unidadRepresentacionId;
    }

    public UnidadProveedora getUnidadProveedora() {
        return unidadProveedora;
    }

    public void setUnidadProveedora(UnidadProveedora unidadProveedora) {
        this.unidadProveedora = unidadProveedora;
    }

    public List<Date> getFechaCreacionRange() {
        return fechaCreacionRange;
    }

    public void setFechaCreacionRange(List<Date> fechaCreacionRange) {
        this.fechaCreacionRange = fechaCreacionRange;
    }

    public List<Date> getFechaActualizacionRange() {
        return fechaActualizacionRange;
    }

    public void setFechaActualizacionRange(List<Date> fechaActualizacionRange) {
        this.fechaActualizacionRange = fechaActualizacionRange;
    }

    
    

}