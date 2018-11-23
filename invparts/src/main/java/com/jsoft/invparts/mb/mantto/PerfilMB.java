/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;


import com.jsoft.invparts.model.seguridad.Perfil;
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
public class PerfilMB implements Serializable{

      private static final long serialVersionUID = 1L;

    private Perfil per = new Perfil();
    private List<Perfil> lstPerfil = new ArrayList();
   
     @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    /**
     * Creates a new instance of PerfilMB
     */
    public PerfilMB() {
    }
     @PostConstruct
    public void init() {
        lstPerfil = manttoService.listPerfil(null);
        
    }

     public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }
    public Perfil getPer() {
        return per;
    }

    public void setPer(Perfil per) {
        this.per = per;
    }

    public List<Perfil> getLstPerfil() {
        return lstPerfil;
    }

    public void setLstPerfil(List<Perfil> lstPerfil) {
        this.lstPerfil = lstPerfil;
    }
    
     public void buscarPerfil() {
        lstPerfil = manttoService.listPerfil(per);
    }
    
      public void guardar() {
        if (per.getNombrePerfil()!= null && !per.getNombrePerfil().isEmpty()) {
            if (manttoService.guardarConIdAutogenerado(per) == 1) {
                lstPerfil = manttoService.listPerfil(null);
                per = new Perfil();
            } 
        } else {
            JsfUtil.addWarningMessage("Debe de completar todos los registros");
        }
    }
    
      public void limpiar(){
          per = new Perfil();
      }
}
