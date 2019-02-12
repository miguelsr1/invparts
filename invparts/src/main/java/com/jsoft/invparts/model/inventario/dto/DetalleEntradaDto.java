/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario.dto;

import com.jsoft.invparts.model.inventario.DetalleEntrada;
import java.io.Serializable;

/**
 *
 * @author DesarrolloPc
 */
public class DetalleEntradaDto extends DetalleEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreProducto;

    public DetalleEntradaDto() {
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

}
