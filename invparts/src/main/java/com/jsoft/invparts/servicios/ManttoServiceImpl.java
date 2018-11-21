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
import com.jsoft.invparts.model.inventario.Producto;
import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.inventario.Vendedor;
import com.jsoft.invparts.model.seguridad.Usuario;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.commons.codec.digest.DigestUtils;

@Service("manttoService")
@Component
public class ManttoServiceImpl implements ManttoService {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    @Autowired
    private ManttoDao dao;

    @Override
    public List<Persona> listPersona(Persona per) {
        return dao.listPersona(per);
    }

    @Override
    public int guardarConIdAutogenerado(PersistenciaDao objeto) {
        return dao.guardarConIdAutogenerado(objeto);
    }

    @Override
    public Boolean isExistEmailPerByEmail(String eMail) {
        return dao.isExistEmailPerByEmail(eMail);
    }
 @Override
    public List<Empresa> listEmpresaUsu() {
        return dao.listEmpresaUsu();
    }
    
    @Override
    public List<Empresa> listEmpresa(Short idTipoEmpresa) {
        return dao.listEmpresa(idTipoEmpresa);
    }

    @Override
    public List<Usuario> listUsuario() {
        return dao.listUsuario();
    }

    @Override
    public Boolean getUsuarioByUsu(String usuario) {
        return dao.getUsuarioByUsu(usuario);
    }

    @Override
    public int guardarNuevoUsuario(PersistenciaDao objeto) {
        return dao.guardarNuevoUsuario(objeto);
    }

    @Override
    public void enviarCorreoActivacionUsuario(Persona per, String codAct) {
        StringBuilder sb = new StringBuilder("");

        sb.append(MessageFormat.format(RESOURCE_BUNDLE.getString("email.cuerpoMensajeConfirmarCuenta"), per.getPrimerNombre() + " " + per.getSegundoNombre(), "www.invparts.com", "www.invparts.com/uact=" + codAct)).append("<br/>");
        sb.append(MessageFormat.format(RESOURCE_BUNDLE.getString("email.firmaDeCorreo"), new Date()));
        System.out.println("correo:\n" + sb.toString());

    }

    @Override
    public String encriptar(String cadena) {
        return DigestUtils.md5Hex(cadena).toUpperCase();
    }

    @Override
    public int guardarConIdString(PersistenciaDao objeto, Boolean nuevo) {
        return dao.guardarConIdString(objeto, nuevo);
    }

    @Override
    public List<Vendedor> listVendedor() {
        return dao.listVendedor();
    }

    @Override
    public List<Sucursal> listSucursal() {
        return dao.listSucursal();
    }

    @Override
    public List<Producto> listProducto() {
        return dao.listProducto();
    }
    
    @Override
    public String nombreTipoEmpresa(Integer id){
        return dao.nombreTipoEmpresa(id);
    }
}
