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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "IndicadorMesSemestreAnioBianual.findById", query = "SELECT i FROM IndicadorMesSemestreAnioBianual i WHERE i.id = :id"),
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @JoinColumn(name = "anio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Anio anioId;
    @JoinColumn(name = "bianual_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BiAnual bianualId;
    @JoinColumn(name = "indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorId;
    @JoinColumn(name = "mes_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Mes mesId;
    @JoinColumn(name = "semestre_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Semestre semestreId;

    public IndicadorMesSemestreAnioBianual() {
    }

    public IndicadorMesSemestreAnioBianual(Integer id) {
        this.id = id;
    }

    public IndicadorMesSemestreAnioBianual(Integer id, String nombre, int logroIndicador, int meta) {
        this.id = id;
        this.nombre = nombre;
        this.logroIndicador = logroIndicador;
        this.meta = meta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Anio getAnioId() {
        return anioId;
    }

    public void setAnioId(Anio anioId) {
        this.anioId = anioId;
    }

    public BiAnual getBianualId() {
        return bianualId;
    }

    public void setBianualId(BiAnual bianualId) {
        this.bianualId = bianualId;
    }

    public Indicador getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(Indicador indicadorId) {
        this.indicadorId = indicadorId;
    }

    public Mes getMesId() {
        return mesId;
    }

    public void setMesId(Mes mesId) {
        this.mesId = mesId;
    }

    public Semestre getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(Semestre semestreId) {
        this.semestreId = semestreId;
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
        if (!(object instanceof IndicadorMesSemestreAnioBianual)) {
            return false;
        }
        IndicadorMesSemestreAnioBianual other = (IndicadorMesSemestreAnioBianual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.IndicadorMesSemestreAnioBianual[ id=" + id + " ]";
    }
    
}
