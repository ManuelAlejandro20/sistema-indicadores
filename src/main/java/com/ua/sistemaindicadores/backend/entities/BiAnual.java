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
@Table(name = "bi_anual", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BiAnual.findAll", query = "SELECT b FROM BiAnual b"),
    @NamedQuery(name = "BiAnual.findById", query = "SELECT b FROM BiAnual b WHERE b.id = :id"),
    @NamedQuery(name = "BiAnual.findByAnio", query = "SELECT b FROM BiAnual b WHERE b.anio = :anio")})
public class BiAnual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "biAnual")
    private Collection<IndicadorMesSemestreAnioBianual> indicadorMesSemestreAnioBianualCollection;

    public BiAnual() {
    }

    public BiAnual(Integer id) {
        this.id = id;
    }

    public BiAnual(Integer id, int anio) {
        this.id = id;
        this.anio = anio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
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
        if (!(object instanceof BiAnual)) {
            return false;
        }
        BiAnual other = (BiAnual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.BiAnual[ id=" + id + " ]";
    }
    
}
