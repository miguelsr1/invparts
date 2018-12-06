/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.model.inventario.ProductoCategoria;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.inventario.Vendedor;
import com.jsoft.invparts.model.inventario.dto.ProductoCategoriaDto;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Usuario;
import com.jsoft.invparts.model.seguridad.Perfil;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public interface ManttoService {

    public List<Persona> listPersona(Persona per);

    public List<Empresa> listEmpresa(Empresa emp, Short idTipoEmpresa);

    public List<Empresa> listEmpresaUsu(Empresa emp);

    public List<Perfil> listPerfil(Perfil per);

    public List<Modulo> listModulo(Modulo mod);

    public List<Usuario> listUsuario();

    public List<Vendedor> listVendedor(Vendedor ven);

    public List<Sucursal> listSucursal(Sucursal suc,Integer idEmp);

    public List<Producto> listProducto(Producto pro);

    public String nombreTipoEmpresa(Integer id);

    public List<Modelo> listModelo(Modelo mod);

    public List<Marca> listMarca(Marca mar);

    public Boolean getUsuarioByUsu(String usuario);

    public Boolean isExistEmailPerByEmail(String eMail);

    public int guardarConIdAutogenerado(PersistenciaDao objeto);

    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo);

    public void enviarCorreoActivacionUsuario(Persona per, String codAct);

    public String encriptar(String cadena);
    
    public Categoria findCategoriaById(Integer idCategoria);

    public List<Categoria> lstCategoria(Categoria categoria);

    public List<Categoria> lstSubCategoria(Categoria categoria);

    public Boolean removerCategoria(Integer idCategoria);

    public List<Categoria> getLstCategoriaByLikeNombre(String nombreCategoria, Integer idProducto);
    
    public List<ProductoCategoriaDto> getLstCategoriasByProducto(Integer idProducto);
}
