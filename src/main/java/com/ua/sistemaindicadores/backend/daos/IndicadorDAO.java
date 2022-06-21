/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.AjustePdei;
import com.ua.sistemaindicadores.backend.entities.AjustePdei_;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento_;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.Clasificacion_;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion_;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import com.ua.sistemaindicadores.backend.entities.GeneracionDatos_;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo_;
import com.ua.sistemaindicadores.backend.entities.Indicador_;
import com.ua.sistemaindicadores.backend.entities.Plazo;
import com.ua.sistemaindicadores.backend.entities.Plazo_;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora_;
import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion;
import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion_;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author aleja
 */
@Stateless
public class IndicadorDAO extends AbstractDAO<Indicador> {

    public static final String NUM_INDICADOR = "numIndicador";
    public static final String NOMBRE_INDICADOR = "nombreIndicador";
    public static final String ESTADO = "estado";
    public static final String DESCRIPCION_INDICADOR = "descripcionIndicador";
    public static final String APLICA_LINEAMIENTO = "aplicaLineamiento";
    public static final String APLICA_OBJETIVO = "aplicaObjetivo";
    public static final String DESCRIPCION_OBJETIVO = "descripcionObjetivo";
    public static final String VERSION = "version";
    public static final String LINEA_BASE = "lineaBase";
    public static final String METAS = "metas";
    public static final String PORC_LOGRO = "porcLogro";
    public static final String MEDIO_VERIFICACION = "medioVerificacion";
    public static final String FORMA_CALCULO = "formaCalculo";
    public static final String FUENTE_INFORMACION = "fuenteInformacion";
    public static final String PROYECTO_ASOCIADO = "proyectoAsociado";
    public static final String COMENTARIO = "comentario";
    public static final String ACTIVIDAD_COMPROMETIDA = "actividadComprometida";
    public static final String ESTADO_ACTIVIDAD = "estadoActividad";
    public static final String FECHA_CREACION = "fechaCreacion";
    public static final String FECHA_ACTUALIZACION = "fechaActualizacion";
    public static final String INDICADOR_TIPO_ID = "INDICADOR_TIPO_ID";
    public static final String AJUSTE_PDEI_ID = "AJUSTE_PDEI_ID";
    public static final String ANIO_CUMPLIMIENTO_ID = "ANIO_CUMPLIMIENTO_ID";
    public static final String CLASIFICACION_ID = "CLASIFICACION_ID";
    public static final String FRECUENCIA_MEDICION_ID = "FRECUENCIA_MEDICION_ID";
    public static final String GENERACION_DATOS_ID = "GENERACION_DATOS_ID";
    public static final String PLAZO_ID = "PLAZO_ID";
    public static final String UNIDAD_PROVEEDORA_ID = "UNIDAD_PROVEEDORA_ID";
    public static final String UNIDAD_REPRESENTACION_ID = "UNIDAD_REPRESENTACION_ID";
    public static final String FECHA_CREACION_RANGE = "FECHA_CREACION_RANGE";
    public static final String FECHA_ACTUALIZACION_RANGE = "FECHA_ACTUALIZACION_RANGE";

    @Inject
    private EntityManagerProvider entityManagerProvider;

    private final Map<String, Join> mapJoins = new HashMap<>();

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public IndicadorDAO() {
        super(Indicador.class);
    }
    
    public Indicador buscarIndicadorID(Integer indicadorId) {
        return find(indicadorId);
    }        

    public AjustePdei buscarAjustePdeiID(Integer id) {
        return getEntityManager().find(AjustePdei.class, id);
    }    
    
    public List<AjustePdei> obtenerAjustePdei(){
        return getEntityManager()
                .createNamedQuery("AjustePdei.findAll", AjustePdei.class)
                .getResultList();    
    }
    
    public UnidadRepresentacion buscarUnidadRepresentacionID(Integer id) {
        return getEntityManager().find(UnidadRepresentacion.class, id);
    }        
    
    public List<UnidadRepresentacion> obtenerUnidadRepresentacion(){
        return getEntityManager()
                .createNamedQuery("UnidadRepresentacion.findAll", UnidadRepresentacion.class)
                .getResultList();    
    }
    
    public Plazo buscarPlazoID(Integer id) {
        return getEntityManager().find(Plazo.class, id);
    }            

    public List<Plazo> obtenerPlazo(){
        return getEntityManager()
                .createNamedQuery("Plazo.findAll", Plazo.class)
                .getResultList();    
    }
    
    public AnioCumplimiento buscarAnioCumplimientoID(Integer id) {
        return getEntityManager().find(AnioCumplimiento.class, id);
    }            

    public List<AnioCumplimiento> obtenerAnioCumplimiento(){
        return getEntityManager()
                .createNamedQuery("AnioCumplimiento.findAll", AnioCumplimiento.class)
                .getResultList();    
    }    
    
    public FrecuenciaMedicion buscarFrecuenciaMedicionID(Integer id) {
        return getEntityManager().find(FrecuenciaMedicion.class, id);
    }            

    public List<FrecuenciaMedicion> obtenerFrecuenciaMedicion(){
        return getEntityManager()
                .createNamedQuery("FrecuenciaMedicion.findAll", FrecuenciaMedicion.class)
                .getResultList();    
    }     
    
    public GeneracionDatos buscarGeneracionDatosID(Integer id) {
        return getEntityManager().find(GeneracionDatos.class, id);
    }            

    public List<GeneracionDatos> obtenerGeneracionDatos(){
        return getEntityManager()
                .createNamedQuery("GeneracionDatos.findAll", GeneracionDatos.class)
                .getResultList();    
    } 
    
    public UnidadProveedora buscarUnidadProveedoraID(Integer id) {
        return getEntityManager().find(UnidadProveedora.class, id);
    }            

    public List<UnidadProveedora> obtenerUnidadProveedora(){
        return getEntityManager()
                .createNamedQuery("UnidadProveedora.findAll", UnidadProveedora.class)
                .getResultList();    
    }     
    
    public IndicadorDTO obtenerIndicadorDTO(Integer indicadorId) {
        List<IndicadorDTO> dtos = getEntityManager()
                .createNamedQuery("Indicador.obtenerDTO", IndicadorDTO.class)
                .setParameter("Id", indicadorId)
                .getResultList();
        return dtos.isEmpty() ? null : dtos.get(0);
    }
    
    public int contar(Map<String, Object> filters) {
        mapJoins.clear(); //limpiar map antes de crear la query

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Indicador> root = cq.from(Indicador.class);
        cq.select(getEntityManager().getCriteriaBuilder().countDistinct(root));
        
        Predicate p = construirPredicado(cb, root, filters);
        
        cq.where(p);
        
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private Join obtenerJoin(Root<Indicador> root, String key) {
        if (!mapJoins.containsKey(key)) {
            switch (key) {
                case INDICADOR_TIPO_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.CLASIFICACION_ID, JoinType.INNER).join(Clasificacion_.INDICADOR_TIPO_ID, JoinType.INNER))
                    );
                    break;                
                case AJUSTE_PDEI_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.AJUSTE_PDEI_ID, JoinType.INNER))
                    );
                    break;
                case ANIO_CUMPLIMIENTO_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.ANIO_CUMPLIMIENTO_ID, JoinType.INNER))
                    );
                    break;
                case CLASIFICACION_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.CLASIFICACION_ID, JoinType.INNER))
                    );
                    break;   
                case FRECUENCIA_MEDICION_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.FRECUENCIA_MEDICION_ID, JoinType.INNER))
                    );
                    break;   
                case GENERACION_DATOS_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.GENERACION_DATOS_COLLECTION, JoinType.INNER))
                    );
                    break;   
                case PLAZO_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.PLAZO_ID, JoinType.INNER))
                    );
                    break;   
                case UNIDAD_PROVEEDORA_ID:
                    mapJoins.put(
                            key, root.join(Indicador_.UNIDAD_PROVEEDORA_COLLECTION, JoinType.INNER)
                    );
                    break;
                case UNIDAD_REPRESENTACION_ID:
                    mapJoins.put(
                            key, (root.join(Indicador_.UNIDAD_REPRESENTACION_ID, JoinType.INNER))
                    );
                    break;                       
                default:
                    break;
            }
        }
        return mapJoins.get(key);
    }

    private Predicate construirPredicado(CriteriaBuilder cb, Root<Indicador> root, Map<String, Object> filters) {
        Predicate p = cb.conjunction();
        if (filters != null && !filters.isEmpty()) {
            for (String key : filters.keySet()) {
                switch (key) {
                    case NOMBRE_INDICADOR:
                    case DESCRIPCION_INDICADOR:
                    case APLICA_LINEAMIENTO:
                    case APLICA_OBJETIVO:
                    case DESCRIPCION_OBJETIVO:
                    case VERSION:
                    case LINEA_BASE:
                    case METAS:
                    case PORC_LOGRO:
                    case MEDIO_VERIFICACION:
                    case FORMA_CALCULO:
                    case FUENTE_INFORMACION:
                    case PROYECTO_ASOCIADO:
                    case COMENTARIO:
                    case ACTIVIDAD_COMPROMETIDA:
                    case ESTADO_ACTIVIDAD:
                        p = cb.and(
                                p, cb.like(cb.lower(root.get(key)),
                                        cb.lower(cb.literal("%" + filters.get(key).toString() + "%")))
                        );
                        break;
                    case NUM_INDICADOR:
                        p = cb.and(p,
                                cb.equal(
                                        root.get(key),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;                        
                    case ESTADO:
                        p = cb.and(p,
                                cb.equal(
                                        root.get(key),
                                        (Short) filters.get(key)
                                )
                        );
                        break;
                    case FECHA_CREACION:
                    case FECHA_ACTUALIZACION:
                        SimpleDateFormat df = new SimpleDateFormat("yyyy");
                        Integer year = Integer.parseInt(df.format((Date) filters.get(key)));
                        p = cb.and(p,
                                cb.equal(
                                        cb.function("YEAR", Integer.class, root.get(key)),
                                        year
                                )
                        );
                        break;
                    case FECHA_CREACION_RANGE:
                        List<Date> fechasCreacion = (List<Date>)filters.get(key);   
                          p = cb.between(
                                root.get(Indicador_.FECHA_CREACION),
                                fechasCreacion.get(0),
                                fechasCreacion.get(1)
                          );
                        break;        
                    case FECHA_ACTUALIZACION_RANGE:
                        List<Date> fechasAct = (List<Date>)filters.get(key);   
                          p = cb.between(
                                root.get(Indicador_.FECHA_ACTUALIZACION),
                                fechasAct.get(0),
                                fechasAct.get(1)
                          );
                        break;                             
                    case INDICADOR_TIPO_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(IndicadorTipo_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;                        
                    case AJUSTE_PDEI_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(AjustePdei_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;
                    case ANIO_CUMPLIMIENTO_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(AnioCumplimiento_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;     
                    case CLASIFICACION_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(Clasificacion_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;            
                    case FRECUENCIA_MEDICION_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(FrecuenciaMedicion_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;     
                    case GENERACION_DATOS_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(GeneracionDatos_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;    
                    case PLAZO_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(Plazo_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;    
                    case UNIDAD_PROVEEDORA_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(UnidadProveedora_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;  
                    case UNIDAD_REPRESENTACION_ID:
                        p = cb.and(
                                p,
                                cb.equal(obtenerJoin(root, key).get(UnidadRepresentacion_.ID),
                                        (Integer) filters.get(key)
                                )
                        );
                        break;                            
                    default:
                        break;
                }
            }
        }
        return p;
    }

    public List<IndicadorDTO> cargar(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters) {
        mapJoins.clear(); //limpiar map antes de crear la query

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<IndicadorDTO> iq = cb.createQuery(IndicadorDTO.class);
        Root<Indicador> rootIndicador = iq.from(Indicador.class);

        Join<Indicador, AjustePdei> joinAjustePdei = rootIndicador.join(Indicador_.AJUSTE_PDEI_ID, JoinType.INNER);
        Join<Indicador, AnioCumplimiento> joinAnioCumplimiento = rootIndicador.join(Indicador_.ANIO_CUMPLIMIENTO_ID, JoinType.INNER);
        Join<Indicador, Clasificacion> joinClas = rootIndicador.join(Indicador_.CLASIFICACION_ID, JoinType.INNER);
        Join<Indicador, FrecuenciaMedicion> joinFrecuencia = rootIndicador.join(Indicador_.FRECUENCIA_MEDICION_ID, JoinType.INNER);
        Join<Indicador, Plazo> joinPlazo = rootIndicador.join(Indicador_.PLAZO_ID, JoinType.INNER);
        Join<Indicador, UnidadRepresentacion> joinUnidadRepresentacion = rootIndicador.join(Indicador_.UNIDAD_REPRESENTACION_ID, JoinType.INNER);                                             
        Join<Indicador, UnidadProveedora> joinUnidadProveedora = null;
        Join<Indicador, GeneracionDatos> joinGenDatos = null;       

        if(filters != null && filters.containsKey(UnidadProveedora_.UNIDAD_PROVEEDORA))
        {
            joinUnidadProveedora = rootIndicador.join(Indicador_.UNIDAD_PROVEEDORA_COLLECTION, JoinType.INNER).join(UnidadProveedora_.UNIDAD_PROVEEDORA, JoinType.INNER);
        }  
        if(filters != null && filters.containsKey(GeneracionDatos_.GENERACION_DATOS))
        {
            joinGenDatos = rootIndicador.join(Indicador_.GENERACION_DATOS_COLLECTION, JoinType.INNER).join(GeneracionDatos_.GENERACION_DATOS, JoinType.INNER);
        }          
        
        Predicate p = construirPredicado(cb, rootIndicador, filters);

        iq.where(p);

        if (sortOrder != null && sortField != null) {
            switch (sortField) {
                case Indicador_.ID:
                    iq.orderBy(cb.desc(rootIndicador.get(sortField))
                    );
                default:
                    break;
            }
        }
                
        System.out.println(Indicador_.ID);
        
        iq.select(
                cb.construct(
                        IndicadorDTO.class,
                        rootIndicador.get(Indicador_.ID),
                        rootIndicador.get(Indicador_.NUM_INDICADOR),
                        rootIndicador.get(Indicador_.NOMBRE_INDICADOR),
                        rootIndicador.get(Indicador_.ESTADO),
                        rootIndicador.get(Indicador_.DESCRIPCION_INDICADOR),
                        rootIndicador.get(Indicador_.APLICA_LINEAMIENTO),
                        rootIndicador.get(Indicador_.APLICA_OBJETIVO),
                        rootIndicador.get(Indicador_.DESCRIPCION_OBJETIVO),
                        rootIndicador.get(Indicador_.VERSION),
                        rootIndicador.get(Indicador_.LINEA_BASE),
                        rootIndicador.get(Indicador_.METAS),
                        rootIndicador.get(Indicador_.PORC_LOGRO),
                        rootIndicador.get(Indicador_.MEDIO_VERIFICACION),
                        rootIndicador.get(Indicador_.FORMA_CALCULO),
                        rootIndicador.get(Indicador_.FUENTE_INFORMACION),
                        rootIndicador.get(Indicador_.PROYECTO_ASOCIADO),
                        rootIndicador.get(Indicador_.COMENTARIO),
                        rootIndicador.get(Indicador_.ACTIVIDAD_COMPROMETIDA),
                        rootIndicador.get(Indicador_.ESTADO_ACTIVIDAD),
                        rootIndicador.get(Indicador_.FECHA_CREACION),
                        rootIndicador.get(Indicador_.FECHA_ACTUALIZACION),
                        joinAjustePdei.get(AjustePdei_.AJUSTE_PDEI),
                        joinClas.get(Clasificacion_.TIPO),
                        joinAnioCumplimiento.get(AnioCumplimiento_.ANIO_CUMPLIMIENTO),
                        joinClas.get(Clasificacion_.NOMBRE),
                        joinFrecuencia.get(FrecuenciaMedicion_.FRECUENCIA_MEDICION),
                        joinPlazo.get(Plazo_.PLAZO),
                        joinUnidadRepresentacion.get(UnidadRepresentacion_.UNIDAD_REPRESENTACION)
                )).distinct(true);

        javax.persistence.Query q = getEntityManager().createQuery(iq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

}
