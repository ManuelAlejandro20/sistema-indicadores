/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;

/**
 *
 * @author diego
 */
@Stateless
public class IndicadorDAO extends AbstractDAO<Indicador> {
    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public IndicadorDAO() {
        super(Indicador.class);
    }

    public List<Indicador> obtenerIndicadores() {
        return getEntityManager()
                .createNamedQuery("Indicador.findAll", Indicador.class)
                .getResultList();
    }

    public Indicador buscarIndicadorID(Integer id) {
        return find(id);
    }

    public IndicadorDTO obtenerIndicadorDTO(Integer indicadorId) {
        List<IndicadorDTO> dtos = getEntityManager()
                .createNamedQuery("Clasificacion.obtenerDTO", IndicadorDTO.class)
                .setParameter("Id", indicadorId)
                .getResultList();
        return dtos.isEmpty() ? null : dtos.get(0);
    }
}
