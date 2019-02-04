/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.inventario.Vendedor;
import com.jsoft.invparts.model.inventario.dto.ProductoCategoriaDto;
import com.jsoft.invparts.model.mapper.CategoriaRowMapper;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.OpcionMenu;
import com.jsoft.invparts.model.seguridad.Perfil;
import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Privilegio;
import com.jsoft.invparts.model.seguridad.Usuario;
import com.jsoft.invparts.util.JsfUtil;
import com.jsoft.invparts.util.XJdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author misanchez
 */
@Repository("manttoDao")
public class ManttoDaoImpl extends XJdbcTemplate implements ManttoDao {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    @Autowired
    Environment env;

    private DefaultMenuModel model;

    public ManttoDaoImpl() {
    }

    public DefaultMenuModel getModel() {
        return model;
    }

    public void setModel(DefaultMenuModel model) {
        this.model = model;
    }

    @Override
    public List<Persona> listPersona(Persona per) {
        String sql = "SELECT * from persona";

        if (per != null) {
            sql += per.getWhere();
        }

        List<Persona> listPer = getJdbcTemplate().query(sql, new RowMapper<Persona>() {

            @Override
            public Persona mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Persona per = new Persona();
                per.setIdPersona(rs.getInt("id_persona"));
                per.setPrimerNombre(rs.getString("primer_nombre"));
                per.setSegundoNombre(rs.getString("segundo_nombre"));
                per.setCorreoElectronico(rs.getString("correo_electronico"));
                per.setNumeroTelefono(rs.getString("numero_telefono"));
                return per;
            }

        });
        return listPer;
    }

    @Override
    public List<Privilegio> listPrivilegio(Privilegio pri) {
        String sql = "SELECT * from Privilegio";

        if (pri != null) {
            sql += pri.getWhere();
        }

        List<Privilegio> listPer = getJdbcTemplate().query(sql, new RowMapper<Privilegio>() {

            @Override
            public Privilegio mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Privilegio per = new Privilegio();
                per.setIdPrivilegio(rs.getInt("id_privilegio"));
                per.setIdModuloPerfil(rs.getInt("id_modulo_perfil"));
                per.setIdOpcionMenu(rs.getInt("id_opcion_menu"));
                return per;
            }

        });
        return listPer;
    }

    @Override
    public List<Perfil> listPerfil(Perfil per) {
        String sql = "SELECT * from perfil";

        if (per != null) {
            sql += per.getWhere();
        }

        List<Perfil> listPer = getJdbcTemplate().query(sql, new RowMapper<Perfil>() {

            @Override
            public Perfil mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Perfil per = new Perfil();
                per.setIdPerfil(rs.getInt("id_perfil"));
                per.setNombrePerfil(rs.getString("nombre_perfil"));
                return per;
            }

        });
        return listPer;
    }

    @Override
    public List<Empresa> listEmpresa(Empresa emp, Short idTipoEmpresa) {
        String sql = "SELECT * from empresa ";

        if (emp != null) {
            if (idTipoEmpresa != null) {
                sql += "where id_tipo_empresa in (" + idTipoEmpresa + ", 3) ";
            } else {
                sql += emp.getWhere();
            }
        }

        List<Empresa> listEmp = getJdbcTemplate().query(sql, new RowMapper<Empresa>() {

            @Override
            public Empresa mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Empresa emp = new Empresa();
                emp.setIdEmpresa(rs.getInt("id_empresa"));
                emp.setNombreEmpresa(rs.getString("nombre_empresa"));
                emp.setCorreoEmpresa(rs.getString("correo_empresa"));
                emp.setTelefonoEmpresa(rs.getString("telefono_empresa"));
                emp.setIdTipoEmpresa(rs.getInt("id_tipo_empresa"));
                return emp;
            }

        });
        return listEmp;
    }

    @Override
    public List<Empresa> listEmpresaUsu(Empresa emp) {
        String sql = "SELECT * from empresa where id_tipo_empresa = 4";

        if (emp != null) {
            sql += emp.getWhere();
        }

        List<Empresa> listEmp = getJdbcTemplate().query(sql, new RowMapper<Empresa>() {

            @Override
            public Empresa mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Empresa emp = new Empresa();
                emp.setIdEmpresa(rs.getInt("id_empresa"));
                emp.setNombreEmpresa(rs.getString("nombre_empresa"));
                emp.setCorreoEmpresa(rs.getString("correo_empresa"));
                emp.setTelefonoEmpresa(rs.getString("telefono_empresa"));
                emp.setIdTipoEmpresa(rs.getInt("id_tipo_empresa"));
                return emp;
            }

        });
        return listEmp;
    }

    @Override
    public List<OpcionMenu> listOpcMenu(OpcionMenu opc) {
        String sql = "SELECT * from Opcion_Menu ";

        if (opc != null) {
            sql += opc.getWhere();
        }

        List<OpcionMenu> listOpc = getJdbcTemplate().query(sql, new RowMapper<OpcionMenu>() {

            @Override
            public OpcionMenu mapRow(ResultSet rs, int rowNumber) throws SQLException {
                OpcionMenu opc = new OpcionMenu();
                opc.setIdOpcionMenu(rs.getInt("id_opcion_menu"));
                opc.setNombreOpcion(rs.getString("nombre_Opcion"));
                opc.setIconoOpcion(rs.getString("icono_Opcion"));
                opc.setUrlOpcion(rs.getString("url_opcion"));
                opc.setOpcionActiva(rs.getInt("opcion_activa"));
                opc.setOrdenOpcion(rs.getInt("orden_opcion"));
                opc.setIdModulo(rs.getInt("id_modulo"));
                opc.setOpcionIdOpcionMenu(rs.getInt("opc_id_opcion_menu"));
                return opc;
            }

        });
        return listOpc;
    }

    @Override
    public List<OpcionMenu> listOpcMenuMod(Integer idMod) {
        String sql = "SELECT * FROM Opcion_Menu WHERE id_modulo=" + idMod;

        List<OpcionMenu> listOpc = getJdbcTemplate().query(sql, new RowMapper<OpcionMenu>() {

            @Override
            public OpcionMenu mapRow(ResultSet rs, int rowNumber) throws SQLException {
                OpcionMenu opc = new OpcionMenu();
                opc.setIdOpcionMenu(rs.getInt("id_opcion_menu"));
                opc.setNombreOpcion(rs.getString("nombre_Opcion"));
                opc.setIconoOpcion(rs.getString("icono_Opcion"));
                opc.setUrlOpcion(rs.getString("url_opcion"));
                opc.setOpcionActiva(rs.getInt("opcion_activa"));
                opc.setOrdenOpcion(rs.getInt("orden_opcion"));
                opc.setIdModulo(rs.getInt("id_modulo"));
                opc.setOpcionIdOpcionMenu(rs.getInt("opc_id_opcion_menu"));
                return opc;
            }

        });
        return listOpc;
    }

    @Override
    public List<Usuario> listUsuario() {
        String sql = "SELECT * from usuario";
        List<Usuario> listUser = getJdbcTemplate().query(sql, new RowMapper<Usuario>() {

            @Override
            public Usuario mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Usuario usu = new Usuario();
                usu.setUsuario(rs.getString("usuario"));
                usu.setIdPersona(rs.getInt("id_empresa"));
                usu.setClaveAcceso(rs.getString("clave_acceso"));
                usu.setUsuarioActivo(rs.getObject("usuario_activo", Short.class));
                usu.setCambiarClave(rs.getObject("cambiar_clave", Short.class));
                usu.setFechaCaducidad(rs.getObject("fecha_caducidad", Date.class));
                usu.setIntentosFallidos(rs.getObject("intentos_fallidos", Short.class));
                return usu;
            }

        });
        return listUser;
    }

    @Override
    public List<Vendedor> listVendedor(Vendedor ven) {
        String sql = "SELECT * from Vendedor";

        if (ven != null) {
            sql += ven.getWhere();
        }

        List<Vendedor> listSeller = getJdbcTemplate().query(sql, new RowMapper<Vendedor>() {

            @Override
            public Vendedor mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Vendedor ven = new Vendedor();
                ven.setIdVendedor(rs.getInt("id_vendedor"));
                ven.setNombreVendedor(rs.getString("nombre_Vendedor"));
                ven.setApellidoVendedor(rs.getString("apellido_Vendedor"));
                return ven;
            }

        });
        return listSeller;
    }

    @Override
    public List<Sucursal> listSucursal(Sucursal suc, Integer idEmpresa) {
        String sql = "SELECT * from Sucursal ";
        if (suc != null) {
            if (idEmpresa != null) {
                sql += "where id_empresa = " + idEmpresa;
            } else {
                sql += suc.getWhere();
            }
        }

        List<Sucursal> listBranch = getJdbcTemplate().query(sql, new RowMapper<Sucursal>() {

            @Override
            public Sucursal mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Sucursal suc = new Sucursal();
                suc.setIdSucursal(rs.getInt("id_sucursal"));
                suc.setNombreSucursal(rs.getString("nombre_sucursal"));
                suc.setDireccionSucursal(rs.getString("direccion_sucursal"));
                suc.setTelefonoSucursal(rs.getString("telefono_sucursal"));
                return suc;
            }

        });
        return listBranch;
    }

    @Override
    public List<Modulo> listModulo(Modulo mod) {
        String sql = "SELECT * from Modulo";

        if (mod != null) {
            sql += mod.getWhere();
        }

        List<Modulo> listMod = getJdbcTemplate().query(sql, new RowMapper<Modulo>() {

            @Override
            public Modulo mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Modulo mod = new Modulo();
                mod.setIdModulo(rs.getInt("id_modulo"));
                mod.setNombreModulo(rs.getString("nombre_modulo"));
                mod.setIconoModulo(rs.getString("icono_modulo"));
                return mod;
            }

        });
        return listMod;
    }

    @Override
    public String nombreTipoEmpresa(Integer id) {
        String nombre = "";
        switch (id) {
            case 1:
                nombre = RESOURCE_BUNDLE.getString("tipoEmpresa1");
                break;
            case 2:
                nombre = RESOURCE_BUNDLE.getString("tipoEmpresa2");
                break;
            case 3:
                nombre = RESOURCE_BUNDLE.getString("tipoEmpresa3");
                break;
            case 4:
                nombre = RESOURCE_BUNDLE.getString("tipoEmpresa4");
                break;
        }

        return nombre;
    }

    /**
     * Metodo que devuelve true si el usuario buscado existe
     *
     * @param usuario
     * @return
     */
    @Override
    public Boolean getUsuarioByUsu(String usuario) {
        return !getJdbcTemplate().query("SELECT * FROM usuario WHERE usuario = '" + usuario + "'", new BeanPropertyRowMapper(Usuario.class)).isEmpty();
    }

    @Override
    public Boolean getUsuarioByClave(String usuario, String clave) {
        return !getJdbcTemplate().query("SELECT * FROM usuario WHERE usuario = '" + usuario + "' and clave_acceso='" + clave + "'", new BeanPropertyRowMapper(Usuario.class)).isEmpty();
    }

    /**
     * Metodo que devuelve true si el correo registrado a un nuevo usuario ya
     * existe
     *
     * @param eMail
     * @return
     */
    @Override
    public Boolean isExistEmailPerByEmail(String eMail) {
        return !getJdbcTemplate().query("SELECT * FROM persona WHERE correo_electronico = '" + eMail + "'", new BeanPropertyRowMapper(Persona.class)).isEmpty();
    }

    @Override
    public int guardarConIdAutogenerado(PersistenciaDao objeto) {
        return super.persistirConIdAutogenerado(objeto);
    }

    @Override
    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo) {
        return super.persistirConIdString(objeto, nuevo);
    }

    @Override
    public List<Categoria> lstCategoria(Categoria cat) {
        String sql = "SELECT * from categoria WHERE padre_id_categoria is null";

        if (cat != null) {
            sql += cat.getWhere();
        }

        List<Categoria> lstCat = getJdbcTemplate().query(sql, new CategoriaRowMapper());
        return lstCat;
    }

    @Override
    public List<Categoria> lstSubCategoria(Categoria cat) {
        String sql = "SELECT * FROM categoria WHERE padre_id_categoria = ?";

        List<Categoria> lstCat = getJdbcTemplate().query(sql, new Object[]{cat.idCategoria}, new CategoriaRowMapper());
        return lstCat;
    }

    @Override
    public List<Modelo> listModelo(Modelo mod) {
        String sql = "SELECT * FROM modelo";

        if (mod != null) {
            sql += mod.getWhere();
        }

        List<Modelo> listMod = getJdbcTemplate().query(sql, new RowMapper<Modelo>() {

            @Override
            public Modelo mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Modelo mod = new Modelo();
                mod.setIdModelo(rs.getInt("id_modelo"));
                mod.setNombreModelo(rs.getString("nombre_Modelo"));
                mod.setCodigoModelo(rs.getString("codigo_Modelo"));
                mod.setIdMarca(rs.getInt("id_marca"));
                return mod;
            }

        });
        return listMod;
    }

    @Override
    public List<Marca> listMarca(Marca mar) {
        String sql = "SELECT * FROM marca";

        if (mar != null) {
            sql += mar.getWhere();
        }

        List<Marca> listMar = getJdbcTemplate().query(sql, new RowMapper<Marca>() {

            @Override
            public Marca mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Marca mar = new Marca();
                mar.setIdMarca(rs.getInt("id_marca"));
                mar.setNombreMarca(rs.getString("nombre_Marca"));
                mar.setMarcaActiva(rs.getShort("marca_activa"));

                return mar;
            }

        });
        return listMar;
    }

    @Override
    public Boolean removerCategoria(Integer idCategoria) {
        String deleteQuery = "delete from categoria where id_categoria = ?";
        return getJdbcTemplate().update(deleteQuery, idCategoria) > 0;
    }

    @Override
    public List<Categoria> getLstCategoriaByLikeNombre(String nombreCategoria, Integer idProducto) {
        String sql = env.getProperty("lstCategoriasByNombreAndProducto");

        List<Categoria> lstCat = getJdbcTemplate().query(sql, new Object[]{idProducto, "%" + nombreCategoria + "%"}, new BeanPropertyRowMapper(Categoria.class));
        return lstCat;
    }

    @Override
    public String findNombreOpcion(Integer id) {
        String name = "-";
        if (id != null && id != 0) {
            String sql = "SELECT nombre_opcion FROM opcion_menu WHERE id_opcion_menu=?";

            name = getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);
        }
        return name;
    }

    @Override
    public String findNombreModulo(Integer id) {
        String sql = "SELECT nombre_modulo FROM modulo WHERE id_modulo=?";

        String name = getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);

        return name;
    }

    @Override
    public String findNombreMarca(Integer id) {
        String sql = "SELECT NOMBRE_MARCA FROM MARCA WHERE ID_MARCA=?";

        String name = getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);

        return name;
    }

    @Override
    public List<ProductoCategoriaDto> getLstCategoriasByProducto(Integer idProducto) {
        String sql = env.getProperty("lstCategoriasByIdProducto");

        List<ProductoCategoriaDto> lstCat = getJdbcTemplate().query(sql, new Object[]{idProducto}, new RowMapper<ProductoCategoriaDto>() {

            @Override
            public ProductoCategoriaDto mapRow(ResultSet rs, int rowNumber) throws SQLException {
                ProductoCategoriaDto proCatDto = new ProductoCategoriaDto();
                proCatDto.setIdItem(rs.getInt("id_producto"));
                proCatDto.setIdCategoria(rs.getInt("id_categoria"));
                proCatDto.setNombreCategoria(rs.getString("nombre_categoria"));

                return proCatDto;
            }

        });
        return lstCat;
    }

    @Override
    public Categoria findCategoriaById(Integer idCategoria) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";

        Categoria customer = (Categoria) getJdbcTemplate().queryForObject(
                sql, new Object[]{idCategoria},
                new BeanPropertyRowMapper(Categoria.class));

        return customer;
    }

    @Override
    public Usuario findUserByLogin(String login) {
        String sql = "SELECT * FROM usuario WHERE usuario = ?";

        Usuario user = (Usuario) getJdbcTemplate().queryForObject(
                sql, new Object[]{login},
                new BeanPropertyRowMapper(Usuario.class));

        return user;
    }

    @Override
    public Integer findIdEmpByLogin(String login) {
        String sql = "SELECT id_empresa FROM usuario_Empresa WHERE usuario = ?";

        Integer idEmp = getJdbcTemplate().queryForObject(sql, new Object[]{login}, Integer.class);

        return idEmp;
    }

    @Override
    public Integer findIdModPerByLogin(String login, Integer emp) {
        String sql = "SELECT id_modulo_perfil FROM usuario_Empresa WHERE usuario = ? and id_empresa=?";

        Integer idEmp = getJdbcTemplate().queryForObject(sql, new Object[]{login, emp}, Integer.class);

        return idEmp;
    }

    @Override
    public List<Modulo> getlstModulos(String login) {
        String sql = "select * from modulo "
                + " where id_modulo in (select mp.id_modulo from usuario_empresa ue inner join modulo_perfil mp on mp.id_modulo_perfil=ue.id_modulo_perfil "
                + " where ue.usuario='" + login + "');";

        List<Modulo> listMod = getJdbcTemplate().query(sql, new RowMapper<Modulo>() {

            @Override
            public Modulo mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Modulo modulos = new Modulo();
                modulos.setIdModulo(rs.getInt("id_modulo"));
                modulos.setNombreModulo(rs.getString("nombre_Modulo"));
                modulos.setIconoModulo(rs.getString("icono_Modulo"));
                return modulos;
            }

        });
        return listMod;

    }

    @Override
    public void crearArbolMenu(List<OpcionMenu> lstOpcionMenu) {
        DefaultMenuModel menu = new DefaultMenuModel();
        DefaultSubMenu subMenu = new DefaultSubMenu();
        Object obj = null;

        try {
            if (!lstOpcionMenu.isEmpty()) {
                OpcionMenu opcMenu = lstOpcionMenu.get(0);

                if (lstOpcionMenu.size() == 1) {
                    DefaultMenuItem itemMenu = new DefaultMenuItem();

                    itemMenu.setValue(" " + opcMenu.getNombreOpcion());
                    itemMenu.setUrl(opcMenu.getUrlOpcion() + "?faces-redirect=true");
                    itemMenu.setIcon(opcMenu.getIconoOpcion() != null ? opcMenu.getIconoOpcion() : null);
                    itemMenu.setAjax(true);
                    itemMenu.setId("item" + opcMenu.getIdOpcionMenu().toString());

                    subMenu.addElement(itemMenu);
                    obj = subMenu;
                } else {
                    subMenu.setLabel(opcMenu.getNombreOpcion());
                    subMenu.setId("sub" + opcMenu.getIdOpcionMenu().toString());

                    if (!lstOpcionMenu.subList(1, lstOpcionMenu.size()).isEmpty()) {
                        obj = getHijo(subMenu, lstOpcionMenu.subList(1, lstOpcionMenu.size()));
                    }
                }
            } else {

                subMenu.setLabel(" Sin opciones");
                obj = subMenu;
            }
            menu.addElement((DefaultSubMenu) obj);
            model = menu;

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Ocurrio una excepción en el proceso de creación del arbol del menu. Contactese con el administrador del sistema.");
        }
    }

    private Object getHijo(DefaultSubMenu opPadre, List<OpcionMenu> resultado) {
        DefaultSubMenu subMenu;
        DefaultMenuItem itemMenu;
        Boolean hijo = true;
        OpcionMenu opcionM;

        for (int j = 0; j < resultado.size(); j++) {
            opcionM = resultado.get(j);

            if (opPadre.getId().replace("sub", "").equals(opcionM.getOpcionIdOpcionMenu().toString())) { //Hijo del padre
                subMenu = new DefaultSubMenu();
                subMenu.setLabel(opcionM.getOrdenOpcion().toString() + ". " + opcionM.getNombreOpcion());
                subMenu.setId("sub" + opcionM.getIdOpcionMenu().toString());

                Object obj = getHijo(subMenu, resultado.subList(j, resultado.size()));

                if (obj instanceof DefaultMenuItem) {
                    opPadre.getElements().add((DefaultMenuItem) obj);
                } else {
                    opPadre.getElements().add((DefaultSubMenu) obj);
                }
                hijo = false;
            }
        }

        if (hijo) {
            opcionM = resultado.get(0);
            itemMenu = new DefaultMenuItem();
            itemMenu.setValue(" " + opcionM.getNombreOpcion());
            itemMenu.setUrl(opcionM.getUrlOpcion() + "?faces-redirect=true");
            itemMenu.setIcon(opcionM.getIconoOpcion() != null ? opcionM.getIconoOpcion() : null);
            itemMenu.setAjax(true);
            itemMenu.setId("item" + opcionM.getIdOpcionMenu().toString());

            return itemMenu;
        } else {
            return opPadre;
        }
    }

    @Override
    public List<OpcionMenu> listOpcMenuByUsuAndModulo(String usuario, Integer idModulo) {
        return getJdbcTemplate().query("SELECT * from vw_opciones_menu_by_usu_and_mod WHERE usuario ='" + usuario + "' and id_modulo = " + idModulo, new BeanPropertyRowMapper(OpcionMenu.class));
    }

    @Override
    public List<Categoria> getLstCategoriaByIdItem(Integer idItem) {
        return getJdbcTemplate().query("select c.* from producto_categoria pc inner join categoria c on pc.id_categoria = c.id_categoria where pc.id_item = ?", new Object[]{idItem}, new BeanPropertyRowMapper(Categoria.class));
    }

}
