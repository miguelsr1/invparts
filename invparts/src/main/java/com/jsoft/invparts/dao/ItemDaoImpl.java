/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
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

    @Override
    public void guardar(Item item) {
        persistirConIdAutogenerado(item);
    }

    @Override
    public Item getItemByPk(Integer idItem) {
        return (Item) getJdbcTemplate().queryForObject("SELECT * FROM Item WHERE id_item=" + idItem, new BeanPropertyRowMapper(Item.class));
    }

    @Override
    public List<Marca> getLstMarca() {
        return getJdbcTemplate().query("SELECT * FROM Marca ORDER BY nombre_marca", new BeanPropertyRowMapper(Marca.class));
    }

    @Override
    public List<Modelo> getLstModeloByIdMarca(Integer idMarca) {
        return getJdbcTemplate().query("SELECT * FROM Modelo WHERE id_marca=" + idMarca + " ORDER BY nombre_modelo", new BeanPropertyRowMapper(Modelo.class));
    }

}
