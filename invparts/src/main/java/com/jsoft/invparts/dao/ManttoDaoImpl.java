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
import com.jsoft.invparts.util.XJdbcTemplate;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author misanchez
 */
@Repository("manttoDao")
public class ManttoDaoImpl extends XJdbcTemplate implements ManttoDao {

    @Autowired
    private DataSource dataSource;

    public ManttoDaoImpl() {
    }

    @PostConstruct
    public void init() {
        if (dataSource == null) {
            throw new BeanCreationException("Must set mySqlDataSource on " + this.getClass().getName());
        }
        setJdbcTemplate(new JdbcTemplate(dataSource));
    }

    @Override
    public List<Persona> listPersona(Persona per) {
        String sql = "SELECT * from persona";
        
        if(per != null){
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
    public List<Empresa> listEmpresa(Short idTipoEmpresa) {
        String sql = "SELECT * from empresa where id_tipo_empresa in (" + idTipoEmpresa + ", 3) ";
        List<Empresa> listEmp = getJdbcTemplate().query(sql, new RowMapper<Empresa>() {

            @Override
            public Empresa mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Empresa emp = new Empresa();
                emp.setIdEmpresa(rs.getObject("id_empresa", BigInteger.class));
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
    public List<Vendedor> listVendedor() {
        String sql = "SELECT * from Vendedor";
        List<Vendedor> listSeller = getJdbcTemplate().query(sql, new RowMapper<Vendedor>() {

            @Override
            public Vendedor mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Vendedor ven = new Vendedor();
                ven.setIdVendedor(rs.getObject("id_vendedor", BigInteger.class));
                ven.setNombreVendedor(rs.getString("nombre_Vendedor"));
                ven.setApellidoVendedor(rs.getString("apellido_Vendedor"));
                return ven;
            }

        });
        return listSeller;
    }

    @Override
    public List<Sucursal> listSucursal() {
        String sql = "SELECT * from Sucursal";
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
    public List<Producto> listProducto() {
        String sql = "SELECT * from Producto";
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
    public int guardarNuevoUsuario(PersistenciaDao objeto) {
        return super.createIdString(objeto);
    }

    @Override
    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo) {
        return super.persistirConIdString(objeto, nuevo);
    }
}
