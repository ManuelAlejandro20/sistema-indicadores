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
public class IndicadorMesSemestreAnioBianualPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "indicador_id")
    private int indicadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_id")
    private int anioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bianual_id")
    private int bianualId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mes_id")
    private int mesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semestre_id")
    private int semestreId;

    public IndicadorMesSemestreAnioBianualPK() {
    }

    public IndicadorMesSemestreAnioBianualPK(int indicadorId, int anioId, int bianualId, int mesId, int semestreId) {
        this.indicadorId = indicadorId;
        this.anioId = anioId;
        this.bianualId = bianualId;
        this.mesId = mesId;
        this.semestreId = semestreId;
    }

    public int getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(int indicadorId) {
        this.indicadorId = indicadorId;
    }

    public int getAnioId() {
        return anioId;
    }

    public void setAnioId(int anioId) {
        this.anioId = anioId;
    }

    public int getBianualId() {
        return bianualId;
    }

    public void setBianualId(int bianualId) {
        this.bianualId = bianualId;
    }

    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }

    public int getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(int semestreId) {
        this.semestreId = semestreId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) indicadorId;
        hash += (int) anioId;
        hash += (int) bianualId;
        hash += (int) mesId;
        hash += (int) semestreId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicadorMesSemestreAnioBianualPK)) {
            return false;
        }
        IndicadorMesSemestreAnioBianualPK other = (IndicadorMesSemestreAnioBianualPK) object;
        if (this.indicadorId != other.indicadorId) {
            return false;
        }
        if (this.anioId != other.anioId) {
            return false;
        }
        if (this.bianualId != other.bianualId) {
            return false;
        }
        if (this.mesId != other.mesId) {
            return false;
        }
        if (this.semestreId != other.semestreId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.IndicadorMesSemestreAnioBianualPK[ indicadorId=" + indicadorId + ", anioId=" + anioId + ", bianualId=" + bianualId + ", mesId=" + mesId + ", semestreId=" + semestreId + " ]";
    }
    
}
