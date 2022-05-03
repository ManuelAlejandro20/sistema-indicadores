/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author aleja
 */
@Stateless
public class IndicadorTipoDAO extends AbstractDAO<IndicadorTipo> {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public IndicadorTipoDAO() {
        super(IndicadorTipo.class);
    }

}
