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
public class RutNoFormateableException extends Exception {

    private static final long serialVersionUID = 1L;

    public RutNoFormateableException(String message) {
        super(message);
    }

    public RutNoFormateableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RutNoFormateableException(Throwable cause) {
        super(cause);
}

    public RutNoFormateableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
