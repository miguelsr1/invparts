/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.dao.PersistenciaDao;
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
public interface ManttoService {

    public List<Persona> listPersona(Persona per);

    public List<Empresa> listEmpresa(Short idTipoEmpresa);

    public List<Usuario> listUsuario();

    public List<Vendedor> listVendedor();

    public List<Sucursal> listSucursal();

    public List<Producto> listProducto();

    public Boolean getUsuarioByUsu(String usuario);

    public Boolean isExistEmailPerByEmail(String eMail);

    public int guardarConIdAutogenerado(PersistenciaDao objeto);

    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo);

    public int guardarNuevoUsuario(PersistenciaDao objeto);

    public void enviarCorreoActivacionUsuario(Persona per, String codAct);

    public String encriptar(String cadena);
}
