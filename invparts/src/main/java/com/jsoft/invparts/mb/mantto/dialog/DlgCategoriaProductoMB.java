/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto.dialog;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.ProductoCategoria;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class DlgCategoriaProductoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cadenaDeBusqueda;
    private ProductoCategoria productoCategoria = new ProductoCategoria();
    
    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public DlgCategoriaProductoMB() {
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public ProductoCategoria getProductoCategoria() {
        return productoCategoria;
    }

    public void setProductoCategoria(ProductoCategoria productoCategoria) {
        this.productoCategoria = productoCategoria;
    }

    public String getCadenaDeBusqueda() {
        return cadenaDeBusqueda;
    }

    public void setCadenaDeBusqueda(String cadenaDeBusqueda) {
        this.cadenaDeBusqueda = cadenaDeBusqueda;
    }

    public List<Categoria> completeCategoriaContains(String query) {
        List<Categoria> lstCategoria = manttoService.getLstCategoriaByLikeNombre(query);

        return lstCategoria;
    }
    
    public void openDialogCategory() {
        Map<String, Object> options = new HashMap();
        options.put("modal", true);
        options.put("width", 1000);
        options.put("height", 320);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        PrimeFaces.current().dialog().openDynamic("/app/mantto/dialog/newCategory", options, null);
    }
}
