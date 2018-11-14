/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.seguridad.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.jsoft.invparts.servicios.ManttoService;
import javax.annotation.PostConstruct;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class CompanyMB {

    private Empresa emp = new Empresa();
    private List<Empresa> lstEmpresa = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public CompanyMB() {
    }
    
    @PostConstruct
    public void init() {
        lstEmpresa = manttoService.listEmpresa();
    }

    //<editor-fold >
    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        if (emp != null) {
            this.emp = emp;
        }
    }

    public List<Empresa> getLstEmpresa() {
        return lstEmpresa;
    }

    public void setLstEmpresa(List<Empresa> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }
    
    //</editor-fold >

    public void guardar() {
        if (manttoService.guardar(emp) == 1) {
            lstEmpresa = manttoService.listEmpresa();
            emp = new Empresa();
        } else {
        }
    }
}
