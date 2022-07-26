/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import com.ua.sistemaindicadores.backend.services.CorreoService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
/**
 *
 * @author aleja
 */
@Named(value = "soporteBean")
@ViewScoped
public class SoporteBean implements Serializable{
    
    private static final long serialVersionUID = 1L;    
    private static final String direccionSI = "http://localhost:8080/SistemaIndicadores-1.0-SNAPSHOT/faces/inicio/inicio.xhtml";

    @Inject
    transient private CorreoService correoService;       
    
    private String nombres;
    private String apellidos;
    private String cargo;
    private String correo;
    private String asunto;
    private String mensaje;

    private boolean disabled;
    
    @PostConstruct
    public void initalize(){
        disabled = false;
        System.out.println("Inicio Bean Soporte");     
    }    
    
    public void enviarMensajeSoporte() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = Calendar.getInstance().getTime();
            String fecha_ = sf.format(fecha);
            try {
                correoService.enviarMensajeTexto("SOPORTE.DVCME@uantof.cl", "Soporte sistema de indicadores", "Se ha enviado una solicitud de soporte del sistema de indicadores.<br/> "
                        +"<br/>"
                        +"<ul>"
                        +"<li>Nombre(s): "+ nombres+".</li>"
                        +"<li>Apellidos: "+ apellidos+".</li>"
                        +"<li>Cargo: "+cargo+".</li>"
                        +"<li>Correo: "+ correo+".</li>"
                        +"<li>Fecha: "+ fecha_+".</li>"
                        +"<li>Asunto: "+ asunto+".</li>"
                        +"<li>Mensaje: "+mensaje+".</li>"
                        +"</ul>"
                        +"<br/><br/>"
                        +"<a href=" + direccionSI + ">Link Sistema de Indicadores</a>"
                        +"<br/><br/>"
                        +"<br/><br/>"                                
                        +"Saludos cordiales. <br/><br/>"
                        +"Sistema de Indicadores."
                );
            } catch (Exception ex) {
                //En caso de capturar algun error se retorna un mensaje y se guarda en el log el error
                Logger.getLogger(SoporteBean.class
                        .getName()).log(Level.SEVERE, "Ocurrio un error al enviar el correo.", ex);
                FacesContext
                        .getCurrentInstance()
                        .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al enviar el correo. Contacte al administrador mediante el correo SOPORTE.DVCME@uantof.cl.")
                        );
            }
            limpiarFormulario();
            //Retornando mensaje de convenio exitoso
            FacesContext.getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El correo se ha enviado exitosamente.")
                    );

        } catch (EJBException ex) {
            //En caso de capturar algun error se retorna un mensaje y se guarda en el log el error
            Logger.getLogger(SoporteBean.class
                    .getName()).log(Level.SEVERE, "Ocurrio un error al enviar el correo", ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage().toString())
                    );
        }
    }

    private void limpiarFormulario() {
        nombres= null;
        cargo = null;
        asunto = null;
        correo = null;
        apellidos = null;
        mensaje = null;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    
    
    
}
