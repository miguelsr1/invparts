/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.dao.EmpresaDao;
import com.jsoft.invparts.dao.ItemDao;
import com.jsoft.invparts.dao.ManttoDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("itemService")
@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ManttoDao dao;

    @Autowired
    private EmpresaDao empresaDao;

    @Override
    public void guardar(Item item) {
        itemDao.guardar(item);
    }

    @Override
    public List<Empresa> getLstClienteOrProvByIdEmpresa(Empresa idEmpresa, Integer idTipoEmpresa) {
        return empresaDao.getLstClienteOrProvByIdEmpresa(idEmpresa, idTipoEmpresa);
    }

    @Override
    public List<Sucursal> getLstSucursalByIdEmpresa(Integer idEmpresa, Sucursal idSucursal) {
        return empresaDao.getLstSucursalByIdEmpresa(idEmpresa, idSucursal);
    }

    @Override
    public List<Estante> getLstEstantesByIdSucursal(Integer idSucursal) {
        return empresaDao.getLstEstantesByIdSucursal(idSucursal);
    }

    @Override
    public Item getItemByPk(Integer idItem) {
        return itemDao.getItemByPk(idItem);
    }

    @Override
    public Modelo getModeloByPk(Integer idModelo) {
        return itemDao.getModeloByPk(idModelo);
    }

    @Override
    public List<Categoria> getLstMarca() {
        return itemDao.getLstMarca();
    }

    @Override
    public List<Categoria> getLstModeloByIdMarca(Integer idMarca) {
        return itemDao.getLstModeloByIdMarca(idMarca);
    }

    @Override
    public List<Categoria> getLstCategoriaByModelo(Integer idModelo) {
        return itemDao.getLstCategoriaByModelo(idModelo);
    }

    @Override
    public List<ItemDto> getLstItemsByCategory(Integer idCategory) {
        return itemDao.getLstItemsByCategory(idCategory);
    }

    @Override
    public void guardarCompatibilidad(CompatibilidadDto compatibilidad) {
        itemDao.guardarCompatibilidad(compatibilidad);
    }

    @Override
    public List<CompatibilidadDto> getLstCompatibilidadByItem(Integer idItem) {
        return itemDao.getLstCompatibilidadByItem(idItem);
    }

    @Override
    public List<InformacionItem> getLstInformacionItemByIdItem(Integer idItem) {
        return itemDao.getLstInformacionItemByIdItem(idItem);
    }

    @Override
    public Item getItemByCod(String cod) {
        return itemDao.getItemByCod(cod);
    }

    @Override
    public List<Categoria> getLstCategoriaByLikeNombre(String nombreCategoria, Integer idItem) {
        return dao.getLstCategoriaByLikeNombre(nombreCategoria, idItem);
    }

    @Override
    public List<Categoria> getLstCategoriaByIdItem(Integer idItem) {
        return dao.getLstCategoriaByIdItem(idItem);
    }

    @Override
    public List<Item> getLstItemsByUpcContains(String upcCode) {
        return itemDao.getLstItemsByUpcContains(upcCode);
    }

    @Override
    public List<Empresa> getLstProveedor() {
        return empresaDao.getLstEmpresaByTipo((short) 1);
    }

    @Override
    public List<DetalleEntradaDto> getLstDetalleEntradaByEntrada(Integer idEntrada) {
        return itemDao.getLstDetalleEntradaByEntrada(idEntrada);
    }

    @Override
    public void guardarEntrada(Entrada entrada) {
        itemDao.guardarEntrada(entrada);
    }
}
