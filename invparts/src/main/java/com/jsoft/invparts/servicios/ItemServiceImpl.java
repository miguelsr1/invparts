/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.dao.EmpresaDao;
import com.jsoft.invparts.dao.ItemDao;
import com.jsoft.invparts.model.inventario.Estante;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.model.inventario.Sucursal;
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
    private EmpresaDao empresaDao;

    @Override
    public List<Producto> getLstProducto(Integer idEmp) {
        return itemDao.getLstProducto(idEmp);
    }

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
    public List<Marca> getLstMarca() {
        return itemDao.getLstMarca();
    }

    @Override
    public List<Modelo> getLstModeloByIdMarca(Integer idMarca) {
        return itemDao.getLstModeloByIdMarca(idMarca);
    }
}
