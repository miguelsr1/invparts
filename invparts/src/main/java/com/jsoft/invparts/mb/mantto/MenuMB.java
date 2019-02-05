/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.OpcionMenu;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
  
    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    private static final long serialVersionUID = 1L;

    public MenuMB() {
    }

    
     @PostConstruct
    public void init() {

  /*    Usuario p = (Usuario) JsfUtil.getVariableSession("USU_SESSION");
      idModulo =  JsfUtil.getRequestParameter("idApp");
      
      lstOpcMenu = manttoService.listOpcMenuMod(Integer.parseInt(idModulo));*/
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

    
  
}
