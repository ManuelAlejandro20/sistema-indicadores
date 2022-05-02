/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import com.ua.sistemaindicadores.backend.services.IndicadorService;
import com.ua.sistemaindicadores.backend.entities.SelectLineamiento;
import javax.inject.Named;
import java.io.IOException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.ExternalContext;

/**
 *
 * @author aleja
 */
@Named(value = "tipoIndicadorBean")
@ViewScoped
public class TipoIndicadorBean implements Serializable {

    private static final long serialVersionUID = 1L;
  
    
    
    /**
     * Creates a new instance of convenioBean
     */
    public TipoIndicadorBean() {
    }

    public String redireccion()throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect(ec.getRequestContextPath());
        fc.responseComplete();
        return null;
    }    
    
}
