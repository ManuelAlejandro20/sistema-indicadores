/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.beans;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Ricardo
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    /**
     * Creates a new instance of loginBean
     */
    @Resource(name="largoMaximoClave")
    private Integer largoMaximoClave;

    public Integer getLargoMaximoClave() {
        return largoMaximoClave;
    }
    
    public LoginBean() {
    }
    
}

