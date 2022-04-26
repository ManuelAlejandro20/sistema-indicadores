/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "select_lineamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SelectLineamiento.findAll", query = "SELECT s FROM SelectLineamiento s"),
    @NamedQuery(name = "SelectLineamiento.findByLineamiento", query = "SELECT s FROM SelectLineamiento s WHERE s.lineamiento = :lineamiento")})
public class SelectLineamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lineamiento")
    private String lineamiento;

    public SelectLineamiento() {
    }

    public SelectLineamiento(String lineamiento) {
        this.lineamiento = lineamiento;
    }

    public String getLineamiento() {
        return lineamiento;
    }

    public void setLineamiento(String lineamiento) {
        this.lineamiento = lineamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineamiento != null ? lineamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SelectLineamiento)) {
            return false;
        }
        SelectLineamiento other = (SelectLineamiento) object;
        if ((this.lineamiento == null && other.lineamiento != null) || (this.lineamiento != null && !this.lineamiento.equals(other.lineamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.SelectLineamiento[ lineamiento=" + lineamiento + " ]";
    }
    
}
