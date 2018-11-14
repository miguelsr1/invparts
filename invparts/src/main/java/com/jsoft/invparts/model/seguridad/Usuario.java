/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.seguridad;

import com.jsoft.invparts.dao.PersistenciaDao;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author DesarrolloPc
 */
public class Usuario implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private BigInteger idPersona;
    private String claveAcceso;
    private Short usuarioActivo;
    private Short cambiarClave;
    private Date fechaCaducidad;
    private Short intentosFallidos;

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigInteger getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(BigInteger idPersona) {
        this.idPersona = idPersona;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public Short getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Short usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public Short getCambiarClave() {
        return cambiarClave;
    }

    public void setCambiarClave(Short cambiarClave) {
        this.cambiarClave = cambiarClave;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Short getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Short intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", idPersona=" + idPersona + '}';
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO usuario(usuario, id_persona, clave_acceso, usuario_activo, cambiar_clave, fecha_caducidad, intentos_fallidos) values(?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{usuario, idPersona, claveAcceso, usuarioActivo, cambiarClave, fechaCaducidad, intentosFallidos};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE usuario SET id_persona=?, clave_acceso=?, usuario_activo=?, cambiar_clave=?, fecha_caducidad=?, intentos_fallidos=? WHERE usuario=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idPersona, claveAcceso, usuarioActivo, cambiarClave, fechaCaducidad, intentosFallidos, usuario};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return (usuario == null) || usuario.trim().isEmpty();
    }

}