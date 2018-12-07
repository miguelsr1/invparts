/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Estante;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.util.XJdbcTemplate;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository(value = "empresaDao")
public class EmpresaDaoImpl extends XJdbcTemplate implements EmpresaDao {

    @Override
    public List<Empresa> getLstClienteOrProvByIdEmpresa(Empresa idEmpresa, Integer idTipoEmpresa) {
        String sql = "SELECT * from empresa ";

        if (idEmpresa != null) {
            if (idTipoEmpresa != null) {
                sql += "where id_tipo_empresa in (" + idTipoEmpresa + ", 3) ";
            } else {
                sql += idEmpresa.getWhere();
            }
        }

        List<Empresa> listEmp = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
        return listEmp;
    }

    @Override
    public List<Sucursal> getLstSucursalByIdEmpresa(Integer idEmpresa, Sucursal idSucursal) {
        String sql = "SELECT * from Sucursal ";
        if (idSucursal != null) {
            if (idEmpresa != null) {
                sql += "where id_empresa = " + idEmpresa;
            } else {
                sql += idSucursal.getWhere();
            }
        }

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Sucursal.class));
    }

    @Override
    public List<Estante> getLstEstantesByIdSucursal(Integer idSucursal) {
        String sql = "SELECT * from Estante ";
        if (idSucursal != null) {
            sql += "where id_sucursal = " + idSucursal;
        }

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Estante.class));
    }

}