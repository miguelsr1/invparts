/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class ProductoMB implements Serializable {
    
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");
    
    private static final long serialVersionUID = 1L;
    
    private boolean esModificacion = false;
    
    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;
    
    public ProductoMB() {
    }
    
    @PostConstruct
    public void init() {
    }

    //<editor-fold desc="Metodos getters y setters">
    
    public void selectProducto(SelectEvent event) {
        esModificacion = true;
    }
    
    public ManttoService getManttoService() {
        return manttoService;
    }
    
    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    //</editor-fold >
    public void guardar() {
        if (manttoService.guardarConIdAutogenerado(null) == 1) {
            if (esModificacion) {
                //lstProducto.add(pro);
            }
            //pro = new Producto();
        }
        esModificacion = false;
    }
    
    public void nuevo() {
//        pro = new Producto();
//        pro.setStatus((short) 0);
    }
    
    public void openDialogCategory() {
        Map<String, Object> options = new HashMap();
        options.put("modal", true);
        options.put("width", 640);
        options.put("height", 360);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        //options.put("headerElement", RESOURCE_BUNDLE.getString("asociarCategoriaProducto"));

        Map<String, List<String>> params = new HashMap();
        List<String> values = new ArrayList();
        //values.add(String.valueOf(pro.getIdProducto()));
        params.put("idProducto", values);
        
        PrimeFaces.current().dialog().openDynamic("/app/mantto/dialog/addCategoryProduct", options, params);
    }
    
    public void editProducto() {
    }
}
