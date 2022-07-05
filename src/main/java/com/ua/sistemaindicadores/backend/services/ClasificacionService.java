/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.ClasificacionDAO;
import com.ua.sistemaindicadores.backend.dtos.ClasificacionDTO;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.ejb3.annotation.SecurityDomain;
import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
@Stateless
@SecurityDomain("DvcmeCredencialesDomain")
@RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
public class ClasificacionService {

    @Inject
    private ClasificacionDAO clasificacionDAO;

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
    public void crearClasificacion(Clasificacion clasificacion) {
        clasificacionDAO.create(clasificacion);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
    public void actualizarClasificacion(Clasificacion clasificacion) {
        clasificacionDAO.edit(clasificacion);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<Clasificacion> obtenerClasificaciones() {
        return clasificacionDAO.obtenerClasificaciones();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
    public int contarClasificaciones(Map<String, Object> filters) {
        return clasificacionDAO.contar(filters);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
    public ClasificacionDTO obtenerClasificacionDTO(Integer clasificacionId) {
        return clasificacionDAO.obtenerClasificacionDTO(clasificacionId);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
    public List<ClasificacionDTO> cargarClasificaciones(int first, int pageSize,
            String sortField,
            String sortOrder,
            Map<String, Object> filters) {
        return clasificacionDAO.cargar(first, pageSize, sortField, sortOrder, filters);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Clasificacion buscarClasificacionID(Integer id) {
        return clasificacionDAO.buscarClasificacionID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Clasificacion buscarClasificacionTipo(String tipo) {
        return clasificacionDAO.buscarClasificacionTipo(tipo);
    }
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public boolean checkClasificacionExists(String nombre, String tipo) {
        return clasificacionDAO.checkClasificacionExists(nombre, tipo);
    }            
}
