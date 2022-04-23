/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.entities.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Ricardo
 */
@Stateless
public class PersonaDAO extends AbstractDAO<Persona> {

   @Inject
    private EntityManagerProviderCredenciales entityManagerProviderCredenciales;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProviderCredenciales.getEntityManager();
    }

    public PersonaDAO() {
        super(Persona.class);
    }
    
    public List<String> obtenerListaEmails()
    {
        return getEntityManager()
                .createNamedQuery("Persona.obtenerListaEmails", String.class)
                .getResultList();
    }
    public List<String> obtenerListaEmailsInstitucionales()
    {
        return getEntityManager()
                .createNamedQuery("Persona.obtenerListaEmailsInstitucionales", String.class)
                .getResultList();
    }
}
