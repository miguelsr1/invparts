/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.InformacionItem;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.model.inventario.dto.CompatibilidadDto;
import com.jsoft.invparts.model.inventario.dto.ItemDto;
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
        for (InformacionItem informacionItem : item.getLstInformacionItem()) {
            informacionItem.setIdItem(item.getIdItem());
            persistirConIdAutogenerado(informacionItem);
        }
    }

    @Override
    public Item getItemByPk(Integer idItem) {
        return (Item) getJdbcTemplate().queryForObject("SELECT * FROM Item WHERE id_item=" + idItem, new BeanPropertyRowMapper(Item.class));
    }

    @Override
    public Item getItemByCod(String codigo) {
        if(codigo.isEmpty()){
            return null;
        }
        List<Item> lstItems = getJdbcTemplate().query("SELECT * FROM Item WHERE codigo_producto=" + codigo, new BeanPropertyRowMapper(Item.class));
        if (!lstItems.isEmpty()) {
            return (Item) getJdbcTemplate().queryForObject("SELECT * FROM Item WHERE codigo_producto=" + codigo, new BeanPropertyRowMapper(Item.class));
        } else {
            return null;
        }
    }

    @Override
    public List<Marca> getLstMarca() {
        return getJdbcTemplate().query("SELECT * FROM Marca ORDER BY nombre_marca", new BeanPropertyRowMapper(Marca.class));
    }

    @Override
    public List<Modelo> getLstModeloByIdMarca(Integer idMarca) {
        return getJdbcTemplate().query("SELECT * FROM Modelo WHERE id_marca=" + idMarca + " ORDER BY nombre_modelo", new BeanPropertyRowMapper(Modelo.class));
    }

    @Override
    public List<Categoria> getLstCategoriaByModelo(Integer idModelo) {
        return getJdbcTemplate().query("SELECT id_categoria, nombre_categoria, padre_id_categoria FROM vw_categoria_by_modelo WHERE id_modelo=" + idModelo + " ORDER BY nombre_categoria", new BeanPropertyRowMapper(Categoria.class));
    }

    @Override
    public List<ItemDto> getLstItemsByModelAndCategory(Integer idModel, Integer idCategory) {
        return getJdbcTemplate().query("SELECT * from vw_find_items_by_model_and_category WHERE id_modelo =" + idModel + " and id_categoria = " + idCategory, new BeanPropertyRowMapper(ItemDto.class));
    }

    @Override
    public void guardarCompatibilidad(CompatibilidadDto compatibilidad) {
        if (compatibilidad.getIdCompatibilidad() == null) {
            getJdbcTemplate().update("INSERT INTO compatibilidad (id_item, id_modelo, tipo_compatibilidad) VALUES (?,?,?)", compatibilidad.getIdItem(), compatibilidad.getIdModelo(), (short) (compatibilidad.getCompatibilidadPrimaria() ? 1 : 0));
        } else {
            getJdbcTemplate().update("UPDATE compatibilidad SET id_item = ?, id_modelo = ?, tipo_compatibilidad = ? WHERE id_compatibilidad = ?", compatibilidad.getIdItem(), compatibilidad.getIdModelo(), (short) (compatibilidad.getCompatibilidadPrimaria() ? 1 : 0), compatibilidad.getIdCompatibilidad());
        }
    }

    @Override
    public Modelo getModeloByPk(Integer idModelo) {
        return (Modelo) getJdbcTemplate().queryForObject("SELECT * FROM Modelo WHERE id_modelo=" + idModelo, new BeanPropertyRowMapper(Modelo.class));
    }

    @Override
    public List<CompatibilidadDto> getLstCompatibilidadByItem(Integer idItem) {
        return getJdbcTemplate().query("SELECT * from vw_compatibilidad_dto WHERE id_item =" + idItem, new BeanPropertyRowMapper(CompatibilidadDto.class));
    }

    @Override
    public List<InformacionItem> getLstInformacionItemByIdItem(Integer idItem) {
        return getJdbcTemplate().query("SELECT * from informacion_item WHERE id_item =" + idItem, new BeanPropertyRowMapper(InformacionItem.class));
    }
}
