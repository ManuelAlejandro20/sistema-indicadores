/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.validators;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;

/**
 *
 * @author aleja
 */
@FacesValidator("checkBoxValidator")
public class CheckboxValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException { 
        List<String> checkBoxList = (List<String>) value;
        if(checkBoxList.size() == 0){
            System.out.println("Es cero");
            throw new ValidatorException(new FacesMessage("El mínimo de columnas permitidas es 1"));
        }  
        if(checkBoxList.size() > 6){
            System.out.println("Es mayor a 6");
            throw new ValidatorException(new FacesMessage("El máximo de columnas permitidas son 5"));
        }
    }
    
}
