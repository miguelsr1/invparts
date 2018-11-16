/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario;

import com.jsoft.invparts.dao.PersistenciaDao;
import java.io.Serializable;

/**
 *
 * @author misanchez
 */
public class ProductoCategoria implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idProductoCategoria;
    public Integer idProducto;
    public Integer idCategoria;

    public ProductoCategoria() {
    }

    public Integer getIdProductoCategoria() {
        return idProductoCategoria;
    }

    public void setIdProductoCategoria(Integer idProductoCategoria) {
        this.idProductoCategoria = idProductoCategoria;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "ProductoCategoria{" + "idProductoCategoria=" + idProductoCategoria + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO producto_categoria (id_producto, id_categoria) VALUES (?, ?,)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idProducto, idProducto};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE producto_categoria SET id_producto=?, id_categoria=? WHERE id_producto_categoria=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idProducto, idProducto, idProductoCategoria};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idProductoCategoria == null;
    }

}
