/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Ubicacion;
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
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class LocationMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Ubicacion ubicacion = new Ubicacion();
    private List<Ubicacion> lstUbicaciones = new ArrayList();

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public LocationMB() {
    }

    @PostConstruct
    public void init() {
        lstUbicaciones = manttoService.getLstUbicaciones();
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Ubicacion> getLstUbicaciones() {
        return lstUbicaciones;
    }

    public void setLstUbicaciones(List<Ubicacion> lstUbicaciones) {
        this.lstUbicaciones = lstUbicaciones;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    //=====================================================================
    public void guardar() {
        manttoService.guardarConIdAutogenerado(ubicacion);
        if (ubicacion.getIdUbicacion() != null) {
            JsfUtil.addSuccessMessage("Información registrada");
            lstUbicaciones.add(ubicacion);
            ubicacion = new Ubicacion();
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error en esta operación");
        }
    }

    public void nuevo() {
        ubicacion = new Ubicacion();
    }

    public void eliminar() {
        if (manttoService.eliminar("ubicacion", ubicacion.getIdUbicacion())) {
            lstUbicaciones = manttoService.getLstUbicaciones();
            JsfUtil.addSuccessMessage("Registro eliminado");
        } else {
            JsfUtil.addErrorMessage("Ocurrio un error en esta operación");
        }
    }
}
