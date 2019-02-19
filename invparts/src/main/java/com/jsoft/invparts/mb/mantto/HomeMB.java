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
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class HomeMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    private Item upcCodigo = new Item();

    public HomeMB() {
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item getUpcCodigo() {
        return upcCodigo;
    }

    public void setUpcCodigo(Item upcCodigo) {
        this.upcCodigo = upcCodigo;
    }

    public List<Item> searchUpc(String query) {
        return itemService.getLstItemsByUpcContains(query, false);
    }

    public String editItem() {
        return "items.xhtml?faces-redirect=true&includeViewParams=true&idItem=" + upcCodigo.getIdItem();
    }

    public void onItemSelect(SelectEvent se) {
        upcCodigo = (Item) se.getObject();
    }

    @FacesConverter(forClass = Item.class, value = "itemControllerConverter")
    public static class ItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HomeMB controller = (HomeMB) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "homeMB");
            if (value.isEmpty() || !value.equals("null")) {
                return controller.getItemService().getItemByPk(getKey(value));
            } else {
                return new Item();
            }
        }

        java.lang.Integer getKey(String value) {
            return Integer.valueOf(value);
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null || ((Item) object).getIdItem() == null) {
                return null;
            }
            if (object instanceof Item) {
                Item o = (Item) object;
                return getStringKey(o.getIdItem());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Item.class.getName());
            }
        }
    }
}
