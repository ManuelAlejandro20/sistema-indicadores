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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "indicador_tipo", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicadorTipo.findAll", query = "SELECT i FROM IndicadorTipo i"),
    @NamedQuery(name = "IndicadorTipo.findById", query = "SELECT i FROM IndicadorTipo i WHERE i.id = :id"),
    @NamedQuery(name = "IndicadorTipo.findByNombre", query = "SELECT i FROM IndicadorTipo i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IndicadorTipo.findByDescripcion", query = "SELECT i FROM IndicadorTipo i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IndicadorTipo.findByEstado", query = "SELECT i FROM IndicadorTipo i WHERE i.estado = :estado"),
    @NamedQuery(name = "IndicadorTipo.findByFechaCreacion", query = "SELECT i FROM IndicadorTipo i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "IndicadorTipo.obtenerDTO", query = "SELECT NEW com.ua.sistemaindicadores.backend.dtos.TipoIndicadorDTO(i.id, i.nombre, i.descripcion, i.estado, i.fechaCreacion, i.fechaActualizacion) FROM IndicadorTipo i WHERE i.id = :Id"),   
    @NamedQuery(name = "IndicadorTipo.findByFechaActualizacion", query = "SELECT i FROM IndicadorTipo i WHERE i.fechaActualizacion = :fechaActualizacion")})
public class IndicadorTipo implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Short estado;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicadorTipoId", fetch = FetchType.EAGER)
    private Collection<Clasificacion> clasificacionCollection;

    public IndicadorTipo() {
    }

    public IndicadorTipo(Integer id) {
        this.id = id;
    }

    public IndicadorTipo(Integer id, String nombre, String descripcion, Short estado, Date fechaCreacion, Date fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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
    public Collection<Clasificacion> getClasificacionCollection() {
        return clasificacionCollection;
    }

    public void setClasificacionCollection(Collection<Clasificacion> clasificacionCollection) {
        this.clasificacionCollection = clasificacionCollection;
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
        if (!(object instanceof IndicadorTipo)) {
            return false;
        }
        IndicadorTipo other = (IndicadorTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.IndicadorTipo[ id=" + id + " ]";
    }
    
}
