/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.inventario.Vendedor;
import com.jsoft.invparts.model.inventario.dto.ProductoCategoriaDto;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.ModuloPerfil;
import com.jsoft.invparts.model.seguridad.OpcionMenu;
import com.jsoft.invparts.model.seguridad.Perfil;
import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Privilegio;
import com.jsoft.invparts.model.seguridad.Usuario;
import java.util.List;
import org.primefaces.model.menu.DefaultMenuModel;

/**
 *
 * @author DesarrolloPc
 */
public interface ManttoDao {

    public List<Persona> listPersona(Persona per);

    /**
     * Recupera el listado de empresa y recibe como parametro el tipo de empresa
     * que se desea recuperar. 1 para Proveedores y 2 para Clientes
     *
     * @param emp
     * @param idTipoEmpresa
     * @return
     */
    public List<Empresa> listEmpresa(Empresa emp, Short idTipoEmpresa);

    public List<Empresa> listEmpresaUsu(Empresa emp);

    public List<OpcionMenu> listOpcMenu(OpcionMenu opc);

    public List<OpcionMenu> listOpcMenuByUsuAndModulo(String usuario, Integer idModulo);

    public List<OpcionMenu> listOpcMenuMod(Integer idApp, Integer idModPer);

    public DefaultMenuModel crearArbolMenu(List<OpcionMenu> lstOpcionMenu);

    public List<Usuario> listUsuario();

    public List<Perfil> listPerfil(Perfil per);

    public List<ModuloPerfil> listModPerfil(ModuloPerfil per);

    public List<Privilegio> listPrivilegio(Privilegio pri);

    public List<Modulo> listModulo(Modulo mod);

    public List<Vendedor> listVendedor(Vendedor ven);

    public List<Sucursal> listSucursal(Sucursal suc, Integer idEmpresa);

    public String nombreTipoEmpresa(Integer id);

    public String findNombreOpcion(Integer id);

    public String findNombreModulo(Integer id);

    public Usuario findUserByLogin(String login);

    public Integer findIdEmpByLogin(String login);

    public Integer findIdModPerByLogin(String login, Integer emp);

    public Boolean getUsuarioByUsu(String usuario);

    public Boolean getUsuarioByClave(String usuario, String clave);

    public Boolean isExistEmailPerByEmail(String eMail);

    public int guardarConIdAutogenerado(PersistenciaDao objeto);

    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo);

    public Categoria findCategoriaById(Integer idCategoria);

    public List<Categoria> lstCategoria(Categoria categoria);

    public List<Categoria> lstSubCategoria(Categoria categoria);

    public Boolean removerCategoria(Integer idCategoria);

    public List<Categoria> getLstCategoriaByLikeNombre(String nombre, Integer idProducto);

    public List<Categoria> getLstCategoriaByIdItem(Integer idItem);

    public String findNombreMarca(Integer id);

    public List<ProductoCategoriaDto> getLstCategoriasByProducto(Integer idProducto);

    public List<Modulo> getlstModulos(String usuario, Integer idEmpresa, Integer idPerfil);

    public Boolean eliminar(String nombreTbl, Integer id);
}
