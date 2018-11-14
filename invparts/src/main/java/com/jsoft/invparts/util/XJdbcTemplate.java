/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.util;

import com.jsoft.invparts.dao.PersistenciaDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author misanchez
 */
public class XJdbcTemplate {

    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int persistir(PersistenciaDao objeto) {
        int valor;
        if (objeto.esNuevoRegistro()) {
            valor = getJdbcTemplate().update(objeto.generarInsertSQL(), objeto.getDatosInsert());
        } else {
            valor = getJdbcTemplate().update(objeto.generarUpdateSQL(), objeto.getDatosUpdate());
        }

        if (valor > 0) {
            return valor;
        } else {
            return JsfUtil.COD_ERROR;
        }
    }

//    public int createIdString() {
//        int valor = getJdbcTemplate().update(getObjeto().generarInsertSQL(), getObjeto().getDatosInsert());
//
//        if (valor > 0) {
//            return valor;
//        } else {
//            return JsfUtil.COD_ERROR;
//        }
//    }
//
//    public PersistenciaDao getObjeto() {
//        return objeto;
//    }
//
//    public void setObjeto(PersistenciaDao Objeto) {
//        this.objeto = Objeto;
//    }
}
