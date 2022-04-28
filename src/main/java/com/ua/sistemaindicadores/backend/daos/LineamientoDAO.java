/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.SelectLineamiento;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author aleja
 */
@Stateless
public class LineamientoDAO extends AbstractDAO<SelectLineamiento>{
    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }    

    public LineamientoDAO() {
        super(SelectLineamiento.class);
    }
    public List<SelectLineamiento> obtenerLineamientos()
    {
        return getEntityManager()
                .createNamedQuery("SelectLineamiento.findAll", SelectLineamiento.class)
                .getResultList();
    } 
    
    public SelectLineamiento obtenerLineamiento(String lineamiento) {
        return getEntityManager()
                .createNamedQuery("SelectLineamiento.findById", SelectLineamiento.class)
                .setParameter("lineamiento", lineamiento)
                .getResultList().get(0);
    }    
    
}
