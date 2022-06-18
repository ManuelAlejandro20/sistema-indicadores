/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.services.IndicadorService;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import com.ua.sistemaindicadores.backend.exceptions.NotificacionCorreoException;
import com.ua.sistemaindicadores.backend.flags.Flag;
import com.ua.sistemaindicadores.backend.flags.FlagImpl;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
import com.ua.sistemaindicadores.backend.services.CorreoService;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;

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

    private String vigencia;

    //
    private String n_indicador;
    private String nombreIndicador;
    private String descripcionIndicador;
    private String aplicaLineamiento;
    private String aplicaObjetivo;
    private String descripcionObjetivo;
    private String ajustePDEI;
    private String unidadRepresentacion;
    private String generacionDatos;
    private String plazo;
    private String version;
    private String lineaBase;
    private String anio2020;
    private String anio2021;
    private String anio2022;
    private String anio2023;
    private String anio2024;
    private String anio2025;
    private String anio2026;
    private String anio2027;
    private String anio2028;
    private String anio2029;
    private String anio2030;
    private String metas;
    private String anioCumplimiento;
    private String logro;
    private String frecuenciaMedicion;
    private String medioVerificacion;
    private String formaCalculo;
    private String fuenteInformacion;
    private String unidadProveedora;
    private String proyectoAsociado;
    private String comentario;
    private String actividadComprometida;
    private String estadoActividad;

    private FlagImpl flagImpl;
    private Flag flagsTipoIndicador;

    private List<IndicadorTipo> listaIndicadorTipo;
    private List<IndicadorTipo> listaIndicadorTipoVigente;
    private List<IndicadorTipo> listaIndicadorTipoNoVigente;
    private List<UnidadProveedora> listaUnidadProveedora;
    private List<GeneracionDatos> listaGeneracionDatos;
    private List<String> listaUnidadesSeleccionadas;
    private List<String> listaGeneracionesSeleccionadas;
    private IndicadorTipo indicadorTipoSeleccionado;

    private Collection<Clasificacion> listaClasificacion;
    private Clasificacion clasificacionSeleccionada;

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
//            for (String key : flagsTipoIndicador.getHiddenFlags().keySet()) {
//                System.out.println(key + "=" + flagsTipoIndicador.getHiddenFlags().get(key));
//            }            

            listaClasificacion = indicadorTipoSeleccionado.getClasificacionCollection();
            clasificacionSeleccionada = listaClasificacion.iterator().next();

        }
        listaUnidadProveedora = indicadorService.obtenerUnidadProveedora();
    }

    /**
     * Creates a new instance of convenioBean
     */
    public CrearIndicadorBean() {
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
                    case "anio2020":
                        anio2020 = null;
                        break;
                    case "anio2021":
                        anio2021 = null;
                        break;
                    case "anio2022":
                        anio2022 = null;
                        break;
                    case "anio2023":
                        anio2023 = null;
                        break;
                    case "anio2024":
                        anio2024 = null;
                        break;
                    case "anio2025":
                        anio2025 = null;
                        break;
                    case "anio2026":
                        anio2026 = null;
                        break;
                    case "anio2027":
                        anio2027 = null;
                        break;
                    case "anio2028":
                        anio2028 = null;
                        break;
                    case "anio2029":
                        anio2029 = null;
                        break;
                    case "anio2030":
                        anio2030 = null;
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

    public void crearIndicador() {
        System.out.println("crear");
        System.out.println(descripcionIndicador);
    }

    public List<IndicadorTipo> getListaIndicadorTipo() {
        return listaIndicadorTipo;
    }

    public List<IndicadorTipo> getListaIndicadorTipoVigente() {
        return listaIndicadorTipoVigente;
    }

    public List<IndicadorTipo> getListaIndicadorTipoNoVigente() {
        return listaIndicadorTipoNoVigente;
    }

    public List<GeneracionDatos> getListaGeneracionDatos() {
        return listaGeneracionDatos;
    }

    public void setListaGeneracionDatos(List<GeneracionDatos> listaGeneracionDatos) {
        this.listaGeneracionDatos = listaGeneracionDatos;
    }

    public List<String> getListaGeneracionesSeleccionadas() {
        return listaGeneracionesSeleccionadas;
    }

    public List<UnidadProveedora> getListaUnidadProveedora() {
        return listaUnidadProveedora;
    }

    public void setListaUnidadProveedora(List<UnidadProveedora> listaUnidadProveedora) {
        this.listaUnidadProveedora = listaUnidadProveedora;
    }

    public List<String> getListaUnidadesSeleccionadas() {
        return listaUnidadesSeleccionadas;
    }

    public void setListaUnidadesSeleccionadas(List<String> listaUnidadesSeleccionadas) {
        this.listaUnidadesSeleccionadas = listaUnidadesSeleccionadas;
    }

    public void setListaGeneracionesSeleccionadas(List<String> listaGeneracionesSeleccionadas) {
        this.listaGeneracionesSeleccionadas = listaGeneracionesSeleccionadas;
    }

    public IndicadorTipo getIndicadorTipoSeleccionado() {
        return indicadorTipoSeleccionado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
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

    public String getAjustePDEI() {
        return ajustePDEI;
    }

    public void setAjustePDEI(String ajustePDEI) {
        this.ajustePDEI = ajustePDEI;
    }

    public String getUnidadRepresentacion() {
        return unidadRepresentacion;
    }

    public void setUnidadRepresentacion(String unidadRepresentacion) {
        this.unidadRepresentacion = unidadRepresentacion;
    }

    public String getGeneracionDatos() {
        return generacionDatos;
    }

    public void setGeneracionDatos(String generacionDatos) {
        this.generacionDatos = generacionDatos;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
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

    public String getAnio2020() {
        return anio2020;
    }

    public void setAnio2020(String anio2020) {
        this.anio2020 = anio2020;
    }

    public String getAnio2021() {
        return anio2021;
    }

    public void setAnio2021(String anio2021) {
        this.anio2021 = anio2021;
    }

    public String getAnio2022() {
        return anio2022;
    }

    public void setAnio2022(String anio2022) {
        this.anio2022 = anio2022;
    }

    public String getAnio2023() {
        return anio2023;
    }

    public void setAnio2023(String anio2023) {
        this.anio2023 = anio2023;
    }

    public String getAnio2024() {
        return anio2024;
    }

    public void setAnio2024(String anio2024) {
        this.anio2024 = anio2024;
    }

    public String getAnio2025() {
        return anio2025;
    }

    public void setAnio2025(String anio2025) {
        this.anio2025 = anio2025;
    }

    public String getAnio2026() {
        return anio2026;
    }

    public void setAnio2026(String anio2026) {
        this.anio2026 = anio2026;
    }

    public String getAnio2027() {
        return anio2027;
    }

    public void setAnio2027(String anio2027) {
        this.anio2027 = anio2027;
    }

    public String getAnio2028() {
        return anio2028;
    }

    public void setAnio2028(String anio2028) {
        this.anio2028 = anio2028;
    }

    public String getAnio2029() {
        return anio2029;
    }

    public void setAnio2029(String anio2029) {
        this.anio2029 = anio2029;
    }

    public String getAnio2030() {
        return anio2030;
    }

    public void setAnio2030(String anio2030) {
        this.anio2030 = anio2030;
    }

    public String getMetas() {
        return metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }

    public String getAnioCumplimiento() {
        return anioCumplimiento;
    }

    public void setAnioCumplimiento(String anioCumplimiento) {
        this.anioCumplimiento = anioCumplimiento;
    }

    public String getLogro() {
        return logro;
    }

    public void setLogro(String logro) {
        this.logro = logro;
    }

    public String getFrecuenciaMedicion() {
        return frecuenciaMedicion;
    }

    public void setFrecuenciaMedicion(String frecuenciaMedicion) {
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

    public String getUnidadProveedora() {
        return unidadProveedora;
    }

    public void setUnidadProveedora(String unidadProveedora) {
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

    public Collection<Clasificacion> getListaClasificacion() {
        return listaClasificacion;
    }

    public void setListaClasificacion(Collection<Clasificacion> listaClasificacion) {
        this.listaClasificacion = listaClasificacion;
    }

    public Clasificacion getClasificacionSeleccionada() {
        return clasificacionSeleccionada;
    }

    public void setClasificacionSeleccionada(Clasificacion clasificacionSeleccionada) {
        this.clasificacionSeleccionada = clasificacionSeleccionada;
    }

    public void setListaIndicadorTipo(List<IndicadorTipo> listaIndicadorTipo) {
        this.listaIndicadorTipo = listaIndicadorTipo;
    }

    public void setListaIndicadorTipoVigente(List<IndicadorTipo> listaIndicadorTipoVigente) {
        this.listaIndicadorTipoVigente = listaIndicadorTipoVigente;
    }

    public void setListaIndicadorTipoNoVigente(List<IndicadorTipo> listaIndicadorTipoNoVigente) {
        this.listaIndicadorTipoNoVigente = listaIndicadorTipoNoVigente;
    }

    public void setIndicadorTipoSeleccionado(IndicadorTipo indicadorTipoSeleccionado) {
        this.indicadorTipoSeleccionado = indicadorTipoSeleccionado;
    }

    public Flag getFlagsTipoIndicador() {
        return flagsTipoIndicador;
    }

    public void setFlagsTipoIndicador(Flag flagsTipoIndicador) {
        this.flagsTipoIndicador = flagsTipoIndicador;
    }

}
