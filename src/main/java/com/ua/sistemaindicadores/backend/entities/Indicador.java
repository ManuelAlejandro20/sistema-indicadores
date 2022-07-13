/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "indicador", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.obtenerDTO", query = "SELECT NEW com.ua.sistemaindicadores.backend.dtos.IndicadorDTO(i.id, i.numIndicador, i.nombreIndicador, i.estado, i.descripcionIndicador, "
                                                            + "i.aplicaLineamiento, i.aplicaObjetivo, i.descripcionObjetivo, i.version, i.lineaBase, i.metas, i.porcLogro, i.medioVerificacion, "
                                                            + "i.formaCalculo, i.fuenteInformacion, i.proyectoAsociado, i.comentario, i.actividadComprometida, i.estadoActividad, i.fechaCreacion, i.fechaActualizacion, "
                                                            + "a.ajustePdei, c.tipo, ac.anioCumplimiento, c.nombre, f.frecuenciaMedicion, p.plazo, ur.unidadRepresentacion) "
                                                     + "FROM Indicador i INNER JOIN i.ajustePdeiId a INNER JOIN i.anioCumplimientoId ac INNER JOIN "
                                                            + "i.clasificacionId c INNER JOIN i.frecuenciaMedicionId f INNER JOIN i.plazoId p INNER JOIN i.unidadRepresentacionId ur "
                                                     + "WHERE i.id = :Id"),   
    @NamedQuery(name = "Indicador.findAll", query = "SELECT i FROM Indicador i"),
    @NamedQuery(name = "Indicador.findById", query = "SELECT i FROM Indicador i WHERE i.id = :id"),
    @NamedQuery(name = "Indicador.findByNumIndicador", query = "SELECT i FROM Indicador i WHERE i.numIndicador = :numIndicador"),
    @NamedQuery(name = "Indicador.findByNombreIndicador", query = "SELECT i FROM Indicador i WHERE i.nombreIndicador = :nombreIndicador"),
    @NamedQuery(name = "Indicador.findByEstado", query = "SELECT i FROM Indicador i WHERE i.estado = :estado"),
    @NamedQuery(name = "Indicador.findByDescripcionIndicador", query = "SELECT i FROM Indicador i WHERE i.descripcionIndicador = :descripcionIndicador"),
    @NamedQuery(name = "Indicador.findByAplicaLineamiento", query = "SELECT i FROM Indicador i WHERE i.aplicaLineamiento = :aplicaLineamiento"),
    @NamedQuery(name = "Indicador.findByAplicaObjetivo", query = "SELECT i FROM Indicador i WHERE i.aplicaObjetivo = :aplicaObjetivo"),
    @NamedQuery(name = "Indicador.findByDescripcionObjetivo", query = "SELECT i FROM Indicador i WHERE i.descripcionObjetivo = :descripcionObjetivo"),
    @NamedQuery(name = "Indicador.findByVersion", query = "SELECT i FROM Indicador i WHERE i.version = :version"),
    @NamedQuery(name = "Indicador.findByLineaBase", query = "SELECT i FROM Indicador i WHERE i.lineaBase = :lineaBase"),
    @NamedQuery(name = "Indicador.findByMetas", query = "SELECT i FROM Indicador i WHERE i.metas = :metas"),
    @NamedQuery(name = "Indicador.findByPorcLogro", query = "SELECT i FROM Indicador i WHERE i.porcLogro = :porcLogro"),
    @NamedQuery(name = "Indicador.findByMedioVerificacion", query = "SELECT i FROM Indicador i WHERE i.medioVerificacion = :medioVerificacion"),
    @NamedQuery(name = "Indicador.findByFormaCalculo", query = "SELECT i FROM Indicador i WHERE i.formaCalculo = :formaCalculo"),
    @NamedQuery(name = "Indicador.findByFuenteInformacion", query = "SELECT i FROM Indicador i WHERE i.fuenteInformacion = :fuenteInformacion"),
    @NamedQuery(name = "Indicador.findByProyectoAsociado", query = "SELECT i FROM Indicador i WHERE i.proyectoAsociado = :proyectoAsociado"),
    @NamedQuery(name = "Indicador.findByComentario", query = "SELECT i FROM Indicador i WHERE i.comentario = :comentario"),
    @NamedQuery(name = "Indicador.findByActividadComprometida", query = "SELECT i FROM Indicador i WHERE i.actividadComprometida = :actividadComprometida"),
    @NamedQuery(name = "Indicador.findByEstadoActividad", query = "SELECT i FROM Indicador i WHERE i.estadoActividad = :estadoActividad"),
    @NamedQuery(name = "Indicador.findByFechaCreacion", query = "SELECT i FROM Indicador i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Indicador.findByFechaActualizacion", query = "SELECT i FROM Indicador i WHERE i.fechaActualizacion = :fechaActualizacion")})
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
    @Column(name = "nombre_indicador")
    private String nombreIndicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion_indicador")
    private String descripcionIndicador;
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
    @Size(min = 1, max = 255)
    @Column(name = "descripcion_objetivo")
    private String descripcionObjetivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "version")
    private String version;
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
    @Size(min = 1, max = 255)
    @Column(name = "porc_logro")
    private String porcLogro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "medio_verificacion")
    private String medioVerificacion;
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
    @Size(min = 1, max = 255)
    @Column(name = "proyecto_asociado")
    private String proyectoAsociado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "actividad_comprometida")
    private String actividadComprometida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "estado_actividad")
    private String estadoActividad;
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
    @Size(min = 1, max = 255)
    @Column(name = "num_indicador")
    private String numIndicador; 
    @JoinTable(name = "generacion_datos_indicador", schema="indicadores", joinColumns = {
        @JoinColumn(name = "id_indicador", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_generacion_datos", referencedColumnName = "id")})    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<GeneracionDatos> generacionDatosCollection;
    @JoinTable(name = "unidad_proveedora_indicador", schema="indicadores", joinColumns = {
        @JoinColumn(name = "id_indicador", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_unidad_proveedora", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UnidadProveedora> unidadProveedoraCollection;
    @JoinColumn(name = "ajuste_pdei_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AjustePdei ajustePdeiId;
    @JoinColumn(name = "anio_cumplimiento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AnioCumplimiento anioCumplimientoId;
    @JoinColumn(name = "clasificacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clasificacion clasificacionId;
    @JoinColumn(name = "frecuencia_medicion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FrecuenciaMedicion frecuenciaMedicionId;
    @JoinColumn(name = "plazo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plazo plazoId;
    @JoinColumn(name = "unidad_representacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UnidadRepresentacion unidadRepresentacionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicadorId", fetch = FetchType.EAGER)
    private Collection<IndicadorMesSemestreAnioBianual> indicadorMesSemestreAnioBianualCollection;

    public Indicador() {
    }

    public Indicador(Integer id) {
        this.id = id;
    }

    public Indicador(Integer id, String nombreIndicador, Short estado, String descripcionIndicador, String aplicaLineamiento, String aplicaObjetivo, String descripcionObjetivo, String version, String lineaBase, String metas, String porcLogro, String medioVerificacion, String formaCalculo, String fuenteInformacion, String proyectoAsociado, String comentario, String actividadComprometida, String estadoActividad, Date fechaCreacion, Date fechaActualizacion, String numIndicador) {
        this.id = id;
        this.nombreIndicador = nombreIndicador;
        this.estado = estado;
        this.descripcionIndicador = descripcionIndicador;
        this.aplicaLineamiento = aplicaLineamiento;
        this.aplicaObjetivo = aplicaObjetivo;
        this.descripcionObjetivo = descripcionObjetivo;
        this.version = version;
        this.lineaBase = lineaBase;
        this.metas = metas;
        this.porcLogro = porcLogro;
        this.medioVerificacion = medioVerificacion;
        this.formaCalculo = formaCalculo;
        this.fuenteInformacion = fuenteInformacion;
        this.proyectoAsociado = proyectoAsociado;
        this.comentario = comentario;
        this.actividadComprometida = actividadComprometida;
        this.estadoActividad = estadoActividad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.numIndicador = numIndicador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreIndicador() {
        return nombreIndicador;
    }

    public void setNombreIndicador(String nombreIndicador) {
        this.nombreIndicador = nombreIndicador;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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

    public String getPorcLogro() {
        return porcLogro;
    }

    public void setPorcLogro(String porcLogro) {
        this.porcLogro = porcLogro;
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

    public String getNumIndicador() {
        return numIndicador;
    }

    public void setNumIndicador(String numIndicador) {
        this.numIndicador = numIndicador;
    }

    @XmlTransient
    public Collection<GeneracionDatos> getGeneracionDatosCollection() {
        return generacionDatosCollection;
    }

    public void setGeneracionDatosCollection(Set<GeneracionDatos> generacionDatosCollection) {
        this.generacionDatosCollection = generacionDatosCollection;
    }

    @XmlTransient
    public Collection<UnidadProveedora> getUnidadProveedoraCollection() {
        return unidadProveedoraCollection;
    }

    public void setUnidadProveedoraCollection(Set<UnidadProveedora> unidadProveedoraCollection) {
        this.unidadProveedoraCollection = unidadProveedoraCollection;
    }

    public AjustePdei getAjustePdeiId() {
        return ajustePdeiId;
    }

    public void setAjustePdeiId(AjustePdei ajustePdeiId) {
        this.ajustePdeiId = ajustePdeiId;
    }

    public AnioCumplimiento getAnioCumplimientoId() {
        return anioCumplimientoId;
    }

    public void setAnioCumplimientoId(AnioCumplimiento anioCumplimientoId) {
        this.anioCumplimientoId = anioCumplimientoId;
    }

    public Clasificacion getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(Clasificacion clasificacionId) {
        this.clasificacionId = clasificacionId;
    }

    public FrecuenciaMedicion getFrecuenciaMedicionId() {
        return frecuenciaMedicionId;
    }

    public void setFrecuenciaMedicionId(FrecuenciaMedicion frecuenciaMedicionId) {
        this.frecuenciaMedicionId = frecuenciaMedicionId;
    }

    public Plazo getPlazoId() {
        return plazoId;
    }

    public void setPlazoId(Plazo plazoId) {
        this.plazoId = plazoId;
    }

    public UnidadRepresentacion getUnidadRepresentacionId() {
        return unidadRepresentacionId;
    }

    public void setUnidadRepresentacionId(UnidadRepresentacion unidadRepresentacionId) {
        this.unidadRepresentacionId = unidadRepresentacionId;
    }

    @XmlTransient
    public Collection<IndicadorMesSemestreAnioBianual> getIndicadorMesSemestreAnioBianualCollection() {
        return indicadorMesSemestreAnioBianualCollection;
    }

    public void setIndicadorMesSemestreAnioBianualCollection(Collection<IndicadorMesSemestreAnioBianual> indicadorMesSemestreAnioBianualCollection) {
        this.indicadorMesSemestreAnioBianualCollection = indicadorMesSemestreAnioBianualCollection;
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
