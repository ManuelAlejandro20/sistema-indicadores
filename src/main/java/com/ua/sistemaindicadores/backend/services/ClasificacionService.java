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
@RolesAllowed({"ADMINISTRADOR"})
public class ClasificacionService {

    @Inject
    private ClasificacionDAO clasificacionDAO;

    @RolesAllowed({"ADMINISTRADOR"})
    public void crearClasificacion(Clasificacion clasificacion) {
        clasificacionDAO.create(clasificacion);
    }
    
    @RolesAllowed({"ADMINISTRADOR"})
    public void actualizarClasificacion(Clasificacion clasificacion) {
        clasificacionDAO.edit(clasificacion);
    }    
    
    @RolesAllowed({"ADMINISTRADOR"})
    public List<Clasificacion> obtenerClasificaciones()
    {
        return clasificacionDAO.obtenerClasificaciones();
    }    
    
    @RolesAllowed({"ADMINISTRADOR"})
    public int contarClasificaciones(Map<String, Object> filters) {
        return clasificacionDAO.contar(filters);
    }    
    
    @RolesAllowed({"ADMINISTRADOR"})
    public ClasificacionDTO obtenerClasificacionDTO(Integer clasificacionId) {
        return clasificacionDAO.obtenerClasificacionDTO(clasificacionId);
    }    
   
    @RolesAllowed({"ADMINISTRADOR"})
    public List<ClasificacionDTO> cargarClasificaciones(int first, int pageSize,
            String sortField,
            String sortOrder,
            Map<String, Object> filters) {
        return clasificacionDAO.cargar(first, pageSize, sortField, sortOrder, filters);
    }    
 
    @RolesAllowed({"ADMINISTRADOR"})
    public Clasificacion buscarClasificacionID(Integer id){
        return clasificacionDAO.buscarClasificacionID(id);
    }
   
}
