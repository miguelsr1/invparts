/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.Persona;
import com.jsoft.invparts.model.seguridad.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.jsoft.invparts.servicios.ManttoService;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class PersonaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Boolean existeUsuario = false;
    private Boolean existeCorreoElectronico = false;
    private Persona per = new Persona();
    private Usuario usu = new Usuario();
    private List<Persona> lstPersonas = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public PersonaMB() {
    }

    @PostConstruct
    public void init() {
        lstPersonas = manttoService.listPersona();
    }

    //<editor-fold desc="Metodos getters y setters">
    public Persona getPer() {
        return per;
    }

    public void setPer(Persona per) {
        if (per != null) {
            this.per = per;
        }
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        if (usu != null) {
            this.usu = usu;
        }
    }

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }
    
    public Boolean getExisteUsuario() {
        return existeUsuario;
    }

    public void setExisteUsuario(Boolean existeUsuario) {
        this.existeUsuario = existeUsuario;
    }

    public Boolean getExisteCorreoElectronico() {
        return existeCorreoElectronico;
    }

    public void setExisteCorreoElectronico(Boolean existeCorreoElectronico) {
        this.existeCorreoElectronico = existeCorreoElectronico;
    }

    //</editor-fold>
    public void guardar() {
        if (manttoService.guardar(per) == 1) {
            lstPersonas = manttoService.listPersona();
            per = new Persona();
        } else {
        }
    }
    
    public void buscarCorreoElectronico(){
        existeCorreoElectronico = manttoService.isExistEmailPerByEmail(per.getCorreoElectronico());
    }
}
