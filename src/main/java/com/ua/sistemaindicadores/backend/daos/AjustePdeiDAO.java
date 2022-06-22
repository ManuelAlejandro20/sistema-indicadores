/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.AjustePdei;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author diego
 */
@Stateless
public class AjustePdeiDAO extends AbstractDAO<AjustePdei> {

    public static final String KEY_AJUSTEPDEI = "ajustePdei";

    @Inject
    private EntityManagerProvider entityManagerProvider;


    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public AjustePdeiDAO() {
        super(AjustePdei.class);
    }

    public boolean checkAjustePdeiExists(String ajustePdei) {
        List<AjustePdei> ti = getEntityManager()
                .createNamedQuery("AjustePdei.checkAjustePdeiExists", AjustePdei.class)
                .setParameter("ajustePdei", ajustePdei)
                .getResultList();
        return !ti.isEmpty();
    }

    public List<AjustePdei> obtenerAjustePdei() {
        return getEntityManager()
                .createNamedQuery("AjustePdei.findAll", AjustePdei.class)
                .getResultList();
    }

    public AjustePdei buscarAjustePdeiPdei(String ajustePdei) {
        List<AjustePdei> ti = getEntityManager()
                .createNamedQuery("AjustePdei.findByAjustePdei", AjustePdei.class)
                .setParameter("ajustePdei", ajustePdei)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public AjustePdei buscarAjustePdeiID(Integer id) {
        return find(id);
    }
}
