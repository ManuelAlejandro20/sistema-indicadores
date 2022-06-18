/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.dtos.IndicadorDTO;
import com.ua.sistemaindicadores.backend.entities.Indicador;
import com.ua.sistemaindicadores.backend.entities.Indicador_;
import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.entities.Clasificacion_;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.entities.IndicadorTipo_;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author diego
 */
@Stateless
public class IndicadorDAO extends AbstractDAO<Indicador> {
    @Inject
    private EntityManagerProvider entityManagerProvider;

    private final Map<String, Join> mapJoins = new HashMap<>();

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
