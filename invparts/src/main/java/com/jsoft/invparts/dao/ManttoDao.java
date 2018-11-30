/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.inventario.Vendedor;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.Perfil;
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
     * @param emp
     * @param idTipoEmpresa
     * @return 
     */
    public List<Empresa> listEmpresa(Empresa emp,Short idTipoEmpresa);

    public List<Empresa> listEmpresaUsu(Empresa emp);
    
    public List<Usuario> listUsuario();
    
    public List<Perfil> listPerfil(Perfil per);

    public List<Modulo> listModulo(Modulo mod);
    
    public List<Vendedor> listVendedor(Vendedor ven);

    public List<Sucursal> listSucursal();

    public List<Producto> listProducto(Producto pro);
    
    public List<Modelo> listModelo(Modelo mod);
    
     public List<Marca> listMarca(Marca mar);
     
    public String nombreTipoEmpresa(Integer id);
    
    public Boolean getUsuarioByUsu(String usuario);

    public Boolean isExistEmailPerByEmail(String eMail);

    public int guardarConIdAutogenerado(PersistenciaDao objeto);

    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo);

    public int guardarNuevoUsuario(PersistenciaDao objeto);
    
    public List<Categoria> lstCategoria(Categoria categoria);
    
    public List<Categoria> lstSubCategoria(Categoria categoria);
    
    public Boolean removerCategoria(Integer idCategoria);
    
    public List<Categoria> getLstCategoriaByLikeNombre(String nombre);
}
