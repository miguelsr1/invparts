/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

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
    public List<Persona> listPersona() {
        String sql = "SELECT * from persona";
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
    public List<Empresa> listEmpresa() {
        String sql = "SELECT * from empresa";
        List<Empresa> listEmp = getJdbcTemplate().query(sql, new RowMapper<Empresa>() {

            @Override
            public Empresa mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Empresa emp = new Empresa();
                emp.setIdEmpresa(rs.getObject("id_empresa", BigInteger.class));
                emp.setNombreEmpresa(rs.getString("nombre_empresa"));
                emp.setCorreoEmpresa(rs.getString("correo_empresa"));
                emp.setTelefonoEmpresa(rs.getString("telefono_empresa"));
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
