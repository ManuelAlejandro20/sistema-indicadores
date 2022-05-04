/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aleja
 */
@Embeddable
public class IndicadorAnioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "indicador_id")
    private Integer indicadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_id")
    private Integer anioId;

    public IndicadorAnioPK() {
    }

    public IndicadorAnioPK(Integer indicadorId, Integer anioId) {
        this.indicadorId = indicadorId;
        this.anioId = anioId;
    }

    public Integer getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(Integer indicadorId) {
        this.indicadorId = indicadorId;
    }

    public Integer getAnioId() {
        return anioId;
    }

    public void setAnioId(Integer anioId) {
        this.anioId = anioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) indicadorId;
        hash += (int) anioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicadorAnioPK)) {
            return false;
        }
        IndicadorAnioPK other = (IndicadorAnioPK) object;
        if (this.indicadorId != other.indicadorId) {
            return false;
        }
        if (this.anioId != other.anioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.IndicadorAnioPK[ indicadorId=" + indicadorId + ", anioId=" + anioId + " ]";
    }
    
}