/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.FrecuenciaMedicion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author diego
 */
@Stateless
public class FrecuenciaMedicionDAO extends AbstractDAO<FrecuenciaMedicion> {

    @Inject
    private EntityManagerProvider entityManagerProvider;


    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public FrecuenciaMedicionDAO() {
        super(FrecuenciaMedicion.class);
    }

    public List<FrecuenciaMedicion> obtenerFrecuenciaMedicion() {
        return getEntityManager()
                .createNamedQuery("FrecuenciaMedicion.findAll", FrecuenciaMedicion.class)
                .getResultList();
    }

    public FrecuenciaMedicion buscarFrecuenciaMedicion(String frecuenciaMedicion) {
        List<FrecuenciaMedicion> ti = getEntityManager()
                .createNamedQuery("FrecuenciaMedicion.findByFrecuenciaMedicion", FrecuenciaMedicion.class)
                .setParameter("frecuenciaMedicion", frecuenciaMedicion)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public FrecuenciaMedicion buscarFrecuenciaMedicionID(Integer id) {
        return find(id);
    }
}
