/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Categoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class CategoriaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cadenaDeBusqueda;

    public CategoriaMB() {
    }

    //<editor-fold desc="Metodos getters y setters">
    public String getCadenaDeBusqueda() {
        return cadenaDeBusqueda;
    }

    public void setCadenaDeBusqueda(String cadenaDeBusqueda) {
        this.cadenaDeBusqueda = cadenaDeBusqueda;
    }

    //</editor-fold>
    public List<Categoria> completeCategoriaContains(String query) {
        List<Categoria> lstCategoria = new ArrayList();
        

        return lstCategoria;
    }
}
