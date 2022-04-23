/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.utils;

import com.ua.sistemaindicadores.backend.exceptions.RutNoFormateableException;
import com.ua.sistemaindicadores.backend.exceptions.RutNoIdentificableException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Ricardo
 */
public abstract class FormatoRut {
    
    public static Object[] obtenerRut(String rutFormateado) throws RutNoIdentificableException {
        try {
            String r = rutFormateado.trim().toUpperCase();
            r = r.replace(".", "").replace("-", "");
            int rut = Integer.parseInt(r.substring(0, r.length() - 1));
            char digitoVerificador = r.charAt(r.length() - 1);
            return new Object[]{rut, digitoVerificador};
        } catch (RuntimeException ex) {
            throw new RutNoIdentificableException("No se puede obtener un rut desde: " + rutFormateado, ex);
}
    }

    public static String formatearRut(int rut, char digitoVerificador) throws RutNoFormateableException {
        String strRut = String.format("%012d", rut);
        try {
            MaskFormatter formatter = new MaskFormatter("###.###.###.###");
            formatter.setValueContainsLiteralCharacters(false);
            String rutFormateado = formatter.valueToString(strRut) + "-" + digitoVerificador;
            rutFormateado = rutFormateado.toUpperCase();
            Matcher matcher = Pattern.compile("[1-9]+").matcher(rutFormateado);
            if (matcher.find()) {
                rutFormateado = rutFormateado.substring(matcher.start());
            }
            return rutFormateado;
        } catch (ParseException | RuntimeException ex) {
            throw new RutNoFormateableException("No se puede dar un formato para rut = " + rut + ", digito = " + digitoVerificador, ex);
        }
    }

}
