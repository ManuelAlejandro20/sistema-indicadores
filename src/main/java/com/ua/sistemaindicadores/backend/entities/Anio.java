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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "anio", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anio.findAll", query = "SELECT a FROM Anio a"),
    @NamedQuery(name = "Anio.findByFlag", query = "SELECT a FROM Anio a WHERE a.flag = :flag"),
    @NamedQuery(name = "Anio.findById", query = "SELECT a FROM Anio a WHERE a.id = :id"),
    @NamedQuery(name = "Anio.findByFecha", query = "SELECT a FROM Anio a WHERE a.fecha = :fecha")})
public class Anio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "flag")
    private short flag;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    private int fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anio")
    private Collection<IndicadorAnio> indicadorAnioCollection;

    public Anio() {
    }

    public Anio(Integer id) {
        this.id = id;
    }

    public Anio(Integer id, short flag, int fecha) {
        this.id = id;
        this.flag = flag;
        this.fecha = fecha;
    }

    public short getFlag() {
        return flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<IndicadorAnio> getIndicadorAnioCollection() {
        return indicadorAnioCollection;
    }

    public void setIndicadorAnioCollection(Collection<IndicadorAnio> indicadorAnioCollection) {
        this.indicadorAnioCollection = indicadorAnioCollection;
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
        if (!(object instanceof Anio)) {
            return false;
        }
        Anio other = (Anio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Anio[ id=" + id + " ]";
    }
    
}
