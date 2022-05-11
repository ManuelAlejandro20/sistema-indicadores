/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
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
@Named(value = "editarTipoIndicadorBean")
@ViewScoped
public class EditarTipoIndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
    @Inject
    transient private CorreoService correoService;  
    
    private IndicadorTipo it;
    private String id; 
    private String nombreTipoIndicador; 
    private String vigencia;
    private String descripcion;    
    
    private boolean disabled;
    
    @PostConstruct
    public void initalize(){
        disabled = false;
        System.out.println("Inicio Bean Editar Tipo Indicador");
    }
    
    /**
     * Creates a new instance of convenioBean
     */
    public EditarTipoIndicadorBean() {
    }

    public void cargarDatos(){ 
        if(id != null){       
            it = tipoIndicadorService.buscarTipoIndicadorID(Integer.valueOf(id));
            nombreTipoIndicador = it.getNombre();
            descripcion = it.getDescripcion();

            if(it.getEstado() == 1){
                vigencia = "VIGENTE";
            }else{
                vigencia = "NO VIGENTE";
            }
        }
        
    }
    
    public void actualizarDatos() throws IOException{        
        
        short numVigencia = 0; 
        if(vigencia.equals("VIGENTE")){
            numVigencia = 1;
        }
        
        //Datos del tipo de indicador antes de actualizar
        String nombreAntiguo = it.getNombre();
        Short vigenciaAntiguaSh = it.getEstado();
        String vigenciaAntigua = (it.getEstado() == 1)? "VIGENTE" : "NO VIGENTE";
        String descripcionAntigua = it.getDescripcion();
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        //Se actualizan los datos del tipo indicador
        it.setNombre(nombreTipoIndicador);
        it.setEstado(numVigencia);
        it.setDescripcion(descripcion);
        it.setFechaActualizacion(new Date());
        
        try{
            tipoIndicadorService.actualizarTipoIndicador(it);          
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN", 
                    "El tipo de indicador " + nombreTipoIndicador + " ha sido actualizado correctamente")
            );
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext()
                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-tipo-indicador.xhtml");
        
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                correoService.enviarMensajeTexto("manueltrigo.at@gmail.com", "Sistema de Indicadores", "Se ha actualizado un tipo de indicador.<br/> "
                        + "<ul>"
                        + "<li>Nombre tipo de indicador: " + nombreAntiguo + " ---> " + it.getNombre() + ".</li>"
                        + "<li>Estado: " + vigenciaAntigua + " ---> " + vigencia + ".</li>"
                        + "<li>Descripción: " + descripcionAntigua + " ---> " + it.getDescripcion() + ".</li>"
                        + "<li>Fecha creación: " + formatter.format(it.getFechaCreacion()) + ".</li>"
                        + "<li>Fecha actualización: " + formatter.format(it.getFechaActualizacion()) + ".</li>"
                        + "</ul>"
                        + "<br/><br/>"
                        + "Saludos cordiales. <br/><br/>"
                        + "Sistema de Indicadores."
                );   
            } catch (Exception ex) {
                
                //En caso de que ocurra una excepcion el registro en la BD no se actualiza pero el objeto tipo de indicador si
                //lo hace, por lo que se tiene que volver a setear los valores a los que tenia antes de actualizar
                it.setNombre(nombreAntiguo);
                it.setEstado(vigenciaAntiguaSh);
                it.setDescripcion(descripcionAntigua);                
                
                //En caso de capturar algun error se retorna un mensaje y se guarda en el log el error
                Logger.getLogger(CrearTipoIndicadorBean.class
                        .getName()).log(Level.SEVERE, "Ocurrio un error al enviar el correo.", ex);
                FacesContext
                        .getCurrentInstance()
                        .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al enviar el correo. Contacte al administrador mediante el correo SOPORTE.DVCME@uantof.cl.")
                        );
            }             
        }
        catch(EJBException e){
            
            //En caso de que ocurra una excepcion el registro en la BD no se actualiza pero el objeto tipo de indicador si
            //lo hace, por lo que se tiene que volver a setear los valores a los que tenia antes de actualizar            
            it.setNombre(nombreAntiguo);
            it.setEstado(vigenciaAntiguaSh);
            it.setDescripcion(descripcionAntigua);
            
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN", 
                    "El tipo de indicador " + nombreTipoIndicador + " ya existe en los registros")
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

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    
    
}
