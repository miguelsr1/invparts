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
import com.jsoft.invparts.model.mapper.CategoriaRowMapper;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.ModuloPerfil;
import com.jsoft.invparts.model.seguridad.OpcionMenu;
import com.jsoft.invparts.model.seguridad.Perfil;
import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Privilegio;
import com.jsoft.invparts.model.seguridad.Usuario;
import com.jsoft.invparts.util.JsfUtil;
import com.jsoft.invparts.util.XJdbcTemplate;
import java.util.List;
import java.util.ResourceBundle;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

        List<Persona> listPer = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Persona.class));
        return listPer;
    }

    @Override
    public List<Privilegio> listPrivilegio(Privilegio pri) {
        String sql = "SELECT * from privilegio";

        if (pri != null) {
            sql += pri.getWhere();
        }

        List<Privilegio> listPer = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Privilegio.class));
        return listPer;
    }

    @Override
    public List<Perfil> listPerfil(Perfil per) {
        String sql = "SELECT * from perfil";

        if (per != null) {
            sql += per.getWhere();
        }

        List<Perfil> listPer = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Perfil.class));
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

        List<Empresa> listEmp = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
        return listEmp;
    }

    @Override
    public List<Empresa> listEmpresaUsu(Empresa emp) {
        String sql = "SELECT * from empresa where id_tipo_empresa = 4";

        if (emp != null) {
            sql += emp.getWhere();
        }

        List<Empresa> listEmp = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Empresa.class));
        return listEmp;
    }

    @Override
    public List<OpcionMenu> listOpcMenu(OpcionMenu opc) {
        String sql = "SELECT * from opcion_menu where opcion_activa=1";

        if (opc != null) {
            sql += opc.getWhere();
        }

        List<OpcionMenu> listOpc = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
        return listOpc;
    }

    @Override
    public List<OpcionMenu> listOpcMenuMod(Integer idMod, Integer idModPer) {
        String sql = "SELECT * FROM opcion_menu WHERE id_modulo=" + idMod + " and opcion_activa=1 and id_opcion_menu in (select id_opcion_menu from privilegio where id_modulo_perfil=" + idModPer + ")";

        List<OpcionMenu> listOpc = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(OpcionMenu.class));
        return listOpc;
    }

    @Override
    public List<Usuario> listUsuario() {
        String sql = "SELECT * from usuario";
        List<Usuario> listUser = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Usuario.class));
        return listUser;
    }

    @Override
    public List<Vendedor> listVendedor(Vendedor ven) {
        String sql = "SELECT * from vendedor";

        if (ven != null) {
            sql += ven.getWhere();
        }

        List<Vendedor> listSeller = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Vendedor.class));
        return listSeller;
    }

    @Override
    public List<Sucursal> listSucursal(Sucursal suc, Integer idEmpresa) {
        String sql = "SELECT * from sucursal ";
        if (suc != null) {
            if (idEmpresa != null) {
                sql += "where id_empresa = " + idEmpresa;
            } else {
                sql += suc.getWhere();
            }
        }

        List<Sucursal> listBranch = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Sucursal.class));
        return listBranch;
    }

    @Override
    public List<Modulo> listModulo(Modulo mod) {
        String sql = "SELECT * from modulo";

        if (mod != null) {
            sql += mod.getWhere();
        }

        List<Modulo> listMod = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Modulo.class));
        return listMod;
    }

    @Override
    public List<ModuloPerfil> listModPerfil(ModuloPerfil mod) {
        String sql = "SELECT * from modulo_perfil";

        if (mod != null) {
            sql += mod.getWhere();
        }

        List<ModuloPerfil> listMod = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ModuloPerfil.class));
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
        String sql = "SELECT nombre_marca FROM marca WHERE id_marca=?";

        String name = getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);

        return name;
    }

    @Override
    public List<ProductoCategoriaDto> getLstCategoriasByProducto(Integer idProducto) {
        String sql = env.getProperty("lstCategoriasByIdProducto");

        List<ProductoCategoriaDto> lstCat = getJdbcTemplate().query(sql, new Object[]{idProducto}, new BeanPropertyRowMapper(ProductoCategoriaDto.class));
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
        String sql = "SELECT DISTINCT id_empresa FROM usuario_empresa WHERE usuario = ?";

        Integer idEmp = getJdbcTemplate().queryForObject(sql, new Object[]{login}, Integer.class);

        return idEmp;
    }

    @Override
    public Integer findIdModPerByLogin(String login, Integer emp) {
        String sql = "select distinct per.id_perfil \n"
                + "from \n"
                + "	usuario us\n"
                + "	inner join usuario_empresa usemp on us.usuario = usemp.usuario\n"
                + "	inner join modulo_perfil modp	on usemp.id_modulo_perfil = modp.id_modulo_perfil\n"
                + "	inner join perfil per on per.id_perfil = modp.id_perfil\n"
                + "where \n"
                + "	us.usuario = ? and\n"
                + "	usemp.id_empresa = ?";

        Integer idEmp = getJdbcTemplate().queryForObject(sql, new Object[]{login, emp}, Integer.class);

        return idEmp;
    }

    @Override
    public List<Modulo> getlstModulos(String login, Integer idEmpresa, Integer idPerfil) {
        String sql = "select modulo.*\n"
                + "from \n"
                + "	usuario_empresa usemp \n"
                + "	inner join modulo_perfil modp	on usemp.id_modulo_perfil = modp.id_modulo_perfil\n"
                + "	inner join modulo on modulo.id_modulo = modp.id_modulo\n"
                + "where \n"
                + "	usemp.usuario = ? and\n"
                + "	usemp.id_empresa = ? and\n"
                + "	modp.id_perfil = ?";

        List<Modulo> listMod = getJdbcTemplate().query(sql, new Object[]{login, idEmpresa, idPerfil}, new BeanPropertyRowMapper(Modulo.class));
        return listMod;

    }

    @Override
    public DefaultMenuModel crearArbolMenu(List<OpcionMenu> lstOpcionMenu) {
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

            return menu;
        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Ocurrio una excepción en el proceso de creación del arbol del menu. Contactese con el administrador del sistema.");
            return null;
        }
    }

    private Object getHijo(DefaultSubMenu opPadre, List<OpcionMenu> resultado) {
        DefaultSubMenu subMenu;
        DefaultMenuItem itemMenu;
        Boolean hijo = true;
        OpcionMenu opcionM;

        for (int j = 0; j < resultado.size(); j++) {
            opcionM = resultado.get(j);

            if (Integer.parseInt(opPadre.getId().replace("sub", "")) == opcionM.getOpcIdOpcionMenu()) { //Hijo del padre
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
        return getJdbcTemplate().query("select c.id_categoria, getpath(c.id_categoria) as nombre_categoria, c.padre_id_categoria from producto_categoria pc inner join categoria c on pc.id_categoria = c.id_categoria where pc.id_item = ?", new Object[]{idItem}, new BeanPropertyRowMapper(Categoria.class));
    }

    @Override
    public Boolean eliminar(String nombreTbl, Integer id) {
        int rows = getJdbcTemplate().update("DELETE FROM " + nombreTbl + " WHERE id_" + nombreTbl + " = ?", new Object[]{id});
        return rows > 0;
    }

}
