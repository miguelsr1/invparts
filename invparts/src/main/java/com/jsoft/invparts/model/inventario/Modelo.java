/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.UtilWhere;
import java.io.Serializable;

/**
 *
 * @author misanchez
 */
public class Modelo extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idModelo;
    public String nombreModelo;
    public String codigoModelo;
    public Integer idMarca;

    public Modelo() {
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public String getCodigoModelo() {
        return codigoModelo;
    }

    public void setCodigoModelo(String codigoModelo) {
        this.codigoModelo = codigoModelo;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public String toString() {
        return "Modelo{" + "idModelo=" + idModelo + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO modelo (nombre_modelo, codigo_modelo, id_marca) VALUES (?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreModelo, codigoModelo, idMarca};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE modelo SET nombre_modelo=?, codigo_modelo=?, id_marca=? WHERE id_modelo=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreModelo, codigoModelo, idMarca, idModelo};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idModelo == null;
    }
}