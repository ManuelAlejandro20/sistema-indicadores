/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "anio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anio.findAll", query = "SELECT a FROM Anio a"),
    @NamedQuery(name = "Anio.findByFecha", query = "SELECT a FROM Anio a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Anio.findByDescripcion", query = "SELECT a FROM Anio a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Anio.findByFlag", query = "SELECT a FROM Anio a WHERE a.flag = :flag")})
public class Anio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fecha")
    private Integer fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "flag")
    private short flag;
    @JoinTable(name = "indicador_anio", joinColumns = {
        @JoinColumn(name = "anio_fecha", referencedColumnName = "fecha")}, inverseJoinColumns = {
        @JoinColumn(name = "indicador_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Indicador> indicadorCollection;

    public Anio() {
    }

    public Anio(Integer fecha) {
        this.fecha = fecha;
    }

    public Anio(Integer fecha, String descripcion, short flag) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.flag = flag;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getFlag() {
        return flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    @XmlTransient
    public Collection<Indicador> getIndicadorCollection() {
        return indicadorCollection;
    }

    public void setIndicadorCollection(Collection<Indicador> indicadorCollection) {
        this.indicadorCollection = indicadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anio)) {
            return false;
        }
        Anio other = (Anio) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Anio[ fecha=" + fecha + " ]";
    }
    
}
