/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.exceptions.NotificacionCorreoException;
import com.ua.sistemaindicadores.backend.services.CorreoService;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;  
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author aleja
 */
@Named(value = "crearTipoIndicadorBean")
@ViewScoped
public class CrearTipoIndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String direccionSI = "http://localhost:8080/SistemaIndicadores-1.0-SNAPSHOT/faces/inicio/inicio.xhtml";
    
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
    @Inject
    transient private CorreoService correoService;    
  
    private String nombreTipoIndicador; 
    private String vigencia;
    private String descripcion;
    
    private boolean disabled;
    
    private IndicadorTipo nuevoIndicadorTipo;
        
    @PostConstruct
    public void initalize(){
        nuevoIndicadorTipo = new IndicadorTipo();
        disabled = false;
        System.out.println("Inicio Bean Crear Tipo Indicador");     
    }
    
    /**
     * Creates a new instance of convenioBean
     */
    public CrearTipoIndicadorBean() {
    }
    
    public void crearTipoIndicador() throws IOException
    {
               
        short numVigencia = 0; 
        if(vigencia.equals("VIGENTE")){
            numVigencia = 1;
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        nuevoIndicadorTipo.setNombre(nombreTipoIndicador);
        nuevoIndicadorTipo.setEstado(numVigencia);
        nuevoIndicadorTipo.setDescripcion(descripcion);
        nuevoIndicadorTipo.setFechaCreacion(new Date());
        nuevoIndicadorTipo.setFechaActualizacion(new Date());
        nuevoIndicadorTipo.setClasificacionCollection(new ArrayList<>());
                
        if(!tipoIndicadorService.checkTipoIndicadorExists(nombreTipoIndicador)){
            tipoIndicadorService.crearTipoIndicador(nuevoIndicadorTipo);          
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN", 
                    "El tipo de indicador " + nombreTipoIndicador + " ha sido agregado correctamente")
            );
                 
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                correoService.enviarMensajeTexto("SOPORTE.DVCME@uantof.cl", "Sistema de Indicadores", "Se ha creado un registro de un nuevo tipo de indicador.<br/> "
                        + "<ul>"
                        + "<li>Nombre tipo de indicador: " + nuevoIndicadorTipo.getNombre() + ".</li>"
                        + "<li>Estado: " + vigencia + ".</li>"
                        + "<li>Descripción: " + nuevoIndicadorTipo.getDescripcion() + ".</li>"
                        + "<li>Fecha creación: " + formatter.format(nuevoIndicadorTipo.getFechaCreacion()) + ".</li>"
                        + "</ul>"
                        + "<br/><br/>"                                
                        + "<a href=" + direccionSI + ">Link Sistema de Indicadores</a>"
                        + "<br/><br/>"
                        + "<br/><br/>"                                
                        + "Saludos cordiales. <br/><br/>"
                        + "Sistema de Indicadores."
                );   
            } catch (NotificacionCorreoException ex) {
                
                context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                        "Ocurrio un error al enviar el correo. Contacte al administrador mediante el correo SOPORTE.DVCME@uantof.cl.")
                );                           
                
                //En caso de capturar algun error se retorna un mensaje y se guarda en el log el error
                Logger.getLogger(CrearTipoIndicadorBean.class
                        .getName()).log(Level.SEVERE, "Ocurrio un error al enviar el correo.", ex);

            }                     
            
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext()
                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-tipo-indicador.xhtml");            
            
        }
        else{
            
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                    "El tipo de indicador " + nombreTipoIndicador + " ya existe en los registros")
            );            
            
            
        }                      
    }        
    
    public String getNombreTipoIndicador() {
        return nombreTipoIndicador;
    }

    public void setNombreTipoIndicador(String nombreTipoIndicador) {
        this.nombreTipoIndicador = nombreTipoIndicador;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

    public IndicadorTipo getNuevoIndicadorTipo() {
        return nuevoIndicadorTipo;
    }

    public void setNuevoIndicadorTipo(IndicadorTipo nuevoIndicadorTipo) {
        this.nuevoIndicadorTipo = nuevoIndicadorTipo;
    }

    public TipoIndicadorService getTipoIndicadorService() {
        return tipoIndicadorService;
    }

    public void setTipoIndicadorService(TipoIndicadorService tipoIndicadorService) {
        this.tipoIndicadorService = tipoIndicadorService;
    }

    public CorreoService getCorreoService() {
        return correoService;
    }

    public void setCorreoService(CorreoService correoService) {
        this.correoService = correoService;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    
          
}
