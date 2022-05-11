/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author guilberto
 */
@ApplicationException(rollback = false)
public class NotificacionCorreoException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotificacionCorreoException(String message) {
        super(message);
    }

    public NotificacionCorreoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificacionCorreoException(Throwable cause) {
        super(cause);
    }

    public NotificacionCorreoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}