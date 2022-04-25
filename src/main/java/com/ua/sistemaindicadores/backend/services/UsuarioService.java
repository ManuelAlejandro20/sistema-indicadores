/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.PersonaDAO;
import com.ua.sistemaindicadores.backend.daos.UsuarioDAO;
import com.ua.sistemaindicadores.backend.entities.Usuario;
import com.ua.sistemaindicadores.backend.dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author guilberto
 */
@Stateless
@SecurityDomain("DvcmeCredencialesDomain")
 @RolesAllowed({"ADMINISTRADOR", "ADMINISTRADOR_CONVENIO", "USUARIO_INTERNO_VRA_CONVENIO", "USUARIO_INTERNO_VRE_CONVENIO", "USUARIO_INTERNO_VRIIP_CONVENIO", "USUARIO_INTERNO_OTL_CONVENIO", "USUARIO_INTERNO_DJ_CONVENIO", "SECRETARIA_GENERAL_CONVENIO", "USUARIO_INTERNO_RECTORIA_CONVENIO","USUARIO_INTERNO_CREACION_CONVENIO","USUARIO_INTERNO_SEGUIMIENTO_CONVENIO","USUARIO_EXTERNO_CONVENIO","USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
public class UsuarioService {

    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private PersonaDAO personaDAO;

    public List<UsuarioDTO> obtenerUsuariosCorreoDTO(String rol)
    {
        return usuarioDAO.obtenerUsuarioCorreoDTO(rol);
    }
    public Usuario obtenerUsuarioRut(Integer rut)
    {
        return usuarioDAO.obtenerUsuarioRut(rut);
    }
     public List<String> generarDestinatarios(List<UsuarioDTO> l1) {
        List<String> destinatarios = new ArrayList<String>();
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).getEmailInstitucional() == null) {
                destinatarios.add(l1.get(i).getEmail());
            } else {
                destinatarios.add(l1.get(i).getEmailInstitucional());
            }
        }
        destinatarios.add("SOPORTE.DVCME@uantof.cl");
        return (destinatarios);
    }

    public String generarStringDestinatarios(List<String> l1) {
        String destinatarios = "";
        for (int i = 0; i < l1.size(); i++) {
            if (i == 0) {
                destinatarios = l1.get(i);
            } else {
                destinatarios = destinatarios + "," + l1.get(i);
            }

        }
        return (destinatarios);
    }
}
