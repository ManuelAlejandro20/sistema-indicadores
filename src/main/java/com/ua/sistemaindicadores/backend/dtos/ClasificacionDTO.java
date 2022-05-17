/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.dtos;

/**
 *
 * @author diego
 */
public class ClasificacionDTO {

    private Integer id;
    private Integer indicador_tipo_id;
    private String nombre;
    private Short estado;
    private String tipo;
    private String descripcion;

    public ClasificacionDTO(Integer id, Integer indicador_tipo_id, String nombre, Short estado, String tipo, String descripcion) {
        this.id = id;
        this.indicador_tipo_id = indicador_tipo_id;
        this.nombre = nombre;
        this.estado = estado;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndicador_tipo_id() {
        return indicador_tipo_id;
    }

    public void setIndicador_tipo_id(Integer indicador_tipo_id) {
        this.indicador_tipo_id = indicador_tipo_id;
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
