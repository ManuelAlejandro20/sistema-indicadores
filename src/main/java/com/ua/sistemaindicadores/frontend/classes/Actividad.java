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
    private Integer logro;

    public Actividad(String nombre, Integer logro) {
        this.nombre = nombre;
        this.logro = logro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getLogro() {
        return logro;
    }

    public void setLogro(Integer logro) {
        this.logro = logro;
    }
    
    
    
}
