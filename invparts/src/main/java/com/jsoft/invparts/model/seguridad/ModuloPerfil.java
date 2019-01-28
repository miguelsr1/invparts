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
public class ModuloPerfil extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idModuloPerfil;
    public Integer idModulo;
    public Integer idPerfil;
    public Integer perfilActivo;
    
    public ModuloPerfil() {
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getIdModuloPerfil() {
        return idModuloPerfil;
    }

    public void setIdModuloPerfil(Integer idModuloPerfil) {
        this.idModuloPerfil = idModuloPerfil;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getPerfilActivo() {
        return perfilActivo;
    }

    public void setPerfilActivo(Integer perfilActivo) {
        this.perfilActivo = perfilActivo;
    }

    
        @Override
    public String toString() {
        return "ModuloPerfil{" + "idModuloPerfil=" + idModuloPerfil + "}";
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO Modulo_perfil(id_modulo,id_perfil,perfil_activo) values(?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idModulo,idPerfil,perfilActivo};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE Modulo_perfil set id_modulo=?,id_perfil=?,perfil_activo=? WHERE id_modulo_perfil=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idModulo,idPerfil, perfilActivo,idModuloPerfil};
    }

    
    @Override
    public Boolean esNuevoRegistro() {
        return idModuloPerfil == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idModuloPerfil = id;
    }
}
