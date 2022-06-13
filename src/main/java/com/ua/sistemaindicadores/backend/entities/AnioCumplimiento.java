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
@Table(name = "anio_cumplimiento", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnioCumplimiento.findAll", query = "SELECT a FROM AnioCumplimiento a"),
    @NamedQuery(name = "AnioCumplimiento.findById", query = "SELECT a FROM AnioCumplimiento a WHERE a.id = :id"),
    @NamedQuery(name = "AnioCumplimiento.findByAnioCumplimiento", query = "SELECT a FROM AnioCumplimiento a WHERE a.anioCumplimiento = :anioCumplimiento")})
public class AnioCumplimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_cumplimiento")
    private int anioCumplimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anioCumplimientoId")
    private Collection<Indicador> indicadorCollection;

    public AnioCumplimiento() {
    }

    public AnioCumplimiento(Integer id) {
        this.id = id;
    }

    public AnioCumplimiento(Integer id, int anioCumplimiento) {
        this.id = id;
        this.anioCumplimiento = anioCumplimiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnioCumplimiento() {
        return anioCumplimiento;
    }

    public void setAnioCumplimiento(int anioCumplimiento) {
        this.anioCumplimiento = anioCumplimiento;
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
        if (!(object instanceof AnioCumplimiento)) {
            return false;
        }
        AnioCumplimiento other = (AnioCumplimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.AnioCumplimiento[ id=" + id + " ]";
    }
    
}
