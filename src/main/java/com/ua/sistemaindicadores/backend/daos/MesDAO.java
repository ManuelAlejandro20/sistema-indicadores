/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.Mes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author aleja
 */
@Stateless
public class MesDAO extends AbstractDAO<Mes> {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public MesDAO() {
        super(Mes.class);
    }

    public List<Mes> obtenerMes() {
        return getEntityManager()
                .createNamedQuery("Mes.findAll", Mes.class)
                .getResultList();
    }

    public Mes buscarMesByMes(String mes) {
        List<Mes> ti = getEntityManager()
                .createNamedQuery("Mes.findByMes", Mes.class)
                .setParameter("mes", mes)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public Mes buscarAnioID(Integer id) {
        return find(id);
    }
}
