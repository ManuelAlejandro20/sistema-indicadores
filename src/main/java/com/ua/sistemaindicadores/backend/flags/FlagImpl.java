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
 * 
 * Campos del formulario que se pueden ocultar. Para ocultarlos se invoca el metodo setFlagTrue con alguno
 * de los siguientes strings:
    descripcionIndicador
    aplicaLineamiento
    aplicaObjetivo
    descripcionObjetivo
    ajustePDEI
    unidadRepresentacion
    generacionDatos
    plazo
    version
    lineaBase
    anio2020
    anio2021
    anio2022
    anio2023
    anio2024
    anio2025
    anio2026
    anio2027
    anio2028
    anio2029
    anio2030
    metas
    anioCumplimiento
    logro
    frecuenciaMedicion
    medioVerificacion
    formaCalculo
    fuenteInformacion
    unidadProveedora
    proyectoAsociado
    comentario
    actividadComprometida
    estadoActividad  
 * 
 */
public class FlagImpl {
    
    private Map<String, Flag> flagsTipoIndicador;
    
    public FlagImpl(){
        this.flagsTipoIndicador = new HashMap<String, Flag>();
        this.flagsTipoIndicador.put("ANT", new Flag());
        this.flagsTipoIndicador.put("CUECH", new Flag());
        this.flagsTipoIndicador.put("PDEI", new Flag());
        this.flagsTipoIndicador.put("UNIDADES", new Flag());
            
        //------
                        
        setFlagsCUECH();
        
    }

    public Map<String, Flag> getFlagsTipoIndicador() {
        return flagsTipoIndicador;
    }

    public void setFlagsTipoIndicador(Map<String, Flag> flagsTipoIndicador) {
        this.flagsTipoIndicador = flagsTipoIndicador;
    }

    public Flag getFlagsTipoIndicador(String tipoIndicador){
        return this.flagsTipoIndicador.get(tipoIndicador);
    }
    
    private void setFlagsCUECH(){
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("aplicaLineamiento");
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("aplicaObjetivo");
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("descripcionObjetivo");
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("descripcionIndicador");
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("ajustePDEI");
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("generacionDatos");
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("plazo");
        this.flagsTipoIndicador.get("CUECH").setFlagTrue("version");    
    }
    
    
}
