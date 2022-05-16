/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.converters;

import com.ua.sistemaindicadores.backend.entities.IndicadorTipo;
import com.ua.sistemaindicadores.backend.services.TipoIndicadorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBAccessException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Ricardo
 */
@FacesConverter(value="tipoIndicadorConverter", managed=true)
public class TipoIndicadorConverter implements Converter<IndicadorTipo>{

    @Inject
    transient private  TipoIndicadorService  tipoIndicadorService;
    
    @Override
    public IndicadorTipo getAsObject(FacesContext context, UIComponent uic, String value) {
        if(value != null && !value.isEmpty())
        {
            System.out.println(value);
            try
            {
                return tipoIndicadorService.buscarTipoIndicadorID(Integer.parseInt(value));
            }catch(EJBAccessException ex)
            {
            Logger.getLogger(TipoIndicadorConverter.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uic, IndicadorTipo value) {
         if(value != null)
         {
             return value.getId().toString();
         }
         return "";
    }
}
