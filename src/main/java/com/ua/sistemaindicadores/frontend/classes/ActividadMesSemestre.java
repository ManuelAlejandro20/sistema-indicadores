/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.classes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aleja
 */
public class ActividadMesSemestre {
    
    private Map<String, Actividad> actividades;

    public ActividadMesSemestre(List<String> keys) {
        this.actividades = new LinkedHashMap<String, Actividad>();
        for(String s : keys){
            actividades.put(s, new Actividad("", 0));
        }
        
    }

    public Map<String, Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Map<String, Actividad> actividades) {
        this.actividades = actividades;
    }



    
    
}
