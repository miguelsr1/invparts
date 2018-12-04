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
public class Estante implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idEstante;
    public String nombreEstante;
    public Integer idSucursal;

    public Estante() {
    }

    public Integer getIdEstante() {
        return idEstante;
    }

    public void setIdEstante(Integer idEstante) {
        this.idEstante = idEstante;
    }

    public String getNombreEstante() {
        return nombreEstante;
    }

    public void setNombreEstante(String nombreEstante) {
        this.nombreEstante = nombreEstante;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public String toString() {
        return "Estante{" + "idEstante=" + idEstante + ", nombreEstante=" + nombreEstante + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO estante (nombre_estante, id_sucursal) VALUES (?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreEstante, idSucursal};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE estante SET nombre_estante=?, id_sucursal=? WHERE id_estante=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreEstante, idSucursal, idEstante};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idEstante == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idEstante = id;
    }
}