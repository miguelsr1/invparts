/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class ProductoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Producto pro = new Producto();
    private List<Producto> lstProducto = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public ProductoMB() {
    }

    @PostConstruct
    public void init() {
        lstProducto = manttoService.listProducto();
    }

    //<editor-fold desc="Metodos getters y setters">
    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        if (pro != null) {
            this.pro = pro;
        }
    }

    public List<Producto> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    //</editor-fold >
    public void guardar() {
        if (manttoService.guardarConIdAutogenerado(pro) == 1) {
            lstProducto = manttoService.listProducto();
            pro = new Producto();
        }
    }

    public void openDialogCategory() {
        Map<String, Object> options = new HashMap();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");

        PrimeFaces.current().dialog().openDynamic("/app/mantto/category", options, null);
    }
}
