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
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.ProductoCategoria;
import com.jsoft.invparts.model.inventario.dto.CompatibilidadDto;
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
        List<Item> lstItems = getJdbcTemplate().query("SELECT * FROM Item WHERE upc_codigo=" + codigo, new BeanPropertyRowMapper(Item.class));
        if (!lstItems.isEmpty()) {
            return (Item) getJdbcTemplate().queryForObject("SELECT * FROM Item WHERE upc_codigo=" + codigo, new BeanPropertyRowMapper(Item.class));
        } else {
            return null;
        }
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

    @Override
    public List<Item> getLstItemsByUpcContains(String upcCode) {
        return getJdbcTemplate().query("SELECT * FROM item where upc_codigo like '%" + upcCode + "%'", new BeanPropertyRowMapper(Item.class));
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
