/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;


import com.jsoft.invparts.model.seguridad.Modulo;
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
public class ModuloMB implements Serializable{

      private static final long serialVersionUID = 1L;

    private Modulo modu = new Modulo();
    private List<Modulo> lstModulo = new ArrayList();
   
     @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    /**
     * Creates a new instance of PerfilMB
     */
    public ModuloMB() {
    }
     @PostConstruct
    public void init() {
        lstModulo = manttoService.listModulo(null);
        
    }

    public Modulo getModu() {
        return modu;
    }

    public void setModu(Modulo modu) {
       if (modu != null) {
            this.modu = modu;
        }
     }

    public List<Modulo> getLstModulo() {
        return lstModulo;
    }

    public void setLstModulo(List<Modulo> lstModulo) {
        this.lstModulo = lstModulo;
    }

     public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }
   
    
      public void guardar() {
        if (modu.getNombreModulo()!= null && !modu.getNombreModulo().isEmpty()) {
            if (manttoService.guardarConIdAutogenerado(modu) == 1) {
                lstModulo = manttoService.listModulo(null);
                modu = new Modulo();
            } else {
            }
        } else {
            JsfUtil.addWarningMessage("Debe de completar todos los registros");
        }
    }
     
          public void buscarModulo() {
        lstModulo = manttoService.listModulo(modu);
    }
    public void limpiar(){
        modu = new Modulo();
    }
    
}
