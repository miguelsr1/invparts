/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.Usuario;

import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.OpcionMenu;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author torre
 */
@ManagedBean
@ViewScoped
public class AutenticacionMB implements Serializable {

    private String login;
    private String clave;
    private String usuario;
    private Integer modPer;
  
    private Usuario user = new Usuario();

    private List<Modulo> lstModulo = new ArrayList<>();
    private String idModulo;
    private List<OpcionMenu> lstOpcMenu = new ArrayList<>();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    /**
     * Creates a new instance of AutenticacionMB
     */
    public AutenticacionMB() {
    }

    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public List<OpcionMenu> getLstOpcMenu() {
        return lstOpcMenu;
    }

    public void setLstOpcMenu(List<OpcionMenu> lstOpcMenu) {
        this.lstOpcMenu = lstOpcMenu;
    }

    public Integer getModPer() {
        return modPer;
    }

    public void setModPer(Integer modPer) {
        this.modPer = modPer;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String validarUsuario() {
        Integer emp;
        String url;

        if (manttoService.getUsuarioByUsu(login)) {
            if (manttoService.getUsuarioByClave(login, clave)) {
                url = "/app/principal.xhtml?faces-redirect=true";
                //obtener empresa 
                emp = manttoService.findIdEmpByLogin(login);
                modPer= manttoService.findIdModPerByLogin(login,emp);

                user = manttoService.findUserByLogin(login);
                lstModulo = manttoService.getlstModulos(login);

                JsfUtil.addVariableSession("USU_SESSION", user);
                JsfUtil.addVariableSession("MODULO_PERFIL", modPer);
                JsfUtil.addVariableSession("EMP_SESSION", emp);
                JsfUtil.addVariableSession("PERSONA_SESSION", user.getIdPersona());

                return url;
            } else {
                JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("claveIncorrecta"));
                return null;
            }
        } else {
            JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("usuarioInvalido"));
            return null;
        }
    }

    public String logout() {
        JsfUtil.removeVariableSession(login);
        return "/login.xhtml";
        //JsfUtil.redireccionar("/login.xhtml");
    }
}
