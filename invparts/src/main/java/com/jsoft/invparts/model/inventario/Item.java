/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario;

import com.jsoft.invparts.dao.PersistenciaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author misanchez
 */
public class Item implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idItem;
    public Integer idProducto;
    public String descripcion;
    public Short tamanio;
    public Double peso;
    public String especificaciones;
    public String urlImagen;
    public String codigoProducto;
    public Integer idEstante;
    public Double precioUnitario;
    public Double precioCompra;

    private List<InformacionItem> lstInformacionItem = new ArrayList();

    public Item() {
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getTamanio() {
        return tamanio;
    }

    public void setTamanio(Short tamanio) {
        this.tamanio = tamanio;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getIdEstante() {
        return idEstante;
    }

    public void setIdEstante(Integer idEstante) {
        this.idEstante = idEstante;
    }

    public List<InformacionItem> getLstInformacionItem() {
        return lstInformacionItem;
    }

    public void setLstInformacionItem(List<InformacionItem> lstInformacionItem) {
        this.lstInformacionItem = lstInformacionItem;
    }

    @Override
    public String toString() {
        return "Item{" + "idItem=" + idItem + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO item (id_producto, descripcion, tamanio, peso, especificaciones, url_imagen, codigo_producto, id_estante, precio_unitario, precio_compra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idProducto, descripcion, tamanio, peso, especificaciones, urlImagen, codigoProducto, idEstante, precioUnitario, precioCompra};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE item SET id_producto=?, descripcion=?, tamanio=?, peso=?, especificaciones=?, precio_unitario =?, precio_compra = ?, url_imagen=?, codigo_producto=?, id_estante=? WHERE id_item=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idProducto, descripcion, tamanio, peso, especificaciones, precioUnitario, precioCompra, urlImagen, codigoProducto, idEstante, idItem};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idItem == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idItem = id;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }
}
