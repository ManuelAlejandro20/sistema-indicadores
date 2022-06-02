/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.exceptions.NotificacionCorreoException;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.services.CorreoService;
import java.util.Date;
import java.util.TimeZone;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static final String direccionSI = "http://localhost:8080/SistemaIndicadores-1.0-SNAPSHOT/faces/inicio/inicio.xhtml";

    @Inject
    transient private ClasificacionService clasificacionService;
    @Inject
    transient private CorreoService correoService;

    private Clasificacion cl;
    private String id;
    private String nombreClasificacion;
    private String vigencia;
    private String descripcion;

    private boolean disabled;

    @PostConstruct
    public void initalize() {
        disabled = false;
        System.out.println("Inicio Bean Editar Tipo Indicador");
    }

    /**
     * Creates a new instance of convenioBean
     */
    public EditarClasificacionBean() {
    }

    public void cargarDatos() {
        if (id != null) {
            cl = clasificacionService.buscarClasificacionID(Integer.valueOf(id));
            nombreClasificacion = cl.getNombre();
            descripcion = cl.getDescripcion();

            if (cl.getEstado() == 1) {
                vigencia = "VIGENTE";
            } else {
                vigencia = "NO VIGENTE";
            }
        }

    }

    public void existeClasificacion() {
        try {
            if (clasificacionService.existeClasificacion(nombreClasificacion)) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN",
                        "La clasificacion " + nombreClasificacion + " ya existe en los registros")
                );
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCIÓN",
                    "La clasificacion " + nombreClasificacion + " no existe en los registros")
            );
        } catch (Exception ex) {

            Logger.getLogger(EditarClasificacionBean.class
                    .getName()).log(Level.SEVERE, "Ocurrio un error al comparar la Clasificacion.", ex);
            FacesContext
                    .getCurrentInstance()
                    .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema al comprobar la clasificacion. Contacte al administrador.")
                    );
        }

    }

    public void actualizarDatos() throws IOException {

        short numVigencia = 0;
        if (vigencia.equals("VIGENTE")) {
            numVigencia = 1;
        }

        //Datos de la clasificacion antes de actualizarlos
        String nombreAntiguo = cl.getNombre();
        Short vigenciaAntiguaSh = cl.getEstado();
        String vigenciaAntigua = (cl.getEstado() == 1) ? "VIGENTE" : "NO VIGENTE";
        String tipo = cl.getTipo();
        String descripcionAntigua = cl.getDescripcion();

        FacesContext context = FacesContext.getCurrentInstance();
        //Se actualizan los datos de la clasificacion
        cl.setNombre(nombreClasificacion);
        cl.setEstado(numVigencia);
        cl.setTipo(tipo);
        cl.setDescripcion(descripcion);
        cl.setFechaActualizacion(new Date());

        try {
            clasificacionService.actualizarClasificacion(cl);
            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN",
                    "La clasificacion " + nombreClasificacion + " ha sido actualizado correctamente")
            );

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                correoService.enviarMensajeTexto("manueltrigo.at@gmail.com", "Sistema de Indicadores", "Se ha actualizado una clasificacion.<br/> "
                        + "<ul>"
                        + "<li>Nombre clasificacion: " + nombreAntiguo + " ---> " + cl.getNombre() + ".</li>"
                        + "<li>Estado: " + vigenciaAntigua + " ---> " + vigencia + ".</li>"
                        + "<li>Descripción: " + descripcionAntigua + " ---> " + cl.getDescripcion() + ".</li>"
                        + "<li>Fecha creación: " + formatter.format(cl.getFechaCreacion()) + ".</li>"
                        + "<li>Fecha actualización: " + formatter.format(cl.getFechaActualizacion()) + ".</li>"
                        + "</ul>"
                        + "<br/><br/>"
                        + "<a href=" + direccionSI + ">Link Sistema de Indicadores</a>"
                        + "<br/><br/>"
                        + "<br/><br/>"
                        + "Saludos cordiales. <br/><br/>"
                        + "Sistema de Indicadores."
                );
            } catch (NotificacionCorreoException ex) {

                //En caso de que ocurra una excepcion el registro en la BD no se actualiza pero el objeto clasificacion si
                //lo hace, por lo que se tiene que volver a setear los valores a los que tenia antes de actualizar
                cl.setNombre(nombreAntiguo);
                cl.setEstado(vigenciaAntiguaSh);
                cl.setDescripcion(descripcionAntigua);

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

        } catch (EJBException e) {

            //En caso de que ocurra una excepcion el registro en la BD no se actualiza pero el objeto clasificacion si
            //lo hace, por lo que se tiene que volver a setear los valores a los que tenia antes de actualizar            
            cl.setNombre(nombreAntiguo);
            cl.setEstado(vigenciaAntiguaSh);
            cl.setDescripcion(descripcionAntigua);

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

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
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
