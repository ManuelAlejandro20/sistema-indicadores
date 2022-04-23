/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.services.UsuarioService;
import com.ua.sistemaindicadores.backend.exceptions.RutNoIdentificableException;
import com.ua.sistemaindicadores.backend.utils.FormatoRut;
import com.ua.sistemaindicadores.backend.entities.Usuario;
import com.vladmihalcea.hibernate.type.basic.Inet;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Ricardo
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Inject
    transient private UsuarioService usuarioService;
//
//    private Usuario usuario;
    private boolean rolAdministrador;
    private boolean rolUsuarioInternoSeguimientoConvenio;
    private boolean rolUsuarioExternoConvenio;
    private boolean rolAdministradorConvenio;
    private boolean rolUsuarioInternoCreacionConvenio;
    private boolean rolUsuarioInternoVraConvenio;
    private boolean rolUsuarioInternoVreConvenio;
    private boolean rolUsuarioInternoVriipConvenio;
    private boolean rolUsuarioInternoDjConvenio;
    private boolean rolUsuarioInternoOtlConvenio;
    private boolean rolUsuarioInternoRectoriaConvenio;
    private boolean rolSecretariaGeneral;
    private Usuario usuario;
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    @PostConstruct
    private void inicializar()
    {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if(principal != null && principal.getName() != null && !principal.getName().equals("anonymous"))
        {
            rolAdministrador = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMINISTRADOR");
            rolAdministradorConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMINISTRADOR_CONVENIO");
            rolUsuarioInternoSeguimientoConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_SEGUIMIENTO_CONVENIO");
            rolUsuarioExternoConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_EXTERNO_CONVENIO");
            rolUsuarioInternoCreacionConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_CREACION_CONVENIO");
            rolUsuarioInternoVraConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_VRA_CONVENIO");
            rolUsuarioInternoVreConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_VRE_CONVENIO");
            rolUsuarioInternoVriipConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_VRIIP_CONVENIO");
            rolUsuarioInternoDjConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_DJ_CONVENIO");
            rolUsuarioInternoOtlConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_OTL_CONVENIO");
            rolUsuarioInternoRectoriaConvenio = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USUARIO_INTERNO_RECTORIA_CONVENIO");
            rolSecretariaGeneral = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("SECRETARIA_GENERAL_CONVENIO");
            Integer rut;
            try
            {
                rut = (Integer) FormatoRut.obtenerRut(principal.getName())[0];
                usuario = usuarioService.obtenerUsuarioRut(rut);
            }catch(EJBException | RutNoIdentificableException ex)
            {
                Logger.getLogger(UsuarioBean.class.getName())
                        .log(Level.SEVERE,"Problema al obtener los roles del usuario.",ex);
                FacesContext.getCurrentInstance()
                        .addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Problema al obtener los roles del usuario. Contacte al administrador."));
            }
        }
    }
//Método encargado de cerrar sessión.
    public String cerrarSesion()throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath());
        fc.responseComplete();
        return null;
    }
    
//    public BitacoraEvento obtenerEvento(String accion, Integer idCuenta)
//    {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
//        BitacoraEvento evento = new BitacoraEvento();
//        evento.setAccionUsuario(accion);
//        evento.setDireccionCliente(new Inet(request.getHeader("X-Forwarded-For") != null ? request.getHeader("X.Forwarded-For"): request.getRemoteAddr()));
//        evento.setJsessionId(ec.getSessionId(true));
//        evento.setUsuarioId(idCuenta);
//        return evento;
//    }
    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public boolean isRolAdministrador() {
        return rolAdministrador;
    }

    public void setRolAdministrador(boolean rolAdministrador) {
        this.rolAdministrador = rolAdministrador;
    }

    public boolean isRolUsuarioInternoSeguimientoConvenio() {
        return rolUsuarioInternoSeguimientoConvenio;
    }

    public void setRolUsuarioInternoSeguimientoConvenio(boolean rolUsuarioInternoSeguimientoConvenio) {
        this.rolUsuarioInternoSeguimientoConvenio = rolUsuarioInternoSeguimientoConvenio;
    }

    public boolean isRolUsuarioExternoConvenio() {
        return rolUsuarioExternoConvenio;
    }

    public void setRolUsuarioExternoConvenio(boolean rolUsuarioExternoConvenio) {
        this.rolUsuarioExternoConvenio = rolUsuarioExternoConvenio;
    }

    public boolean isRolAdministradorConvenio() {
        return rolAdministradorConvenio;
    }

    public void setRolAdministradorConvenio(boolean rolAdministradorConvenio) {
        this.rolAdministradorConvenio = rolAdministradorConvenio;
    }

    public boolean isRolUsuarioInternoCreacionConvenio() {
        return rolUsuarioInternoCreacionConvenio;
    }

    public void setRolUsuarioInternoCreacionConvenio(boolean rolUsuarioInternoCreacionConvenio) {
        this.rolUsuarioInternoCreacionConvenio = rolUsuarioInternoCreacionConvenio;
    }

    public boolean isRolUsuarioInternoVraConvenio() {
        return rolUsuarioInternoVraConvenio;
    }

    public void setRolUsuarioInternoVraConvenio(boolean rolUsuarioInternoVraConvenio) {
        this.rolUsuarioInternoVraConvenio = rolUsuarioInternoVraConvenio;
    }

    public boolean isRolUsuarioInternoVreConvenio() {
        return rolUsuarioInternoVreConvenio;
    }

    public void setRolUsuarioInternoVreConvenio(boolean rolUsuarioInternoVreConvenio) {
        this.rolUsuarioInternoVreConvenio = rolUsuarioInternoVreConvenio;
    }

    public boolean isRolUsuarioInternoVriipConvenio() {
        return rolUsuarioInternoVriipConvenio;
    }

    public void setRolUsuarioInternoVriipConvenio(boolean rolUsuarioInternoVriipConvenio) {
        this.rolUsuarioInternoVriipConvenio = rolUsuarioInternoVriipConvenio;
    }

    public boolean isRolUsuarioInternoDjConvenio() {
        return rolUsuarioInternoDjConvenio;
    }

    public void setRolUsuarioInternoDjConvenio(boolean rolUsuarioInternoDjConvenio) {
        this.rolUsuarioInternoDjConvenio = rolUsuarioInternoDjConvenio;
    }

    public boolean isRolUsuarioInternoOtlConvenio() {
        return rolUsuarioInternoOtlConvenio;
    }

    public void setRolUsuarioInternoOtlConvenio(boolean rolUsuarioInternoOtlConvenio) {
        this.rolUsuarioInternoOtlConvenio = rolUsuarioInternoOtlConvenio;
    }

    public boolean isRolUsuarioInternoRectoriaConvenio() {
        return rolUsuarioInternoRectoriaConvenio;
    }

    public void setRolUsuarioInternoRectoriaConvenio(boolean rolUsuarioInternoRectoriaConvenio) {
        this.rolUsuarioInternoRectoriaConvenio = rolUsuarioInternoRectoriaConvenio;
    }

    public boolean isRolSecretariaGeneral() {
        return rolSecretariaGeneral;
    }

    public void setRolSecretariaGeneral(boolean rolSecretariaGeneral) {
        this.rolSecretariaGeneral = rolSecretariaGeneral;
    }
    
}
