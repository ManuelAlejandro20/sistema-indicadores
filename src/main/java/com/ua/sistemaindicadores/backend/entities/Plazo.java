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
@Table(name = "plazo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plazo.findAll", query = "SELECT p FROM Plazo p"),
    @NamedQuery(name = "Plazo.findById", query = "SELECT p FROM Plazo p WHERE p.id = :id"),
    @NamedQuery(name = "Plazo.findByPlazo", query = "SELECT p FROM Plazo p WHERE p.plazo = :plazo")})
public class Plazo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "plazo")
    private String plazo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plazoId")
    private Collection<Indicador> indicadorCollection;

    public Plazo() {
    }

    public Plazo(Integer id) {
        this.id = id;
    }

    public Plazo(Integer id, String plazo) {
        this.id = id;
        this.plazo = plazo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
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
        if (!(object instanceof Plazo)) {
            return false;
        }
        Plazo other = (Plazo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Plazo[ id=" + id + " ]";
    }
    
}
