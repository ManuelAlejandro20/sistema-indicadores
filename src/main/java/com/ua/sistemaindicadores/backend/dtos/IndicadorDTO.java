/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.dtos;

import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author aleja
 */
public class IndicadorDTO {
    private Integer id;
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
    private String ajustePdeiId;
    private String nombreTipoIndicador;
    private Integer anioCumplimientoId;
    private String clasificacionId;
    private String frecuenciaMedicionId;
    private String plazoId;
    private String unidadRepresentacionId; 

    public IndicadorDTO(Integer id, String numIndicador, String nombreIndicador, Short estado, 
            String descripcionIndicador, String aplicaLineamiento, String aplicaObjetivo, 
            String descripcionObjetivo, String version, String lineaBase, String metas, String porcLogro, 
            String medioVerificacion, String formaCalculo, String fuenteInformacion, String proyectoAsociado, 
            String comentario, String actividadComprometida, String estadoActividad, Date fechaCreacion, 
            Date fechaActualizacion, String ajustePdeiId, String nombreTipoIndicador, 
            Integer anioCumplimientoId, String clasificacionId, String frecuenciaMedicionId, String plazoId, 
            String unidadRepresentacionId) {
        this.id = id;
        this.numIndicador = numIndicador;
        this.nombreIndicador = nombreIndicador;
        this.estado = estado;
        this.descripcionIndicador = descripcionIndicador;
        this.aplicaLineamiento = aplicaLineamiento;
        this.aplicaObjetivo = aplicaObjetivo;
        this.descripcionObjetivo = descripcionObjetivo;
        this.version = version;
        this.lineaBase = lineaBase;
        this.metas = metas;
        this.porcLogro = porcLogro;
        this.medioVerificacion = medioVerificacion;
        this.formaCalculo = formaCalculo;
        this.fuenteInformacion = fuenteInformacion;
        this.proyectoAsociado = proyectoAsociado;
        this.comentario = comentario;
        this.actividadComprometida = actividadComprometida;
        this.estadoActividad = estadoActividad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.ajustePdeiId = ajustePdeiId;
        this.nombreTipoIndicador = nombreTipoIndicador;
        this.anioCumplimientoId = anioCumplimientoId;
        this.clasificacionId = clasificacionId;
        this.frecuenciaMedicionId = frecuenciaMedicionId;
        this.plazoId = plazoId;
        this.unidadRepresentacionId = unidadRepresentacionId;
        
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAjustePdeiId() {
        return ajustePdeiId;
    }

    public void setAjustePdeiId(String ajustePdeiId) {
        this.ajustePdeiId = ajustePdeiId;
    }

    public Integer getAnioCumplimientoId() {
        return anioCumplimientoId;
    }

    public void setAnioCumplimientoId(Integer anioCumplimientoId) {
        this.anioCumplimientoId = anioCumplimientoId;
    }

    public String getNombreTipoIndicador() {
        return nombreTipoIndicador;
    }

    public void setNombreTipoIndicador(String nombreTipoIndicador) {
        this.nombreTipoIndicador = nombreTipoIndicador;
    }   
    
    public String getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(String clasificacionId) {
        this.clasificacionId = clasificacionId;
    }

    public String getFrecuenciaMedicionId() {
        return frecuenciaMedicionId;
    }

    public void setFrecuenciaMedicionId(String frecuenciaMedicionId) {
        this.frecuenciaMedicionId = frecuenciaMedicionId;
    }

    public String getPlazoId() {
        return plazoId;
    }

    public void setPlazoId(String plazoId) {
        this.plazoId = plazoId;
    }

    public String getUnidadRepresentacionId() {
        return unidadRepresentacionId;
    }

    public void setUnidadRepresentacionId(String unidadRepresentacionId) {
        this.unidadRepresentacionId = unidadRepresentacionId;
    }



    
    
    
}
