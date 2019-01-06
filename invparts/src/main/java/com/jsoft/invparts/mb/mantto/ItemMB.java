/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Estante;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.servicios.ItemService;
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
        item = itemService.getItemByPk(1);
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
    
    public List<Estante> getLstEstantes(){
        return itemService.getLstEstantesByIdSucursal(1);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void guardar() {
        itemService.guardar(item);
    }

    public void nuevo() {
        item = new Item();
    }

    public void showDialogFotografias(){
        if(item.getIdItem() != null){
            PrimeFaces.current().executeScript("PF('dlgFotos').show()");
        }else{
            JsfUtil.addWarningMessage("Debe de guardar primero el item");
        }
    }
}
