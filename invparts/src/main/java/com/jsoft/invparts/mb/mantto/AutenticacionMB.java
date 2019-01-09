/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.Usuario;
import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class AutenticacionMB implements Serializable{

    private String login;
    private String clave;
    private Usuario user = new Usuario();
    private List<Modulo> lstModulo = new ArrayList<>();

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

    /*    
JsfUtil.addVariableSession("USU_SESSION", user);
        Usuario p = (Usuario) JsfUtil.getVariableSession("USU_SESSION");*/
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

    public List<Modulo> getLstModulo() {
        return lstModulo;
    }

    public void setLstModulo(List<Modulo> lstModulo) {
        this.lstModulo = lstModulo;
    }

    public void validarUsuario() {
        if (manttoService.getUsuarioByUsu(login)) {
            if (!manttoService.getUsuarioByClave(login, clave)) {
                
                user = manttoService.findUserByLogin(login);
                JsfUtil.addVariableSession("USU_SESSION", login);
                lstModulo = manttoService.getlstModulos(login);
                JsfUtil.redireccionar("/principal.xhtml");
            } else {
                JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("usuarioInvalido"));
            }
        } else {
            JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("claveIncorrecta"));
        }
    }

}
