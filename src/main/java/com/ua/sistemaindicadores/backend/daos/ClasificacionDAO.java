/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.dtos.ClasificacionDTO;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.Clasificacion_;
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
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author diego
 */
@Stateless
public class ClasificacionDAO extends AbstractDAO<Clasificacion> {

    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_TIPO = "tipo";
    public static final String KEY_ESTADO = "estado";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_FCREACION = "fechaCreacion";
    public static final String KEY_FACT = "fechaActualizacion";

    @Inject
    private EntityManagerProvider entityManagerProvider;

    private final Map<String, Join> mapJoins = new HashMap<>();

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public ClasificacionDAO() {
        super(Clasificacion.class);
    }

    public List<Clasificacion> obtenerClasificaciones() {
        return getEntityManager()
                .createNamedQuery("Clasificacion.findAll", Clasificacion.class)
                .getResultList();
    }

    public Clasificacion buscarClasificacionID(Integer id) {
        return find(id);
    }

    public ClasificacionDTO obtenerClasificacionDTO(Integer clasificacionId) {
        List<ClasificacionDTO> dtos = getEntityManager()
                .createNamedQuery("Clasificacion.obtenerDTO", ClasificacionDTO.class)
                .setParameter("Id", clasificacionId)
                .getResultList();
        return dtos.isEmpty() ? null : dtos.get(0);
    }

    public int contar(Map<String, Object> filters) {
        mapJoins.clear(); //limpiar map antes de crear la query

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Clasificacion> root = cq.from(Clasificacion.class);
        cq.select(getEntityManager().getCriteriaBuilder().countDistinct(root));

        Predicate p = construirPredicado(cb, root, filters);

        cq.where(p);

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private Predicate construirPredicado(CriteriaBuilder cb, Root<Clasificacion> root, Map<String, Object> filters) {
        Predicate p = cb.conjunction();
        if (filters != null && !filters.isEmpty()) {
            for (String key : filters.keySet()) {
                switch (key) {
                    case KEY_TIPO:
                    case KEY_NOMBRE:
                    case KEY_DESCRIPCION:
                        p = cb.and(
                                p, cb.like(cb.lower(root.get(key)),
                                        cb.lower(cb.literal("%" + filters.get(key).toString() + "%")))
                        );
                        break;
                    case KEY_ESTADO:
                        p = cb.and(p,
                                cb.equal(
                                        root.get(key),
                                        (Short) filters.get(key)
                                )
                        );
                        break;
                    case KEY_FCREACION:
                    case KEY_FACT:
                        SimpleDateFormat df = new SimpleDateFormat("yyyy");
                        Integer year = Integer.parseInt(df.format((Date) filters.get(key)));
                        p = cb.and(p,
                                cb.equal(
                                        cb.function("YEAR", Integer.class, root.get(key)),
                                        year
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

    public List<ClasificacionDTO> cargar(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters) {
        mapJoins.clear(); //limpiar map antes de crear la query

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClasificacionDTO> cq = cb.createQuery(ClasificacionDTO.class);
        Root<Clasificacion> rootInformacion = cq.from(Clasificacion.class);

        Predicate p = construirPredicado(cb, rootInformacion, filters);

        cq.where(p);

        if (sortOrder != null && sortField != null) {
            switch (sortField) {
                case Clasificacion_.ID:
                    cq.orderBy(cb.desc(rootInformacion.get(sortField))
                    );
                default:
                    break;
            }
        }

        cq.select(
                cb.construct(
                        ClasificacionDTO.class,
                        rootInformacion.get(Clasificacion_.ID),
                        //rootInformacion.get(Clasificacion_.INDICADOR_TIPO_ID),
                        rootInformacion.get(Clasificacion_.NOMBRE),
                        rootInformacion.get(Clasificacion_.TIPO),
                        rootInformacion.get(Clasificacion_.ESTADO),
                        rootInformacion.get(Clasificacion_.DESCRIPCION),
                        rootInformacion.get(Clasificacion_.FECHA_CREACION),
                        rootInformacion.get(Clasificacion_.FECHA_ACTUALIZACION)
                )).distinct(true);

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

}
