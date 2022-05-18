/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
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
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author diego
 */
@Named(value = "crearClasificacionBean")
@ViewScoped
public class CrearClasificacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    transient private ClasificacionService clasificacionService;
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
    @Inject
    transient private CorreoService correoService;       

    private Integer indicador_tipo_id;
    private String nombreClasificacion;
    private String vigencia;
    private String descripcion;
    private List<IndicadorTipo> listaIndicadorTipo;
    private IndicadorTipo indicadorTipoSeleccionado;

    private Clasificacion nuevaClasificacion;

    private boolean disabled;
    
    @PostConstruct
    public void initalize() {
        nuevaClasificacion = new Clasificacion();
        listaIndicadorTipo = tipoIndicadorService.obtenerIndicadorTipos();
        disabled = false;
        System.out.println("Inicio Bean Crear Clasificacion");
    }

    /**
     * Creates a new instance of convenioBean
     */
    public CrearClasificacionBean() {
    }

    public void crearClasificacion() throws IOException {
        short numVigencia = 0;
        if (vigencia.equals("VIGENTE")) {
            numVigencia = 1;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        
        nuevaClasificacion.setIndicadorTipoId(indicadorTipoSeleccionado);
        nuevaClasificacion.setNombre(nombreClasificacion);
        nuevaClasificacion.setEstado(numVigencia);
        nuevaClasificacion.setTipo(indicadorTipoSeleccionado.getNombre());
        nuevaClasificacion.setDescripcion(descripcion);
        nuevaClasificacion.setFechaCreacion(new Date());
        nuevaClasificacion.setFechaActualizacion(new Date());
        nuevaClasificacion.setIndicadorCollection(new ArrayList<>());        

        try {
            clasificacionService.crearClasificacion(nuevaClasificacion);
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN",
                    "La clasificación " + nombreClasificacion + " ha sido agregada correctamente")
            );
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext()
                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-clasificacion.xhtml");
        
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                correoService.enviarMensajeTexto("manueltrigo.at@gmail.com", "Sistema de Indicadores", "Se ha creado un registro de una nueva clasificación.<br/> "
                        + "<ul>"
                        + "<li>Nombre clasificación: " + nuevaClasificacion.getNombre() + ".</li>"
                        + "<li>Tipo de indicador asociado: " + nuevaClasificacion.getTipo() + ".</li>"                                    
                        + "<li>Estado: " + vigencia + ".</li>"
                        + "<li>Descripción: " + nuevaClasificacion.getDescripcion() + ".</li>"       
                        + "<li>Fecha creación: " + formatter.format(nuevaClasificacion.getFechaCreacion()) + ".</li>"                              
                        + "</ul>"
                        + "<br/><br/>"
                        + "Saludos cordiales. <br/><br/>"
                        + "Sistema de Indicadores."
                );   
            } catch (Exception ex) {
                
                //En caso de capturar algun error se retorna un mensaje y se guarda en el log el error
                Logger.getLogger(CrearTipoIndicadorBean.class
                        .getName()).log(Level.SEVERE, "Ocurrio un error al enviar el correo.", ex);
                FacesContext
                        .getCurrentInstance()
                        .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un error al enviar el correo. Contacte al administrador mediante el correo SOPORTE.DVCME@uantof.cl.")
                        );
            }           
        } catch (EJBException e) {

            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN",
                    "La clasificación " + nombreClasificacion + " ya existe en los registros")
            );

        }
    }

    public List<IndicadorTipo> getListaIndicadorTipo() {
        return listaIndicadorTipo;
    }

    public void setListaIndicadorTipo(List<IndicadorTipo> listaIndicadorTipo) {
        this.listaIndicadorTipo = listaIndicadorTipo;
    }

    public IndicadorTipo getIndicadorTipoSeleccionado() {
        return indicadorTipoSeleccionado;
    }

    public void setIndicadorTipoSeleccionado(IndicadorTipo indicadorTipoSeleccionado) {
        this.indicadorTipoSeleccionado = indicadorTipoSeleccionado;
    }
    
    public ClasificacionService getClasificacionService() {
        return clasificacionService;
    }

    public void setClasificacionService(ClasificacionService clasificacionService) {
        this.clasificacionService = clasificacionService;
    }

    public Integer getIndicador_tipo_id() {
        return indicador_tipo_id;
    }

    public void setIndicador_tipo_id(Integer indicador_tipo_id) {

        this.indicador_tipo_id = indicador_tipo_id;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public String getEstado() {
        return vigencia;
    }

    public void setEstado(String vigencia) {
        this.vigencia = vigencia;
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
    
    public Clasificacion getNuevaClasificacion() {
        return nuevaClasificacion;
    }

    public void setNuevaClasificacion(Clasificacion nuevaClasificacion) {
        this.nuevaClasificacion = nuevaClasificacion;
    }

}
