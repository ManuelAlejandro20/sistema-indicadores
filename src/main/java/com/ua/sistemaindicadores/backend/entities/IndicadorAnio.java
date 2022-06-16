/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "indicador_anio", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicadorAnio.findAll", query = "SELECT i FROM IndicadorAnio i"),
    @NamedQuery(name = "IndicadorAnio.findByIndicadorId", query = "SELECT i FROM IndicadorAnio i WHERE i.indicadorAnioPK.indicadorId = :indicadorId"),
    @NamedQuery(name = "IndicadorAnio.findByAnioId", query = "SELECT i FROM IndicadorAnio i WHERE i.indicadorAnioPK.anioId = :anioId"),
    @NamedQuery(name = "IndicadorAnio.findByDescripcion", query = "SELECT i FROM IndicadorAnio i WHERE i.descripcion = :descripcion")})
public class IndicadorAnio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IndicadorAnioPK indicadorAnioPK;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "anio_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Anio anio;
    @JoinColumn(name = "indicador_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Indicador indicador;

    public IndicadorAnio() {
    }

    public IndicadorAnio(IndicadorAnioPK indicadorAnioPK) {
        this.indicadorAnioPK = indicadorAnioPK;
    }

    public IndicadorAnio(int indicadorId, int anioId) {
        this.indicadorAnioPK = new IndicadorAnioPK(indicadorId, anioId);
    }

    public IndicadorAnioPK getIndicadorAnioPK() {
        return indicadorAnioPK;
    }

    public void setIndicadorAnioPK(IndicadorAnioPK indicadorAnioPK) {
        this.indicadorAnioPK = indicadorAnioPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Anio getAnio() {
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indicadorAnioPK != null ? indicadorAnioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicadorAnio)) {
            return false;
        }
        IndicadorAnio other = (IndicadorAnio) object;
        if ((this.indicadorAnioPK == null && other.indicadorAnioPK != null) || (this.indicadorAnioPK != null && !this.indicadorAnioPK.equals(other.indicadorAnioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.IndicadorAnio[ indicadorAnioPK=" + indicadorAnioPK + " ]";
    }
    
}
