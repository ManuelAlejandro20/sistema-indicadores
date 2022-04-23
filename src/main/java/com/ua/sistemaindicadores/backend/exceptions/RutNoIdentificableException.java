/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Ricardo
 */
@ApplicationException(rollback = false)
public class RutNoIdentificableException extends Exception {

    private static final long serialVersionUID = 1L;

    public RutNoIdentificableException(String message) {
        super(message);
    }

    public RutNoIdentificableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RutNoIdentificableException(Throwable cause) {
        super(cause);
}

    public RutNoIdentificableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
