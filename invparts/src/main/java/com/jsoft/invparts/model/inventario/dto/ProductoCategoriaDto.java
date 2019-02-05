/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario.dto;

import com.jsoft.invparts.model.inventario.Categoria;
import java.io.Serializable;

/**
 *
 * @author DesarrolloPc
 */
public class ProductoCategoriaDto extends Categoria implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer idProductoCategoria;
    private Integer idItem;

    public ProductoCategoriaDto() {
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdProductoCategoria() {
        return idProductoCategoria;
    }

    public void setIdProductoCategoria(Integer idProductoCategoria) {
        this.idProductoCategoria = idProductoCategoria;
    }
}
