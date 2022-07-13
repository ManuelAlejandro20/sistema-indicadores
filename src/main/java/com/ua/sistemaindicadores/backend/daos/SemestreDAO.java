/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.BiAnual;
import com.ua.sistemaindicadores.backend.entities.Semestre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author aleja
 */
@Stateless
public class SemestreDAO extends AbstractDAO<Semestre> {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public SemestreDAO() {
        super(Semestre.class);
    }

    public List<Semestre> obtenerSemestre() {
        return getEntityManager()
                .createNamedQuery("Semestre.findAll", Semestre.class)
                .getResultList();
    }

    public Semestre buscarSemestreBySemestre(String semestre) {
        List<Semestre> ti = getEntityManager()
                .createNamedQuery("Semestre.findBySemestre", Semestre.class)
                .setParameter("semestre", semestre)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public Semestre buscarAnioID(Integer id) {
        return find(id);
    }
}
