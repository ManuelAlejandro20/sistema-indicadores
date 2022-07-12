/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.frontend.classes;

import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author aleja
 */
public class TreeNodeMesSemestre {
    private TreeNode root;
    
    public TreeNodeMesSemestre(List<String> keys, String uRepr) {  
        this.root = new DefaultTreeNode("Root Node", null);
        
        TreeNode key;
        TreeNode primeraFila;        
        
        for(String s : keys){     
            TreeNodeRow periodo = new TreeNodeRow(s);
            TreeNodeRow primeraActividad = new TreeNodeRow("", s);
            switch(uRepr){
                case "Peso ($)":        
                    periodo.setNumActividades(1);
                    periodo.setMonto(0);
                    primeraActividad.setMonto(0);
                    break;                
                case "Porcentaje (%)":
                    periodo.setPorcActividad(1);                    
                    break;
                default:        
                    periodo.setNumActividades(1);
                    periodo.setLogro(0);                  
                    break;
            }
            key = new DefaultTreeNode(periodo, this.root);
            primeraFila = new DefaultTreeNode(primeraActividad, key);              
        }                       
    }
    
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }    
}
