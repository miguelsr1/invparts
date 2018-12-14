/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servicios;

import com.jsoft.invparts.dao.ItemDao;
import com.jsoft.invparts.model.inventario.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("itemService")
@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao dao;

    @Override
    public List<Producto> getLstProducto(Integer idEmp) {
        return dao.getLstProducto(idEmp);
    }

}
