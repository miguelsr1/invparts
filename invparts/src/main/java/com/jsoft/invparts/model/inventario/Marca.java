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
public class Marca extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idMarca;
    public String nombreMarca;
    public Short marcaActiva;

    private Boolean activa = false;

    public Marca() {
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public Short getMarcaActiva() {
        return marcaActiva;
    }

    public void setMarcaActiva(Short marcaActiva) {
        this.marcaActiva = marcaActiva;
    }

    @Override
    public String toString() {
        return "Marca{" + "idMarca=" + idMarca + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO marca(nombre_marca, marca_activa) VALUES (?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreMarca, marcaActiva};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE marca SET nombre_marca=?, marca_activa=? WHERE id_marca=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreMarca, marcaActiva, idMarca};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idMarca == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idMarca = id;
    }

    public Boolean getActiva() {
        if (marcaActiva != null) {
            return marcaActiva == 1;
        } else {
            return false;
        }
    }

    public void setActiva(Boolean activa) {
        marcaActiva = activa ? (short) 1 : 0;
    }
}
