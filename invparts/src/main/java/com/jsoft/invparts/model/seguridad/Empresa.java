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
public class Empresa extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private Integer idEmpresa;
    private String nombreEmpresa;
    private String correoEmpresa;
    private String telefonoEmpresa;
    private Integer idTipoEmpresa;

    public Empresa() {
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public Integer getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(Integer idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    @Override
    public String toString() {
        return "Empresa{" + "idEmpresa=" + idEmpresa + "}";
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO empresa(nombre_empresa, correo_empresa, telefono_empresa, id_tipo_empresa) values(?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreEmpresa, correoEmpresa, telefonoEmpresa, idTipoEmpresa};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE empresa SET nombre_empresa=?, correo_empresa=?, telefono_empresa=?, id_tipo_empresa=? WHERE id_empresa=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreEmpresa, correoEmpresa, telefonoEmpresa, idTipoEmpresa, idEmpresa};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idEmpresa == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idEmpresa = id;
    }

}
