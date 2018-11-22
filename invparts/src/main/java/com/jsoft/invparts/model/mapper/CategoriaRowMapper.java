/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.mapper;

import com.jsoft.invparts.model.inventario.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author DesarrolloPc
 */
public class CategoriaRowMapper implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(ResultSet rs, int i) throws SQLException {
        Categoria cat = new Categoria();
        cat.setIdCategoria(rs.getInt("id_categoria"));
        cat.setNombreCategoria(rs.getString("nombre_categoria"));
        cat.setPadreIdCategoria(rs.getInt("padre_id_categoria"));
        return cat;
    }

}
