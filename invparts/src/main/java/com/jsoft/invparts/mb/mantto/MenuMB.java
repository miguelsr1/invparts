/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@SessionScoped
public class MenuMB implements Serializable {
    
    private HashMap<String, Object> variables = new HashMap();

    private static final long serialVersionUID = 1L;

    public MenuMB() {
    }

    public HashMap<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, Object> variables) {
        this.variables = variables;
    }

}
