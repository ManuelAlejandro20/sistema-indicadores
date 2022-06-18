/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.IndicadorDAO;
import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.daos.UnidadProveedoraDAO;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
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
public class IndicadorService {

    @Inject
    private IndicadorDAO indicadorDAO;
    @Inject
    private UnidadProveedoraDAO unidadProveedoraDAO;

    @RolesAllowed({"ADMINISTRADOR"})
    public void crearIndicador(Indicador indicador) {
        indicadorDAO.create(indicador);
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public void actualizarIndicador(Indicador indicador) {
        indicadorDAO.edit(indicador);
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<Indicador> obtenerIndicadores() {
        return indicadorDAO.obtenerIndicadores();
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public IndicadorDTO obtenerIndicadorDTO(Integer indicadorId) {
        return indicadorDAO.obtenerIndicadorDTO(indicadorId);
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public Indicador buscarIndicadorID(Integer id) {
        return indicadorDAO.buscarIndicadorID(id);
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<UnidadProveedora> obtenerUnidadProveedora() {
        return unidadProveedoraDAO.obtenerUnidadProveedora();
    }
}
