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
public class Ubicacion implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private Integer idUbicacion;
    private String descripcion;

    public Ubicacion() {
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO ubicacion (descripcion) VALUES (?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{descripcion};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE ubicacion SET descripcion=? WHERE id_ubicacion=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{descripcion, idUbicacion};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idUbicacion == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idUbicacion = id;
    }

}
