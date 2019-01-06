/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.compra;

import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.servicios.ItemService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@SessionScoped
public class CarritoMB implements Serializable {

    private Integer idMarca;
    private List<Marca> lstMarcas = new ArrayList();

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    public CarritoMB() {
    }

    @PostConstruct
    public void init() {
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public List<Marca> getLstMarcas() {
        return itemService.getLstMarca();
    }

    public List<Modelo> getLstModelo(){
        return itemService.getLstModeloByIdMarca(idMarca);
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }
    
}
