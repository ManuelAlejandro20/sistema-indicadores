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
import com.ua.sistemaindicadores.backend.daos.GeneracionDatosDAO;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.daos.AjustePdeiDAO;
import com.ua.sistemaindicadores.backend.entities.AjustePdei;
import com.ua.sistemaindicadores.backend.daos.AnioCumplimientoDAO;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.daos.PlazoDAO;
import com.ua.sistemaindicadores.backend.entities.Plazo;
import com.ua.sistemaindicadores.backend.daos.FrecuenciaMedicionDAO;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import com.ua.sistemaindicadores.backend.daos.UnidadRepresentacionDAO;
import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion;
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
public class IndicadorService {

    @Inject
    private IndicadorDAO indicadorDAO;
    @Inject
    private UnidadProveedoraDAO unidadProveedoraDAO;
    @Inject
    private GeneracionDatosDAO generacionDatosDAO;
    @Inject
    private AjustePdeiDAO ajustePdeiDAO;
    @Inject
    private AnioCumplimientoDAO anioCumplimientoDAO;
    @Inject
    private PlazoDAO plazoDAO;
    @Inject
    private FrecuenciaMedicionDAO frecuenciaMedicionDAO;
    @Inject
    private UnidadRepresentacionDAO unidadRepresentacionDAO;

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

    @RolesAllowed({"ADMINISTRADOR"})
    public List<GeneracionDatos> obtenerGeneracionDatos() {
        return generacionDatosDAO.obtenerGeneracionDatos();
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<AjustePdei> obtenerAjustePdei() {
        return ajustePdeiDAO.obtenerAjustePdei();
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<AnioCumplimiento> obtenerAnioCumplimiento() {
        return anioCumplimientoDAO.obtenerAnioCumplimiento();
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<Plazo> obtenerPlazo() {
        return plazoDAO.obtenerPlazo();
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<FrecuenciaMedicion> obtenerFrecuenciaMedicion() {
        return frecuenciaMedicionDAO.obtenerFrecuenciaMedicion();
    }

    @RolesAllowed({"ADMINISTRADOR"})
    public List<UnidadRepresentacion> obtenerUnidadRepresentacion() {
        return unidadRepresentacionDAO.obtenerUnidadRepresentacion();
    }
}
