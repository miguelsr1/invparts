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
import com.jsoft.invparts.model.inventario.dto.ProductoCategoriaDto;
import com.jsoft.invparts.model.mapper.CategoriaRowMapper;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.Perfil;
import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Usuario;
import com.jsoft.invparts.util.XJdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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

    public ManttoDaoImpl() {
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
    public List<Sucursal> listSucursal(Sucursal suc,Integer idEmpresa) {
        String sql = "SELECT * from Sucursal ";
        if (suc != null) {
            if (idEmpresa != null) {
                sql += "where id_empresa = " + idEmpresa ;
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
    public List<Producto> listProducto(Producto pro) {
        String sql = "SELECT * from Producto";

        if (pro != null) {
            sql += pro.getWhere();
        }

        List<Producto> listProv = getJdbcTemplate().query(sql, new RowMapper<Producto>() {

            @Override
            public Producto mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Producto pro = new Producto();
                pro.setIdProducto(rs.getInt("id_producto"));
                pro.setStatus(rs.getShort("status"));
                pro.setCodigoProducto(rs.getString("codigo_producto"));
                pro.setNombreProducto(rs.getString("nombre_producto"));
                return pro;
            }

        });
        return listProv;
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

        List<Categoria> lstCat = getJdbcTemplate().query(sql, new Object[]{idProducto, "%" + nombreCategoria + "%"}, new CategoriaRowMapper());
        return lstCat;
    }
    
    @Override
    public String findNombreMarca(Integer id){
        String sql = "SELECT NOMBRE_MARCA FROM MARCA WHERE ID_MARCA=?";
        
        String name = getJdbcTemplate().queryForObject(sql, new Object[] {id},String.class);
        
        return name;
    }

    @Override
    public List<ProductoCategoriaDto> getLstCategoriasByProducto(Integer idProducto) {
        String sql = env.getProperty("lstCategoriasByIdProducto");

        List<ProductoCategoriaDto> lstCat = getJdbcTemplate().query(sql, new Object[]{idProducto}, new RowMapper<ProductoCategoriaDto>() {

            @Override
            public ProductoCategoriaDto mapRow(ResultSet rs, int rowNumber) throws SQLException {
                ProductoCategoriaDto proCatDto = new ProductoCategoriaDto();
                proCatDto.setIdProducto(rs.getInt("id_producto"));
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
}
