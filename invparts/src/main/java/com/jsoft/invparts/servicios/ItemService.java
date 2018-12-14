/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.model.inventario.Producto;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public interface ItemService {

    public List<Producto> getLstProducto(Integer idEmp);
}
