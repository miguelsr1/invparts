/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;


import com.jsoft.invparts.model.seguridad.Modulo;
import com.jsoft.invparts.model.seguridad.OpcionMenu;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class OpcionMenuMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private OpcionMenu opc = new OpcionMenu();
    private Modulo mod = new Modulo();
    private List<OpcionMenu> lstOpciones = new ArrayList();
    private List<OpcionMenu> lstOpcSup = new ArrayList();
    private List<Modulo> lstModulos = new ArrayList();
    
    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public OpcionMenuMB() {
    }

    @PostConstruct
    public void init() {
        lstOpciones = manttoService.listOpcionesMenu(opc);
        lstOpcSup = manttoService.listOpcionesMenu(null);
        lstModulos = manttoService.listModulo(mod);
    }

    
    public OpcionMenu getOpc() {
        return opc;
    }

    public void setOpc(OpcionMenu opc) {
          if (opc != null) {
            this.opc = opc;
          }
    }

    public List<OpcionMenu> getLstOpcSup() {
        return lstOpcSup;
    }

    public void setLstOpcSup(List<OpcionMenu> lstOpcSup) {
        this.lstOpcSup = lstOpcSup;
    }

    
    public List<OpcionMenu> getLstOpciones() {
        return lstOpciones;
    }

    public void setLstOpciones(List<OpcionMenu> lstOpciones) {
        this.lstOpciones = lstOpciones;
    }

    public List<Modulo> getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(List<Modulo> lstModulos) {
        this.lstModulos = lstModulos;
    }

    
    public Modulo getMod() {
        return mod;
    }

    public void setMod(Modulo mod) {
        this.mod = mod;
    }

    
    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

     public void buscarOpcion() {
        lstOpciones = manttoService.listOpcionesMenu(opc);
    }
    
    //</editor-fold >
    public void guardarOpcion() {
        if (opc.getNombreOpcion()!= null && !opc.getNombreOpcion().isEmpty()) {
            if (manttoService.guardarConIdAutogenerado(opc) == 1) {
                lstOpciones = manttoService.listOpcionesMenu(opc);
                opc = new OpcionMenu();
            }
        } else {
            JsfUtil.addWarningMessage("Debe de completar todos los registros");
        }
    }

 public String nombreOpcion(Integer id) {
        return manttoService.nombreOpcion(id);

    }

 public String nombreModulo(Integer id) {
        return manttoService.nombreModulo(id);

    }
    public void limpiarOpc() {
        opc = new OpcionMenu();
    }
    
   
}
