/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.Usuario;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author torre
 */
@Named(value = "autenticacionMB")
@ViewScoped
public class AutenticacionMB {

    private String login;
    private String clave;
    private Usuario user = new Usuario();
    
    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    
    /**
     * Creates a new instance of AutenticacionMB
     */
    public AutenticacionMB() {
    }
    
    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    
     @PostConstruct
    public void init() {
    
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
 
    public void validarUsuario(String login,String clave){
       if(!manttoService.getUsuarioByUsu(login)){
         //  user = manttoService.getUsuarioByUsu(clave);
       }else{
           JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("usuarioInvalido"));
       }
    }
    
    
}
