/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.AjustePdei;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.entities.Plazo;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion;
import com.ua.sistemaindicadores.backend.models.IndicadorLazyDataModel;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.services.IndicadorService;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.Visibility;

/**
 *
 * @author aleja
 */
@Named(value = "verIndicadorBean")
@ViewScoped
public class VerIndicadorBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
 
    @Inject
    private IndicadorLazyDataModel model;    
    @Inject
    private TipoIndicadorService tipoIndicadorService;    
    @Inject
    private ClasificacionService clasificacionService;
    @Inject
    private IndicadorService indicadorService;    
    
    private IndicadorDTO indicadorSeleccionadoDTO;    
    
    private List<IndicadorTipo> listaIndicadorTipo;
    private IndicadorTipo indicadorTipoSeleccionado;    
    
    private List<Clasificacion> listaClasificaciones;
    private Clasificacion clasSeleccionada;
    
    private List<AjustePdei> listaAjustes;
    private AjustePdei ajusteSeleccionado;

    private List<UnidadRepresentacion> listaUnidadR;
    private UnidadRepresentacion unidadRSeleccionada;

    private List<Plazo> listaPlazos;
    private Plazo plazoSeleccionado; 
    
    private List<AnioCumplimiento> listaAnioCumplimiento;
    private AnioCumplimiento anioCumplimientoSeleccionado; 

    private List<FrecuenciaMedicion> listaFrecuencia;
    private FrecuenciaMedicion frecuenciaSeleccionada;    
    
    private List<GeneracionDatos> listaGen;
    private GeneracionDatos GenSeleccionada;   
    
    private List<UnidadProveedora> listaUnidadProveedora;
    private UnidadProveedora unidadProvSeleccionada;       
    
    private Boolean filtros;
    private boolean siguiente;
    private String mensajeFiltros;    
    
    private String numIndicadorSeleccionado;
    private String nombreSeleccionado;
    private String estadoSeleccionado;
    private String descripcionIndicadorSeleccionada;
    private String lineamientoSeleccionado;
    private String objetivoSeleccionado;
    private String descripcionObjetivoSeleccionado;
    private String versionSeleccionada;
    private String lineaBaseSeleccionada;
    private String metaSeleccionada;
    private String porcLogroSeleccionado;
    private String medioVerificacionSeleccionado;
    private String formaCalculoSeleccionado;
    private String fuenteInformacionSeleccionado;
    private String proyectoAsociadoSeleccionado;
    private String comentarioSeleccionado;
    private String actividadSeleccionada;
    private String estadoActividadSeleccionada;
    private String anioCreacionSeleccionado;
    private String anioActualizacionSeleccionado;
    private List<Date> fechaCreacionRango;
    private List<Date> fechaActualizacionRango;
    
    private List<Boolean> visibleColumns;
    private List<String> nameColumns;
    private List<String> selectedColumns;    
    private Integer ultimaColumnaIndex = -1;
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    private Indicador indicadorSeleccionado;
    private String mensajeVerInfoIndicador;

    public List<String> getNameColumns() {
        return nameColumns;
    }

    public void setNameColumns(List<String> nameColumns) {
        this.nameColumns = nameColumns;
    }

    public Integer getUltimaColumnaIndex() {
        return ultimaColumnaIndex;
    }

    public void setUltimaColumnaIndex(Integer ultimaColumnaIndex) {
        this.ultimaColumnaIndex = ultimaColumnaIndex;
    }


    
    
    
    
    @PostConstruct
    public void initalize(){        
        System.out.println("Inicio Bean Ver Indicador");     
        initColumnProperties();
        listaIndicadorTipo = tipoIndicadorService.obtenerIndicadorTipos();
        listaClasificaciones = clasificacionService.obtenerClasificaciones();
        listaAjustes = indicadorService.obtenerAjustePdei();
        listaUnidadR = indicadorService.obtenerUnidadRepresentacion();
        listaPlazos = indicadorService.obtenerPlazo();
        listaAnioCumplimiento = indicadorService.obtenerAnioCumplimiento();
        listaFrecuencia = indicadorService.obtenerFrecuenciaMedicion();
        listaGen = indicadorService.obtenerGeneracionDatos();
        listaUnidadProveedora = indicadorService.obtenerUnidadProveedora();
        filtros = true;
        mensajeFiltros = "Mostrar filtros";        
    }
    
    public VerIndicadorBean() {
    }
    
    private void initColumnProperties(){
        visibleColumns = Arrays.asList(
                true, //Tipo de indicador
                true, //Clasificación
                true, //Estado
                false,//Descripción indicador
                false,//Aplica lineamiento
                false,//Aplica objetivo
                false,//Descripcion objetivo 
                false,//Ajuste PDEI 
                false,//Unidad de representacion 
                false,//Generacion de datos                 
                false,//Plazo 
                false,//Version 
                false,//Linea base 
                false,//Metas 
                false,//Anio Cumplimiento 
                true, //% logro 
                true, //Frecuencia medicion
                false,//Medio verificacion
                false,//Forma de calculo
                false,//Fuente de informacion 
                false,//Unidad proveedora                   
                false,//Proyecto asociado 
                false,//Comentario 
                false,//Actividad comprometida 
                false,//Estado actividad               
                false,//Fecha de creacion 
                false);//Fecha de actualizacion      
        
        nameColumns = Arrays.asList(
                "Tipo de indicador",
                "Clasificación",
                "Estado",
                "Descripción indicador",
                "Aplica lineamiento",
                "Aplica objetivo",
                "Descripción objetivo", 
                "Ajuste PDEI", 
                "Unidad de representación", 
                "Generación de datos",                 
                "Plazo", 
                "Versión", 
                "Linea base", 
                "Metas", 
                "Año Cumplimiento", 
                "% Logro", 
                "Frecuencia medición",
                "Medio verificación",
                "Forma de cálculo",
                "Fuente de información", 
                "Unidad proveedora",                   
                "Proyecto asociado", 
                "Comentario", 
                "Actividad comprometida", 
                "Estado actividad",               
                "Fecha de creacion", 
                "Fecha de actualizacion");          
        
        selectedColumns = new LinkedList<String>(Arrays.asList(
                "Tipo de indicador",
                "Clasificación",
                "Estado",
                "% Logro", 
                "Frecuencia medición"                
        ));
            
    }
    
    
    public void eventofiltros() {
        if (filtros) {            
            mensajeFiltros = "Ocultar filtros";
            filtros = false;
        } else {
            limpiarFiltros();
            mensajeFiltros = "Mostrar filtros";
            filtros = true;
        }
    }
    
    public void limpiarFiltros() {
        try {           
           indicadorTipoSeleccionado = null;
           clasSeleccionada = null;
           ajusteSeleccionado = null;
           unidadRSeleccionada = null;
           plazoSeleccionado = null;
           anioCumplimientoSeleccionado = null;
           frecuenciaSeleccionada = null;    
           GenSeleccionada = null;
           unidadProvSeleccionada = null;           
           numIndicadorSeleccionado = null;
           nombreSeleccionado = null;
           estadoSeleccionado = null;
           descripcionIndicadorSeleccionada = null;
           lineamientoSeleccionado = null;
           objetivoSeleccionado = null;
           descripcionObjetivoSeleccionado = null;
           versionSeleccionada = null;
           lineaBaseSeleccionada = null;
           metaSeleccionada = null;
           porcLogroSeleccionado = null;
           medioVerificacionSeleccionado = null;
           formaCalculoSeleccionado = null;
           fuenteInformacionSeleccionado = null;
           proyectoAsociadoSeleccionado = null;
           comentarioSeleccionado = null;
           actividadSeleccionada = null;
           estadoActividadSeleccionada = null;      
           anioCreacionSeleccionado = null;
           anioActualizacionSeleccionado = null;
           fechaCreacionRango = null;
           fechaActualizacionRango = null;
           onSeleccionTipoIndicadorListener();
           onSeleccionClasificacionListener();
           onSeleccionAjustePdeiListener();
           onSeleccionUnidadRepresentacionListener();
           onSeleccionPlazoListener();
           onSeleccionAnioCumplimientoListener();
           onSeleccionFrecuenciaMedicionListener();  
           onSeleccionGenDatosListener();
           onSeleccionUnidadProveedoraListener();
           onSeleccionNumIndicadorListener();
           onSeleccionNombreListener();
           onSeleccionEstadoListener();
           onSeleccionDescripcionIndicadorListener();
           onSeleccionLineamientoListener();
           onSeleccionObjetivoListener();
           onSeleccionDescripcionObjetivoListener();
           onSeleccionVersionListener();
           onSeleccionLineaBaseListener();
           onSeleccionMetaListener();
           onSeleccionPorcLogroListener();
           onSeleccionMedioVerificacionListener();
           onSeleccionFormaCalculoListener();
           onSeleccionFuenteInformacionListener();
           onSeleccionProyectoAsociadoListener();
           onSeleccionComentarioListener();
           onSeleccionActividadListener();
           onSeleccionEstadoActividadListener();
           onSeleccionAnioCreacionListener();
           onSeleccionAnioActualizacionListener();
           onSeleccionFechaCreacionListener();
           onSeleccionFechaActualizacionListener();
            
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema al borrar los filtros. Contacte al administrador.")
                    );
        }
    }        
    
    public String flujoProceso(FlowEvent event) {
        if (siguiente) {
            siguiente = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }    
        
    public void toggleProceso(ToggleEvent e){
        Integer indexElemento = (Integer) e.getData() - 2;

        if(selectedColumns.contains(nameColumns.get(indexElemento))){
            selectedColumns.remove(nameColumns.get(indexElemento));
        }else{
            selectedColumns.add(nameColumns.get(indexElemento));
        }        
                            
        if(selectedColumns.size() == 7){   
            selectedColumns.remove(nameColumns.get(indexElemento));
            PrimeFaces.current().executeScript("handleCheckBox("+ indexElemento +");");
            FacesContext.getCurrentInstance().addMessage("mensaje", 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN", 
                    "Sólo se pueden escoger 6 opciones para desplegar")
            );  
        }else{
            visibleColumns.set(indexElemento, e.getVisibility() == Visibility.VISIBLE);  
        }                        
    }
           
    public void desplegarIndicador() {
        //Si el tipoSeleccionado es nulo (ya se porque la página carga por primera vez o porque se borran los filtros)
        //capturara el parametro de la url, si no es nulo significa que ya cargo el parametro tipo y el filtro tipo
        //fue fijado   
        if(indicadorSeleccionado == null){
            Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            try{
                indicadorSeleccionado = indicadorService.buscarIndicadorID(Integer.valueOf(params.get("i")));
            }catch(NumberFormatException e){
                indicadorSeleccionado = null;
            }
            if(indicadorSeleccionado != null){
                mensajeVerInfoIndicador = "Información completa de indicador " + indicadorSeleccionado.getNombreIndicador();                        
            }else{
                mensajeVerInfoIndicador = "No hay datos sobre este indicador";                                        
            }
        }
    }          
    
    public String getUnidadesProveedoras(Integer indicadorId){
        Indicador indicador = indicadorService.buscarIndicadorID(indicadorId);
        Collection<UnidadProveedora> listaUP = indicador.getUnidadProveedoraCollection();
        String unidadesProveedoras = "Sin datos";
        
        if(listaUP != null){
            if(listaUP.size() != 0){
                unidadesProveedoras = "";
                for(UnidadProveedora up : listaUP){
                    unidadesProveedoras += up.getUnidadProveedora() + ",\n";
                }
                unidadesProveedoras = unidadesProveedoras.substring(0, unidadesProveedoras.length() - 2);  
            }
        }
        return unidadesProveedoras;
    }    
    
    public String getGeneracionesDatos(Integer indicadorId){
        Indicador indicador = indicadorService.buscarIndicadorID(indicadorId);
        Collection<GeneracionDatos> listaGD = indicador.getGeneracionDatosCollection();
        String generacionDatos = "Sin datos";
        
        if(listaGD != null){
            if(listaGD.size() != 0){
                generacionDatos = "";
                for(GeneracionDatos gd : listaGD){
                    generacionDatos += gd.getGeneracionDatos() + ",\n";
                }
                generacionDatos = generacionDatos.substring(0, generacionDatos.length() - 2);  
            }
        }
        return generacionDatos;
    }        
       
    public Boolean getFiltros() {
        return filtros;
    }

    public void setFiltros(Boolean filtros) {
        this.filtros = filtros;
    }

    public String getMensajeFiltros() {
        return mensajeFiltros;
    }

    public void setMensajeFiltros(String mensajeFiltros) {
        this.mensajeFiltros = mensajeFiltros;
    }

    public boolean getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(boolean siguiente) {
        this.siguiente = siguiente;
    }
   
    public IndicadorDTO getIndicadorSeleccionadoDTO() {
        return indicadorSeleccionadoDTO;
    }

    public void setIndicadorSeleccionadoDTO(IndicadorDTO indicadorSeleccionadoDTO) {
        this.indicadorSeleccionadoDTO = indicadorSeleccionadoDTO;
    }

    public IndicadorLazyDataModel getModel() {
        return model;
    }

    public void setModel(IndicadorLazyDataModel model) {
        this.model = model;
    }

    public ClasificacionService getClasificacionService() {
        return clasificacionService;
    }

    public void setClasificacionService(ClasificacionService clasificacionService) {
        this.clasificacionService = clasificacionService;
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
    
    public List<Clasificacion> getListaClasificaciones() {
        return listaClasificaciones;
    }

    public void setListaClasificaciones(List<Clasificacion> listaClasificaciones) {
        this.listaClasificaciones = listaClasificaciones;
    }

    public Clasificacion getClasSeleccionada() {
        return clasSeleccionada;
    }

    public void setClasSeleccionada(Clasificacion clasSeleccionada) {
        this.clasSeleccionada = clasSeleccionada;
    }        

    public List<AjustePdei> getListaAjustes() {
        return listaAjustes;
    }

    public void setListaAjustes(List<AjustePdei> listaAjustes) {
        this.listaAjustes = listaAjustes;
    }

    public AjustePdei getAjusteSeleccionado() {
        return ajusteSeleccionado;
    }

    public void setAjusteSeleccionado(AjustePdei ajusteSeleccionado) {
        this.ajusteSeleccionado = ajusteSeleccionado;
    }

    public List<UnidadRepresentacion> getListaUnidadR() {
        return listaUnidadR;
    }

    public void setListaUnidadR(List<UnidadRepresentacion> listaUnidadR) {
        this.listaUnidadR = listaUnidadR;
    }

    public UnidadRepresentacion getUnidadRSeleccionada() {
        return unidadRSeleccionada;
    }

    public void setUnidadRSeleccionada(UnidadRepresentacion unidadRSeleccionada) {
        this.unidadRSeleccionada = unidadRSeleccionada;
    }

    public List<Plazo> getListaPlazos() {
        return listaPlazos;
    }

    public void setListaPlazos(List<Plazo> listaPlazos) {
        this.listaPlazos = listaPlazos;
    }

    public Plazo getPlazoSeleccionado() {
        return plazoSeleccionado;
    }

    public void setPlazoSeleccionado(Plazo plazoSeleccionado) {
        this.plazoSeleccionado = plazoSeleccionado;
    }

    public List<AnioCumplimiento> getListaAnioCumplimiento() {
        return listaAnioCumplimiento;
    }

    public void setListaAnioCumplimiento(List<AnioCumplimiento> listaAnioCumplimiento) {
        this.listaAnioCumplimiento = listaAnioCumplimiento;
    }

    public AnioCumplimiento getAnioCumplimientoSeleccionado() {
        return anioCumplimientoSeleccionado;
    }

    public void setAnioCumplimientoSeleccionado(AnioCumplimiento anioCumplimientoSeleccionado) {
        this.anioCumplimientoSeleccionado = anioCumplimientoSeleccionado;
    }

    public List<FrecuenciaMedicion> getListaFrecuencia() {
        return listaFrecuencia;
    }

    public void setListaFrecuencia(List<FrecuenciaMedicion> listaFrecuencia) {
        this.listaFrecuencia = listaFrecuencia;
    }

    public FrecuenciaMedicion getFrecuenciaSeleccionada() {
        return frecuenciaSeleccionada;
    }

    public void setFrecuenciaSeleccionada(FrecuenciaMedicion frecuenciaSeleccionada) {
        this.frecuenciaSeleccionada = frecuenciaSeleccionada;
    }            

    public List<GeneracionDatos> getListaGen() {
        return listaGen;
    }

    public void setListaGen(List<GeneracionDatos> listaGen) {
        this.listaGen = listaGen;
    }

    public List<UnidadProveedora> getListaUnidadProveedora() {
        return listaUnidadProveedora;
    }

    public void setListaUnidadProveedora(List<UnidadProveedora> listaUnidadProveedora) {
        this.listaUnidadProveedora = listaUnidadProveedora;
    }

    public GeneracionDatos getGenSeleccionada() {
        return GenSeleccionada;
    }

    public void setGenSeleccionada(GeneracionDatos GenSeleccionada) {
        this.GenSeleccionada = GenSeleccionada;
    }           
    
    public String getNumIndicadorSeleccionado() {
        return numIndicadorSeleccionado;
    }

    public void setNumIndicadorSeleccionado(String numIndicadorSeleccionado) {
        this.numIndicadorSeleccionado = numIndicadorSeleccionado;
    }

    public String getNombreSeleccionado() {
        return nombreSeleccionado;
    }

    public void setNombreSeleccionado(String nombreSeleccionado) {
        this.nombreSeleccionado = nombreSeleccionado;
    }
               
    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public String getDescripcionIndicadorSeleccionada() {
        return descripcionIndicadorSeleccionada;
    }

    public void setDescripcionIndicadorSeleccionada(String descripcionIndicadorSeleccionada) {
        this.descripcionIndicadorSeleccionada = descripcionIndicadorSeleccionada;
    }

    public String getLineamientoSeleccionado() {
        return lineamientoSeleccionado;
    }

    public void setLineamientoSeleccionado(String lineamientoSeleccionado) {
        this.lineamientoSeleccionado = lineamientoSeleccionado;
    }

    public String getObjetivoSeleccionado() {
        return objetivoSeleccionado;
    }

    public void setObjetivoSeleccionado(String objetivoSeleccionado) {
        this.objetivoSeleccionado = objetivoSeleccionado;
    }

    public String getDescripcionObjetivoSeleccionado() {
        return descripcionObjetivoSeleccionado;
    }

    public void setDescripcionObjetivoSeleccionado(String descripcionObjetivoSeleccionado) {
        this.descripcionObjetivoSeleccionado = descripcionObjetivoSeleccionado;
    }                         

    public String getVersionSeleccionada() {
        return versionSeleccionada;
    }

    public void setVersionSeleccionada(String versionSeleccionada) {
        this.versionSeleccionada = versionSeleccionada;
    }

    public String getLineaBaseSeleccionada() {
        return lineaBaseSeleccionada;
    }

    public void setLineaBaseSeleccionada(String lineaBaseSeleccionada) {
        this.lineaBaseSeleccionada = lineaBaseSeleccionada;
    }

    public String getMetaSeleccionada() {
        return metaSeleccionada;
    }

    public void setMetaSeleccionada(String metaSeleccionada) {
        this.metaSeleccionada = metaSeleccionada;
    }

    public String getPorcLogroSeleccionado() {
        return porcLogroSeleccionado;
    }

    public void setPorcLogroSeleccionado(String porcLogroSeleccionado) {
        this.porcLogroSeleccionado = porcLogroSeleccionado;
    }

    public String getMedioVerificacionSeleccionado() {
        return medioVerificacionSeleccionado;
    }

    public void setMedioVerificacionSeleccionado(String medioVerificacionSeleccionado) {
        this.medioVerificacionSeleccionado = medioVerificacionSeleccionado;
    }

    public String getFormaCalculoSeleccionado() {
        return formaCalculoSeleccionado;
    }

    public void setFormaCalculoSeleccionado(String formaCalculoSeleccionado) {
        this.formaCalculoSeleccionado = formaCalculoSeleccionado;
    }

    public String getFuenteInformacionSeleccionado() {
        return fuenteInformacionSeleccionado;
    }

    public void setFuenteInformacionSeleccionado(String fuenteInformacionSeleccionado) {
        this.fuenteInformacionSeleccionado = fuenteInformacionSeleccionado;
    }

    public String getProyectoAsociadoSeleccionado() {
        return proyectoAsociadoSeleccionado;
    }

    public void setProyectoAsociadoSeleccionado(String proyectoAsociadoSeleccionado) {
        this.proyectoAsociadoSeleccionado = proyectoAsociadoSeleccionado;
    }

    public String getComentarioSeleccionado() {
        return comentarioSeleccionado;
    }

    public void setComentarioSeleccionado(String comentarioSeleccionado) {
        this.comentarioSeleccionado = comentarioSeleccionado;
    }

    public UnidadProveedora getUnidadProvSeleccionada() {
        return unidadProvSeleccionada;
    }

    public void setUnidadProvSeleccionada(UnidadProveedora unidadProvSeleccionada) {
        this.unidadProvSeleccionada = unidadProvSeleccionada;
    }
       
    public String getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(String actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    public String getEstadoActividadSeleccionada() {
        return estadoActividadSeleccionada;
    }

    public void setEstadoActividadSeleccionada(String estadoActividadSeleccionada) {
        this.estadoActividadSeleccionada = estadoActividadSeleccionada;
    }

    public String getAnioCreacionSeleccionado() {
        return anioCreacionSeleccionado;
    }

    public void setAnioCreacionSeleccionado(String anioCreacionSeleccionado) {
        this.anioCreacionSeleccionado = anioCreacionSeleccionado;
    }

    public String getAnioActualizacionSeleccionado() {
        return anioActualizacionSeleccionado;
    }

    public void setAnioActualizacionSeleccionado(String anioActualizacionSeleccionado) {
        this.anioActualizacionSeleccionado = anioActualizacionSeleccionado;
    }        

    public List<Date> getFechaCreacionRango() {
        return fechaCreacionRango;
    }

    public void setFechaCreacionRango(List<Date> fechaCreacionRango) {
        this.fechaCreacionRango = fechaCreacionRango;
    }

    public List<Date> getFechaActualizacionRango() {
        return fechaActualizacionRango;
    }

    public void setFechaActualizacionRango(List<Date> fechaActualizacionRango) {
        this.fechaActualizacionRango = fechaActualizacionRango;
    }       

    public List<Boolean> getVisibleColumns() {
        return visibleColumns;
    }

    public void setVisibleColumns(List<Boolean> visibleColumns) {
        this.visibleColumns = visibleColumns;
    }

    public List<String> getSelectedColumns() {
        return selectedColumns;
    }

    public void setSelectedColumns(List<String> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }
               
    //Filtros
    
    public void onSeleccionTipoIndicadorListener(){
        try {
            if (indicadorTipoSeleccionado != null) {
                model.setIndicadorTipo(indicadorTipoSeleccionado);
                listaClasificaciones = new ArrayList<>(indicadorTipoSeleccionado.getClasificacionCollection());
            } else {
                model.setIndicadorTipo(null);
                listaClasificaciones = clasificacionService.obtenerClasificaciones();
            }
            clasSeleccionada = null;
            onSeleccionClasificacionListener();
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro tipo indicador. Contacte al administrador.")
                    );
        }    
    }    
    
    public void onSeleccionClasificacionListener(){
        try {
            if (clasSeleccionada != null) {
                model.setClasificacionId(clasSeleccionada);
            } else {
                model.setClasificacionId(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro clasificacion. Contacte al administrador.")
                    );
        }    
    }

    public void onSeleccionAjustePdeiListener(){
        try {
            if (ajusteSeleccionado != null) {
                model.setAjustePdeiId(ajusteSeleccionado);
            } else {
                model.setAjustePdeiId(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro ajuste pdei. Contacte al administrador.")
                    );
        }    
    }
    
    public void onSeleccionUnidadRepresentacionListener(){
        try {
            if (unidadRSeleccionada != null) {
                model.setUnidadRepresentacionId(unidadRSeleccionada);
            } else {
                model.setUnidadRepresentacionId(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro unidad representación. Contacte al administrador.")
                    );
        }    
    }    
    
    public void onSeleccionPlazoListener(){
        try {
            if (plazoSeleccionado != null) {
                model.setPlazoId(plazoSeleccionado);
            } else {
                model.setPlazoId(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro plazo. Contacte al administrador.")
                    );
        }    
    }     
    
    public void onSeleccionAnioCumplimientoListener(){
        try {
            if (anioCumplimientoSeleccionado != null) {
                model.setAnioCumplimientoId(anioCumplimientoSeleccionado);
            } else {
                model.setAnioCumplimientoId(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro año cumplimiento. Contacte al administrador.")
                    );
        }    
    }         
    
    public void onSeleccionFrecuenciaMedicionListener(){
        try {
            if (frecuenciaSeleccionada != null) {
                model.setFrecuenciaMedicionId(frecuenciaSeleccionada);
            } else {
                model.setFrecuenciaMedicionId(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro frecuencia medicion. Contacte al administrador.")
                    );
        }    
    }      
    
    public void onSeleccionUnidadProveedoraListener(){
        try {
            if (unidadProvSeleccionada != null) {    
                model.setUnidadProveedora(unidadProvSeleccionada);
            } else {
                model.setUnidadProveedora(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro unidades proveedoras. Contacte al administrador.")
                    );
        }    
    }        
    
    public void onSeleccionGenDatosListener(){
        try {
            if (GenSeleccionada != null) {    
                model.setGeneracionDatosId(GenSeleccionada);
            } else {
                model.setGeneracionDatosId(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro generación datos. Contacte al administrador.")
                    );
        }    
    }       
    
    public void onSeleccionNumIndicadorListener(){        
        try {
            if (numIndicadorSeleccionado != null) {
                model.setNumIndicador(Integer.valueOf(numIndicadorSeleccionado));
            } else {
                model.setNumIndicador(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro numero de indicador. Contacte al administrador.")
                    );
        } 
        catch (NumberFormatException ex){
            model.setNumIndicador(null);
        }
    }    
    
    public void onSeleccionNombreListener() {        
        try {
            if (nombreSeleccionado != null) {
                model.setNombreIndicador(nombreSeleccionado);
            } else {
                model.setNombreIndicador(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro nombre de indicador. Contacte al administrador.")
                    );
        }
    }            
    
    public void onSeleccionEstadoListener() {        
        try {
            if (estadoSeleccionado != null) {
                model.setEstado(Short.valueOf(estadoSeleccionado));
            } else {
                model.setEstado(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro estado. Contacte al administrador.")
                    );
        }
    }        
    
    public void onSeleccionDescripcionIndicadorListener() {        
        try {
            if (descripcionIndicadorSeleccionada != null) {
                model.setDescripcionIndicador(descripcionIndicadorSeleccionada);
            } else {
                model.setDescripcionIndicador(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro descripción de indicador. Contacte al administrador.")
                    );
        }
    }         
    
    public void onSeleccionLineamientoListener() {        
        try {
            if (lineamientoSeleccionado != null) {
                model.setAplicaLineamiento(lineamientoSeleccionado);
            } else {
                model.setAplicaLineamiento(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro aplica lineamiento. Contacte al administrador.")
                    );
        }
    }         
                   
    public void onSeleccionObjetivoListener() {        
        try {
            if (objetivoSeleccionado != null) {
                model.setAplicaObjetivo(objetivoSeleccionado);
            } else {
                model.setAplicaObjetivo(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro aplica objetivo. Contacte al administrador.")
                    );
        }
    }            
                    
    public void onSeleccionDescripcionObjetivoListener() {        
        try {
            if (descripcionObjetivoSeleccionado != null) {
                model.setDescripcionObjetivo(descripcionObjetivoSeleccionado);
            } else {
                model.setDescripcionObjetivo(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro descripción objetivo. Contacte al administrador.")
                    );
        }
    }                        
                    
    public void onSeleccionVersionListener() {        
        try {
            if (versionSeleccionada != null) {
                model.setVersion(versionSeleccionada);
            } else {
                model.setVersion(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro versión. Contacte al administrador.")
                    );
        }
    }                
        
    public void onSeleccionLineaBaseListener() {        
        try {
            if (lineaBaseSeleccionada != null) {
                model.setLineaBase(lineaBaseSeleccionada);
            } else {
                model.setLineaBase(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro linea base. Contacte al administrador.")
                    );
        }
    }               
        
    public void onSeleccionMetaListener() {        
        try {
            if (metaSeleccionada != null) {
                model.setMetas(metaSeleccionada);
            } else {
                model.setMetas(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro metas. Contacte al administrador.")
                    );
        }
    }       
     
    public void onSeleccionPorcLogroListener() {        
        try {
            if (porcLogroSeleccionado != null) {
                model.setPorcLogro(porcLogroSeleccionado);
            } else {
                model.setPorcLogro(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro porcentaje logro. Contacte al administrador.")
                    );
        }
    }               
        
    public void onSeleccionMedioVerificacionListener() {        
         try {
             if (medioVerificacionSeleccionado != null) {
                 model.setMedioVerificacion(medioVerificacionSeleccionado);
             } else {
                 model.setMedioVerificacion(null);
             }
         } catch (EJBException ex) {
             Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance()
                     .addMessage(
                             "mensaje",
                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro medio verificación. Contacte al administrador.")
                     );
         }
    }              
      
    public void onSeleccionFormaCalculoListener() {        
         try {
             if (formaCalculoSeleccionado != null) {
                 model.setFormaCalculo(formaCalculoSeleccionado);
             } else {
                 model.setFormaCalculo(null);
             }
         } catch (EJBException ex) {
             Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance()
                     .addMessage(
                             "mensaje",
                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro forma cálculo. Contacte al administrador.")
                     );
         }
    }
        
    public void onSeleccionFuenteInformacionListener() {        
         try {
             if (fuenteInformacionSeleccionado != null) {
                 model.setFuenteInformacion(fuenteInformacionSeleccionado);
             } else {
                 model.setFuenteInformacion(null);
             }
         } catch (EJBException ex) {
             Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance()
                     .addMessage(
                             "mensaje",
                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro fuente información. Contacte al administrador.")
                     );
         }
    }            
        
    public void onSeleccionProyectoAsociadoListener() {        
         try {
             if (proyectoAsociadoSeleccionado != null) {
                 model.setProyectoAsociado(proyectoAsociadoSeleccionado);
             } else {
                 model.setProyectoAsociado(null);
             }
         } catch (EJBException ex) {
             Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance()
                     .addMessage(
                             "mensaje",
                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro proyecto asociado. Contacte al administrador.")
                     );
         }
    }            
        
    public void onSeleccionComentarioListener() {        
         try {
             if (comentarioSeleccionado != null) {
                 model.setComentario(comentarioSeleccionado);
             } else {
                 model.setComentario(null);
             }
         } catch (EJBException ex) {
             Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance()
                     .addMessage(
                             "mensaje",
                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro comentario. Contacte al administrador.")
                     );
         }
    }               
        
    public void onSeleccionActividadListener() {        
         try {
             if (actividadSeleccionada != null) {
                 model.setActividadComprometida(actividadSeleccionada);
             } else {
                 model.setActividadComprometida(null);
             }
         } catch (EJBException ex) {
             Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance()
                     .addMessage(
                             "mensaje",
                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro actividad comprometida. Contacte al administrador.")
                     );
         }
    }                   
        
    public void onSeleccionEstadoActividadListener() {        
         try {
             if (estadoActividadSeleccionada != null) {
                 model.setEstadoActividad(estadoActividadSeleccionada);
             } else {
                 model.setEstadoActividad(null);
             }
         } catch (EJBException ex) {
             Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
             FacesContext.getCurrentInstance()
                     .addMessage(
                             "mensaje",
                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro estado actividad. Contacte al administrador.")
                     );
         }
    }  
        
    public void onSeleccionAnioCreacionListener() {        
        try {
            if (anioCreacionSeleccionado != null) {
                model.setFechaCreacion(formatter.parse("01-01-" + anioCreacionSeleccionado));
            } else {
                model.setFechaCreacion(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro fecha creacion. Contacte al administrador.")
                    );
        } catch (ParseException ex){
            model.setFechaCreacion(null);
        }
    }      
             
    public void onSeleccionAnioActualizacionListener() {        
        try {
            if (anioActualizacionSeleccionado != null) {
                model.setFechaActualizacion(formatter.parse("01-01-" + anioActualizacionSeleccionado));
            } else {
                model.setFechaActualizacion(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro fecha actualización. Contacte al administrador.")
                    );
        } catch (ParseException ex){
            model.setFechaActualizacion(null);
        }
    }          
    
    public void onSeleccionFechaCreacionListener() {     
        try {
            if (fechaCreacionRango != null) {
                if (fechaCreacionRango.size() == 2) {
                    model.setFechaCreacionRange(fechaCreacionRango);
                }             
                else {
                    model.setFechaCreacionRange(null);
                }                
            } else {
               model.setFechaCreacionRange(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro fecha creación rango. Contacte al administrador.")
                    );
        } 
    }           
    
    public void onSeleccionFechaActualizacionListener() {     
        try {
            if (fechaActualizacionRango != null) {
                if (fechaActualizacionRango.size() == 2) {
                    model.setFechaActualizacionRange(fechaActualizacionRango);
                }             
                else {
                    model.setFechaActualizacionRange(null);
                }                
            } else {
               model.setFechaActualizacionRange(null);
            }
        } catch (EJBException ex) {
            Logger.getLogger(Indicador.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance()
                    .addMessage(
                            "mensaje",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Problema con el filtro fecha actualización rango. Contacte al administrador.")
                    );
        }         
    }     
    
    //Metodos que se utilizan en la vista desplegar la info de un indicador

    public Indicador getIndicadorSeleccionado() {
        return indicadorSeleccionado;
    }

    public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
        this.indicadorSeleccionado = indicadorSeleccionado;
    }

    public String getMensajeVerInfoIndicador() {
        return mensajeVerInfoIndicador;
    }

    public void setMensajeVerInfoIndicador(String mensajeVerInfoIndicador) {
        this.mensajeVerInfoIndicador = mensajeVerInfoIndicador;
    }
      
}
