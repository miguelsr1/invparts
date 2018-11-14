/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.seguridad.Empresa;
import com.jsoft.invparts.model.seguridad.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.jsoft.invparts.dao.ManttoDao;
import com.jsoft.invparts.model.seguridad.Usuario;

@Service("manttoService")
@Component
public class ManttoServiceImpl implements ManttoService {
    @Autowired
    private ManttoDao dao;

    @Override
    public List<Persona> listPersona() {
        return dao.listPersona();
    }

    @Override
    public int guardar(PersistenciaDao objeto) {
        return dao.guardar(objeto);
    }

    @Override
    public Boolean isExistEmailPerByEmail(String eMail) {
        return dao.isExistEmailPerByEmail(eMail);
    }

    @Override
    public List<Empresa> listEmpresa() {
        return dao.listEmpresa();
    }

    @Override
    public List<Usuario> listUsuario() {
        return dao.listUsuario();
    }

    @Override
    public Boolean getUsuarioByUsu(String usuario) {
        return dao.getUsuarioByUsu(usuario);
    }
}
