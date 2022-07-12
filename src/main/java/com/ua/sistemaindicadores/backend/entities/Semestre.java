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
@Table(name = "semestre", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semestre.findAll", query = "SELECT s FROM Semestre s"),
    @NamedQuery(name = "Semestre.findById", query = "SELECT s FROM Semestre s WHERE s.id = :id"),
    @NamedQuery(name = "Semestre.findBySemestre", query = "SELECT s FROM Semestre s WHERE s.semestre = :semestre")})
public class Semestre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "semestre")
    private String semestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semestre")
    private Collection<IndicadorMesSemestreAnioBianual> indicadorMesSemestreAnioBianualCollection;

    public Semestre() {
    }

    public Semestre(Integer id) {
        this.id = id;
    }

    public Semestre(Integer id, String semestre) {
        this.id = id;
        this.semestre = semestre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
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
        if (!(object instanceof Semestre)) {
            return false;
        }
        Semestre other = (Semestre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Semestre[ id=" + id + " ]";
    }
    
}
