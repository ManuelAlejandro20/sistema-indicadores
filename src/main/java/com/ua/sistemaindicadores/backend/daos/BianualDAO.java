/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.BiAnual;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author aleja
 */
@Stateless
public class BianualDAO extends AbstractDAO<BiAnual> {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public BianualDAO() {
        super(BiAnual.class);
    }

    public List<BiAnual> obtenerBianual() {
        return getEntityManager()
                .createNamedQuery("BiAnual.findAll", BiAnual.class)
                .getResultList();
    }

    public BiAnual buscarBianualByAnio(Integer anio) {
        List<BiAnual> ti = getEntityManager()
                .createNamedQuery("BiAnual.findByAnio", BiAnual.class)
                .setParameter("anio", anio)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public BiAnual buscarAnioID(Integer id) {
        return find(id);
    }
}
