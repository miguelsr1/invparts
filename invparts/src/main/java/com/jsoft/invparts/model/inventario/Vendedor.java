/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.UtilWhere;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author torre
 */
public class Vendedor extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private BigInteger idVendedor;
    private String nombreVendedor;
    private String apellidoVendedor;

    public Vendedor() {
    }

    public BigInteger getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(BigInteger idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getApellidoVendedor() {
        return apellidoVendedor;
    }

    public void setApellidoVendedor(String apellidoVendedor) {
        this.apellidoVendedor = apellidoVendedor;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "idVendedor=" + idVendedor + ", nombreVendedor=" + nombreVendedor + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO Vendedor(nombre_vendedor, apellido_vendedor) values(?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreVendedor, apellidoVendedor};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE Vendedor SET nombre_vendedor=?, apellido_vendedor=? WHERE id_vendedor=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreVendedor, apellidoVendedor, idVendedor};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idVendedor == null;
    }
}
