/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.exceptions.NotificacionCorreoException;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.services.CorreoService;
import com.ua.sistemaindicadores.backend.services.IndicadorService;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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
    transient private IndicadorService indicadorService;     
    @Inject
    transient private ClasificacionService clasificacionService;
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;    
    @Inject
    transient private CorreoService correoService;       
    

    private Clasificacion clas;
    private String id;
    private String nombreClasificacion;
    private String tipo;
    private String descripcion;
    private String vigencia;
    private List<IndicadorTipo> listaIndicadorTipo;
    private List<IndicadorTipo> listaIndicadorTipoVigente;
    private List<IndicadorTipo> listaIndicadorTipoNoVigente;
    private IndicadorTipo indicadorTipoSeleccionado;        
    
    private boolean disabled;

    @PostConstruct
    public void initalize() {
        listaIndicadorTipoVigente = tipoIndicadorService.obtenerTiposIndicadoresByEstado(Short.valueOf("1"));
        listaIndicadorTipoNoVigente = tipoIndicadorService.obtenerTiposIndicadoresByEstado(Short.valueOf("0"));
        disabled = false;
        System.out.println("Inicio Bean Editar Tipo Indicador");
    }

    /**
     * Creates a new instance of convenioBean
     */
    public EditarClasificacionBean() {
    }

    public void cargarDatos() {
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
            clas = clasificacionService.buscarClasificacionID(id_int);
                
            //clas es nulo cuando el id leido no existe en la bd
            if(clas != null){
                nombreClasificacion = clas.getNombre();
                descripcion = clas.getDescripcion();
                indicadorTipoSeleccionado = clas.getIndicadorTipoId();

                if(clas.getEstado() == 1){
                    vigencia = "VIGENTE";
                    listaIndicadorTipo = listaIndicadorTipoVigente;
                }else{
                    vigencia = "NO VIGENTE";
                    listaIndicadorTipo = listaIndicadorTipoNoVigente;
                }
            }            
        
        }      
    }
    
    public void cambiarEstado(){
        
        if(vigencia.equals("VIGENTE")){
            listaIndicadorTipo = listaIndicadorTipoVigente;
        }else{
            listaIndicadorTipo = listaIndicadorTipoNoVigente;
        }    
    } 

    public void editarClasificacion() throws IOException {
        //El clas es nulo cuando damos por parametro una id que no existe o id nulo
        if(clas != null){
            short numVigencia = 0; 
            if(vigencia.equals("VIGENTE")){
                numVigencia = 1;
            }

            //Datos de la clasificacion antes de actualizar
            String nombreAntiguo = clas.getNombre();
            String nombreTipoAntiguo = clas.getTipo();
            Short vigenciaAntiguaSh = clas.getEstado();
            String vigenciaAntigua = (clas.getEstado() == 1)? "VIGENTE" : "NO VIGENTE";
            String descripcionAntigua = clas.getDescripcion();
            
            FacesContext context = FacesContext.getCurrentInstance();

            //Se actualizan los datos de la clasificacion
            clas.setIndicadorTipoId(indicadorTipoSeleccionado);
            clas.setNombre(nombreClasificacion);
            clas.setTipo(indicadorTipoSeleccionado.getNombre());
            clas.setEstado(numVigencia);
            clas.setDescripcion(descripcion);
            clas.setFechaActualizacion(new Date());

            try{
                clasificacionService.actualizarClasificacion(clas);          
                context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN", 
                        "La clasificación " + nombreClasificacion + " ha sido actualizado correctamente")
                );
                
                String mensajeIndicadores = "";
                
                //Si la clasificacion tiene indicadores asociados...
                if(indicadorService.obtenerIndicadorByClasID(clas.getId()) != null){
                    ArrayList<String> afectados = actualizarIndicadores(vigenciaAntiguaSh, numVigencia);                    
                    if(!afectados.isEmpty()){
                        mensajeIndicadores = mensajeIndicadoresAfectados(afectados, numVigencia);
                    }
                }                
                                                               
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                    correoService.enviarMensajeTexto("manueltrigo.at@gmail.com", "Sistema de Indicadores", "Se ha actualizado una clasificación.<br/> "
                            + "<ul>"
                            + "<li>Nombre clasificación: " + nombreAntiguo + " ---> " + clas.getNombre() + ".</li>"
                            + "<li>Tipo: " + nombreTipoAntiguo + " ---> " + clas.getTipo() + ".</li>"
                            + "<li>Estado: " + vigenciaAntigua + " ---> " + vigencia + ".</li>"
                            + "<li>Descripción: " + descripcionAntigua + " ---> " + clas.getDescripcion() + ".</li>"
                            + "<li>Fecha creación: " + formatter.format(clas.getFechaCreacion()) + ".</li>"
                            + "<li>Fecha actualización: " + formatter.format(clas.getFechaActualizacion()) + ".</li>"
                            + "</ul>"
                            + "<br/>"
                            + mensajeIndicadores
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
                        .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-clasificacion.xhtml");     

            }
            catch(EJBException e){

                context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                        "El tipo de indicador " + nombreClasificacion + " ya existe en los registros")
                );            


            }          
        }
    }
    
    private ArrayList<String> actualizarIndicadores(Short vigenciaAntiguaSh, Short numVigencia){
        ArrayList<String> indicadoresAfectados = new ArrayList<String>();
        List<Indicador> indicadores = indicadorService.obtenerIndicadores();
        for(Indicador i : indicadores){

            //Se le esta intentando cambiar la vigencia a la clasificacion por lo tanto seria un indicador
            //afectado
            if(vigenciaAntiguaSh != numVigencia){
                if(i.getClasificacionId().getId() == clas.getId()){
                    i.setEstado(numVigencia);
                    indicadoresAfectados.add(i.getNombreIndicador());
                    indicadorService.actualizarIndicador(i);
                }                                                            
            }                                 

        }                
        
        return indicadoresAfectados;
    }
    
    private String mensajeIndicadoresAfectados(ArrayList<String> indicadoresAfectados, Short numVigencia){        
        String mensaje = "Las siguientes indicadores pertenecientes a " + nombreClasificacion + " cambiaron su estado a ";
        if(numVigencia == 1){
            mensaje += " VIGENTE:";
        }else{
            mensaje += " NO VIGENTE:";
        }
        mensaje += "<ul>";
        for(String s : indicadoresAfectados){
            mensaje += "<li>" + s + "</li>";
        }
        mensaje += "</ul><br/>";
        return mensaje;
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

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Clasificacion getClas() {
        return clas;
    }

    public void setClas(Clasificacion clas) {
        this.clas = clas;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public List<IndicadorTipo> getListaIndicadorTipo() {
        return listaIndicadorTipo;
    }

    public void setListaIndicadorTipo(List<IndicadorTipo> listaIndicadorTipo) {
        this.listaIndicadorTipo = listaIndicadorTipo;
    }

    public List<IndicadorTipo> getListaIndicadorTipoVigente() {
        return listaIndicadorTipoVigente;
    }

    public void setListaIndicadorTipoVigente(List<IndicadorTipo> listaIndicadorTipoVigente) {
        this.listaIndicadorTipoVigente = listaIndicadorTipoVigente;
    }

    public List<IndicadorTipo> getListaIndicadorTipoNoVigente() {
        return listaIndicadorTipoNoVigente;
    }

    public void setListaIndicadorTipoNoVigente(List<IndicadorTipo> listaIndicadorTipoNoVigente) {
        this.listaIndicadorTipoNoVigente = listaIndicadorTipoNoVigente;
    }

    public IndicadorTipo getIndicadorTipoSeleccionado() {
        return indicadorTipoSeleccionado;
    }

    public void setIndicadorTipoSeleccionado(IndicadorTipo indicadorTipoSeleccionado) {
        this.indicadorTipoSeleccionado = indicadorTipoSeleccionado;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    

}
