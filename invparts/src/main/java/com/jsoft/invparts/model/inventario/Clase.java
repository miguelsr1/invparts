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
public class Clase implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;
    
    private Integer idClase;
    private Integer idModelo;
    private Integer idUbicacion;

    public Clase() {
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO clase (idModulo, idUbicacion) VALUES(?, ?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idModelo, idUbicacion};
    }

    @Override
    public String generarUpdateSQL() {
        return "INSERT INTO clase (idModulo, idUbicacion) VALUES(?, ?)";
    }

    @Override
    public Object[] getDatosUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean esNuevoRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdGenerado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
