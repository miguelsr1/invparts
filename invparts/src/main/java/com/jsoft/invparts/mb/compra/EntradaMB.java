/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.compra;

import com.jsoft.invparts.model.inventario.Entrada;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.dto.DetalleEntradaDto;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.servicios.ItemService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class EntradaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    private Entrada entrada = new Entrada();
    private Item item = new Item();

    private List<DetalleEntradaDto> lstDetalleEntrada = new ArrayList();

    public EntradaMB() {
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Empresa> getLstProveedor() {
        return itemService.getLstProveedor();
    }

    public List<DetalleEntradaDto> getLstDetalleEntrada() {
        return lstDetalleEntrada;
    }

    public void setLstDetalleEntrada(List<DetalleEntradaDto> lstDetalleEntrada) {
        this.lstDetalleEntrada = lstDetalleEntrada;
    }

    //==============================================================
    public List<Item> searchUpc(String query) {
        return itemService.getLstItemsByUpcContains(query);
    }

    public void onItemSelect(SelectEvent se) {
        item = (Item) se.getObject();
    }

    public void addItemToOrder() {
        if (item == null || item.getIdItem() == null) {
            JsfUtil.addWarningMessage("Debe de seleccionar un producto valido");
        } else {
            DetalleEntradaDto det = new DetalleEntradaDto();

            det.setIdItem(item.getIdItem());
            det.setNombreProducto(item.getNombreItem());

            lstDetalleEntrada.add(det);

            item = new Item();
        }
    }
    
   public void guardar(){    
       entrada.setLstDetalleEntrada(lstDetalleEntrada);
       itemService.guardarEntrada(entrada);
       if(entrada.getIdEntrada() != null){
           JsfUtil.addSuccessMessage("Registros almacenados con éxito");
       }
   } 
}
