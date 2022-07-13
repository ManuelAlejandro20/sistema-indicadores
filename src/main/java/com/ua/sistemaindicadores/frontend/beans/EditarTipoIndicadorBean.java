/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.exceptions.NotificacionCorreoException;
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
import java.util.Collection;
import java.util.Date;  
import java.util.Map;
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
    private static final String direccionSI = "http://localhost:8080/SistemaIndicadores-1.0-SNAPSHOT/faces/inicio/inicio.xhtml";
    
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
    @Inject
    transient private ClasificacionService clasificacionService;    
    @Inject
    transient private CorreoService correoService;  
    
    private IndicadorTipo it;
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
        
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("i");
        
        int id_int = 0;
        
        try{
            id_int = Integer.valueOf(id);
        }catch(Exception e){
            id = null;
        }
                 
        //El id es nulo cuando cargamos la pagina pero no brindamos ningun parametro
        if(id != null){
            it = tipoIndicadorService.buscarTipoIndicadorID(id_int);
        
            //it es nulo cuando el id leido no existe en la bd
            if(it != null){
                nombreTipoIndicador = it.getNombre();
                descripcion = it.getDescripcion();

                if(it.getEstado() == 1){
                    vigencia = "VIGENTE";
                }else{
                    vigencia = "NO VIGENTE";
                }
            }            
        
        }                       
    }
    
    public void actualizarDatos() throws IOException{        
        //El it es nulo cuando damos por parametro una id que no existe o id nulo
        if(it != null){
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

                String mensaje = "";
                
                //Si el tipo de indicador tiene clasificaciones asociadas y si se hace un cambio de vigencia...
                if(it.getClasificacionCollection().size() != 0){
                    ArrayList<String> clasificacionesAfectadas = actualizarClasificaciones(vigenciaAntiguaSh, numVigencia);
                    if(clasificacionesAfectadas.size() != 0){
                        mensaje = mensajeClasificacionesAfectadas(clasificacionesAfectadas, it.getNombre(), numVigencia);
                    }
                }
                                                               
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
                            + "<br/>"
                            + mensaje
                            + "<a href=" + direccionSI + ">Link Sistema de Indicadores</a>"
                            + "<br/>"                                
                            + "<br/>"
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
            catch(EJBException e){

                context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                        "El tipo de indicador " + nombreTipoIndicador + " ya existe en los registros")
                );            


            }          
        }
    }    
    
    private ArrayList<String> actualizarClasificaciones(Short vigenciaAntiguaSh, Short numVigencia){
        ArrayList<String> clasificacionesAfectadas = new ArrayList<String>();
        Collection<Clasificacion> clasificaciones = it.getClasificacionCollection();
        for(Clasificacion c : clasificaciones){
            c.setTipo(it.getNombre());
            
            //Se le esta intentando cambiar la vigencia al tipo de indicador por lo tanto seria una clas
            //afectada
            if(vigenciaAntiguaSh != numVigencia){
                c.setEstado(numVigencia);
                clasificacionesAfectadas.add(c.getNombre());
            }            
            
            clasificacionService.actualizarClasificacion(c);
        }
        return clasificacionesAfectadas;
    }
    
    private String mensajeClasificacionesAfectadas(ArrayList<String> clasificacionesAfectadas, String tipoIndicador, Short numVigencia){        
        String mensaje = "Las siguientes clasificaciones pertenecientes a " + tipoIndicador + " cambiaron su estado a ";
        if(numVigencia == 1){
            mensaje += " VIGENTE:";
        }else{
            mensaje += " NO VIGENTE:";
        }
        mensaje += "<ul>";
        for(String s : clasificacionesAfectadas){
            mensaje += "<li>" + s + "</li>";
        }
        mensaje += "</ul><br/>";
        return mensaje;
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
