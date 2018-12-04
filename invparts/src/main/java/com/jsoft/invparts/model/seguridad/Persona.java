/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.seguridad;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.UtilWhere;
import java.io.Serializable;

/**
 *
 * @author DesarrolloPc
 */
public class Persona extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idPersona;
    public String primerNombre;
    public String segundoNombre;
    public String correoElectronico;
    public String numeroTelefono;

    public Persona() {
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + "}";
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO persona(primer_nombre, segundo_nombre, correo_electronico, numero_telefono) values(?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{primerNombre, segundoNombre, correoElectronico, numeroTelefono};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE persona set primer_nombre=?, segundo_nombre=?, correo_electronico=?, numero_telefono=? WHERE id_persona=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{primerNombre, segundoNombre, correoElectronico, numeroTelefono, idPersona};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idPersona == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idPersona = id;
    }
}
