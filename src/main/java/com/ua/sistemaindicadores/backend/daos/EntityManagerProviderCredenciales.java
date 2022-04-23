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
public class EntityManagerProviderCredenciales{

    @Resource
    SessionContext context;
    @PersistenceContext(unitName = "CredencialesAdminPU")
    private EntityManager emCredencialesAdmin;
    
    public EntityManager getEntityManager()
    {
         return emCredencialesAdmin;
    }    
}
