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
@Table(name = "mes", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mes.findAll", query = "SELECT m FROM Mes m"),
    @NamedQuery(name = "Mes.findById", query = "SELECT m FROM Mes m WHERE m.id = :id"),
    @NamedQuery(name = "Mes.findByMes", query = "SELECT m FROM Mes m WHERE m.mes = :mes")})
public class Mes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mes")
    private String mes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mes")
    private Collection<IndicadorMesSemestreAnioBianual> indicadorMesSemestreAnioBianualCollection;

    public Mes() {
    }

    public Mes(Integer id) {
        this.id = id;
    }

    public Mes(Integer id, String mes) {
        this.id = id;
        this.mes = mes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
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
        if (!(object instanceof Mes)) {
            return false;
        }
        Mes other = (Mes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Mes[ id=" + id + " ]";
    }
    
}
