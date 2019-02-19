/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.seguridad;

import com.jsoft.invparts.dao.PersistenciaDao;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DesarrolloPc
 */
public class UsuarioEmpresa implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private Integer idEmpresa;
    private Integer idUsuEmp;
     private Integer idModuloPerfil;
    
    public UsuarioEmpresa() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdUsuEmp() {
        return idUsuEmp;
    }

    public void setIdUsuEmp(Integer idUsuEmp) {
        this.idUsuEmp = idUsuEmp;
    }

    public Integer getIdModuloPerfil() {
        return idModuloPerfil;
    }

    public void setIdModuloPerfil(Integer idModuloPerfil) {
        this.idModuloPerfil = idModuloPerfil;
    }

    
    
    
    @Override
    public String toString() {
        return "Usuario_Empresa{" + "usuario=" + usuario + "}";
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO usuario_empresa(id_empresa,usuario,id_modulo_perfil) values(?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idEmpresa,usuario, idModuloPerfil};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE usuario_empresa SET id_empresa=?, usuario=?, id_modulo_perfil=? WHERE id_usu_emp=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idEmpresa, usuario, idModuloPerfil, idUsuEmp};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return (usuario == null) || usuario.trim().isEmpty();
    }

    @Override
    public void setIdGenerado(Integer id) {
    }

}