/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.classes;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author aleja
 */
public class ActividadAnio {
    
    private Integer numActividades;
    private Integer logro;
    private Integer anio;
    private List<Actividad> actividades;

    public ActividadAnio(Integer anio) {
        this.numActividades = 1;
        this.logro = 0;
        this.anio = anio;
        this.actividades = new LinkedList<Actividad>();
        this.actividades.add(new Actividad(""));        
    }    

    public Integer getNumActividades() {
        return numActividades;
    }

    public void setNumActividades(Integer numActividades) {
        if(numActividades == null){
            numActividades = 1;
        }
        this.numActividades = numActividades;
    }

    public Integer getLogro() {
        return logro;
    }

    public void setLogro(Integer logro) {
        this.logro = logro;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }         
}
