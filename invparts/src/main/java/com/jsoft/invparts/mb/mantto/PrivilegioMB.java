/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.ModuloPerfil;
import com.jsoft.invparts.model.seguridad.OpcionMenu;
import com.jsoft.invparts.model.seguridad.Privilegio;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author torre
 */
@ManagedBean
@ViewScoped
public class PrivilegioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Privilegio pr = new Privilegio();
    private List<Privilegio> lstPrivilegio = new ArrayList();
    private List<ModuloPerfil> lstModPer = new ArrayList();
    private List<OpcionMenu> lstOpcMen = new ArrayList();
    private String nombreModulo;
    private String nombreOpcion;

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    /**
     * Creates a new instance of PerfilMB
     */
    public PrivilegioMB() {
    }

    @PostConstruct
    public void init() {
        lstPrivilegio = manttoService.listPrivilegio(null);

    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public Privilegio getPr() {
        return pr;
    }

    public void setPr(Privilegio pr) {
            if (pr != null) {
            this.pr = pr;
        } 
    }

    public List<Privilegio> getLstPrivilegio() {
        return lstPrivilegio;
    }

    public void setLstPrivilegio(List<Privilegio> lstPrivilegio) {
        this.lstPrivilegio = lstPrivilegio;
    }

    public List<ModuloPerfil> getLstModPer() {
        return lstModPer;
    }

    public List<OpcionMenu> getLstOpcMen() {
        return lstOpcMen;
    }

    public void setLstOpcMen(List<OpcionMenu> lstOpcMen) {
        this.lstOpcMen = lstOpcMen;
    }

    
    
    public void setLstModPer(List<ModuloPerfil> lstModPer) {
        this.lstModPer = lstModPer;
    }

    public void obtenerNomModulo(Integer idModulo){
        nombreModulo = manttoService.nombreModulo(idModulo);
    }
  
    public void obtenerNomOpcion(Integer idOpcion){
        nombreOpcion = manttoService.nombreOpcion(idOpcion);
    }
    
    
    public void buscarPrivilegio() {
        lstPrivilegio = manttoService.listPrivilegio(pr);
    }

    public void guardar() {
        if (pr.getIdModuloPerfil()!= null && pr.getIdModuloPerfil()!=0) {
            int valor = manttoService.guardarConIdAutogenerado(pr);
            if (valor > 0) {
                pr.setIdPrivilegio(valor);
                lstPrivilegio.add(pr);
                pr = new Privilegio();
            }
        } else {
            JsfUtil.addWarningMessage("Debe de completar todos los registros");
        }
    }

    public void limpiar() {
        pr = new Privilegio();
    }
}
