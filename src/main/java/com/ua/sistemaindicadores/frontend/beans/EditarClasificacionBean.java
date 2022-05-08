/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.entities.SelectLineamiento;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author diego
 */
@Named(value = "clasificacionesBean")
@ViewScoped
public class EditarClasificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    transient private ClasificacionService clasificacionService;

    private List<SelectLineamiento> listaLineamientos;
    private SelectLineamiento lineamientoSeleccionado;

    /**
     * Creates a new instance of convenioBean
     */
    public EditarClasificacionBean() {
    }

    @PostConstruct
    private void inicializar() {

        listaLineamientos = clasificacionService.obtenerLineamientos();

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
