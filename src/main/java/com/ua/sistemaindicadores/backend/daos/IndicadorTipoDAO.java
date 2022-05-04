/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.dtos.TipoIndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo_;
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
 * @author aleja
 */
@Stateless
public class IndicadorTipoDAO extends AbstractDAO<IndicadorTipo> {

    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_ESTADO = "estado";
    public static final String KEY_FCREACION = "fecha_creacion";
    public static final String KEY_FACT = "fecha_actualizacion";    
    
    @Inject
    private EntityManagerProvider entityManagerProvider;

    private final Map<String, Join> mapJoins = new HashMap<>();
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public IndicadorTipoDAO() {
        super(IndicadorTipo.class);
    }

    public List<IndicadorTipo> obtenerIndicadorTipos()
    {
        return getEntityManager()
                .createNamedQuery("IndicadorTipo.findAll", IndicadorTipo.class)
                .getResultList();
    }     
    
    public TipoIndicadorDTO obtenerTipoIndicadorDTO(Integer tipoIndicadorId) {
        List<TipoIndicadorDTO> dtos = getEntityManager()
                .createNamedQuery("IndicadorTipo.obtenerDTO", TipoIndicadorDTO.class)
                .setParameter("Id", tipoIndicadorId)
                .getResultList();
        return dtos.isEmpty() ? null : dtos.get(0);
    }    
    
    public int contar(Map<String, Object> filters) {
        mapJoins.clear(); //limpiar map antes de crear la query

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<IndicadorTipo> root = cq.from(IndicadorTipo.class);
        cq.select(getEntityManager().getCriteriaBuilder().countDistinct(root));

        Predicate p = construirPredicado(cb, root, filters);

        cq.where(p);

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }    
    
    private Predicate construirPredicado(CriteriaBuilder cb, Root<IndicadorTipo> root, Map<String, Object> filters) {
        Predicate p = cb.conjunction();
        if (filters != null && !filters.isEmpty()) {
            for (String key : filters.keySet()) {
                switch (key) {
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
                        p = cb.and(p,
                                cb.equal(
                                        root.get(key),
                                        (Date) filters.get(key)
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
    public List<TipoIndicadorDTO> cargar(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters) {
        mapJoins.clear(); //limpiar map antes de crear la query

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TipoIndicadorDTO> cq = cb.createQuery(TipoIndicadorDTO.class);
        Root<IndicadorTipo> rootInformacion = cq.from(IndicadorTipo.class);

        Predicate p = construirPredicado(cb, rootInformacion, filters);

        cq.where(p);

        if (sortOrder != null && sortField != null) {
            switch (sortField) {
                case IndicadorTipo_.ID:
                    cq.orderBy(cb.desc(rootInformacion.get(sortField))
                    );
                default:
                    break;
            }
        }

        cq.select(
                cb.construct(
                        TipoIndicadorDTO.class,
                        rootInformacion.get(IndicadorTipo_.ID),
                        rootInformacion.get(IndicadorTipo_.NOMBRE),
                        rootInformacion.get(IndicadorTipo_.DESCRIPCION),
                        rootInformacion.get(IndicadorTipo_.ESTADO),
                        rootInformacion.get(IndicadorTipo_.FECHA_CREACION),
                        rootInformacion.get(IndicadorTipo_.FECHA_ACTUALIZACION)
                )).distinct(true);

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }    
    
    
}
