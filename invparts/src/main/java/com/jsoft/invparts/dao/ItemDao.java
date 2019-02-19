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
import com.jsoft.invparts.model.inventario.dto.DetalleEntradaDto;
import com.jsoft.invparts.model.inventario.dto.ItemDto;
import java.util.List;

/**
 *
 * @author misanchez
 */
public interface ItemDao {

    public Item getItemByPk(Integer idItem);

    public Item getItemByCod(String codigo);
    
    public ItemDto getItemDtoByPk(Integer idItem);

    public void guardar(Item item);

    public List<Categoria> getLstMarca();

    public List<Categoria> getLstModeloByIdMarca(Integer idMarca);

    public List<Categoria> getLstCategoriaByModelo(Integer idModelo);

    public List<ItemDto> getLstItemsByCategory(Integer idCategory);

    public List<InformacionItem> getLstInformacionItemByIdItem(Integer idItem);
    
    public List<Item> getLstItemsByUpcContains(String upcCode, Boolean anyCode);
    
    public List<DetalleEntradaDto> getLstDetalleEntradaByEntrada(Integer idEntrada);
    
    public void guardarEntrada(Entrada entrada);
}
