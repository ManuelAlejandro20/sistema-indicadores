/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import com.ua.sistemaindicadores.backend.entities.UnidadProveedora_;
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
public class UnidadProveedoraDAO extends AbstractDAO<UnidadProveedora> {

    public static final String KEY_UNIDADPROVEEDORA = "unidadProveedora";

    @Inject
    private EntityManagerProvider entityManagerProvider;

    private final Map<String, Join> mapJoins = new HashMap<>();

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public UnidadProveedoraDAO() {
        super(UnidadProveedora.class);
    }

    public boolean checkUnidadProveedoraExists(String unidadProveedora) {
        List<UnidadProveedora> ti = getEntityManager()
                .createNamedQuery("UnidadProveedora.checkUnidadProveedoraExists", UnidadProveedora.class)
                .setParameter("unidadProveedora", unidadProveedora)
                .getResultList();
        return !ti.isEmpty();
    }

    public List<UnidadProveedora> obtenerUnidadProveedora() {
        return getEntityManager()
                .createNamedQuery("UnidadProveedora.findAll", UnidadProveedora.class)
                .getResultList();
    }

    public UnidadProveedora buscarUnidadProveedoraUnidad(String unidadProveedora) {
        List<UnidadProveedora> ti = getEntityManager()
                .createNamedQuery("UnidadProveedora.findByUnidadProveedora", UnidadProveedora.class)
                .setParameter("unidadProveedora", unidadProveedora)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public UnidadProveedora buscarUnidadProveedoraID(Integer id) {
        return find(id);
    }

    public int contar(Map<String, Object> filters) {
        mapJoins.clear(); //limpiar map antes de crear la query

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnidadProveedora> root = cq.from(UnidadProveedora.class);
        cq.select(getEntityManager().getCriteriaBuilder().countDistinct(root));

        Predicate p = construirPredicado(cb, root, filters);

        cq.where(p);

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private Predicate construirPredicado(CriteriaBuilder cb, Root<UnidadProveedora> root, Map<String, Object> filters) {
        Predicate p = cb.conjunction();
        if (filters != null && !filters.isEmpty()) {
            for (String key : filters.keySet()) {
                switch (key) {
                    case KEY_UNIDADPROVEEDORA:
                    default:
                        break;
                }
            }
        }
        return p;
    }
}
