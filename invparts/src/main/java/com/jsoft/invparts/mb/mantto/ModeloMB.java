/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author torre
 */
@ManagedBean
@ViewScoped
public class ModeloMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Modelo model = new Modelo();
    private List<Modelo> lstModelo = new ArrayList();
    private List<Marca> lstMarca = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    /**
     * Creates a new instance of PerfilMB
     */
    public ModeloMB() {
    }

    @PostConstruct
    public void init() {
        lstModelo = manttoService.listModelo(null);
        lstMarca = manttoService.listMarca(null);

    }
    
    public void updateListaModelo(){
        lstModelo = manttoService.listModelo(model);
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public Modelo getModel() {
        return model;
    }

    public void setModel(Modelo model) {
        if (model != null) {
            this.model = model;
        }
    }

    public List<Marca> getLstMarca() {
        return lstMarca;
    }

    public void setLstMarca(List<Marca> lstMarca) {
        this.lstMarca = lstMarca;
    }

    public List<Modelo> getLstModelo() {
        return lstModelo;
    }

    public void setLstModelo(List<Modelo> lstModelo) {
        this.lstModelo = lstModelo;
    }

    public void buscarModelo() {
        lstModelo = manttoService.listModelo(model);
    }

    public void guardar() {
        if (model.getNombreModelo() != null && !model.getNombreModelo().isEmpty()) {
            int valor = manttoService.guardarConIdAutogenerado(model);
            if (valor > 0) {
                lstModelo.add(model);
                model = new Modelo();
            }
        } else {
            JsfUtil.addWarningMessage("Debe de completar todos los registros");
        }
    }

    public void limpiar() {
        model = new Modelo();
    }
    
    public void selectModelo(){
        PrimeFaces.current().dialog().closeDynamic(model.idModelo);
    }
    
    public void closeSelectModelo(){
        PrimeFaces.current().dialog().closeDynamic(null);
    }
}
