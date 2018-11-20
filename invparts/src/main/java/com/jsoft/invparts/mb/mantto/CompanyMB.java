/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Sucursal;
import com.jsoft.invparts.model.seguridad.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class CompanyMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Empresa emp = new Empresa();
    private Sucursal suc = new Sucursal();
    private List<Empresa> lstEmpresa = new ArrayList();
    private List<Empresa> lstProveedor = new ArrayList();
    private List<Sucursal> lstSucursal = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public CompanyMB() {
    }

    //<editor-fold desc="Metodos getters y setters">
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

    public List<Empresa> getLstProveedor() {
        return lstProveedor;
    }

    public void setLstProveedor(List<Empresa> lstProveedor) {
        this.lstProveedor = lstProveedor;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public Sucursal getSuc() {
        return suc;
    }

    public void setSuc(Sucursal suc) {
        this.suc = suc;
    }

    //</editor-fold >
    public void guardar() {
        if (manttoService.guardarConIdAutogenerado(emp) == 1) {
            lstEmpresa = manttoService.listEmpresa((short) 4);
            emp = new Empresa();
        }
    }

    public void guardarSuc() {
        if (manttoService.guardarConIdAutogenerado(suc) == 1) {
            lstSucursal = manttoService.listSucursal();
            suc = new Sucursal();
        }
    }

    public void guardarProveedor() {
        if (manttoService.guardarConIdAutogenerado(emp) == 1) {
            lstProveedor = manttoService.listEmpresa((short) 1);
            emp = new Empresa();
        }
    }
}
