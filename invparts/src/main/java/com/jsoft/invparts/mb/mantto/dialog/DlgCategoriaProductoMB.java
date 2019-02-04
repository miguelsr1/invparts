/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto.dialog;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.ProductoCategoria;
import com.jsoft.invparts.model.inventario.dto.ProductoCategoriaDto;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.PrimeFaces;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class DlgCategoriaProductoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private Item idItem;
    private String item;
    private String cadenaDeBusqueda;
    private Integer idCategoriaSeleccionada;
    private Categoria categoria = new Categoria();
    private ProductoCategoriaDto pcDto;
    private List<ProductoCategoriaDto> lstCategoriasPorProducto = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public DlgCategoriaProductoMB() {
    }

    public Integer getIdCategoriaSeleccionada() {
        return idCategoriaSeleccionada;
    }

    public void setIdCategoriaSeleccionada(Integer idCategoriaSeleccionada) {
        this.idCategoriaSeleccionada = idCategoriaSeleccionada;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Item getIdItem() {
        return idItem;
    }

    public void setIdItem(Item idProducto) {
        this.idItem = idProducto;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public String getCadenaDeBusqueda() {
        return cadenaDeBusqueda;
    }

    public void setCadenaDeBusqueda(String cadenaDeBusqueda) {
        this.cadenaDeBusqueda = cadenaDeBusqueda;
    }

    

    public List<ProductoCategoriaDto> getLstCategoriasPorProducto() {
        if (lstCategoriasPorProducto.isEmpty() && idItem != null) {
            lstCategoriasPorProducto = manttoService.getLstCategoriasByProducto(Integer.parseInt(idItem.getIdItem().toString()));
        }

        return lstCategoriasPorProducto;
    }

    public void addCategoriaAProducto() {
        pcDto = new ProductoCategoriaDto();
        pcDto.setIdCategoria(categoria.getIdCategoria());
        pcDto.setIdItem(idItem.getIdItem());
        pcDto.setNombreCategoria(categoria.getNombreCategoria());

        ProductoCategoria pc = new ProductoCategoria();
        pc.setIdCategoria(pcDto.getIdCategoria());
        pc.setIdItem(pcDto.getIdItem());
        if (manttoService.guardarConIdAutogenerado(pc) > 0) {
            lstCategoriasPorProducto.add(pcDto);
            categoria = new Categoria();
            pcDto = new ProductoCategoriaDto();
        }
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

    @FacesConverter(forClass = Categoria.class, value = "categoriaControllerConverter")
    public static class CategoriaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DlgCategoriaProductoMB controller = (DlgCategoriaProductoMB) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "dlgCategoriaProductoMB");
            if (value.isEmpty() || !value.equals("null")) {
                return controller.getManttoService().findCategoriaById(getKey(value));
            } else {
                return new Categoria();
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
            if (object == null || ((Categoria) object).getIdCategoria() == null) {
                return null;
            }
            if (object instanceof Categoria) {
                Categoria o = (Categoria) object;
                return getStringKey(o.getIdCategoria());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Categoria.class.getName());
            }
        }
    }
}
