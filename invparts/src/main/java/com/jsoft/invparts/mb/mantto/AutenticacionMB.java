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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author torre
 */
@ManagedBean
@SessionScoped
public class AutenticacionMB implements Serializable {

    private String login;
    private String clave;
    private String usuario;
    private String idApp;
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

    @PostConstruct
    public void init() {

   /*     Usuario p = (Usuario) JsfUtil.getVariableSession("USU_SESSION");
      idModulo =  JsfUtil.getRequestParameter("idApp");
      
      lstOpcMenu = manttoService.listOpcMenuMod(Integer.parseInt(idModulo));*/
     
    }

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

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
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

    public void obtenerMenu() {
        
        JsfUtil.redireccionar("/menu.xhtml");
    
    }

    public void validarUsuario() {
        Integer emp;
        
        
        if (manttoService.getUsuarioByUsu(login)) {
            if (manttoService.getUsuarioByClave(login, clave)) {

                emp=manttoService.findIdEmpByLogin(login);
             //   modPer= manttoService.findIdModPerByLogin(login,emp);
                
                user = manttoService.findUserByLogin(login);
                
                JsfUtil.addVariableSession("USU_SESSION", user);
                JsfUtil.addVariableSession("EMP_SESSION",emp );
                JsfUtil.addVariableSession("PERSONA_SESSION",user.getIdPersona());
                
                lstModulo = manttoService.getlstModulos(login);
                JsfUtil.redireccionar("/app/principal.xhtml");
            } else {
                JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("claveIncorrecta"));
            }
        } else {
            JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("usuarioInvalido"));
        }
    }
     
    public void logout(){
            JsfUtil.removeVariableSession(login);
                     JsfUtil.redireccionar("/login.xhtml");
        }
}
