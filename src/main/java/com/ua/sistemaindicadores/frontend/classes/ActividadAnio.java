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
    
    private Integer montoProceso;
    private Integer porcActividades;
    
    private List<Actividad> actividades;

    public ActividadAnio(Integer anio, String uRepr) {
        Actividad actividad;
        if(uRepr.equals("Número Actividades")){
            this.logro = 0;
            this.numActividades = 1;
            actividad = new Actividad("");
        }
        else if(uRepr.equals("Peso ($)")){
            this.montoProceso = 0;
            this.numActividades = 1;
            actividad = new Actividad("", 0);
        }else{
            this.porcActividades = 1;
            actividad = new Actividad("");
        }        
                
        this.anio = anio;
        this.actividades = new LinkedList<Actividad>();
        this.actividades.add(actividad);        
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

    public Integer getMontoProceso() {
        return montoProceso;
    }

    public void setMontoProceso(Integer montoProceso) {        
        this.montoProceso = montoProceso;
    }

    public Integer getPorcActividades() {
        return porcActividades;
    }

    public void setPorcActividades(Integer porcActividades) {
        if(porcActividades == null){
            porcActividades = 1;
        }
        this.porcActividades = porcActividades;
    }

    

}
