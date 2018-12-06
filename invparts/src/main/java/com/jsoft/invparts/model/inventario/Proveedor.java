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
public class Proveedor implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idProveedor;
    public String nombreProveedor;
    public String telefonoProveedor;
    public String correoElectronico;

    public Proveedor() {
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "idProveedor=" + idProveedor + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO proveedor (nombre_proveedor, telefono_proveedor, correo_electronico) VALUES (?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreProveedor, telefonoProveedor, correoElectronico};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE proveedor SET nombre_proveedor=?, telefono_proveedor=?, correo_electronico=? WHERE id_proveedor=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreProveedor, telefonoProveedor, correoElectronico, idProveedor};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idProveedor == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idProveedor = id;
    }

}
