/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.servicios.ItemService;
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
public class ItemMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    private Item item = new Item();
    private List<Producto> lstProductos = new ArrayList();

    public ItemMB() {
    }

    @PostConstruct
    public void init() {
        lstProductos = itemService.getLstProducto(0);
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void guardar() {
    }

    public void nuevo() {
    }

}
