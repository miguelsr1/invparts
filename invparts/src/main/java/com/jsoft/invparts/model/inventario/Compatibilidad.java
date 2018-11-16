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
public class Compatibilidad implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idCompatibilidad;
    public Integer idItem;
    public Integer idModelo;
    public Short tipoCompatibilidad;

    public Compatibilidad() {
    }

    public Integer getIdCompatibilidad() {
        return idCompatibilidad;
    }

    public void setIdCompatibilidad(Integer id_compatibilidad) {
        this.idCompatibilidad = id_compatibilidad;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Short getTipoCompatibilidad() {
        return tipoCompatibilidad;
    }

    public void setTipoCompatibilidad(Short tipoCompatibilidad) {
        this.tipoCompatibilidad = tipoCompatibilidad;
    }

    @Override
    public String toString() {
        return "Compatibilidad{" + "id_compatibilidad=" + idCompatibilidad + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO compatibilidad (id_item, id_modelo, tipo_compatibilidad) VALUES (?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idItem, idModelo, tipoCompatibilidad};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE compatibilidad SET id_item=?, id_modelo=?, tipo_compatibilidad=? WHERE id_compatibilidad=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idItem, idModelo, tipoCompatibilidad, idCompatibilidad};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idCompatibilidad == null;
    }
}
