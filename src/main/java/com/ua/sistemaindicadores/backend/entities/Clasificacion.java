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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@Table(name = "clasificacion", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificacion.findAll", query = "SELECT c FROM Clasificacion c"),
    @NamedQuery(name = "Clasificacion.findById", query = "SELECT c FROM Clasificacion c WHERE c.id = :id"),
    @NamedQuery(name = "Clasificacion.findByNombre", query = "SELECT c FROM Clasificacion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clasificacion.findByEstado", query = "SELECT c FROM Clasificacion c WHERE c.estado = :estado"),
    @NamedQuery(name = "Clasificacion.findByTipo", query = "SELECT c FROM Clasificacion c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Clasificacion.findByDescripcion", query = "SELECT c FROM Clasificacion c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Clasificacion.obtenerDTO", query = "SELECT NEW com.ua.sistemaindicadores.backend.dtos.ClasificacionDTO(i.id, r.id, i.nombre, i.estado, i.tipo, i.descripcion, i.fechaCreacion, i.fechaActualizacion) FROM Clasificacion i INNER JOIN i.indicadorTipoId r WHERE i.id = :Id"),
    @NamedQuery(name = "Clasificacion.findByFechaCreacion", query = "SELECT c FROM Clasificacion c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Clasificacion.findByFechaActualizacion", query = "SELECT c FROM Clasificacion c WHERE c.fechaActualizacion = :fechaActualizacion")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Clasificacion.existeClasificacion", query
            = "SELECT c.nombre = ?1 \n"
            + "FROM sistema_indicadores_clasificacion c \n"
            + "WHERE c.nombre = ?1 \n"),
})
public class Clasificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clasificacionId")
    private Collection<Indicador> indicadorCollection;
    @JoinColumn(name = "indicador_tipo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IndicadorTipo indicadorTipoId;

    public Clasificacion() {
    }

    public Clasificacion(Integer id) {
        this.id = id;
    }

    public Clasificacion(Integer id, IndicadorTipo indicadorTipoId, String nombre, Short estado, String tipo, String descripcion, Date fechaCreacion, Date fechaActualizacion) {
        this.id = id;
        this.indicadorTipoId = indicadorTipoId;
        this.nombre = nombre;
        this.estado = estado;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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

    @XmlTransient
    public Collection<Indicador> getIndicadorCollection() {
        return indicadorCollection;
    }

    public void setIndicadorCollection(Collection<Indicador> indicadorCollection) {
        this.indicadorCollection = indicadorCollection;
    }

    public IndicadorTipo getIndicadorTipoId() {
        return indicadorTipoId;
    }

    public void setIndicadorTipoId(IndicadorTipo indicadorTipoId) {
        this.indicadorTipoId = indicadorTipoId;
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
        if (!(object instanceof Clasificacion)) {
            return false;
        }
        Clasificacion other = (Clasificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Clasificacion[ id=" + id + " ]";
    }

}
