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
 * @author DesarrolloPc
 */
public class InformacionItem implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private Integer idInformacionItem;
    private Integer idItem;
    private String label;
    private String value;
    
    private Boolean eliminar = false;

    public InformacionItem() {
    }

    public Integer getIdInformacionItem() {
        return idInformacionItem;
    }

    public void setIdInformacionItem(Integer idInformacionItem) {
        this.idInformacionItem = idInformacionItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO informacion_item (id_item, label, value) VALUES (?, ?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idItem, label, value};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE informacion_item SET label = ?, value = ? WHERE id_informacion_item = ?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{label, value, idInformacionItem};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idInformacionItem == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idInformacionItem = id;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }
}