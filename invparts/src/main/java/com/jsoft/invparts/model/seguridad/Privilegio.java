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
public class Privilegio extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idPrivilegio;
    public Integer idModuloPerfil;
    public Integer idOpcionMenu;
    
    public Privilegio() {
    }

    public Integer getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public Integer getIdModuloPerfil() {
        return idModuloPerfil;
    }

    public void setIdModuloPerfil(Integer idModuloPerfil) {
        this.idModuloPerfil = idModuloPerfil;
    }

    public Integer getIdOpcionMenu() {
        return idOpcionMenu;
    }

    public void setIdOpcionMenu(Integer idOpcionMenu) {
        this.idOpcionMenu = idOpcionMenu;
    }

    @Override
    public String toString() {
        return "Privilegio{" + "idPrivilegio=" + idPrivilegio + '}';
    }

    
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO Privilegio(id_Modulo_perfil,id_opcion_menu) values(?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idModuloPerfil,idOpcionMenu};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE Privilegio set id_modulo_perfil=?,id_opcion_menu WHERE id_privilegio=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idModuloPerfil, idOpcionMenu,idPrivilegio};
    }

    
    @Override
    public Boolean esNuevoRegistro() {
        return idPrivilegio == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idPrivilegio = id;
    }
}
