/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.utils;

import com.ua.sistemaindicadores.backend.exceptions.RutNoIdentificableException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ValidaRut {

/**
     * Formato del rut de entrada 12.345.678-9
 *
     * @param rutFormateado
     * @return
 */
    public static boolean verificarRut(String rutFormateado) {
        if (rutFormateado == null
                || rutFormateado.trim().replace(".", "").replace("-", "").isEmpty()) {
            return true;
        }
    
        Object[] resultado;
        try {
            resultado = FormatoRut.obtenerRut(rutFormateado);
            int rut = (Integer) resultado[0];
            char digitoVerificador = (Character) resultado[1];
            int m = 0, s = 1;
            for (; rut != 0; rut /= 10) {
                s = (s + rut % 10 * (9 - m++ % 6)) % 11;
}
            return digitoVerificador == (char) (s != 0 ? s + 47 : 75);
        } catch (RutNoIdentificableException ex) {
            Logger.getLogger(ValidaRut.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
