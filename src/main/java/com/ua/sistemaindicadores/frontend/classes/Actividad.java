/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.classes;

/**
 *
 * @author aleja
 */
public class Actividad {
    private String nombre;
    private Integer montoActividad;

    public Actividad(String nombre) {
        this.nombre = nombre;
    }
    
    public Actividad(String nombre, Integer montoActividad) {
        this.nombre = nombre;
        this.montoActividad = montoActividad;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMontoActividad() {
        return montoActividad;
    }

    public void setMontoActividad(Integer montoActividad) {
        if(montoActividad == null){
            montoActividad = 0;
        }
        this.montoActividad = montoActividad;
    }
    
    
    
    
}
