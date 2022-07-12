/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.services.IndicadorService;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import com.ua.sistemaindicadores.backend.entities.AjustePdei;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.Plazo;
import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion;
import com.ua.sistemaindicadores.backend.exceptions.NotificacionCorreoException;
import com.ua.sistemaindicadores.backend.flags.Flag;
import com.ua.sistemaindicadores.backend.flags.FlagImpl;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.services.CorreoService;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import com.ua.sistemaindicadores.frontend.classes.Actividad;
import com.ua.sistemaindicadores.frontend.classes.ActividadAnio;
import com.ua.sistemaindicadores.frontend.classes.TreeNodeMesSemestre;
import com.ua.sistemaindicadores.frontend.classes.TreeNodeRow;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.treetable.TreeTable;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author aleja
 */
@Named(value = "crearIndicadorBean")
@ViewScoped
public class CrearIndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String direccionSI = "http://localhost:8080/SistemaIndicadores-1.0-SNAPSHOT/faces/inicio/inicio.xhtml";

    @Inject
    transient private ClasificacionService clasificacionService;
    @Inject
    transient private TipoIndicadorService tipoIndicadorService;
    @Inject
    transient private IndicadorService indicadorService;
    @Inject
    transient private CorreoService correoService;   

    private boolean siguiente;

    //
    private String vigencia;
    private String n_indicador;
    private String nombreIndicador;
    private String descripcionIndicador;
    private String aplicaLineamiento;
    private String aplicaObjetivo;
    private String descripcionObjetivo;
    private AjustePdei ajustePDEI;
    private UnidadRepresentacion unidadRepresentacion;
    private GeneracionDatos[] generacionDatos;
    private Plazo plazo;
    private String version;
    private String lineaBase;
    private String metas;
    private AnioCumplimiento anioCumplimiento;
    private String logro;
    private FrecuenciaMedicion frecuenciaMedicion;
    private String medioVerificacion;
    private String formaCalculo;
    private String fuenteInformacion;
    private UnidadProveedora[] unidadProveedora;
    private String proyectoAsociado;
    private String comentario;
    private String actividadComprometida;
    private String estadoActividad;

    private FlagImpl flagImpl;
    private Flag flagsTipoIndicador;

    //SELECTS
    private Collection<Clasificacion> listaClasificacion;
    private List<IndicadorTipo> listaIndicadorTipo;
    private List<IndicadorTipo> listaIndicadorTipoVigente;
    private List<IndicadorTipo> listaIndicadorTipoNoVigente;
    private List<UnidadProveedora> listaUnidadProveedora;
    private List<GeneracionDatos> listaGeneracionDatos;
    private List<AjustePdei> listaAjustePdei;
    private List<AnioCumplimiento> listaAnioCumplimiento;
    private List<Plazo> listaPlazo;
    private List<FrecuenciaMedicion> listaFrecuenciaMedicion;
    private List<UnidadRepresentacion> listaUnidadRepresentacion;

    private Integer min = 2020;
    private Integer max = 2030;
    private Integer minValue = 2020;
    private Integer maxValue = 2030;
    
    private Map<Integer, ActividadAnio> listaActividades;    
    private Map<Integer, TreeNodeMesSemestre> listaActividadesMesSemestre;
    
    private boolean mantenerLogros;    
    private boolean mantenerLogrosNewStep;    
    
    private IndicadorTipo indicadorTipoSeleccionado;
    private Clasificacion clasificacionSeleccionada;
    private Indicador nuevoIndicador;

    @PostConstruct
    public void initalize() {
        System.out.println("Inicio Bean Crear Indicador");
        flagImpl = new FlagImpl();
        listaIndicadorTipoVigente = tipoIndicadorService.obtenerTiposIndicadoresByEstado(Short.valueOf("1"));
        listaIndicadorTipoNoVigente = tipoIndicadorService.obtenerTiposIndicadoresByEstado(Short.valueOf("0"));
        listaIndicadorTipo = listaIndicadorTipoVigente;
        if (listaIndicadorTipo != null) {
            indicadorTipoSeleccionado = listaIndicadorTipo.get(0);
            flagsTipoIndicador = flagImpl.getFlagsTipoIndicador(indicadorTipoSeleccionado.getNombre());
            listaClasificacion = indicadorTipoSeleccionado.getClasificacionCollection();
            clasificacionSeleccionada = listaClasificacion.iterator().next();

        }
        // Se obtienen los select
        listaUnidadProveedora = indicadorService.obtenerUnidadProveedora();
        listaGeneracionDatos = indicadorService.obtenerGeneracionDatos();
        listaAjustePdei = indicadorService.obtenerAjustePdei();
        listaAnioCumplimiento = indicadorService.obtenerAnioCumplimiento();
        listaPlazo = indicadorService.obtenerPlazo();
        listaFrecuenciaMedicion = indicadorService.obtenerFrecuenciaMedicion();
        listaUnidadRepresentacion = indicadorService.obtenerUnidadRepresentacion();
        
        listaActividades = new LinkedHashMap<Integer, ActividadAnio>();        
        listaActividadesMesSemestre = new LinkedHashMap<Integer, TreeNodeMesSemestre>();
        mantenerLogros = false;        
        mantenerLogrosNewStep = false;        
        
        for(int i=minValue; i<=maxValue; i++){
            listaActividadesMesSemestre.put(i, new TreeNodeMesSemestre(Arrays.asList(
                    "Semestre 1",
                    "Semestre 2"             
            ), ""));                
        }        
        
    }

    /**
     * Creates a new instance of convenioBean
     */
    public CrearIndicadorBean() {
    }
    
    public void limpiarListas(){
        if(!mantenerLogrosNewStep){
            logro = null;
            listaActividades.clear();
            listaActividadesMesSemestre.clear();    
            mantenerLogros = false;        
        }
        mantenerLogrosNewStep = false;
    }
    
    //Cuando cambiemos la meta o la unidad de representacion se limpiaran los datos
    public void cambioMetasURepr(){
        mantenerLogrosNewStep = false;
        limpiarListas();
    }
    
    public void createList(){
        
        if(anioCumplimiento == null){
            FacesContext.getCurrentInstance().addMessage("mensaje", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                    "Debe escoger un año de cumplimiento primero."));        
            return;
        }
        if(metas == null){
            FacesContext.getCurrentInstance().addMessage("mensaje", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                    "Debe escoger una meta primero."));                  
            return;
        }
        if(Integer.valueOf(metas) == 0){
            FacesContext.getCurrentInstance().addMessage("mensaje", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                    "La cantidad de metas escogidas es 0."));                  
            return;                   
        }
 
        if(maxValue != anioCumplimiento.getAnioCumplimiento()){
            FacesContext.getCurrentInstance().addMessage("mensaje", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                    "El año de término de evaluación no puede ser superior al año de cumplimiento del indicador."));                  
            return;
        }
                        
        if(!mantenerLogros){
            switch(frecuenciaMedicion.getFrecuenciaMedicion()){
                case "Mensual":
                    for(int i=minValue; i<=maxValue; i++){
                        listaActividadesMesSemestre.put(i, new TreeNodeMesSemestre(Arrays.asList(
                                "Enero",
                                "Febrero",
                                "Marzo",
                                "Abril",
                                "Mayo",
                                "Junio",
                                "Julio",
                                "Agosto",
                                "Septiembre",
                                "Octubre",
                                "Noviembre",
                                "Diciembre"                    
                        ), unidadRepresentacion.getUnidadRepresentacion()));
                    }                         
                    break;
                case "Semestral":
                    for(int i=minValue; i<=maxValue; i++){
                        listaActividadesMesSemestre.put(i, new TreeNodeMesSemestre(Arrays.asList(
                                "Semestre 1",
                                "Semestre 2"             
                        ), unidadRepresentacion.getUnidadRepresentacion()));                
                    }
                    break;
                case "Anual":
                    for(int i=minValue; i<=maxValue; i++){
                        listaActividades.put(i, new ActividadAnio(i, unidadRepresentacion.getUnidadRepresentacion()));
                    }                       
                    break;
                default:
                    if(maxValue - minValue >= 2){
                        for(int i=minValue+2; i<=maxValue; i+=2){
                            listaActividades.put(i,  new ActividadAnio(i, unidadRepresentacion.getUnidadRepresentacion()));
                        }
                    }
                    break;
            }       
        }
        PrimeFaces.current().executeScript("PF('dialog').show();");
    }

    //Funciones para el treetable que despliega las actividades con frecuencia medicion mensual y semestral
    //Funcion que agrega o quita columnas dependiendo de la unidad de representacion, y el valor del spinner mostrado
    public void onChangeSpinnerMesSemestre(ValueChangeEvent e){        
        
        Integer anio = (Integer) e.getComponent().getAttributes().get("anio");
        String nombrePeriodo = (String) e.getComponent().getAttributes().get("nombrePeriodo");
        String uRepr = unidadRepresentacion.getUnidadRepresentacion();   
        
        TreeNodeRow row;
        TreeNodeRow nuevaActividad;            
        
        int oldValue = 1;
        int newValue = 1;
        if(e.getOldValue() != null){
            oldValue = Integer.parseInt(e.getOldValue().toString());
        }
        if(e.getNewValue() != null){
            newValue = Integer.parseInt(e.getNewValue().toString());
        }            
                       
        List<TreeNode> children = listaActividadesMesSemestre.get(anio).getRoot().getChildren();     
                    
        if(uRepr.equals("Número Actividades") || uRepr.equals("Peso ($)")){
            for(TreeNode t: children){
                row = (TreeNodeRow) t.getData();            
                if(row.getNombrePeriodo().equals(nombrePeriodo)){
                    if(newValue > oldValue){
                        for(int i=oldValue; i<newValue; i++){           
                            nuevaActividad = new TreeNodeRow("", nombrePeriodo);
                            if(uRepr.equals("Peso ($)")){
                                nuevaActividad.setMonto(0);
                            }
                            t.getChildren().add(new DefaultTreeNode(nuevaActividad, t));
                        }
                    }else if(newValue < oldValue){
                        for(int i=oldValue; i>newValue; i--){
                            t.getChildren().remove(i-1);
                        }                
                    }
                    break;
                }            
            }    
            if(uRepr.equals("Número Actividades")){
                calcularPorcentajeLogroMesSemestre(anio, nombrePeriodo);
            }
            if(uRepr.equals("Peso ($)")){
                calcularMontoMesSemestre(anio, nombrePeriodo);
            }                                        
        }else{
            float porcentaje = (float) newValue / 100f;
            float actividades = Float.parseFloat(metas) * porcentaje;
            nuevaActividad = new TreeNodeRow("", nombrePeriodo);
            for(TreeNode t: children){
                row = (TreeNodeRow) t.getData();            
                if(row.getNombrePeriodo().equals(nombrePeriodo)){
                    t.getChildren().clear();
                    for(int i = 0; i < actividades; i++){
                        t.getChildren().add(new DefaultTreeNode(nuevaActividad, t));
                    }                        
                    break;
                }            
            }                         
        }
                                    
    }
                     
    //Funcion que calcula y setea el porcentaje de cada periodo dependiendo de las actividades totales y las actividades escritas
    public void calcularPorcentajeLogroMesSemestre(Integer anio, String nombrePeriodo){
        List<TreeNode> children = listaActividadesMesSemestre.get(anio).getRoot().getChildren();    
        int actividadesTotales = 1;
        int actividadesEscritas = 0;        
        for(TreeNode t: children){
            TreeNodeRow row = (TreeNodeRow) t.getData();            
            if(row.getNombrePeriodo().equals(nombrePeriodo)){
                actividadesTotales = t.getChildren().size();
                for(TreeNode t1 : t.getChildren()){
                    TreeNodeRow rowActividad = (TreeNodeRow) t1.getData();
                    if(rowActividad.getNombreActividad()!= null){
                        if(!rowActividad.getNombreActividad().isBlank() && !rowActividad.getNombreActividad().isEmpty()){
                            actividadesEscritas++;
                        }
                    }
                }         
                                
                float actividadesTotales_f = (float) actividadesTotales;
                float actividadesEscritas_f = (float) actividadesEscritas;

                float porc = (actividadesEscritas_f / actividadesTotales_f) * 100f;      
                row.setLogro((int) porc);                                              
                break;
            }            
        }               
    }            

    //Funcion que calcula y setea el monto total del periodo sumando todas los montos escritos
    public void calcularMontoMesSemestre(Integer anio, String nombrePadre){
          
        List<TreeNode> children = listaActividadesMesSemestre.get(anio).getRoot().getChildren();   
   
        int sumaMontos = 0;

        for(TreeNode t: children){
            TreeNodeRow row = (TreeNodeRow) t.getData();          
            System.out.println(row.getNombrePeriodo());
            System.out.println(nombrePadre);
            if(row.getNombrePeriodo().equals(nombrePadre)){
                for(TreeNode t1 : t.getChildren()){
                    TreeNodeRow rowActividad = (TreeNodeRow) t1.getData();
                    sumaMontos += rowActividad.getMonto();
                }                    
                row.setMonto(sumaMontos);
                break;
            }                      
        }  

    }    
        
    //------------------------------------------------
    
    //Funciones para el datatable que despliega las actividades con frecuencia medicion anual y bianual
    public void onChangeSpinnerAnio(ValueChangeEvent e){
        
        Integer anio = (Integer) e.getComponent().getAttributes().get("anio");
        String uRepr = unidadRepresentacion.getUnidadRepresentacion();  
        
        int oldValue = 1;
        int newValue = 1;
        if(e.getOldValue() != null){
            oldValue = Integer.parseInt(e.getOldValue().toString());
        }
        if(e.getNewValue() != null){
            newValue = Integer.parseInt(e.getNewValue().toString());
        }        
        
        ActividadAnio an = listaActividades.get(anio);
                    
        if(newValue > oldValue){
            if(uRepr.equals("Número Actividades")){
                for(int i=oldValue; i<newValue; i++){                       
                    an.getActividades().add(new Actividad(""));
                }                                                    
            }else{
                for(int i=oldValue; i<newValue; i++){                       
                    an.getActividades().add(new Actividad("", 0));
                }                    
            }
        }else if(newValue < oldValue){
            for(int i=oldValue; i>newValue; i--){                
                an.getActividades().remove(i-1);                               
            }
        }                        
        if(uRepr.equals("Número Actividades")){
            calcularPorcentajeLogroAnio(anio);
        }else{
            calcularMontoAnio(anio);
        }
        
                
    }      
               
    //metodo que añade n actividades dependiendo del porcentaje ingresado
    public void onChangeInputPorcAnio(ValueChangeEvent e){
        
        Integer anio = (Integer) e.getComponent().getAttributes().get("anio");
        int newValue = 1;
        if(e.getNewValue() != null){
            newValue = Integer.parseInt(e.getNewValue().toString());
        }        
        
        ActividadAnio an = listaActividades.get(anio);     
        List<Actividad> actividades = an.getActividades();
        
        float porcentaje = (float) newValue / 100f;
        float actividades_f = Float.parseFloat(metas) * porcentaje;
        int actividades_i = (int) actividades_f;
        
        if(actividades_i == 0){
            actividades_i = 1;
        }
        
        actividades.clear();                
        for(int i = 0; i < actividades_i; i++){
            actividades.add(new Actividad(""));
        }                                    
    }
    
    //Metodo que calculo el porcentaje de logro de un año
    public void calcularPorcentajeLogroAnio(Integer anio){       
        ActividadAnio an = listaActividades.get(anio);
        List<Actividad> actividades = an.getActividades();
                  
        int actividadesTotales = actividades.size();
        int actividadesEscritas = 0;
        
        for(Actividad a : actividades){
            if(a.getNombre() != null){
                if(!a.getNombre().isBlank() && !a.getNombre().isEmpty()){
                    actividadesEscritas++;
                }                
            }                       
        }
               
        float actividadesTotales_f = (float) actividadesTotales;
        float actividadesEscritas_f = (float) actividadesEscritas;

        float porc = (actividadesEscritas_f / actividadesTotales_f) * 100f;      
        an.setLogro((int) porc);                                                                           
    }
        
    //Metodo que suma todos los montos
    public void calcularMontoAnio(Integer anio){          
        ActividadAnio an = listaActividades.get(anio);     
        List<Actividad> actividades = an.getActividades();
               
        int montoPeriodo = 0;
        
        for(Actividad a : actividades){
            montoPeriodo += a.getMontoActividad();
        }
                
        an.setMontoProceso(montoPeriodo);
    }
    
    
    //------------------------------------------------       
    //------------------------------------------------
    
    public void guardarLogros(){       
        String fm = frecuenciaMedicion.getFrecuenciaMedicion();
        int metasInt = Integer.parseInt(metas);                     
        int metasTotalesPeriodo = 0;
        int actividadesEscritas = 0;
        String uRepr = unidadRepresentacion.getUnidadRepresentacion();
                                                
        if(fm.equals("Semestral") || fm.equals("Mensual")){
            List<TreeNode> childrenList_lvl1;
            TreeNodeRow row;
            TreeNodeRow rowChild;
            for(int i = minValue; i <= maxValue; i++){
                childrenList_lvl1 = listaActividadesMesSemestre.get(i).getRoot().getChildren();
                for(TreeNode t: childrenList_lvl1){
                    row = (TreeNodeRow) t.getData();                       
                    if(uRepr.equals("Número Actividades") || uRepr.equals("Porcentaje (%)")){
                        metasTotalesPeriodo += t.getChildren().size();
                        for(TreeNode t1: t.getChildren()){
                            rowChild = (TreeNodeRow) t1.getData();                        
                            if(rowChild.getNombreActividad() != null){
                                if(!rowChild.getNombreActividad().isBlank() && !rowChild.getNombreActividad().isEmpty()){
                                    actividadesEscritas++;
                                }
                            }

                        }
                    }else if(uRepr.equals("Peso ($)")){
                        metasTotalesPeriodo += row.getMonto();
                    }
                }
            }
        }else{
            ActividadAnio an;
            for(int i = minValue; i <= maxValue; i++){
                an = listaActividades.get(i);
                if(uRepr.equals("Número Actividades") || uRepr.equals("Porcentaje (%)")){   
                    metasTotalesPeriodo += an.getActividades().size();                                    
                    for(Actividad a: an.getActividades()){
                        if(a.getNombre() != null){
                            if(!a.getNombre().isBlank() && !a.getNombre().isEmpty()){
                                actividadesEscritas++;
                            }
                        }
                    }               
                }else if(uRepr.equals("Peso ($)")){
                    metasTotalesPeriodo += an.getMontoProceso();
                }             
            }            
        }
                
        mantenerLogros = true;
        
        float metas_f = (float) metasInt;
        float porc = 0;
        
        if(uRepr.equals("Número Actividades") || uRepr.equals("Porcentaje (%)")){
            if(metasTotalesPeriodo != metasInt){
                FacesContext.getCurrentInstance().addMessage("mensaje", 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                        "La cantidad de actividades propuestas (" + String.valueOf(metasTotalesPeriodo) + ") no "
                                + "es igual a la cantidad de metas establecidas (" + String.valueOf(metasInt) + ")."));  
                return;
            }
            float actividadesEscritas_f = (float) actividadesEscritas;
            porc = (actividadesEscritas_f / metas_f) * 100f;      
        }else if(uRepr.equals("Peso ($)")){
            if(metasTotalesPeriodo > metasInt){
                FacesContext.getCurrentInstance().addMessage("mensaje", 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                        "La cantidad de monto propuesto ($" + String.valueOf(metasTotalesPeriodo) + ") es "
                                + "mayor al monto de la meta establecida ($" + String.valueOf(metasInt) + ")."));   
                return;
            }
            
            float metasTotalesPeriodo_f = (float) metasTotalesPeriodo;
            porc = (metasTotalesPeriodo_f / metas_f) * 100f;                                       
            
        }
                      
        logro = String.valueOf((int)porc);
        PrimeFaces.current().executeScript("PF('dialog').hide();");            
     
    }    

    public void cambiarEstado() {
        if (vigencia.equals("VIGENTE")) {
            listaIndicadorTipo = listaIndicadorTipoVigente;
        } else {
            listaIndicadorTipo = listaIndicadorTipoNoVigente;
        }
        indicadorTipoSeleccionado = listaIndicadorTipo.get(0);
        flagsTipoIndicador = flagImpl.getFlagsTipoIndicador(indicadorTipoSeleccionado.getNombre());
        listaClasificacion = indicadorTipoSeleccionado.getClasificacionCollection();

        PrimeFaces.current().resetInputs("form:formulario");
        setEmptyFields();
        Ajax.update("form:formulario");

    }

    public void cambiarTipoIndicador() {
        flagsTipoIndicador = flagImpl.getFlagsTipoIndicador(indicadorTipoSeleccionado.getNombre());
        listaClasificacion = indicadorTipoSeleccionado.getClasificacionCollection();

        PrimeFaces.current().resetInputs("form:formulario");
        setEmptyFields();
        Ajax.update("form:formulario");
    }

    public void setEmptyFields() {
        for (String key : flagsTipoIndicador.getHiddenFlags().keySet()) {
            if (flagsTipoIndicador.getHiddenFlags().get(key)) {
                switch (key) {
                    case "descripcionIndicador":
                        descripcionIndicador = null;
                        break;
                    case "aplicaLineamiento":
                        aplicaLineamiento = null;
                        break;
                    case "aplicaObjetivo":
                        aplicaObjetivo = null;
                        break;
                    case "descripcionObjetivo":
                        descripcionObjetivo = null;
                        break;
                    case "ajustePDEI":
                        ajustePDEI = null;
                        break;
                    case "unidadRepresentacion":
                        unidadRepresentacion = null;
                        break;
                    case "generacionDatos":
                        generacionDatos = null;
                        break;
                    case "plazo":
                        plazo = null;
                        break;
                    case "version":
                        version = null;
                        break;
                    case "lineaBase":
                        lineaBase = null;
                        break;
                    case "metas":
                        metas = null;
                        break;
                    case "anioCumplimiento":
                        anioCumplimiento = null;
                        break;
                    case "logro":
                        logro = null;
                        break;
                    case "frecuenciaMedicion":
                        frecuenciaMedicion = null;
                        break;
                    case "medioVerificacion":
                        medioVerificacion = null;
                        break;
                    case "formaCalculo":
                        formaCalculo = null;
                        break;
                    case "fuenteInformacion":
                        fuenteInformacion = null;
                        break;
                    case "unidadProveedora":
                        unidadProveedora = null;
                        break;
                    case "proyectoAsociado":
                        proyectoAsociado = null;
                        break;
                    case "comentario":
                        comentario = null;
                        break;
                    case "actividadComprometida":
                        actividadComprometida = null;
                        break;
                    case "estadoActividad":
                        estadoActividad = null;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void crearIndicador() throws IOException {
        //Para que al clickear el boton "crear indicador" no se eliminen los logros y el porcentaje calculado
        mantenerLogrosNewStep = true;
        System.out.println("crear");
        if(logro == null){
            FacesContext.getCurrentInstance().addMessage("mensaje", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN", 
                    "Se necesita configurar las activdades y logros."));                  
            return;
        }
                
        
//        System.out.println(descripcionIndicador);
//
//        short numVigencia = 0;
//        if (vigencia.equals("VIGENTE")) {
//            numVigencia = 1;
//        }
//
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        nuevoIndicador.setNumIndicador(parseInt(n_indicador));
//        nuevoIndicador.setNombreIndicador(nombreIndicador);
//        nuevoIndicador.setEstado(numVigencia);
//        nuevoIndicador.setDescripcionIndicador(descripcionIndicador);
//        nuevoIndicador.setAplicaLineamiento(aplicaLineamiento);
//        nuevoIndicador.setAplicaObjetivo(aplicaObjetivo);
//        nuevoIndicador.setDescripcionObjetivo(descripcionObjetivo);
//        nuevoIndicador.setVersion(version);
//        nuevoIndicador.setLineaBase(lineaBase);
//        nuevoIndicador.setMetas(metas);
//        nuevoIndicador.setPorcLogro(logro);
//        nuevoIndicador.setMedioVerificacion(medioVerificacion);
//        nuevoIndicador.setFormaCalculo(formaCalculo);
//        nuevoIndicador.setFuenteInformacion(fuenteInformacion);
//        nuevoIndicador.setProyectoAsociado(proyectoAsociado);
//        nuevoIndicador.setComentario(comentario);
//        nuevoIndicador.setActividadComprometida(actividadComprometida);
//        nuevoIndicador.setEstadoActividad(estadoActividad);
//        nuevoIndicador.setFechaCreacion(new Date());
//        nuevoIndicador.setFechaActualizacion(new Date());
//
//        nuevoIndicador.setAjustePdeiId(ajustePDEI);
//        nuevoIndicador.setAnioCumplimientoId(anioCumplimiento);
//        nuevoIndicador.setClasificacionId(clasificacionSeleccionada);
//        nuevoIndicador.setFrecuenciaMedicionId(frecuenciaMedicion);
//        nuevoIndicador.setGeneracionDatosId(generacionDatos[0]);
//        nuevoIndicador.setPlazoId(plazo);
//        nuevoIndicador.setUnidadProveedoraId(unidadProveedora[0]);
//        nuevoIndicador.setUnidadRepresentacionId(unidadRepresentacion);
//
//        nuevoIndicador.setIndicadorAnioCollection(new ArrayList<>()); //TODO
//
//        try {
//            indicadorService.crearIndicador(nuevoIndicador);
//            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "ATENCIÓN",
//                    "El indicador " + nombreIndicador + " ha sido agregada correctamente")
//            );
//
//            try {
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                formatter.setTimeZone(TimeZone.getTimeZone("GMT-4"));
//                correoService.enviarMensajeTexto("manueltrigo.at@gmail.com", "Sistema de Indicadores", "Se ha creado un registro de una nueva clasificación.<br/> "
//                        + "<ul>"
//                        + "<li>Nombre indicador: " + nuevoIndicador.getNombreIndicador() + ".</li>"
//                        //+ "<li>Clasificacion asociada: " + nuevoIndicador.getClasificacionId() + ".</li>"
//                        + "<li>Estado: " + vigencia + ".</li>"
//                        + "<li>Descripción: " + nuevoIndicador.getDescripcionIndicador() + ".</li>"
//                        + "<li>Fecha creación: " + formatter.format(nuevoIndicador.getFechaCreacion()) + ".</li>"
//                        + "</ul>"
//                        + "<br/><br/>"
//                        + "<a href=" + direccionSI + ">Link Sistema de Indicadores</a>"
//                        + "<br/><br/>"
//                        + "<br/><br/>"
//                        + "Saludos cordiales. <br/><br/>"
//                        + "Sistema de Indicadores."
//                );
//            } catch (NotificacionCorreoException ex) {
//
//                context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN",
//                        "Ocurrio un error al enviar el correo. Contacte al administrador mediante el correo SOPORTE.DVCME@uantof.cl.")
//                );
//
//                //En caso de capturar algun error se retorna un mensaje y se guarda en el log el error
//                Logger.getLogger(CrearTipoIndicadorBean.class
//                        .getName()).log(Level.SEVERE, "Ocurrio un error al enviar el correo.", ex);
//            }
//
//            context.getExternalContext().getFlash().setKeepMessages(true);
//            context.getExternalContext()
//                    .redirect(context.getExternalContext().getRequestContextPath() + "/faces/administracion/admin-indicador.xhtml");
//
//        } catch (EJBException e) {
//
//            context.addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "ATENCIÓN",
//                    "El indicador " + nuevoIndicador + " ya existe en los registros")
//            );
//
//        }
    }

    public String flujoProceso(FlowEvent event) {
        PrimeFaces.current().resetInputs("form:formulario");
        if(mantenerLogros){
            mantenerLogrosNewStep = true;
        }        
        if (siguiente) {
            siguiente = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public ClasificacionService getClasificacionService() {
        return clasificacionService;
    }

    public void setClasificacionService(ClasificacionService clasificacionService) {
        this.clasificacionService = clasificacionService;
    }

    public TipoIndicadorService getTipoIndicadorService() {
        return tipoIndicadorService;
    }

    public void setTipoIndicadorService(TipoIndicadorService tipoIndicadorService) {
        this.tipoIndicadorService = tipoIndicadorService;
    }

    public IndicadorService getIndicadorService() {
        return indicadorService;
    }

    public void setIndicadorService(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    public CorreoService getCorreoService() {
        return correoService;
    }

    public void setCorreoService(CorreoService correoService) {
        this.correoService = correoService;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public boolean isSiguiente() {
        return siguiente;
    }

    public void setSiguiente(boolean siguiente) {
        this.siguiente = siguiente;
    }

    public String getN_indicador() {
        return n_indicador;
    }

    public void setN_indicador(String n_indicador) {
        this.n_indicador = n_indicador;
    }

    public String getNombreIndicador() {
        return nombreIndicador;
    }

    public void setNombreIndicador(String nombreIndicador) {
        this.nombreIndicador = nombreIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public String getAplicaLineamiento() {
        return aplicaLineamiento;
    }

    public void setAplicaLineamiento(String aplicaLineamiento) {
        this.aplicaLineamiento = aplicaLineamiento;
    }

    public String getAplicaObjetivo() {
        return aplicaObjetivo;
    }

    public void setAplicaObjetivo(String aplicaObjetivo) {
        this.aplicaObjetivo = aplicaObjetivo;
    }

    public String getDescripcionObjetivo() {
        return descripcionObjetivo;
    }

    public void setDescripcionObjetivo(String descripcionObjetivo) {
        this.descripcionObjetivo = descripcionObjetivo;
    }

    public AjustePdei getAjustePDEI() {
        return ajustePDEI;
    }

    public void setAjustePDEI(AjustePdei ajustePDEI) {
        this.ajustePDEI = ajustePDEI;
    }

    public UnidadRepresentacion getUnidadRepresentacion() {
        return unidadRepresentacion;
    }

    public void setUnidadRepresentacion(UnidadRepresentacion unidadRepresentacion) {
        this.unidadRepresentacion = unidadRepresentacion;
    }

    public GeneracionDatos[] getGeneracionDatos() {
        return generacionDatos;
    }

    public void setGeneracionDatos(GeneracionDatos[] generacionDatos) {
        this.generacionDatos = generacionDatos;
    }

    public Plazo getPlazo() {
        return plazo;
    }

    public void setPlazo(Plazo plazo) {
        this.plazo = plazo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLineaBase() {
        return lineaBase;
    }

    public void setLineaBase(String lineaBase) {
        this.lineaBase = lineaBase;
    }

    public String getMetas() {
        return metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }

    public AnioCumplimiento getAnioCumplimiento() {
        return anioCumplimiento;
    }

    public void setAnioCumplimiento(AnioCumplimiento anioCumplimiento) {
        this.anioCumplimiento = anioCumplimiento;
    }

    public String getLogro() {
        return logro;
    }

    public void setLogro(String logro) {
        this.logro = logro;
    }

    public FrecuenciaMedicion getFrecuenciaMedicion() {
        return frecuenciaMedicion;
    }

    public void setFrecuenciaMedicion(FrecuenciaMedicion frecuenciaMedicion) {
        this.frecuenciaMedicion = frecuenciaMedicion;
    }

    public String getMedioVerificacion() {
        return medioVerificacion;
    }

    public void setMedioVerificacion(String medioVerificacion) {
        this.medioVerificacion = medioVerificacion;
    }

    public String getFormaCalculo() {
        return formaCalculo;
    }

    public void setFormaCalculo(String formaCalculo) {
        this.formaCalculo = formaCalculo;
    }

    public String getFuenteInformacion() {
        return fuenteInformacion;
    }

    public void setFuenteInformacion(String fuenteInformacion) {
        this.fuenteInformacion = fuenteInformacion;
    }

    public UnidadProveedora[] getUnidadProveedora() {
        return unidadProveedora;
    }

    public void setUnidadProveedora(UnidadProveedora[] unidadProveedora) {
        this.unidadProveedora = unidadProveedora;
    }

    public String getProyectoAsociado() {
        return proyectoAsociado;
    }

    public void setProyectoAsociado(String proyectoAsociado) {
        this.proyectoAsociado = proyectoAsociado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getActividadComprometida() {
        return actividadComprometida;
    }

    public void setActividadComprometida(String actividadComprometida) {
        this.actividadComprometida = actividadComprometida;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public FlagImpl getFlagImpl() {
        return flagImpl;
    }

    public void setFlagImpl(FlagImpl flagImpl) {
        this.flagImpl = flagImpl;
    }

    public Flag getFlagsTipoIndicador() {
        return flagsTipoIndicador;
    }

    public void setFlagsTipoIndicador(Flag flagsTipoIndicador) {
        this.flagsTipoIndicador = flagsTipoIndicador;
    }

    public Collection<Clasificacion> getListaClasificacion() {
        return listaClasificacion;
    }

    public void setListaClasificacion(Collection<Clasificacion> listaClasificacion) {
        this.listaClasificacion = listaClasificacion;
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

    public List<UnidadProveedora> getListaUnidadProveedora() {
        return listaUnidadProveedora;
    }

    public void setListaUnidadProveedora(List<UnidadProveedora> listaUnidadProveedora) {
        this.listaUnidadProveedora = listaUnidadProveedora;
    }

    public List<GeneracionDatos> getListaGeneracionDatos() {
        return listaGeneracionDatos;
    }

    public void setListaGeneracionDatos(List<GeneracionDatos> listaGeneracionDatos) {
        this.listaGeneracionDatos = listaGeneracionDatos;
    }

    public List<AjustePdei> getListaAjustePdei() {
        return listaAjustePdei;
    }

    public void setListaAjustePdei(List<AjustePdei> listaAjustePdei) {
        this.listaAjustePdei = listaAjustePdei;
    }

    public List<AnioCumplimiento> getListaAnioCumplimiento() {
        return listaAnioCumplimiento;
    }

    public void setListaAnioCumplimiento(List<AnioCumplimiento> listaAnioCumplimiento) {
        this.listaAnioCumplimiento = listaAnioCumplimiento;
    }

    public List<Plazo> getListaPlazo() {
        return listaPlazo;
    }

    public void setListaPlazo(List<Plazo> listaPlazo) {
        this.listaPlazo = listaPlazo;
    }

    public List<FrecuenciaMedicion> getListaFrecuenciaMedicion() {
        return listaFrecuenciaMedicion;
    }

    public void setListaFrecuenciaMedicion(List<FrecuenciaMedicion> listaFrecuenciaMedicion) {
        this.listaFrecuenciaMedicion = listaFrecuenciaMedicion;
    }

    public List<UnidadRepresentacion> getListaUnidadRepresentacion() {
        return listaUnidadRepresentacion;
    }

    public void setListaUnidadRepresentacion(List<UnidadRepresentacion> listaUnidadRepresentacion) {
        this.listaUnidadRepresentacion = listaUnidadRepresentacion;
    }

    public IndicadorTipo getIndicadorTipoSeleccionado() {
        return indicadorTipoSeleccionado;
    }

    public void setIndicadorTipoSeleccionado(IndicadorTipo indicadorTipoSeleccionado) {
        this.indicadorTipoSeleccionado = indicadorTipoSeleccionado;
    }

    public Clasificacion getClasificacionSeleccionada() {
        return clasificacionSeleccionada;
    }

    public void setClasificacionSeleccionada(Clasificacion clasificacionSeleccionada) {
        this.clasificacionSeleccionada = clasificacionSeleccionada;
    }

    public Indicador getNuevoIndicador() {
        return nuevoIndicador;
    }

    public void setNuevoIndicador(Indicador nuevoIndicador) {
        this.nuevoIndicador = nuevoIndicador;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Map<Integer, ActividadAnio> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(Map<Integer, ActividadAnio> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public Map<Integer, TreeNodeMesSemestre> getListaActividadesMesSemestre() {
        return listaActividadesMesSemestre;
    }

    public void setListaActividadesMesSemestre(Map<Integer, TreeNodeMesSemestre> listaActividadesMesSemestre) {
        this.listaActividadesMesSemestre = listaActividadesMesSemestre;
    }

    public boolean getMantenerLogros() {
        return mantenerLogros;
    }

    public void setMantenerLogros(boolean mantenerLogros) {
        this.mantenerLogros = mantenerLogros;
    }

    public boolean getMantenerLogrosNewStep() {
        return mantenerLogrosNewStep;
    }

    public void setMantenerLogrosNewStep(boolean mantenerLogrosNewStep) {
        this.mantenerLogrosNewStep = mantenerLogrosNewStep;
    }   
}
