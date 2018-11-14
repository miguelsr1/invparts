/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

/**
 *
 * @author misanchez
 */
public interface PersistenciaDao {

    public String generarInsertSQL();

    public Object[] getDatosInsert();

    public String generarUpdateSQL();

    public Object[] getDatosUpdate();

    public Boolean esNuevoRegistro();
}
