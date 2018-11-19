/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Vendedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.jsoft.invparts.servicios.ManttoService;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class SellerMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean existeUsuario = false;
    private Boolean existeCorreoElectronico = false;
    private Vendedor ven = new Vendedor();
    private List<Vendedor> listVendedor = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public SellerMB() {
    }

    @PostConstruct
    public void init() {
        listVendedor = manttoService.listVendedor();
    }

    public Vendedor getVen() {
        return ven;
    }

    //<editor-fold desc="Metodos getters y setters">
    public void setVen(Vendedor ven) {
        if (ven != null) {
            this.ven = ven;
        }
    }

    public List<Vendedor> getListVendedor() {
        return listVendedor;
    }

    public void setListVendedor(List<Vendedor> listVendedor) {
        this.listVendedor = listVendedor;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public Boolean getExisteUsuario() {
        return existeUsuario;
    }

    public void setExisteUsuario(Boolean existeUsuario) {
        this.existeUsuario = existeUsuario;
    }

    public Boolean getExisteCorreoElectronico() {
        return existeCorreoElectronico;
    }

    public void setExisteCorreoElectronico(Boolean existeCorreoElectronico) {
        this.existeCorreoElectronico = existeCorreoElectronico;
    }

    //</editor-fold>
    public void guardar() {
        if (manttoService.guardarConIdAutogenerado(ven) == 1) {
            listVendedor = manttoService.listVendedor();
            ven = new Vendedor();
        } else {
        }
    }

}
