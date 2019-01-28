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
import java.util.List;

/**
 *
 * @author misanchez
 */
public interface ItemDao {

    public List<Producto> getLstProducto(Integer idEmp);

    public Item getItemByPk(Integer idItem);

    public Item getItemByCod(String codigo);

    public Modelo getModeloByPk(Integer idModelo);

    public void guardar(Item item);

    public void guardarCompatibilidad(CompatibilidadDto compatibilidad);

    public List<Marca> getLstMarca();

    public List<Modelo> getLstModeloByIdMarca(Integer idMarca);

    public List<Categoria> getLstCategoriaByModelo(Integer idModelo);

    public List<ItemDto> getLstItemsByModelAndCategory(Integer idModel, Integer idCategory);

    public List<CompatibilidadDto> getLstCompatibilidadByItem(Integer idItem);

    public List<InformacionItem> getLstInformacionItemByIdItem(Integer idItem);
}
