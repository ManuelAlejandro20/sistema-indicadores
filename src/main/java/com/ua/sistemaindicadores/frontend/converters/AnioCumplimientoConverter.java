/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.converters;

import com.ua.sistemaindicadores.backend.entities.AnioCumplimiento;
import com.ua.sistemaindicadores.backend.services.IndicadorService;
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
 * @author aleja
 */
@FacesConverter(value="anioCumplimientoConverter", managed=true)
public class AnioCumplimientoConverter implements Converter<AnioCumplimiento>{

    @Inject
    transient private IndicadorService indicadorService;
    
    @Override
    public AnioCumplimiento getAsObject(FacesContext context, UIComponent uic, String value) {
        if(value != null && !value.isEmpty())
        {
            try
            {
                return indicadorService.buscarAnioCumplimientoID(Integer.parseInt(value));
            }catch(EJBAccessException ex)
            {
            Logger.getLogger(AnioCumplimientoConverter.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent uic, AnioCumplimiento value) {
         if(value != null)
         {
             return value.getId().toString();
         }
         return "";
    }
}
