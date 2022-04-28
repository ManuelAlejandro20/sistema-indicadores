/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author Ricardo
 */
@Stateless
@SecurityDomain("DvcmeCredencialesDomain")
@PermitAll
public class EntityManagerProvider{

    @Resource
    SessionContext context;
   
    @PersistenceContext(unitName = "IndicadoresPU")
    private EntityManager emIndicadores;
    @PersistenceContext(unitName = "IndicadoresAdminPU")
    private EntityManager emIndicadoresAdmin;
    
    public EntityManager getEntityManager()
    {
     EntityManager em = emIndicadores;
     if(context.isCallerInRole("ADMINISTRADOR") || context.isCallerInRole("USUARIO_ADMINISTRADOR_INFEXT_VINCULACION"))
     {
         em =  emIndicadoresAdmin;
     }
     return em;
    }    
}
