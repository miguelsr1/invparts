/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.inventario.Vendedor;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Usuario;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public interface ManttoDao {

    public List<Persona> listPersona(Persona per);

    /**
     * Recupera el listado de empresa y recibe como parametro el tipo de empresa que se desea recuperar.
     * 1 para Proveedores y 2 para Clientes
     * @param idTipoEmpresa
     * @return 
     */
    public List<Empresa> listEmpresa(Short idTipoEmpresa);

    public List<Empresa> listEmpresaUsu();
    
    public List<Usuario> listUsuario();

    public List<Vendedor> listVendedor();

    public List<Sucursal> listSucursal();

    public List<Producto> listProducto();
    
    public String nombreTipoEmpresa(Integer id);
    
    public Boolean getUsuarioByUsu(String usuario);

    public Boolean isExistEmailPerByEmail(String eMail);

    public int guardarConIdAutogenerado(PersistenciaDao objeto);

    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo);

    public int guardarNuevoUsuario(PersistenciaDao objeto);
}