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
 * @author DesarrolloPc
 */
public class DetalleEntrada implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private Integer idDetalleEntrada;
    private Integer idEntrada;
    private Integer idItem;
    private Integer cantidad;
    private Double precioCompra;
    private Double precioVenta;

    public DetalleEntrada() {
    }

    public Integer getIdDetalleEntrada() {
        return idDetalleEntrada;
    }

    public void setIdDetalleEntrada(Integer idDetalleEntrada) {
        this.idDetalleEntrada = idDetalleEntrada;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO detalle_entrada (id_entrada, id_item, cantidad, precio_compra, precio_venta) VALUES (?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idEntrada, idItem, cantidad, precioCompra, precioVenta};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE detalle_entrada SET cantidad=?, precio_compra=?, precio_venta=? WHERE id_detalle_entrada=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{cantidad, precioCompra, precioVenta, idDetalleEntrada};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idDetalleEntrada == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idDetalleEntrada = id;
    }
}
