/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.OpcionMenu;
import com.jsoft.invparts.model.seguridad.Usuario;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuModel;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@SessionScoped
public class MenuMB implements Serializable {

    private List<OpcionMenu> lstOpcMenu = new ArrayList();
    private HashMap<String, Object> variables = new HashMap();
    private String idModulo;
    private String login;
    private Integer idApp;
    private List<Modulo> lstModulo = new ArrayList<>();
    private DefaultMenuModel model;
    private Integer idPerfilUsuario;

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    private static final long serialVersionUID = 1L;

    public MenuMB() {
    }

    @PostConstruct
    public void init() {

    }

    public void validarUsuario() {
        if (JsfUtil.getVariableSession("USU_SESSION") != null) {
            Usuario p = (Usuario) JsfUtil.getVariableSession("USU_SESSION");
            login = p.getUsuario();
            idPerfilUsuario = Integer.parseInt(JsfUtil.getVariableSession("MODULO_PERFIL").toString());
            lstModulo = manttoService.getlstModulos(login, Integer.parseInt(JsfUtil.getVariableSession("EMP_SESSION").toString()), idPerfilUsuario);
        }
    }

    public HashMap<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, Object> variables) {
        this.variables = variables;
    }

    public List<OpcionMenu> getLstOpcMenu() {
        return lstOpcMenu;
    }

    public void setLstOpcMenu(List<OpcionMenu> lstOpcMenu) {
        this.lstOpcMenu = lstOpcMenu;
    }

    public Integer getIdApp() {
        return idApp;
    }

    public void setIdApp(Integer idApp) {
        this.idApp = idApp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public DefaultMenuModel getModel() {
        return model;
    }

    public void setModel(DefaultMenuModel model) {
        this.model = model;
    }

    public Integer getModuloPer() {
        return idPerfilUsuario;
    }

    public void setModuloPer(Integer moduloPer) {
        this.idPerfilUsuario = moduloPer;
    }

    public List<Modulo> getLstModulo() {
        return lstModulo;
    }

    public void setLstModulo(List<Modulo> lstModulo) {
        this.lstModulo = lstModulo;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public String obtenerMenu() {
        lstOpcMenu = manttoService.listOpcMenuMod(Integer.parseInt(idModulo), idPerfilUsuario);
        model = manttoService.crearArbolMenu(lstOpcMenu);

        return "menu.xhtml?faces-redirect=true";

    }

    public void logout() {
        JsfUtil.eliminarSesion();
    }

}
