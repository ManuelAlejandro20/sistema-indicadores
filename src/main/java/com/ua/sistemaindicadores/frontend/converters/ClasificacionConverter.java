/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.converters;

import com.ua.sistemaindicadores.backend.entities.Clasificacion;
import com.ua.sistemaindicadores.backend.services.ClasificacionService;
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
@FacesConverter(value="clasificacionConverter", managed=true)
public class ClasificacionConverter implements Converter<Clasificacion>{

    @Inject
    transient private ClasificacionService clasificacionService;
    
    @Override
    public Clasificacion getAsObject(FacesContext context, UIComponent uic, String value) {
        if(value != null && !value.isEmpty())
        {
            try
            {
                return clasificacionService.buscarClasificacionID(Integer.parseInt(value));
            }catch(EJBAccessException ex)
            {
            Logger.getLogger(ClasificacionConverter.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uic, Clasificacion value) {
         if(value != null)
         {
             return value.getId().toString();
         }
         return "";
    }
}

