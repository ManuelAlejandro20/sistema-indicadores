/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.UnidadRepresentacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author diego
 */
@Stateless
public class UnidadRepresentacionDAO extends AbstractDAO<UnidadRepresentacion> {

    @Inject
    private EntityManagerProvider entityManagerProvider;


    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public UnidadRepresentacionDAO() {
        super(UnidadRepresentacion.class);
    }

    public List<UnidadRepresentacion> obtenerUnidadRepresentacion() {
        return getEntityManager()
                .createNamedQuery("UnidadRepresentacion.findAll", UnidadRepresentacion.class)
                .getResultList();
    }

    public UnidadRepresentacion buscarUnidadRepresentacionUnidad(String unidadRepresentacion) {
        List<UnidadRepresentacion> ti = getEntityManager()
                .createNamedQuery("UnidadRepresentacion.findByUnidadRepresentacion", UnidadRepresentacion.class)
                .setParameter("unidadRepresentacion", unidadRepresentacion)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public UnidadRepresentacion buscarUnidadRepresentacionID(Integer id) {
        return find(id);
    }
}
