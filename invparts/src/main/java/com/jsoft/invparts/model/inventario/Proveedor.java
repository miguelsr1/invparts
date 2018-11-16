/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario;

import com.jsoft.invparts.dao.PersistenciaDao;
import java.io.Serializable;

/**
 *
 * @author misanchez
 */
public class Proveedor implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;
    
    public Integer idProveedor;
    public String nombreProveedor;
    public String telefonoProveedor;
    public String correoElectronico;
    
}
