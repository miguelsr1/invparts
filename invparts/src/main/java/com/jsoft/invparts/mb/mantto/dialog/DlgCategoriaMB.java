/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto.dialog;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class DlgCategoriaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Categoria categoria = new Categoria();
    private List<Categoria> lstCategorias = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public DlgCategoriaMB() {
    }

    @PostConstruct
    public void init() {
        lstCategorias = manttoService.lstCategoria(null);
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getLstCategorias() {
        return lstCategorias;
    }

    public void setLstCategorias(List<Categoria> lstCategorias) {
        this.lstCategorias = lstCategorias;
    }

    public void guardarCategoria() {
        manttoService.guardarConIdAutogenerado(categoria);
    }
}
