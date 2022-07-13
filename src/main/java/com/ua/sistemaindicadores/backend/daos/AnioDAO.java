/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.Anio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author aleja
 */
@Stateless
public class AnioDAO extends AbstractDAO<Anio> {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public AnioDAO() {
        super(Anio.class);
    }

    public List<Anio> obtenerAnio() {
        return getEntityManager()
                .createNamedQuery("Anio.findAll", Anio.class)
                .getResultList();
    }

    public Anio buscarAnioByAnio(Integer anio) {
        List<Anio> ti = getEntityManager()
                .createNamedQuery("Anio.findByAnio", Anio.class)
                .setParameter("anio", anio)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public Anio buscarAnioID(Integer id) {
        return find(id);
    }
}
