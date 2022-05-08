/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.LineamientoDAO;
import com.ua.sistemaindicadores.backend.entities.SelectLineamiento;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.ejb3.annotation.SecurityDomain;
import java.util.List;

/**
 *
 * @author diego
 */
@Stateless
@SecurityDomain("DvcmeCredencialesDomain")
@RolesAllowed({"ADMINISTRADOR"})
public class ClasificacionService {

    @Inject
    private LineamientoDAO lineamientoDAO;

    public List<SelectLineamiento> obtenerLineamientos()
    {
        return lineamientoDAO.obtenerLineamientos();
    }
   
}
