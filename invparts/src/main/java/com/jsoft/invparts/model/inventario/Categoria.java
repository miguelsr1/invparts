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
public class Categoria implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idCategoria;
    public String nombreCategoria;
    public Integer padreIdCategoria;

    public Categoria() {
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getPadreIdCategoria() {
        return padreIdCategoria;
    }

    public void setPadreIdCategoria(Integer padreIdCategoria) {
        this.padreIdCategoria = padreIdCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", padreIdCategoria=" + padreIdCategoria + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO categoria (nombre_categoria, padre_id_categoria) VALUES (?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreCategoria, padreIdCategoria};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE categoria SET nombre_categoria=?, padre_id_categoria=? WHERE id_categoria=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreCategoria, padreIdCategoria, idCategoria};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idCategoria == null;
    }

}
