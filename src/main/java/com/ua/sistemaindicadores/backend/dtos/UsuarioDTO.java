/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.dtos;

import java.util.Date;
import java.util.List;


/**
 *
 * @author guilberto
 */
public class UsuarioDTO {
    private Integer rut;
    private Character digitoVerificador;
    private String nombreLegal;
    private String nombreSocial;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String generoLegal;
    private String generoSocial;
    private Date fechaNacimiento;
    private String email;
    private String emailInstitucional;
    private Integer personaId;
    private boolean habilitado;
    private Date fechaExpiracion;
    private Date fechaUltimoCambioClave;
    private List<String> roles;

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public boolean isHabilitado() {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UsuarioDTO(
            Integer rut,
            Character digitoVerificador,
            String nombreLegal,
            String nombreSocial,
            String apellidoPaterno,
            String apellidoMaterno,
            String generoLegal,
            String generoSocial,
            String email,
            String emailInstitucional,
            Integer personaId,
            boolean habilitado,
            Date fechaExpiracion,
            Date fechaUltimoCambioClave,
            Date fechaNacimiento) {
        this.rut = rut;
        this.digitoVerificador = digitoVerificador;
        this.nombreLegal = nombreLegal;
        this.nombreSocial = nombreSocial;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.generoLegal = generoLegal;
        this.generoSocial = generoSocial;
        this.email = email;
        this.emailInstitucional = emailInstitucional;
        this.personaId = personaId;
        this.habilitado = habilitado;
        this.fechaExpiracion = fechaExpiracion;
        this.fechaUltimoCambioClave = fechaUltimoCambioClave;
        this.fechaNacimiento = fechaNacimiento;
    }

    public UsuarioDTO() {
    }

}
