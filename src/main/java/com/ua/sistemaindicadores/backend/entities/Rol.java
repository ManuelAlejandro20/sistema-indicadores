/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import com.ua.sistemaindicadores.backend.enums.ConstanteRol;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

/**
 *
 * @author guilberto
 */
@Entity
@Table(name = "rol", schema = "credenciales")
@DynamicInsert
@DynamicUpdate
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.identificarConstante", query = "SELECT r FROM Rol r WHERE r.constanteRol = :constanteRol"),
    @NamedQuery(name = "Rol.obtenerOrdenados", query = "SELECT r FROM Rol r ORDER BY r.nombre ASC"),
    @NamedQuery(name = "Rol.identificarNombre", query = "SELECT r FROM Rol r WHERE r.nombre = :nombre")
})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "RolSequenceGenerator", sequenceName = "rol_id_seq", allocationSize = 1, schema = "credenciales")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RolSequenceGenerator")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "constante", columnDefinition = "credenciales.constante_rol")
    @Type(type = "pgsql_enum")
    private ConstanteRol constanteRol;

    public Rol() {
    }

    public Rol(Integer id) {
        this.id = id;
    }

    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ConstanteRol getConstanteRol() {
        return constanteRol;
    }

    public void setConstanteRol(ConstanteRol constanteRol) {
        this.constanteRol = constanteRol;
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
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Rol[ id=" + id + " ]";
    }

}
