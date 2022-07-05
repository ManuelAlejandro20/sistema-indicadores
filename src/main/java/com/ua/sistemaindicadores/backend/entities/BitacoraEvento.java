/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "bitacora_evento", schema = "indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BitacoraEvento.findAll", query = "SELECT b FROM BitacoraEvento b"),
    @NamedQuery(name = "BitacoraEvento.findById", query = "SELECT b FROM BitacoraEvento b WHERE b.id = :id"),
    @NamedQuery(name = "BitacoraEvento.findByFechaHora", query = "SELECT b FROM BitacoraEvento b WHERE b.fechaHora = :fechaHora"),
    @NamedQuery(name = "BitacoraEvento.findByAccionUsuario", query = "SELECT b FROM BitacoraEvento b WHERE b.accionUsuario = :accionUsuario"),
    @NamedQuery(name = "BitacoraEvento.findByTransactionId", query = "SELECT b FROM BitacoraEvento b WHERE b.transactionId = :transactionId"),
    @NamedQuery(name = "BitacoraEvento.findByDireccionCliente", query = "SELECT b FROM BitacoraEvento b WHERE b.direccionCliente = :direccionCliente"),
    @NamedQuery(name = "BitacoraEvento.findByJsessionId", query = "SELECT b FROM BitacoraEvento b WHERE b.jsessionId = :jsessionId"),
    @NamedQuery(name = "BitacoraEvento.findByUsuarioId", query = "SELECT b FROM BitacoraEvento b WHERE b.usuarioId = :usuarioId")})
public class BitacoraEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "accion_usuario")
    private String accionUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id")
    private long transactionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "direccion_cliente")
    private String direccionCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "jsession_id")
    private String jsessionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_id")
    private int usuarioId;

    public BitacoraEvento() {
    }

    public BitacoraEvento(Integer id) {
        this.id = id;
    }

    public BitacoraEvento(Integer id, Date fechaHora, String accionUsuario, long transactionId, String direccionCliente, String jsessionId, int usuarioId) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.accionUsuario = accionUsuario;
        this.transactionId = transactionId;
        this.direccionCliente = direccionCliente;
        this.jsessionId = jsessionId;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getAccionUsuario() {
        return accionUsuario;
    }

    public void setAccionUsuario(String accionUsuario) {
        this.accionUsuario = accionUsuario;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getJsessionId() {
        return jsessionId;
    }

    public void setJsessionId(String jsessionId) {
        this.jsessionId = jsessionId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof BitacoraEvento)) {
            return false;
        }
        BitacoraEvento other = (BitacoraEvento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.BitacoraEvento[ id=" + id + " ]";
    }
    
}
