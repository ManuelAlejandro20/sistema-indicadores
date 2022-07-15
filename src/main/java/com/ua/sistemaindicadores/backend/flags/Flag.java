/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.flags;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aleja
 */
public class Flag {
    
    private Map<String, Boolean> hiddenFlags;
    
    public Flag(){
        this.hiddenFlags = new HashMap<String, Boolean>();
        hiddenFlags.put("descripcionIndicador", false);
        hiddenFlags.put("aplicaLineamiento", false);
        hiddenFlags.put("aplicaObjetivo", false);
        hiddenFlags.put("descripcionObjetivo", false);
        hiddenFlags.put("ajustePDEI", false);
        hiddenFlags.put("generacionDatos", false);
        hiddenFlags.put("plazo", false);
        hiddenFlags.put("version", false);
        hiddenFlags.put("lineaBase", false);
        hiddenFlags.put("medioVerificacion", false);
        hiddenFlags.put("formaCalculo", false);
        hiddenFlags.put("fuenteInformacion", false);
        hiddenFlags.put("unidadProveedora", false);
        hiddenFlags.put("proyectoAsociado", false);
        hiddenFlags.put("comentario", false);
        hiddenFlags.put("actividadComprometida", false);
        hiddenFlags.put("estadoActividad", false);        
    }
    
    public void setFlagTrue(String nombreCampo){
        hiddenFlags.put(nombreCampo, true);
    }
    
    public boolean getInputFlag(String nombreCampo){
        return this.hiddenFlags.get(nombreCampo);
    }    

    public Map<String, Boolean> getHiddenFlags() {
        return hiddenFlags;
    }

    public void setHiddenFlags(Map<String, Boolean> hiddenFlags) {
        this.hiddenFlags = hiddenFlags;
    }
    
    
    
    
    
}
