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
   
    //Variables que no seran null cuando se traten de filas que contengan actividades
    private String titulo;
    private Integer numActividades;
    private Integer logro;   
    
    //No sera null cuando se trate de una actividad
    private String nombre;
    private String parentNodeString;

    public TreeNodeRow(String titulo, Integer numActividades, Integer logro, String nombre, String parentNodeString) {
        this.titulo = titulo;
        this.numActividades = numActividades;
        this.logro = logro;
        this.nombre = nombre;
        this.parentNodeString = parentNodeString;
    }   
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Integer getNumActividades() {
        return numActividades;
    }

    public void setNumActividades(Integer numActividades) {
        if(numActividades == null){
            numActividades = 1;
        }
        this.numActividades = numActividades;
    }    

    public String getParentNodeString() {
        return parentNodeString;
    }

    public void setParentNodeString(String parentNodeString) {
        this.parentNodeString = parentNodeString;
    }


    
    
}
