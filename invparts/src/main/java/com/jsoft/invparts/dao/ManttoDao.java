/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Usuario;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public interface ManttoDao {

    public List<Persona> listPersona();

    public List<Empresa> listEmpresa();

    public List<Usuario> listUsuario();

    public Boolean getUsuarioByUsu(String usuario);

    public Boolean isExistEmailPerByEmail(String eMail);

    public int guardarConIdAutogenerado(PersistenciaDao objeto);
    
    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo);
    
    public int guardarNuevoUsuario(PersistenciaDao objeto);
}
