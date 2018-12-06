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
import com.jsoft.invparts.util.JsfUtil;

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
    private String pass1;
    private String pass2;

    private Persona per = new Persona();
    private Usuario usu = new Usuario();

    private List<Persona> lstPersonas = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public PersonaMB() {
    }

    @PostConstruct
    public void init() {
        lstPersonas = manttoService.listPersona(null);
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

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    //</editor-fold>
    public void guardar() {
        if (per.getCorreoElectronico() != null && !per.getCorreoElectronico().isEmpty()) {
            if (manttoService.guardarConIdAutogenerado(per) == 1) {
                lstPersonas = manttoService.listPersona(null);
                per = new Persona();
            } else {
            }
        } else {
            JsfUtil.addWarningMessage("Debe de completar todos los registros");
        }
    }

    public void guardarNewUsuario() {
        int valor = manttoService.guardarConIdString(per, true);
        if (valor != -1) {
            per.setIdPersona(valor);
            usu.setCambiarClave((short) 0);
            usu.setClaveAcceso(manttoService.encriptar(pass1));
            usu.setFechaCaducidad(null);
            usu.setIdPersona(per.getIdPersona());
            usu.setIntentosFallidos((short) 0); 
            
            usu.setUsuarioActivo((short) 0);
            usu.setCodigoActivacion(manttoService.encriptar(per.getCorreoElectronico().concat(per.getPrimerNombre()).concat(per.getSegundoNombre())));

            if (manttoService.guardarConIdString(usu, true) == 1) {
                manttoService.enviarCorreoActivacionUsuario(per, usu.getCodigoActivacion());
            } else {
                //error al guardar el usuario
            }
        }
    }

    public void buscarPersona() {
        lstPersonas = manttoService.listPersona(per);
    }

    public void buscarCorreoElectronico() {
        existeCorreoElectronico = manttoService.isExistEmailPerByEmail(per.getCorreoElectronico());
    }

    private boolean validacionDeFormulario() {
        return false;
    }
}
