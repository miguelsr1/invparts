/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario.dto;

/**
 *
 * @author DesarrolloPc
 */
public class CompatibilidadDto {
    private Integer idCompatibilidad;
    private Integer idItem;
    private Integer idModelo;
    private String nombreModelo;
    private Boolean compatibilidadPrimaria;

    public CompatibilidadDto() {
    }

    public Integer getIdCompatibilidad() {
        return idCompatibilidad;
    }

    public void setIdCompatibilidad(Integer idCompatibilidad) {
        this.idCompatibilidad = idCompatibilidad;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Boolean getCompatibilidadPrimaria() {
        return compatibilidadPrimaria;
    }

    public void setCompatibilidadPrimaria(Boolean compatibilidadPrimaria) {
        this.compatibilidadPrimaria = compatibilidadPrimaria;
    }
    
}
