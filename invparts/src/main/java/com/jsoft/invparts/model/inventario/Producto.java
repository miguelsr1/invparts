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
public class Producto implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idProducto;
    public String nombreProducto;
    public Short status;

    public Producto() {
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + "}";
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO producto (nombre_producto, status)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreProducto, status};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE producto SET nombre_producto=?, status=? WHERE id_producto=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreProducto, status, idProducto};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idProducto == null;
    }
}
