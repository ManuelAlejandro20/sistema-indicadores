/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.IndicadorTipoDAO;
import com.ua.sistemaindicadores.backend.daos.LineamientoDAO;
import com.ua.sistemaindicadores.backend.dtos.TipoIndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.entities.SelectLineamiento;
import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author aleja
 */
@Stateless
@SecurityDomain("DvcmeCredencialesDomain")
@RolesAllowed({"ADMINISTRADOR"})
public class TipoIndicadorService {

    @Inject
    private IndicadorTipoDAO indicadorTipoDAO;

    @RolesAllowed({"ADMINISTRADOR"})
    public void crearTipoIndicador(IndicadorTipo indicadorTipo) {
        indicadorTipoDAO.create(indicadorTipo);
    }
    
    @RolesAllowed({"ADMINISTRADOR"})
    public void actualizarTipoIndicador(IndicadorTipo indicadorTipo) {
        indicadorTipoDAO.edit(indicadorTipo);
    }    
    
    @RolesAllowed({"ADMINISTRADOR"})
    public List<IndicadorTipo> obtenerIndicadorTipos()
    {
        return indicadorTipoDAO.obtenerIndicadorTipos();
    }    
    
    @RolesAllowed({"ADMINISTRADOR"})
    public int contarTipoIndicadores(Map<String, Object> filters) {
        return indicadorTipoDAO.contar(filters);
    }    
    
    @RolesAllowed({"ADMINISTRADOR"})
    public TipoIndicadorDTO obtenerTipoIndicadorDTO(Integer tipoIndicadorId) {
        return indicadorTipoDAO.obtenerTipoIndicadorDTO(tipoIndicadorId);
    }    
   
    @RolesAllowed({"ADMINISTRADOR"})
    public List<TipoIndicadorDTO> cargarTipoIndicadores(int first, int pageSize,
            String sortField,
            String sortOrder,
            Map<String, Object> filters) {
        return indicadorTipoDAO.cargar(first, pageSize, sortField, sortOrder, filters);
    }    
 
    @RolesAllowed({"ADMINISTRADOR"})
    public IndicadorTipo buscarTipoIndicadorID(Integer id){
        return indicadorTipoDAO.buscarTipoIndicadorID(id);
    }
    
}

