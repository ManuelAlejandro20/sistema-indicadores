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
@Table(name = "select_frecuencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SelectFrecuencia.findAll", query = "SELECT s FROM SelectFrecuencia s"),
    @NamedQuery(name = "SelectFrecuencia.findByFrecuenciaMedicion", query = "SELECT s FROM SelectFrecuencia s WHERE s.frecuenciaMedicion = :frecuenciaMedicion")})
public class SelectFrecuencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "frecuencia_medicion")
    private String frecuenciaMedicion;

    public SelectFrecuencia() {
    }

    public SelectFrecuencia(String frecuenciaMedicion) {
        this.frecuenciaMedicion = frecuenciaMedicion;
    }

    public String getFrecuenciaMedicion() {
        return frecuenciaMedicion;
    }

    public void setFrecuenciaMedicion(String frecuenciaMedicion) {
        this.frecuenciaMedicion = frecuenciaMedicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frecuenciaMedicion != null ? frecuenciaMedicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SelectFrecuencia)) {
            return false;
        }
        SelectFrecuencia other = (SelectFrecuencia) object;
        if ((this.frecuenciaMedicion == null && other.frecuenciaMedicion != null) || (this.frecuenciaMedicion != null && !this.frecuenciaMedicion.equals(other.frecuenciaMedicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.SelectFrecuencia[ frecuenciaMedicion=" + frecuenciaMedicion + " ]";
    }
    
}
