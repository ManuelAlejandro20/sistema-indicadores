/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "frecuencia_medicion", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrecuenciaMedicion.findAll", query = "SELECT f FROM FrecuenciaMedicion f"),
    @NamedQuery(name = "FrecuenciaMedicion.findById", query = "SELECT f FROM FrecuenciaMedicion f WHERE f.id = :id"),
    @NamedQuery(name = "FrecuenciaMedicion.findByFrecuenciaMedicion", query = "SELECT f FROM FrecuenciaMedicion f WHERE f.frecuenciaMedicion = :frecuenciaMedicion")})
public class FrecuenciaMedicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "frecuencia_medicion")
    private String frecuenciaMedicion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frecuenciaMedicionId")
    private Collection<Indicador> indicadorCollection;

    public FrecuenciaMedicion() {
    }

    public FrecuenciaMedicion(Integer id) {
        this.id = id;
    }

    public FrecuenciaMedicion(Integer id, String frecuenciaMedicion) {
        this.id = id;
        this.frecuenciaMedicion = frecuenciaMedicion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrecuenciaMedicion() {
        return frecuenciaMedicion;
    }

    public void setFrecuenciaMedicion(String frecuenciaMedicion) {
        this.frecuenciaMedicion = frecuenciaMedicion;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrecuenciaMedicion)) {
            return false;
        }
        FrecuenciaMedicion other = (FrecuenciaMedicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion[ id=" + id + " ]";
    }
    
}
