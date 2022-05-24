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
@Table(name = "unidad_representacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadRepresentacion.findAll", query = "SELECT u FROM UnidadRepresentacion u"),
    @NamedQuery(name = "UnidadRepresentacion.findById", query = "SELECT u FROM UnidadRepresentacion u WHERE u.id = :id"),
    @NamedQuery(name = "UnidadRepresentacion.findByUnidadRepresentacion", query = "SELECT u FROM UnidadRepresentacion u WHERE u.unidadRepresentacion = :unidadRepresentacion")})
public class UnidadRepresentacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "unidad_representacion")
    private String unidadRepresentacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadRepresentacionId")
    private Collection<Indicador> indicadorCollection;

    public UnidadRepresentacion() {
    }

    public UnidadRepresentacion(Integer id) {
        this.id = id;
    }

    public UnidadRepresentacion(Integer id, String unidadRepresentacion) {
        this.id = id;
        this.unidadRepresentacion = unidadRepresentacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnidadRepresentacion() {
        return unidadRepresentacion;
    }

    public void setUnidadRepresentacion(String unidadRepresentacion) {
        this.unidadRepresentacion = unidadRepresentacion;
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
        if (!(object instanceof UnidadRepresentacion)) {
            return false;
        }
        UnidadRepresentacion other = (UnidadRepresentacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion[ id=" + id + " ]";
    }
    
}
