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
import com.ua.sistemaindicadores.backend.daos.AnioDAO;
import com.ua.sistemaindicadores.backend.daos.BianualDAO;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.daos.PlazoDAO;
import com.ua.sistemaindicadores.backend.entities.Plazo;
import com.ua.sistemaindicadores.backend.daos.FrecuenciaMedicionDAO;
import com.ua.sistemaindicadores.backend.daos.MesDAO;
import com.ua.sistemaindicadores.backend.daos.SemestreDAO;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import com.ua.sistemaindicadores.backend.daos.UnidadRepresentacionDAO;
import com.ua.sistemaindicadores.backend.entities.Anio;
import com.ua.sistemaindicadores.backend.entities.BiAnual;
import com.ua.sistemaindicadores.backend.entities.Mes;
import com.ua.sistemaindicadores.backend.entities.Semestre;
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
    
    @Inject
    private AnioDAO anioDAO;        
    @Inject
    private BianualDAO bianualDAO;        
    @Inject
    private MesDAO mesDAO;        
    @Inject
    private SemestreDAO semestreDAO;        

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
    public void crearIndicador(Indicador indicador) {
        indicadorDAO.create(indicador);
    }
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO"})
    public void actualizarIndicador(Indicador indicador) {
        indicadorDAO.edit(indicador);
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<Indicador> obtenerIndicadores() {
        return indicadorDAO.obtenerIndicadores();
    }    

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Indicador buscarIndicadorID(Integer indicadorId) {
        return indicadorDAO.buscarIndicadorID(indicadorId);
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

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public AjustePdei buscarAjustePdeiID(Integer id) {
        return ajustePdeiDAO.buscarAjustePdeiID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public UnidadRepresentacion buscarUnidadRepresentacionID(Integer id) {
        return unidadRepresentacionDAO.buscarUnidadRepresentacionID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public GeneracionDatos buscarGeneracionDatosID(Integer id) {
        return generacionDatosDAO.buscarGeneracionDatosID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Plazo buscarPlazoID(Integer id) {
        return plazoDAO.buscarPlazoID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public AnioCumplimiento buscarAnioCumplimientoID(Integer id) {
        return anioCumplimientoDAO.buscarAnioCumplimientoID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public FrecuenciaMedicion buscarFrecuenciaMedicionID(Integer id) {
        return frecuenciaMedicionDAO.buscarFrecuenciaMedicionID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public UnidadProveedora buscarUnidadProveedoraID(Integer id) {
        return unidadProveedoraDAO.buscarUnidadProveedoraID(id);
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<UnidadProveedora> obtenerUnidadProveedora() {
        return unidadProveedoraDAO.obtenerUnidadProveedora();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<GeneracionDatos> obtenerGeneracionDatos() {
        return generacionDatosDAO.obtenerGeneracionDatos();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<AjustePdei> obtenerAjustePdei() {
        return ajustePdeiDAO.obtenerAjustePdei();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<AnioCumplimiento> obtenerAnioCumplimiento() {
        return anioCumplimientoDAO.obtenerAnioCumplimiento();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<Plazo> obtenerPlazo() {
        return plazoDAO.obtenerPlazo();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<FrecuenciaMedicion> obtenerFrecuenciaMedicion() {
        return frecuenciaMedicionDAO.obtenerFrecuenciaMedicion();
    }

    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<UnidadRepresentacion> obtenerUnidadRepresentacion() {
        return unidadRepresentacionDAO.obtenerUnidadRepresentacion();
    }
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Anio buscarAnioByAnio(Integer anio) {
        return anioDAO.buscarAnioByAnio(anio);
    }    
        
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<Anio> obtenerAnio() {
        return anioDAO.obtenerAnio();
    }
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public BiAnual buscarBianualByAnio(Integer anio) {
        return bianualDAO.buscarBianualByAnio(anio);
    }    
        
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<BiAnual> obtenerBianual() {
        return bianualDAO.obtenerBianual();
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Mes buscarMesByMes(String mes) {
        return mesDAO.buscarMesByMes(mes);
    }    
        
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<Mes> obtenerMes() {
        return mesDAO.obtenerMes();
    }    
    
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public Semestre buscarSemestreBySemestre(String semestre) {
        return semestreDAO.buscarSemestreBySemestre(semestre);
    }    
        
    @RolesAllowed({"ADMINISTRADOR", "USUARIO_ADMINISTRADOR_SEGUIMIENTO", "USUARIO_INTERNO_SEGUIMIENTO", "USUARIO_EXTERNO_SEGUIMIENTO"})
    public List<Semestre> obtenerSemestre() {
        return semestreDAO.obtenerSemestre();
    }    
    
    
    
    
    
    
    
}
