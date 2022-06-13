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
@Table(name = "unidad_proveedora", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadProveedora.findAll", query = "SELECT u FROM UnidadProveedora u"),
    @NamedQuery(name = "UnidadProveedora.findById", query = "SELECT u FROM UnidadProveedora u WHERE u.id = :id"),
    @NamedQuery(name = "UnidadProveedora.findByUnidadProveedora", query = "SELECT u FROM UnidadProveedora u WHERE u.unidadProveedora = :unidadProveedora")})
public class UnidadProveedora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "unidad_proveedora")
    private String unidadProveedora;
    @ManyToMany(mappedBy = "unidadProveedoraCollection")
    private Collection<Indicador> indicadorCollection;

    public UnidadProveedora() {
    }

    public UnidadProveedora(Integer id) {
        this.id = id;
    }

    public UnidadProveedora(Integer id, String unidadProveedora) {
        this.id = id;
        this.unidadProveedora = unidadProveedora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnidadProveedora() {
        return unidadProveedora;
    }

    public void setUnidadProveedora(String unidadProveedora) {
        this.unidadProveedora = unidadProveedora;
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
        if (!(object instanceof UnidadProveedora)) {
            return false;
        }
        UnidadProveedora other = (UnidadProveedora) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.UnidadProveedora[ id=" + id + " ]";
    }
    
}
