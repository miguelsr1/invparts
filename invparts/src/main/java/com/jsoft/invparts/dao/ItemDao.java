/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.dao;

import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Marca;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.Producto;
import java.util.List;

/**
 *
 * @author misanchez
 */
public interface ItemDao {

    public List<Producto> getLstProducto(Integer idEmp);

    public Item getItemByPk(Integer idItem);
    
    public void guardar(Item item);
    
    public List<Marca> getLstMarca();
    
    public List<Modelo> getLstModeloByIdMarca(Integer idMarca);
}
