/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.mapper;

import com.jsoft.invparts.model.seguridad.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author torre
 */
public class UsuarioRowMapper implements RowMapper<Usuario>{

    @Override
    public Usuario mapRow(ResultSet rs, int i) throws SQLException {
        Usuario usu = new Usuario();
       usu.setUsuario(rs.getString("usuario"));
        usu.setIdPersona(rs.getInt("id_persona"));
        usu.setClaveAcceso(rs.getString("clave_acceso"));
        usu.setUsuarioActivo(rs.getShort("usuario_activo"));
        usu.setCambiarClave(rs.getShort("cambiar_clave"));
        usu.setIntentosFallidos(rs.getShort("intentos_fallidos"));
        usu.setFechaCaducidad(rs.getDate("fecha_caducidad"));
        usu.setCodigoActivacion(rs.getString("codigo_activacion"));
        return usu; 
    }
    
}
