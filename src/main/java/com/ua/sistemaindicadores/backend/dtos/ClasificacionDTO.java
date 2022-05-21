/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.dtos;

import java.util.Date;

/**
 *
 * @author diego
 */
public class ClasificacionDTO {

    private Integer id;
    private Integer indicadorTipoId;
    private String nombre;
    private Short estado;
    private String descripcion;
    private String tipo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public ClasificacionDTO(Integer id, Integer indicadorTipoId, String nombre, String tipo, String descripcion, Short estado, Date fechaCreacion, Date fechaActualizacion) {
        this.id = id;
        this.indicadorTipoId = indicadorTipoId;
        this.nombre = nombre;
        this.estado = estado;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIndicadorTipoId() {
        return indicadorTipoId;
    }

    public void setIndicadorTipoId(Integer indicadorTipoId) {
        this.indicadorTipoId = indicadorTipoId;
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



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
