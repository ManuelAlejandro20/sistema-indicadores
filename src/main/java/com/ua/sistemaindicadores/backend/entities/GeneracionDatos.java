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
@Table(name = "generacion_datos", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneracionDatos.findAll", query = "SELECT g FROM GeneracionDatos g"),
    @NamedQuery(name = "GeneracionDatos.findById", query = "SELECT g FROM GeneracionDatos g WHERE g.id = :id"),
    @NamedQuery(name = "GeneracionDatos.findByGeneracionDatos", query = "SELECT g FROM GeneracionDatos g WHERE g.generacionDatos = :generacionDatos")})
public class GeneracionDatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "generacion_datos")
    private String generacionDatos;
    @ManyToMany(mappedBy = "generacionDatosCollection")
    private Collection<Indicador> indicadorCollection;

    public GeneracionDatos() {
    }

    public GeneracionDatos(Integer id) {
        this.id = id;
    }

    public GeneracionDatos(Integer id, String generacionDatos) {
        this.id = id;
        this.generacionDatos = generacionDatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeneracionDatos() {
        return generacionDatos;
    }

    public void setGeneracionDatos(String generacionDatos) {
        this.generacionDatos = generacionDatos;
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
        if (!(object instanceof GeneracionDatos)) {
            return false;
        }
        GeneracionDatos other = (GeneracionDatos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.GeneracionDatos[ id=" + id + " ]";
    }
    
}
