/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Estante;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.seguridad.Empresa;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public interface EmpresaDao {

    /**
     * Este metodo recupera los clientes/proveedores de una empresa en
     * particular
     *
     * @param idEmpresa Identificador de la empresa que esta en sessión
     * @param idTipoEmpresa 1=Proveedor; 2=Cliente; 3=Proveedor/Cliente;
     * @return
     */
    public List<Empresa> getLstClienteOrProvByIdEmpresa(Empresa idEmpresa, Integer idTipoEmpresa);

    /**
     * Recupera todas las sucursales registradas a una empresa en particular
     *
     * @param idEmpresa Identificador de la empresa que esta en sessión
     * @param idSucursal Opcional para busquedas con filtros
     * @return
     */
    public List<Sucursal> getLstSucursalByIdEmpresa(Integer idEmpresa, Sucursal idSucursal);

    /**
     * Recupera todos los estantes en los que estan alojados los items para una
     * sucursal en particular
     *
     * @param idSucursal Identificador de la sucursal seleccionada de la empresa
     * que esta en sessión
     * @return
     */
    public List<Estante> getLstEstantesByIdSucursal(Integer idSucursal);

    public List<Empresa> getLstEmpresaByTipo(Short idTipoEmpresa);
}
