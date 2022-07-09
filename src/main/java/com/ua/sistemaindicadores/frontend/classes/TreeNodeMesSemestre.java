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
    
    public TreeNodeMesSemestre(List<String> keys) {  
        this.root = new DefaultTreeNode("Root Node", null);
        TreeNode key;
        TreeNode primeraActividad;
        for(String s : keys){     
            key = new DefaultTreeNode(new TreeNodeRow(s, 1, 0, null, null), this.root);
            primeraActividad = new DefaultTreeNode(new TreeNodeRow(null, null, null, "", s), key);
        }                       
    }
    
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }    
}
