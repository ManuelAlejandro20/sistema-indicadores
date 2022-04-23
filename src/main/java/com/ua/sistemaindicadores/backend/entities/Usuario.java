/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author guilberto
 */
@Entity
@Table(name = "usuario", schema = "credenciales")
@DynamicInsert
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.obtenerPersonaRut", query = "SELECT u FROM Usuario u INNER JOIN u.persona p WHERE p.rut = :rut"),
    @NamedQuery(name = "Usuario.obtenerUsuarioCorreoDTO", query
            = "SELECT NEW com.ua.sistemaindicadores.backend.dtos.UsuarioDTO(p.rut, p.digitoVerificador, p.nombreLegal, p.nombreSocial, p.apellidoPaterno, p.apellidoMaterno, p.generoLegal, p.generoSocial, p.email, p.emailInstitucional, u.personaId, u.habilitado, u.fechaExpiracion, u.fechaUltimoCambioClave, p.fechaNacimiento) "
            + "FROM Usuario u INNER JOIN u.persona p INNER JOIN u.roles r "
            + "WHERE :rolUsuario = r.nombre and u.habilitado = true")
})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "persona_id")
    private Integer personaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpiracion;
    @Column(name = "fecha_ultimo_cambio_clave")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoCambioClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cambiar_clave")
    private boolean cambiarClave;
    @JoinTable(name = "usuario_rol", schema = "credenciales", joinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "persona_id")}, inverseJoinColumns = {
        @JoinColumn(name = "rol_id", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Rol> roles = new HashSet<>();
    @JoinColumn(name = "persona_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @MapsId
    private Persona persona;

    @Transient
    private String claveSinCifrar;

    public Usuario() {
    }

    public Usuario(Integer personaId) {
        this.personaId = personaId;
    }

    public Usuario(Integer personaId, String clave, boolean habilitado, boolean cambiarClave) {
        this.personaId = personaId;
        this.clave = clave;
        this.habilitado = habilitado;
        this.cambiarClave = cambiarClave;
    }

    public Usuario(Persona persona, String clave, boolean habilitado, boolean cambiarClave) {
        this.personaId = persona.getId();
        this.clave = clave;
        this.habilitado = habilitado;
        this.cambiarClave = cambiarClave;
        this.persona = persona;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Date getFechaUltimoCambioClave() {
        return fechaUltimoCambioClave;
    }

    public void setFechaUltimoCambioClave(Date fechaUltimoCambioClave) {
        this.fechaUltimoCambioClave = fechaUltimoCambioClave;
    }

    public boolean getCambiarClave() {
        return cambiarClave;
    }

    public void setCambiarClave(boolean cambiarClave) {
        this.cambiarClave = cambiarClave;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getClaveSinCifrar() {
        return claveSinCifrar;
    }

    public void setClaveSinCifrar(String claveSinCifrar) {
        this.claveSinCifrar = claveSinCifrar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Usuario[ personaId=" + personaId + " ]";
    }

}

