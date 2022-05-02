/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "indicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.findAll", query = "SELECT i FROM Indicador i"),
    @NamedQuery(name = "Indicador.findById", query = "SELECT i FROM Indicador i WHERE i.id = :id"),
    @NamedQuery(name = "Indicador.findByFuente", query = "SELECT i FROM Indicador i WHERE i.fuente = :fuente"),
    @NamedQuery(name = "Indicador.findByAplicaLineamiento", query = "SELECT i FROM Indicador i WHERE i.aplicaLineamiento = :aplicaLineamiento"),
    @NamedQuery(name = "Indicador.findByAplicaObjetivo", query = "SELECT i FROM Indicador i WHERE i.aplicaObjetivo = :aplicaObjetivo"),
    @NamedQuery(name = "Indicador.findByNumIndicador", query = "SELECT i FROM Indicador i WHERE i.numIndicador = :numIndicador"),
    @NamedQuery(name = "Indicador.findByDescripcionIndicador", query = "SELECT i FROM Indicador i WHERE i.descripcionIndicador = :descripcionIndicador"),
    @NamedQuery(name = "Indicador.findByIndicador", query = "SELECT i FROM Indicador i WHERE i.indicador = :indicador"),
    @NamedQuery(name = "Indicador.findByAjustePdei", query = "SELECT i FROM Indicador i WHERE i.ajustePdei = :ajustePdei"),
    @NamedQuery(name = "Indicador.findByUnidadRepresentacion", query = "SELECT i FROM Indicador i WHERE i.unidadRepresentacion = :unidadRepresentacion"),
    @NamedQuery(name = "Indicador.findByResponsable", query = "SELECT i FROM Indicador i WHERE i.responsable = :responsable"),
    @NamedQuery(name = "Indicador.findByPlazo", query = "SELECT i FROM Indicador i WHERE i.plazo = :plazo"),
    @NamedQuery(name = "Indicador.findByVersion", query = "SELECT i FROM Indicador i WHERE i.version = :version"),
    @NamedQuery(name = "Indicador.findByLineaBase", query = "SELECT i FROM Indicador i WHERE i.lineaBase = :lineaBase"),
    @NamedQuery(name = "Indicador.findByMetas", query = "SELECT i FROM Indicador i WHERE i.metas = :metas"),
    @NamedQuery(name = "Indicador.findByAnioCumplimiento", query = "SELECT i FROM Indicador i WHERE i.anioCumplimiento = :anioCumplimiento"),
    @NamedQuery(name = "Indicador.findByLogro", query = "SELECT i FROM Indicador i WHERE i.logro = :logro"),
    @NamedQuery(name = "Indicador.findByFrecuenciaMedicion", query = "SELECT i FROM Indicador i WHERE i.frecuenciaMedicion = :frecuenciaMedicion"),
    @NamedQuery(name = "Indicador.findByMedioVerificaciom", query = "SELECT i FROM Indicador i WHERE i.medioVerificaciom = :medioVerificaciom"),
    @NamedQuery(name = "Indicador.findByFormaCalculo", query = "SELECT i FROM Indicador i WHERE i.formaCalculo = :formaCalculo"),
    @NamedQuery(name = "Indicador.findByFuenteInformacion", query = "SELECT i FROM Indicador i WHERE i.fuenteInformacion = :fuenteInformacion"),
    @NamedQuery(name = "Indicador.findByUnidadProveedoraId", query = "SELECT i FROM Indicador i WHERE i.unidadProveedoraId = :unidadProveedoraId"),
    @NamedQuery(name = "Indicador.findByFechaCreacion", query = "SELECT i FROM Indicador i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Indicador.findByFechaActualizacion", query = "SELECT i FROM Indicador i WHERE i.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Indicador.findByAnioIngresoSistema", query = "SELECT i FROM Indicador i WHERE i.anioIngresoSistema = :anioIngresoSistema"),
    @NamedQuery(name = "Indicador.findByEstadoActividad", query = "SELECT i FROM Indicador i WHERE i.estadoActividad = :estadoActividad")})
public class Indicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fuente")
    private String fuente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "aplica_lineamiento")
    private String aplicaLineamiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "aplica_objetivo")
    private String aplicaObjetivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_indicador")
    private int numIndicador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion_indicador")
    private String descripcionIndicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "indicador")
    private int indicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ajuste_pdei")
    private double ajustePdei;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "unidad_representacion")
    private String unidadRepresentacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "responsable")
    private String responsable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plazo")
    private int plazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "linea_base")
    private String lineaBase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "metas")
    private String metas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_cumplimiento")
    private int anioCumplimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "logro")
    private int logro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "frecuencia_medicion")
    private int frecuenciaMedicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "medio_verificaciom")
    private int medioVerificaciom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "forma_calculo")
    private String formaCalculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fuente_informacion")
    private String fuenteInformacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidad_proveedora_id")
    private int unidadProveedoraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_ingreso_sistema")
    @Temporal(TemporalType.TIMESTAMP)
    private Date anioIngresoSistema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "estado_actividad")
    private String estadoActividad;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "estado")
    private byte[] estado;
    @ManyToMany(mappedBy = "indicadorCollection")
    private Collection<Anio> anioCollection;
    @JoinColumn(name = "clasificacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clasificacion clasificacionId;

    public Indicador() {
    }

    public Indicador(Integer id) {
        this.id = id;
    }

    public Indicador(Integer id, String fuente, String aplicaLineamiento, String aplicaObjetivo, int numIndicador, String descripcionIndicador, int indicador, double ajustePdei, String unidadRepresentacion, String responsable, int plazo, int version, String lineaBase, String metas, int anioCumplimiento, int logro, int frecuenciaMedicion, int medioVerificaciom, String formaCalculo, String fuenteInformacion, int unidadProveedoraId, Date fechaCreacion, Date fechaActualizacion, Date anioIngresoSistema, String estadoActividad, byte[] estado) {
        this.id = id;
        this.fuente = fuente;
        this.aplicaLineamiento = aplicaLineamiento;
        this.aplicaObjetivo = aplicaObjetivo;
        this.numIndicador = numIndicador;
        this.descripcionIndicador = descripcionIndicador;
        this.indicador = indicador;
        this.ajustePdei = ajustePdei;
        this.unidadRepresentacion = unidadRepresentacion;
        this.responsable = responsable;
        this.plazo = plazo;
        this.version = version;
        this.lineaBase = lineaBase;
        this.metas = metas;
        this.anioCumplimiento = anioCumplimiento;
        this.logro = logro;
        this.frecuenciaMedicion = frecuenciaMedicion;
        this.medioVerificaciom = medioVerificaciom;
        this.formaCalculo = formaCalculo;
        this.fuenteInformacion = fuenteInformacion;
        this.unidadProveedoraId = unidadProveedoraId;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.anioIngresoSistema = anioIngresoSistema;
        this.estadoActividad = estadoActividad;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
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

    public int getNumIndicador() {
        return numIndicador;
    }

    public void setNumIndicador(int numIndicador) {
        this.numIndicador = numIndicador;
    }

    public String getDescripcionIndicador() {
        return descripcionIndicador;
    }

    public void setDescripcionIndicador(String descripcionIndicador) {
        this.descripcionIndicador = descripcionIndicador;
    }

    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public double getAjustePdei() {
        return ajustePdei;
    }

    public void setAjustePdei(double ajustePdei) {
        this.ajustePdei = ajustePdei;
    }

    public String getUnidadRepresentacion() {
        return unidadRepresentacion;
    }

    public void setUnidadRepresentacion(String unidadRepresentacion) {
        this.unidadRepresentacion = unidadRepresentacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
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

    public int getAnioCumplimiento() {
        return anioCumplimiento;
    }

    public void setAnioCumplimiento(int anioCumplimiento) {
        this.anioCumplimiento = anioCumplimiento;
    }

    public int getLogro() {
        return logro;
    }

    public void setLogro(int logro) {
        this.logro = logro;
    }

    public int getFrecuenciaMedicion() {
        return frecuenciaMedicion;
    }

    public void setFrecuenciaMedicion(int frecuenciaMedicion) {
        this.frecuenciaMedicion = frecuenciaMedicion;
    }

    public int getMedioVerificaciom() {
        return medioVerificaciom;
    }

    public void setMedioVerificaciom(int medioVerificaciom) {
        this.medioVerificaciom = medioVerificaciom;
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

    public int getUnidadProveedoraId() {
        return unidadProveedoraId;
    }

    public void setUnidadProveedoraId(int unidadProveedoraId) {
        this.unidadProveedoraId = unidadProveedoraId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getAnioIngresoSistema() {
        return anioIngresoSistema;
    }

    public void setAnioIngresoSistema(Date anioIngresoSistema) {
        this.anioIngresoSistema = anioIngresoSistema;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public byte[] getEstado() {
        return estado;
    }

    public void setEstado(byte[] estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Anio> getAnioCollection() {
        return anioCollection;
    }

    public void setAnioCollection(Collection<Anio> anioCollection) {
        this.anioCollection = anioCollection;
    }

    public Clasificacion getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(Clasificacion clasificacionId) {
        this.clasificacionId = clasificacionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Indicador[ id=" + id + " ]";
    }
    
}