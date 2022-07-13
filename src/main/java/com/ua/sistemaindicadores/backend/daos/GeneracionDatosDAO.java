/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.GeneracionDatos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author diego
 */
@Stateless
public class GeneracionDatosDAO extends AbstractDAO<GeneracionDatos> {

    @Inject
    private EntityManagerProvider entityManagerProvider;


    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public GeneracionDatosDAO() {
        super(GeneracionDatos.class);
    }

    public List<GeneracionDatos> obtenerGeneracionDatos() {
        return getEntityManager()
                .createNamedQuery("GeneracionDatos.findAll", GeneracionDatos.class)
                .getResultList();
    }

    public GeneracionDatos buscarGeneracionDatosGeneracion(String generacionDatos) {
        List<GeneracionDatos> ti = getEntityManager()
                .createNamedQuery("GeneracionDatos.findByGeneracionDatos", GeneracionDatos.class)
                .setParameter("generacionDatos", generacionDatos)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public GeneracionDatos buscarGeneracionDatosID(Integer id) {
        return find(id);
    }
}
