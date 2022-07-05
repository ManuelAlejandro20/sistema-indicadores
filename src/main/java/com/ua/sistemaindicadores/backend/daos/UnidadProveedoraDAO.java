/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.UnidadProveedora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author diego
 */
@Stateless
public class UnidadProveedoraDAO extends AbstractDAO<UnidadProveedora> {

    public static final String KEY_UNIDADPROVEEDORA = "unidadProveedora";

    @Inject
    private EntityManagerProvider entityManagerProvider;


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
}
