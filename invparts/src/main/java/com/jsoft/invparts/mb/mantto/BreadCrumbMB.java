/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@SessionScoped
public class BreadCrumbMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private MenuModel model;

    public BreadCrumbMB() {
    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        DefaultMenuItem root = new DefaultMenuItem("Home", "fa fa-home", "/app/principal.xhtml");

        model.addElement(root);
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public String addElemento(String nombre, String icono, String url) {
        DefaultMenuItem elemento = new DefaultMenuItem(nombre, icono, url);
        model.addElement(elemento);
        return "addStock.xhtml?faces-redirect=true";
    }
}
