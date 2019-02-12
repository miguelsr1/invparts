/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Entrada;
import com.jsoft.invparts.model.inventario.Estante;
import com.jsoft.invparts.model.inventario.InformacionItem;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.inventario.dto.CompatibilidadDto;
import com.jsoft.invparts.model.inventario.dto.DetalleEntradaDto;
import com.jsoft.invparts.model.inventario.dto.ItemDto;
import com.jsoft.invparts.model.seguridad.Empresa;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public interface ItemService {

    public Item getItemByPk(Integer idItem);

    public Item getItemByCod(String cod);

    public Modelo getModeloByPk(Integer idModelo);

    public void guardar(Item item);

    public void guardarCompatibilidad(CompatibilidadDto compatibilidad);

    public List<Empresa> getLstClienteOrProvByIdEmpresa(Empresa idEmpresa, Integer idTipoEmpresa);

    public List<Sucursal> getLstSucursalByIdEmpresa(Integer idEmpresa, Sucursal idSucursal);

    public List<Estante> getLstEstantesByIdSucursal(Integer idSucursal);

    public List<Marca> getLstMarca();

    public List<Modelo> getLstModeloByIdMarca(Integer idMarca);

    public List<Categoria> getLstCategoriaByModelo(Integer idModelo);

    public List<ItemDto> getLstItemsByModelAndCategory(Integer idModel, Integer idCategory);

    public List<CompatibilidadDto> getLstCompatibilidadByItem(Integer idItem);

    public List<InformacionItem> getLstInformacionItemByIdItem(Integer idItem);
    
    public List<Categoria> getLstCategoriaByLikeNombre(String nombreCategoria, Integer idItem);
    
    public List<Categoria> getLstCategoriaByIdItem(Integer idItem);
    
    public List<Item> getLstItemsByUpcContains(String upcCode);
    
    public List<Empresa> getLstProveedor();
    
    public List<DetalleEntradaDto> getLstDetalleEntradaByEntrada(Integer idEntrada);
    
    public void guardarEntrada(Entrada entrada);
}
