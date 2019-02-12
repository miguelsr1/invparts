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
    public String nombreItem;
    public String descripcion;
    public String upcCodigo;
    public String codigoProducto;
    public Short tamanio;
    public Double peso;
    public String especificaciones;
    public String urlImagen;
    public Integer idEstante;

    private List<InformacionItem> lstInformacionItem = new ArrayList();
    private List<ProductoCategoria> lstCategorias = new ArrayList();

    public Item() {
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getUpcCodigo() {
        return upcCodigo;
    }

    public void setUpcCodigo(String upcCodigo) {
        this.upcCodigo = upcCodigo;
    }

    @Override
    public String toString() {
        if (upcCodigo == null) {
            return "";
        } else {
            return upcCodigo + ' ' + nombreItem;
        }
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO item (nombre_item, descripcion, upc_codigo, codigo_producto, tamanio, peso, especificaciones, url_imagen, id_estante) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreItem, descripcion, upcCodigo, codigoProducto, tamanio, peso, especificaciones, urlImagen, idEstante};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE item SET nombre_item=?, descripcion=?, upc_codigo=?, codigo_producto=?, tamanio=?, peso=?, especificaciones=?, url_imagen=?, id_estante=? WHERE id_item=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreItem, descripcion, upcCodigo, codigoProducto, tamanio, peso, especificaciones, urlImagen, idEstante, idItem};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idItem == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idItem = id;
    }

    public List<ProductoCategoria> getLstCategorias() {
        return lstCategorias;
    }

    public void setLstCategorias(List<ProductoCategoria> lstCategorias) {
        this.lstCategorias = lstCategorias;
    }
}
