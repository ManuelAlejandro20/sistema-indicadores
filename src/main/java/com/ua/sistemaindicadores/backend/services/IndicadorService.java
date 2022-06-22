/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.services;

import com.ua.sistemaindicadores.backend.daos.IndicadorDAO;
import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.AjustePdei;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.Plazo;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion;
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
@RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
public class IndicadorService {

    @Inject
    private IndicadorDAO indicadorDAO;

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Indicador buscarIndicadorID(Integer indicadorId) {
        return indicadorDAO.buscarIndicadorID(indicadorId);
    }        
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public AjustePdei buscarAjustePdeiID(Integer id) {
        return indicadorDAO.buscarAjustePdeiID(id);
    }
        
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<AjustePdei> obtenerAjustePdei() {
        return indicadorDAO.obtenerAjustePdei();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public UnidadRepresentacion buscarUnidadRepresentacionID(Integer id) {
        return indicadorDAO.buscarUnidadRepresentacionID(id);
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<UnidadRepresentacion> obtenerUnidadRepresentacion() {
        return indicadorDAO.obtenerUnidadRepresentacion();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Plazo buscarPlazoID(Integer id) {
        return indicadorDAO.buscarPlazoID(id);
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<Plazo> obtenerPlazo() {
        return indicadorDAO.obtenerPlazo();
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public AnioCumplimiento buscarAnioCumplimientoID(Integer id) {
        return indicadorDAO.buscarAnioCumplimientoID(id);
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<AnioCumplimiento> obtenerAnioCumplimiento() {
        return indicadorDAO.obtenerAnioCumplimiento();
    }  
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public FrecuenciaMedicion buscarFrecuenciaMedicionID(Integer id) {
        return indicadorDAO.buscarFrecuenciaMedicionID(id);
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<FrecuenciaMedicion> obtenerFrecuenciaMedicion() {
        return indicadorDAO.obtenerFrecuenciaMedicion();
    }      
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public GeneracionDatos buscarGeneracionDatosID(Integer id) {
        return indicadorDAO.buscarGeneracionDatosID(id);
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<GeneracionDatos> obtenerGeneracionDatos() {
        return indicadorDAO.obtenerGeneracionDatos();
    }          
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public UnidadProveedora buscarUnidadProveedoraID(Integer id) {
        return indicadorDAO.buscarUnidadProveedoraID(id);
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<UnidadProveedora> obtenerUnidadProveedora() {
        return indicadorDAO.obtenerUnidadProveedora();
    }       
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public IndicadorDTO obtenerIndicadorDTO(Integer indicadorId) {
        return indicadorDAO.obtenerIndicadorDTO(indicadorId);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<IndicadorDTO> cargarIndicadores(int first, int pageSize,
            String sortField,
            String sortOrder,
            Map<String, Object> filters) {
        return indicadorDAO.cargar(first, pageSize, sortField, sortOrder, filters);
    }
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public int contarIndicadores(Map<String, Object> filters) {
        return indicadorDAO.contar(filters);
    }    

    
    
}
