/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.model.inventario.Estante;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.seguridad.Empresa;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public interface ItemService {

    public List<Producto> getLstProducto(Integer idEmp);
    
    public Item getItemByPk(Integer idItem);
    
    public void guardar(Item item);
    
    public List<Empresa> getLstClienteOrProvByIdEmpresa(Empresa idEmpresa, Integer idTipoEmpresa);
    
    public List<Sucursal> getLstSucursalByIdEmpresa(Integer idEmpresa, Sucursal idSucursal);
    
    public List<Estante> getLstEstantesByIdSucursal(Integer idSucursal);
    
    public List<Marca> getLstMarca();
    
    public List<Modelo> getLstModeloByIdMarca(Integer idMarca);
}
