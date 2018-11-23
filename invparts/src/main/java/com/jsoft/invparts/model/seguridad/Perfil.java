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
public class Perfil extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idPerfil;
    public String nombrePerfil;
    
    public Perfil() {
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

        @Override
    public String toString() {
        return "Perfil{" + "idPerfil=" + idPerfil + "}";
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO Perfil(nombre_perfil) values(?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombrePerfil};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE Perfil set nombre_perfil=? WHERE id_perfil=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombrePerfil, idPerfil};
    }

    
    @Override
    public Boolean esNuevoRegistro() {
        return idPerfil == null;
    }
}
