/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario;

import com.jsoft.invparts.dao.PersistenciaDao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author misanchez
 */
public class Compra implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idCompra;
    public Date fechaCompra;
    public Date fechaRecibido;
    public BigDecimal montoCompra;
    public Integer idEmpresa;

    public Compra() {
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public BigDecimal getMontoCompra() {
        return montoCompra;
    }

    public void setMontoCompra(BigDecimal montoCompra) {
        this.montoCompra = montoCompra;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public String toString() {
        return "Compra{" + "id_compra=" + idCompra + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO compra (fecha_compra, fecha_recibido, monto_compra, id_empresa) VALUES (?, ?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{fechaCompra, fechaRecibido, montoCompra, idEmpresa};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE compra SET fecha_compra=?, fecha_recibido=?, monto_compra=?, id_empresa=? WHERE id_compra=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{fechaCompra, fechaRecibido, montoCompra, idEmpresa, idCompra};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idCompra == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idCompra = id;
    }
}
