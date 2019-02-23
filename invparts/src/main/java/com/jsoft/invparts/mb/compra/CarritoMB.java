/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.compra;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.dto.CarritoDto;
import com.jsoft.invparts.model.inventario.dto.ItemDto;
import com.jsoft.invparts.servicios.ItemService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@SessionScoped
public class CarritoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    private Integer numItems;
    private Integer idMarca;
    private Integer idModelo;
    private Integer idCategoria;
    private Integer totalItem;
    private Double montoTotal;
    private Double tax;

    private ItemDto itemSelected;
    private List<String> imagenesDeProducto = new ArrayList();
    private List<ItemDto> lstItems = new ArrayList();
    private List<CarritoDto> lstItemsCarrito = new ArrayList();

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

    public List<Categoria> getLstMarcas() {
        return itemService.getLstMarca();
    }

    public List<Categoria> getLstModelo() {
        return itemService.getLstModeloByIdMarca(idMarca);
    }

    public List<Categoria> getLstCategoria() {
        return itemService.getLstCategoriaByModelo(idModelo);
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<ItemDto> getLstItems() {
        return lstItems;
    }

    public void setLstItems(List<ItemDto> lstItems) {
        this.lstItems = lstItems;
    }

    public ItemDto getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(ItemDto itemSelected) {
        this.itemSelected = itemSelected;
    }

    public List<String> getImagenesDeProducto() {
        return imagenesDeProducto;
    }

    public void setImagenesDeProducto(List<String> imagenesDeProducto) {
        this.imagenesDeProducto = imagenesDeProducto;
    }

    public Integer getNumItems() {
        return numItems;
    }

    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    public List<CarritoDto> getLstItemsCarrito() {
        return lstItemsCarrito;
    }

    public void setLstItemsCarrito(List<CarritoDto> lstItemsCarrito) {
        this.lstItemsCarrito = lstItemsCarrito;
    }

    public String getNumItemDeCarro() {
        return getLstItemsCarrito().isEmpty() ? "0" : String.valueOf(getLstItemsCarrito().size());
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public Double getTax() {
        return tax;
    }

    //========================================================================================================
    public void calcularTotales() {
        totalItem = 0;
        for (CarritoDto carritoDto : lstItemsCarrito) {
            totalItem += carritoDto.getNumero();
        }

        montoTotal = 0d;
        for (CarritoDto carritoDto : lstItemsCarrito) {
            montoTotal += carritoDto.getNumero() * carritoDto.getItemDto().getPrecioVenta();
        }

        tax = 0d;

        if (montoTotal > 0d) {
            tax = montoTotal * 0.06;
        }
    }

    public void limpiarFiltros() {
        idCategoria = 0;
        idModelo = 0;
        idMarca = 0;
        lstItems.clear();
        imagenesDeProducto.clear();
    }

    public void buscarItems() {
        lstItems = itemService.getLstItemsByCategory(idCategoria);
    }

    public void cargarFotos() {
        imagenesDeProducto.clear();

        File folderImg = new File(RESOURCE_BUNDLE.getString("pathimagenesitem") + itemSelected.getCodigoProducto());
        if (folderImg.exists()) {
            for (File listFile : folderImg.listFiles()) {
                imagenesDeProducto.add(listFile.getName());
            }
        }
    }

    public void addItemToCar() {
        addItemToCar(itemSelected);
    }

    public void addItemToCar(ItemDto idItem) {
        if (numItems != null && numItems > 0) {
            if (numItems > idItem.getCantidad()) {
                JsfUtil.addWarningMessage("La cantidad ingresada sobrepasa la disponibilidad actual.");
            } else {
                CarritoDto carritoItem = new CarritoDto();
                carritoItem.setItemDto(idItem);
                carritoItem.setNumero(numItems);

                lstItemsCarrito.add(carritoItem);
                numItems = null;
            }
        }else{
            JsfUtil.addWarningMessage("Debe de ingresar un número válido.");
        }
    }

    public void removerItemDelCarro(CarritoDto carritoDto) {
        lstItemsCarrito.remove(carritoDto);
        calcularTotales();
    }
}
