/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.seguridad;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.UtilWhere;
import java.io.Serializable;

/**
 *
 * @author DesarrolloPc
 */
public class Modulo extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idModulo;
    public String nombreModulo;
    public String iconoModulo;
    
    public Modulo() {
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getIconoModulo() {
        return iconoModulo;
    }

    public void setIconoModulo(String iconoModulo) {
        this.iconoModulo = iconoModulo;
    }

    
        @Override
    public String toString() {
        return idModulo + "-"+nombreModulo;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO Modulo(nombre_modulo,icono_modulo) values(?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreModulo,iconoModulo};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE Modulo set nombre_modulo=?,icono_modulo WHERE id_modulo=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreModulo,iconoModulo, idModulo};
    }

    
    @Override
    public Boolean esNuevoRegistro() {
        return idModulo == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idModulo = id;
    }
}
