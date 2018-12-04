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
public class Sucursal implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idSucursal;
    public String nombreSucursal;
    public String direccionSucursal;
    public String telefonoSucursal;
    public Integer idEmpresa;

    public Sucursal() {
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }

    public String getTelefonoSucursal() {
        return telefonoSucursal;
    }

    public void setTelefonoSucursal(String telefonoSucursal) {
        this.telefonoSucursal = telefonoSucursal;
    }

    @Override
    public String toString() {
        return "Sucursal{" + "idSucursal=" + idSucursal + ", nombreSucursal=" + nombreSucursal + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO sucursal (nombre_sucursal, id_empresa, direccion_sucursal, telefono_sucursal) VALUES (?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreSucursal, idEmpresa, direccionSucursal, telefonoSucursal};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE sucursal SET nombre_sucursal=?, id_empresa=?, direccion_sucursal=?, telefono_sucursal=? WHERE id_sucursal=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreSucursal, idEmpresa, direccionSucursal, telefonoSucursal, idSucursal};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idSucursal == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idSucursal = id;
    }

}
