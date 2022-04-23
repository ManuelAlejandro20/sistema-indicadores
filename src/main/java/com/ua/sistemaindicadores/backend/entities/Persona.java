/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.entities;

import com.ua.sistemaindicadores.backend.constraints.RutValido;
import com.ua.sistemaindicadores.backend.exceptions.RutNoFormateableException;
import com.ua.sistemaindicadores.backend.exceptions.RutNoIdentificableException;
import com.ua.sistemaindicadores.backend.utils.FormatoRut;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author guilberto
 */
@Entity
@Table(name = "persona", schema = "credenciales")
@DynamicInsert
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByRut", query = "SELECT p FROM Persona p WHERE p.rut = :rut"),
    @NamedQuery(name = "Persona.obtenerIdRut", query = "SELECT p.id FROM Persona p WHERE p.rut = :rut"),
    @NamedQuery(name = "Persona.obtenerListaRut", query = "SELECT p.rut FROM Persona p WHERE p.rut IS NOT NULL"),
    @NamedQuery(name = "Persona.obtenerListaEmails", query = "SELECT LOWER(p.email) FROM Persona p WHERE p.email IS NOT NULL"),
    @NamedQuery(name = "Persona.obtenerListaEmailsInstitucionales", query = "SELECT LOWER(p.emailInstitucional) FROM Persona p WHERE p.emailInstitucional IS NOT NULL")
})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name = "PersonaSequenceGenerator", sequenceName = "persona_id_seq", allocationSize = 100, schema = "credenciales")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PersonaSequenceGenerator")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "rut")
    private Integer rut;
    @Column(name = "digito_verificador")
    private Character digitoVerificador;
//    @Email
    @Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 300)
    @Column(name = "dni")
    private String dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "nombre_legal")
    private String nombreLegal;
    @Size(max = 1000)
    @Column(name = "nombre_social")
    private String nombreSocial;
    @Size(max = 1)
    @Pattern(regexp = "F|M", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "genero_legal")
    private String generoLegal;
    @Size(max = 1)
    @Pattern(regexp = "F|M", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "genero_social")
    private String generoSocial;
//    @Email
    @Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")
    @Size(max = 255)
    @Column(name = "email_institucional")
    private String emailInstitucional;
    @Past
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
//    private PersonaFotografia personaFotografia;

    @Transient
    @RutValido
    private String rutFormateado;

    public Persona() {
    }

    public Persona(Integer id) {
        this.id = id;
    }

    public Persona(Integer id, String apellidoPaterno, String apellidoMaterno, String nombreLegal) {
        this.id = id;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreLegal = nombreLegal;
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public Character getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(Character digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreLegal() {
        return nombreLegal;
    }

    public void setNombreLegal(String nombreLegal) {
        this.nombreLegal = nombreLegal;
    }

    public String getNombreSocial() {
        return nombreSocial;
    }

    public void setNombreSocial(String nombreSocial) {
        this.nombreSocial = nombreSocial;
    }

    public String getGeneroLegal() {
        return generoLegal;
    }

    public void setGeneroLegal(String generoLegal) {
        this.generoLegal = generoLegal;
    }

    public String getGeneroSocial() {
        return generoSocial;
    }

    public void setGeneroSocial(String generoSocial) {
        this.generoSocial = generoSocial;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getRutFormateado() {
        return rutFormateado;
    }

    public void setRutFormateado(String rutFormateado) {
        this.rutFormateado = rutFormateado;
    }

    public String getNombreCompleto() {
        return (nombreSocial != null ? nombreSocial : nombreLegal) + " " + apellidoPaterno + " " + apellidoMaterno;
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
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ua.sistemaindicadores.backend.entities.Persona[ id=" + id + " ]";
    }

    @PostLoad
    public void formatearRut() {
        try {
            rutFormateado = FormatoRut.formatearRut(rut, digitoVerificador);
        } catch (RutNoFormateableException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PrePersist
    public void asignarRut() {
        try {
            Object[] resultado = FormatoRut.obtenerRut(rutFormateado);
            rut = (Integer) resultado[0];
            digitoVerificador = (Character) resultado[1];
        } catch (RutNoIdentificableException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
