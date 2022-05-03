/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.IndicadorTipoDAO;
import com.ua.sistemaindicadores.backend.daos.LineamientoDAO;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
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
   
}

