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
@Table(name = "ajuste_pdei")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AjustePdei.findAll", query = "SELECT a FROM AjustePdei a"),
    @NamedQuery(name = "AjustePdei.findById", query = "SELECT a FROM AjustePdei a WHERE a.id = :id"),
    @NamedQuery(name = "AjustePdei.findByAjustePdei", query = "SELECT a FROM AjustePdei a WHERE a.ajustePdei = :ajustePdei")})
public class AjustePdei implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ajuste_pdei")
    private String ajustePdei;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ajustePdeiId")
    private Collection<Indicador> indicadorCollection;

    public AjustePdei() {
    }

    public AjustePdei(Integer id) {
        this.id = id;
    }

    public AjustePdei(Integer id, String ajustePdei) {
        this.id = id;
        this.ajustePdei = ajustePdei;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAjustePdei() {
        return ajustePdei;
    }

    public void setAjustePdei(String ajustePdei) {
        this.ajustePdei = ajustePdei;
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
        if (!(object instanceof AjustePdei)) {
            return false;
        }
        AjustePdei other = (AjustePdei) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.AjustePdei[ id=" + id + " ]";
    }
    
}
