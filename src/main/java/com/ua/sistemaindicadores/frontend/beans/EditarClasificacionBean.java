/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.entities.SelectLineamiento;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author diego
 */
@Named(value = "editarClasificacionBean")
@ViewScoped
public class EditarClasificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private ClasificacionService clasificacionService;

    private Clasificacion it;
    private String id;
    private String nombreClasificacion;
    private String tipo;
    private String descripcion;
    private Short estado;

    @PostConstruct
    public void initalize() {
        System.out.println("Inicio Bean Editar Tipo Indicador");
    }

    /**
     * Creates a new instance of convenioBean
     */
    public EditarClasificacionBean() {
    }

    public void cargarDatos() {
        it = clasificacionService.buscarClasificacionID(Integer.valueOf(id));
        nombreClasificacion = it.getNombre();
        tipo = it.getTipo();
        descripcion = it.getDescripcion();
        estado = it.getEstado();
    }

    public void actualizarDatos() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        it.setNombre(nombreClasificacion);
        it.setTipo(tipo);
        it.setEstado(estado);
        it.setDescripcion(descripcion);

        try {
            clasificacionService.actualizarClasificacion(it);
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN",
                    "La clasificacion " + nombreClasificacion + " ha sido actualizado correctamente")
            );
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext()
                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-clasificacion.xhtml");
        } catch (EJBException e) {

            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN",
                    "La clasificacion " + nombreClasificacion + " ya existe en los registros")
            );

        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreTipoIndicador() {
        return nombreClasificacion;
    }

    public void setNombreTipoIndicador(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

}
