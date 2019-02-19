/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.servicios.ItemService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class SearchProductMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    private Item item = new Item();

    public SearchProductMB() {
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> searchProduct(String query) {
        return itemService.getLstItemsByUpcContains(query, true);
    }

    public void onItemSelect(SelectEvent se) {
        item = (Item) se.getObject();

        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "/app/shopping/viewProduct.xhtml??faces-redirect=true&idItem=" + item.getIdItem());
    }
}
