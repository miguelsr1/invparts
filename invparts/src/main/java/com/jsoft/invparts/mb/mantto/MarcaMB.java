/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;


import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author torre
 */
@ManagedBean
@ViewScoped
public class MarcaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Marca mar = new Marca();
    private List<Marca> lstMarca = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    /**
     * Creates a new instance of PerfilMB
     */
    public MarcaMB() {
    }

    @PostConstruct
    public void init() {
        lstMarca = manttoService.listMarca(null);

    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public Marca getMar() {
        return mar;
    }

    public void setMar(Marca mar) {
        if (mar != null) {
            this.mar = mar;
        }
    }

    public List<Marca> getLstMarca() {
        return lstMarca;
    }

    public void setLstMarca(List<Marca> lstMarca) {
        this.lstMarca = lstMarca;
    }

     public void buscarMarca() {
        lstMarca = manttoService.listMarca(mar);
    }

    public void guardar() {
        if (mar.getNombreMarca() != null && !mar.getNombreMarca().isEmpty()) {
            int valor = manttoService.guardarConIdAutogenerado(mar);
            if (valor > 0) {
                mar.setIdMarca(valor);
                lstMarca.add(mar);
                mar = new Marca();
            }
        } else {
            JsfUtil.addWarningMessage("Debe de completar todos los registros");
        }
    }

    public void limpiar() {
        mar = new Marca();
    }
}
