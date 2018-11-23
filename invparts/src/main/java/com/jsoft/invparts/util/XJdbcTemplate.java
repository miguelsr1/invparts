/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.util;

import com.jsoft.invparts.dao.PersistenciaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author misanchez
 */
public class XJdbcTemplate {

    JdbcTemplate jdbcTemplate;
    PersistenciaDao objeto;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int persistirConIdAutogenerado(PersistenciaDao objeto) {
        this.objeto = objeto;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int valor = getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PreparedStatement ps = cnctn.prepareStatement(getObjeto().generarInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                ps = JsfUtil.setValuesPreparedStatement(ps, getObjeto().getDatosInsert());
                return ps;
            }
        }, keyHolder);

        if (valor > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return JsfUtil.COD_ERROR;
        }
//        int valor;
//        if (objeto.esNuevoRegistro()) {
//            valor = getJdbcTemplate().update(objeto.generarInsertSQL(), objeto.getDatosInsert());
//        } else {
//            valor = getJdbcTemplate().update(objeto.generarUpdateSQL(), objeto.getDatosUpdate());
//        }
//
//        if (valor > 0) {
//            return valor;
//        } else {
//            return JsfUtil.COD_ERROR;
//        }
    }

    public int persistirConIdString(PersistenciaDao objeto, Boolean nuevo) {
        int valor;
        if (nuevo) {
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

    /**
     * Devuelve el id generado por el gestor de base de datos, al momento de
     * ejecutar la sentencia de inserción
     *
     * @param objeto Objeto a persistir (implementa de la interfaz
     * PersistenciaDao)
     * @return id generado
     */
    public int createIdString(PersistenciaDao objeto) {
        this.objeto = objeto;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int valor = getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PreparedStatement ps = cnctn.prepareStatement(getObjeto().generarInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                ps = JsfUtil.setValuesPreparedStatement(ps, getObjeto().getDatosInsert());
                return ps;
            }
        }, keyHolder);

        if (valor > 0) {
            return keyHolder.getKey().intValue();
        } else {
            return JsfUtil.COD_ERROR;
        }
    }

    public PersistenciaDao getObjeto() {
        return objeto;
    }

    public void setObjeto(PersistenciaDao Objeto) {
        this.objeto = Objeto;
    }
}
