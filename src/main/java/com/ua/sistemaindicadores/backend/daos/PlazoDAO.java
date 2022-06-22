/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.Plazo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author diego
 */
@Stateless
public class PlazoDAO extends AbstractDAO<Plazo> {

    public static final String KEY_PLAZO = "plazo";

    @Inject
    private EntityManagerProvider entityManagerProvider;


    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public PlazoDAO() {
        super(Plazo.class);
    }

    public boolean checkPlazoExists(String plazo) {
        List<Plazo> ti = getEntityManager()
                .createNamedQuery("Plazo.checkPlazoExists", Plazo.class)
                .setParameter("plazo", plazo)
                .getResultList();
        return !ti.isEmpty();
    }

    public List<Plazo> obtenerPlazo() {
        return getEntityManager()
                .createNamedQuery("Plazo.findAll", Plazo.class)
                .getResultList();
    }

    public Plazo buscarPlazoPlazo(String plazo) {
        List<Plazo> ti = getEntityManager()
                .createNamedQuery("Plazo.findByPlazo", Plazo.class)
                .setParameter("plazo", plazo)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public Plazo buscarPlazoID(Integer id) {
        return find(id);
    }
}
