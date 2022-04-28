/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.services.IndicadorService;
import com.ua.sistemaindicadores.backend.entities.SelectLineamiento;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aleja
 */
@Named(value = "indicadorBean")
@ViewScoped
public class IndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    transient private IndicadorService indicadorService;

    private List<SelectLineamiento> listaLineamientos;
    private SelectLineamiento lineamientoSeleccionado;

    /**
     * Creates a new instance of convenioBean
     */
    public IndicadorBean() {
    }

    @PostConstruct
    private void inicializar() {

        listaLineamientos = indicadorService.obtenerLineamientos();

        System.out.println(listaLineamientos);
        
    }

    public List<SelectLineamiento> getlistaLineamientos() {
        return this.listaLineamientos;
    }

    public void setlistaLineamientos(List<SelectLineamiento> lineamientos) {
        this.listaLineamientos = lineamientos;
    }

    public SelectLineamiento getlineamientoSeleccionado() {
        return this.lineamientoSeleccionado;
    }

    public void setlineamientoSeleccionado(SelectLineamiento lineamientoSeleccionado) {
        this.lineamientoSeleccionado = lineamientoSeleccionado;
    }

}
