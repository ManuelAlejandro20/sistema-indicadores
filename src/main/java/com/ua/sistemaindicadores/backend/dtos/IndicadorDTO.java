/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.dtos;

import com.ua.sistemaindicadores.backend.entities.Indicador;
import java.util.Date;

/**
 *
 * @author diego
 */
public class IndicadorDTO {
    private Integer id;
    private Integer numero;
    private String nombre;
    private Short estado;
    private String descripcion;
    private String lineamiento;
    private String objetivo;
    private String descripcion_objetivo;
    private String version;
    private String linea_base;
    private String metas;
    private String porcentaje_logro;
    private String medio_verificacion;
    private String forma_calculo;
    private String fuente_informacion;
    private String proyecto_asociado;
    private String comentario;
    private String actividad_comprometida;
    private String estado_actividad;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private Integer indicadorTipoId;
    private Integer clasificacionId;

    public IndicadorDTO(Integer id, Integer numero, String nombre, Short estado, String descripcion, String lineamiento, String objetivo, String descripcion_objetivo, String version, String linea_base, String metas, String porcentaje_logro, String medio_verificacion, String forma_calculo, String fuente_informacion, String proyecto_asociado, String comentario, String actividad_comprometida, String estado_actividad, Date fechaCreacion, Date fechaActualizacion, Integer indicadorTipoId, Integer clasificacionId) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.lineamiento = lineamiento;
        this.objetivo = objetivo;
        this.descripcion_objetivo = descripcion_objetivo;
        this.version = version;
        this.linea_base = linea_base;
        this.metas = metas;
        this.porcentaje_logro = porcentaje_logro;
        this.medio_verificacion = medio_verificacion;
        this.forma_calculo = forma_calculo;
        this.fuente_informacion = fuente_informacion;
        this.proyecto_asociado = proyecto_asociado;
        this.comentario = comentario;
        this.actividad_comprometida = actividad_comprometida;
        this.estado_actividad = estado_actividad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.indicadorTipoId = indicadorTipoId;
        this.clasificacionId = clasificacionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLineamiento() {
        return lineamiento;
    }

    public void setLineamiento(String lineamiento) {
        this.lineamiento = lineamiento;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescripcion_objetivo() {
        return descripcion_objetivo;
    }

    public void setDescripcion_objetivo(String descripcion_objetivo) {
        this.descripcion_objetivo = descripcion_objetivo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLinea_base() {
        return linea_base;
    }

    public void setLinea_base(String linea_base) {
        this.linea_base = linea_base;
    }

    public String getMetas() {
        return metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }

    public String getPorcentaje_logro() {
        return porcentaje_logro;
    }

    public void setPorcentaje_logro(String porcentaje_logro) {
        this.porcentaje_logro = porcentaje_logro;
    }

    public String getMedio_verificacion() {
        return medio_verificacion;
    }

    public void setMedio_verificacion(String medio_verificacion) {
        this.medio_verificacion = medio_verificacion;
    }

    public String getForma_calculo() {
        return forma_calculo;
    }

    public void setForma_calculo(String forma_calculo) {
        this.forma_calculo = forma_calculo;
    }

    public String getFuente_informacion() {
        return fuente_informacion;
    }

    public void setFuente_informacion(String fuente_informacion) {
        this.fuente_informacion = fuente_informacion;
    }

    public String getProyecto_asociado() {
        return proyecto_asociado;
    }

    public void setProyecto_asociado(String proyecto_asociado) {
        this.proyecto_asociado = proyecto_asociado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getActividad_comprometida() {
        return actividad_comprometida;
    }

    public void setActividad_comprometida(String actividad_comprometida) {
        this.actividad_comprometida = actividad_comprometida;
    }

    public String getEstado_actividad() {
        return estado_actividad;
    }

    public void setEstado_actividad(String estado_actividad) {
        this.estado_actividad = estado_actividad;
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

    public Integer getIndicadorTipoId() {
        return indicadorTipoId;
    }

    public void setIndicadorTipoId(Integer indicadorTipoId) {
        this.indicadorTipoId = indicadorTipoId;
    }

    public Integer getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(Integer clasificacionId) {
        this.clasificacionId = clasificacionId;
    }
    
}
