/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Entrada;
import com.jsoft.invparts.model.inventario.InformacionItem;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.ProductoCategoria;
import com.jsoft.invparts.model.inventario.dto.DetalleEntradaDto;
import com.jsoft.invparts.model.inventario.dto.ItemDto;
import com.jsoft.invparts.util.XJdbcTemplate;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemDaoImpl extends XJdbcTemplate implements ItemDao {

    @Override
    public void guardar(Item item) {
        persistirConIdAutogenerado(item);
        for (InformacionItem informacionItem : item.getLstInformacionItem()) {
            informacionItem.setIdItem(item.getIdItem());
            persistirConIdAutogenerado(informacionItem);
        }

        for (ProductoCategoria categoria : item.getLstCategorias()) {
            categoria.setIdItem(item.getIdItem());
            persistirConIdAutogenerado(categoria);
        }
    }

    @Override
    public Item getItemByPk(Integer idItem) {
        return (Item) getJdbcTemplate().queryForObject("SELECT * FROM Item WHERE id_item=" + idItem, new BeanPropertyRowMapper(Item.class));
    }

    @Override
    public Item getItemByCod(String codigo) {
        if (codigo.isEmpty()) {
            return null;
        }
        List<Item> lstItems = getJdbcTemplate().query("SELECT * FROM Item WHERE upc_codigo='" + codigo + "'", new BeanPropertyRowMapper(Item.class));
        if (!lstItems.isEmpty()) {
            return (Item) getJdbcTemplate().queryForObject("SELECT * FROM Item WHERE upc_codigo='" + codigo + "'", new BeanPropertyRowMapper(Item.class));
        } else {
            return null;
        }
    }

    @Override
    public ItemDto getItemDtoByPk(Integer idItem) {
        return (ItemDto) getJdbcTemplate().queryForObject("SELECT * from vw_find_items_by_model_and_category WHERE id_item = " + idItem + "  LIMIT 1", new BeanPropertyRowMapper(ItemDto.class));
    }

    @Override
    public List<Categoria> getLstMarca() {
        return getJdbcTemplate().query("SELECT * FROM categoria where padre_id_categoria=107", new BeanPropertyRowMapper(Categoria.class));
    }

    @Override
    public List<Categoria> getLstModeloByIdMarca(Integer idMarca) {
        return getJdbcTemplate().query("SELECT * FROM categoria WHERE padre_id_categoria=" + idMarca, new BeanPropertyRowMapper(Categoria.class));
    }

    @Override
    public List<Categoria> getLstCategoriaByModelo(Integer idModelo) {
        return getJdbcTemplate().query("SELECT * FROM categoria WHERE padre_id_categoria=" + idModelo, new BeanPropertyRowMapper(Categoria.class));
    }

    @Override
    public List<ItemDto> getLstItemsByCategory(Integer idCategory) {
        return getJdbcTemplate().query("SELECT * from vw_find_items_by_model_and_category WHERE id_categoria = " + idCategory, new BeanPropertyRowMapper(ItemDto.class));
    }

    @Override
    public List<InformacionItem> getLstInformacionItemByIdItem(Integer idItem) {
        return getJdbcTemplate().query("SELECT * from informacion_item WHERE id_item =" + idItem, new BeanPropertyRowMapper(InformacionItem.class));
    }

    @Override
    public List<Item> getLstItemsByUpcContains(String upcCode, Boolean anyCode) {
        if (anyCode) {
            return getJdbcTemplate().query("SELECT * FROM item where upc_codigo like '%" + upcCode + "%' or codigo_producto like '%" + upcCode + "%' ", new BeanPropertyRowMapper(Item.class));
        } else {
            return getJdbcTemplate().query("SELECT * FROM item where upc_codigo like '%" + upcCode + "%'", new BeanPropertyRowMapper(Item.class));
        }
    }

    @Override
    public List<DetalleEntradaDto> getLstDetalleEntradaByEntrada(Integer idEntrada) {
        return getJdbcTemplate().query("SELECT * from vw_detalle_entrada_dto WHERE id_entrada =" + idEntrada, new BeanPropertyRowMapper(DetalleEntradaDto.class));
    }

    @Override
    public void guardarEntrada(Entrada entrada) {
        persistirConIdAutogenerado(entrada);
        for (DetalleEntradaDto detalleEntradaDto : entrada.getLstDetalleEntrada()) {
            detalleEntradaDto.setIdEntrada(entrada.getIdEntrada());
            persistirConIdAutogenerado(detalleEntradaDto);
        }
    }
}
