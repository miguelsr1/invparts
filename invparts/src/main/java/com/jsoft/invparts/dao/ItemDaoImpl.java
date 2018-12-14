/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.util.XJdbcTemplate;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemDaoImpl extends XJdbcTemplate implements ItemDao {

    
    @Override
    public List<Producto> getLstProducto(Integer idEmp) {
        return getJdbcTemplate().query("SELECT * FROM Producto", new BeanPropertyRowMapper(Producto.class));
    }

}
