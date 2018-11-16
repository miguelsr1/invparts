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

    @Override
    public String toString() {
        return "Sucursal{" + "idSucursal=" + idSucursal + ", nombreSucursal=" + nombreSucursal + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO sucursal (nombre_sucursal, id_empresa) VALUES (?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreSucursal, idEmpresa};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE sucursal SET nombre_sucursal=?, id_empresa=? WHERE id_sucursal=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreSucursal, idEmpresa, idSucursal};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idSucursal == null;
    }

}
