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
public class TreeNodeRow {
   
    private String nombrePeriodo;
    private Integer numActividades;
    
    private String nombreActividad;
    private String nombrePadre;    
    
    private Integer logro;
    private Integer monto;
    private Integer porcActividad;

    public TreeNodeRow(String nombrePeriodo){    
        this.nombrePeriodo = nombrePeriodo;         
    }
    
    public TreeNodeRow(String nombreActividad, String nombrePadre){    
        this.nombreActividad = nombreActividad;
        this.nombrePadre = nombrePadre;
    }    

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }                 

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
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

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        if(monto == null){
            monto = 0;
        }
        this.monto = monto;
    }

    public Integer getPorcActividad() {
        return porcActividad;
    }

    public void setPorcActividad(Integer porcActividad) {
        System.out.println(porcActividad);
        if(porcActividad == null){
            porcActividad = 1;
        }
        this.porcActividad = porcActividad;
    }          


    
    
}
