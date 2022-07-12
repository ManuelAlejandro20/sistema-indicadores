/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "indicador_mes_semestre_anio_bianual", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findAll", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByIndicadorId", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.indicadorMesSemestreAnioBianualPK.indicadorId = :indicadorId"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByAnioId", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.indicadorMesSemestreAnioBianualPK.anioId = :anioId"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByBianualId", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.indicadorMesSemestreAnioBianualPK.bianualId = :bianualId"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByMesId", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.indicadorMesSemestreAnioBianualPK.mesId = :mesId"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findBySemestreId", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.indicadorMesSemestreAnioBianualPK.semestreId = :semestreId"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByNombre", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByMonto", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.monto = :monto"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByCantActividadesPeriodo", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.cantActividadesPeriodo = :cantActividadesPeriodo"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByPorcActividadesPeriodo", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.porcActividadesPeriodo = :porcActividadesPeriodo"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByLogroPeriodo", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.logroPeriodo = :logroPeriodo"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByMontoPeriodo", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.montoPeriodo = :montoPeriodo"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByLogroIndicador", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.logroIndicador = :logroIndicador"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByMeta", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.meta = :meta"),
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findByFlag", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.flag = :flag")})
public class IndicadorMesSemestreAnioBianual implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IndicadorMesSemestreAnioBianualPK indicadorMesSemestreAnioBianualPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "cant_actividades_periodo")
    private Integer cantActividadesPeriodo;
    @Column(name = "porc_actividades_periodo")
    private Integer porcActividadesPeriodo;
    @Column(name = "logro_periodo")
    private Integer logroPeriodo;
    @Column(name = "monto_periodo")
    private Integer montoPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "logro_indicador")
    private int logroIndicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "meta")
    private int meta;
    @Column(name = "flag")
    private Integer flag;
    @JoinColumn(name = "anio_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Anio anio;
    @JoinColumn(name = "bianual_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BiAnual biAnual;
    @JoinColumn(name = "indicador_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Indicador indicador;
    @JoinColumn(name = "mes_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mes mes;
    @JoinColumn(name = "semestre_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Semestre semestre;

    public IndicadorMesSemestreAnioBianual() {
    }

    public IndicadorMesSemestreAnioBianual(IndicadorMesSemestreAnioBianualPK indicadorMesSemestreAnioBianualPK) {
        this.indicadorMesSemestreAnioBianualPK = indicadorMesSemestreAnioBianualPK;
    }

    public IndicadorMesSemestreAnioBianual(IndicadorMesSemestreAnioBianualPK indicadorMesSemestreAnioBianualPK, String nombre, int logroIndicador, int meta) {
        this.indicadorMesSemestreAnioBianualPK = indicadorMesSemestreAnioBianualPK;
        this.nombre = nombre;
        this.logroIndicador = logroIndicador;
        this.meta = meta;
    }

    public IndicadorMesSemestreAnioBianual(int indicadorId, int anioId, int bianualId, int mesId, int semestreId) {
        this.indicadorMesSemestreAnioBianualPK = new IndicadorMesSemestreAnioBianualPK(indicadorId, anioId, bianualId, mesId, semestreId);
    }

    public IndicadorMesSemestreAnioBianualPK getIndicadorMesSemestreAnioBianualPK() {
        return indicadorMesSemestreAnioBianualPK;
    }

    public void setIndicadorMesSemestreAnioBianualPK(IndicadorMesSemestreAnioBianualPK indicadorMesSemestreAnioBianualPK) {
        this.indicadorMesSemestreAnioBianualPK = indicadorMesSemestreAnioBianualPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getCantActividadesPeriodo() {
        return cantActividadesPeriodo;
    }

    public void setCantActividadesPeriodo(Integer cantActividadesPeriodo) {
        this.cantActividadesPeriodo = cantActividadesPeriodo;
    }

    public Integer getPorcActividadesPeriodo() {
        return porcActividadesPeriodo;
    }

    public void setPorcActividadesPeriodo(Integer porcActividadesPeriodo) {
        this.porcActividadesPeriodo = porcActividadesPeriodo;
    }

    public Integer getLogroPeriodo() {
        return logroPeriodo;
    }

    public void setLogroPeriodo(Integer logroPeriodo) {
        this.logroPeriodo = logroPeriodo;
    }

    public Integer getMontoPeriodo() {
        return montoPeriodo;
    }

    public void setMontoPeriodo(Integer montoPeriodo) {
        this.montoPeriodo = montoPeriodo;
    }

    public int getLogroIndicador() {
        return logroIndicador;
    }

    public void setLogroIndicador(int logroIndicador) {
        this.logroIndicador = logroIndicador;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Anio getAnio() {
        return anio;
    }

    public void setAnio(Anio anio) {
        this.anio = anio;
    }

    public BiAnual getBiAnual() {
        return biAnual;
    }

    public void setBiAnual(BiAnual biAnual) {
        this.biAnual = biAnual;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indicadorMesSemestreAnioBianualPK != null ? indicadorMesSemestreAnioBianualPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicadorMesSemestreAnioBianual)) {
            return false;
        }
        IndicadorMesSemestreAnioBianual other = (IndicadorMesSemestreAnioBianual) object;
        if ((this.indicadorMesSemestreAnioBianualPK == null && other.indicadorMesSemestreAnioBianualPK != null) || (this.indicadorMesSemestreAnioBianualPK != null && !this.indicadorMesSemestreAnioBianualPK.equals(other.indicadorMesSemestreAnioBianualPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.IndicadorMesSemestreAnioBianual[ indicadorMesSemestreAnioBianualPK=" + indicadorMesSemestreAnioBianualPK + " ]";
    }
    
}
