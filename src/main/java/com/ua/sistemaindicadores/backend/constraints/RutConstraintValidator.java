/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.constraints;

import com.ua.sistemaindicadores.backend.utils.ValidaRut;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RutConstraintValidator implements ConstraintValidator<RutValido, String> {

    @Override
    public void initialize(RutValido a) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ValidaRut.verificarRut(value);
    }

}

