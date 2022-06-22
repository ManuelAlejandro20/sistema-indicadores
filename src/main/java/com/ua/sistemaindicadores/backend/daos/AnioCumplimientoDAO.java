/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.ejb.Stateless;
/**
 *
 * @author diego
 */
@Stateless
public class AnioCumplimientoDAO extends AbstractDAO<AnioCumplimiento> {

    public static final String KEY_ANIOCUMPLIMIENTO = "anioCumplimiento";

    @Inject
    private EntityManagerProvider entityManagerProvider;


    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    public AnioCumplimientoDAO() {
        super(AnioCumplimiento.class);
    }

    public boolean checkAnioCumplimientoExists(String anioCumplimiento) {
        List<AnioCumplimiento> ti = getEntityManager()
                .createNamedQuery("AnioCumplimiento.checkAnioCumplimientoExists", AnioCumplimiento.class)
                .setParameter("anioCumplimiento", anioCumplimiento)
                .getResultList();
        return !ti.isEmpty();
    }

    public List<AnioCumplimiento> obtenerAnioCumplimiento() {
        return getEntityManager()
                .createNamedQuery("AnioCumplimiento.findAll", AnioCumplimiento.class)
                .getResultList();
    }

    public AnioCumplimiento buscarAnioCumplimientoAnio(String anioCumplimiento) {
        List<AnioCumplimiento> ti = getEntityManager()
                .createNamedQuery("AnioCumplimiento.findByAnioCumplimiento", AnioCumplimiento.class)
                .setParameter("anioCumplimiento", anioCumplimiento)
                .getResultList();
        return ti.isEmpty() ? null : ti.get(0);
    }

    public AnioCumplimiento buscarAnioCumplimientoID(Integer id) {
        return find(id);
    }
}
