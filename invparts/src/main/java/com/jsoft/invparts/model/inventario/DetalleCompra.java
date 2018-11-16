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
public class DetalleCompra implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;
    
    private Integer idDetalleCompra;
    private Integer idCompra;
    private Integer idItem;
    private Double precioUnitario;
    private Integer existencia;

    public DetalleCompra() {
    }

    public Integer getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Integer idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "idDetalleCompra=" + idDetalleCompra + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO detalle_compra (id_compra, id_item, precio_unitario, existencia, id_estante) VALUES (?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idCompra, idItem, precioUnitario, existencia};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE detalle_compra SET id_compra=?, id_item=?, precio_unitario=?, existencia=? WHERE id_detalle_compra=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idCompra, idItem, precioUnitario, existencia, idDetalleCompra};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idDetalleCompra == null;
    }
    
}
