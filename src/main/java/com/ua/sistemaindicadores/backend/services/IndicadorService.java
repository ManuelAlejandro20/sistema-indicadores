/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.IndicadorDAO;
import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.ejb3.annotation.SecurityDomain;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aleja
 */
@Stateless
@SecurityDomain("DvcmeCredencialesDomain")
@RolesAllowed({"ADMINISTRADOR"})
public class IndicadorService {

    @Inject
    private IndicadorDAO indicadorDAO;

    @RolesAllowed({"ADMINISTRADOR"})
    public IndicadorDTO obtenerIndicadorDTO(Integer indicadorId) {
        return indicadorDAO.obtenerIndicadorDTO(indicadorId);
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<IndicadorDTO> cargarIndicadores(int first, int pageSize,
            String sortField,
            String sortOrder,
            Map<String, Object> filters) {
        return indicadorDAO.cargar(first, pageSize, sortField, sortOrder, filters);
    }
    
    @RolesAllowed({"ADMINISTRADOR"})
    public int contarIndicadores(Map<String, Object> filters) {
        return indicadorDAO.contar(filters);
    }    

}
