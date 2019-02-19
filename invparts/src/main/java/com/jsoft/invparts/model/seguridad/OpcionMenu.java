/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.seguridad;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.UtilWhere;
import java.io.Serializable;

/**
 *
 * @author DesarrolloPc
 */
public class OpcionMenu extends UtilWhere implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    public Integer idOpcionMenu;
    public String nombreOpcion;
    public String iconoOpcion;
    public String urlOpcion;
    public Integer opcionActiva;
    public Integer ordenOpcion;
    public Integer idModulo;
    public Integer opcIdOpcionMenu;
    
    
    
    public OpcionMenu() {
    }

    public Integer getIdOpcionMenu() {
        return idOpcionMenu;
    }

    public void setIdOpcionMenu(Integer idOpcionMenu) {
        this.idOpcionMenu = idOpcionMenu;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public String getIconoOpcion() {
        return iconoOpcion;
    }

    public void setIconoOpcion(String iconoOpcion) {
        this.iconoOpcion = iconoOpcion;
    }

    public String getUrlOpcion() {
        return urlOpcion;
    }

    public void setUrlOpcion(String urlOpcion) {
        this.urlOpcion = urlOpcion;
    }

    public Integer getOrdenOpcion() {
        return ordenOpcion;
    }
    public void setOrdenOpcion(Integer ordenOpcion) {
        this.ordenOpcion = ordenOpcion;
    }

    public Integer getOpcionActiva() {
        return opcionActiva;
    }

    public void setOpcionActiva(Integer opcionActiva) {
        this.opcionActiva = opcionActiva;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Integer getOpcIdOpcionMenu() {
        return opcIdOpcionMenu;
    }

    public void setOpcIdOpcionMenu(Integer opcionIdOpcionMenu) {
        this.opcIdOpcionMenu = opcionIdOpcionMenu;
    }

    @Override
    public String toString() {
        return  idOpcionMenu + "-" + nombreOpcion;
    }

        
    @Override
    public String generarInsertSQL() {
        return "INSERT INTO Opcion_Menu(nombre_opcion,icono_opcion,url_opcion,opcion_activa,orden_opcion,id_modulo,opc_id_opcion_menu) values(?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{nombreOpcion,iconoOpcion,urlOpcion,opcionActiva,ordenOpcion,idModulo,opcIdOpcionMenu};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE opcion_menu set nombre_opcion=?,icono_opcion=?,url_opcion=?,opcion_activa=?,orden_opcion=?,id_modulo=?,opc_id_opcion_menu=? WHERE id_opcion_menu=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{nombreOpcion,iconoOpcion,urlOpcion,opcionActiva,ordenOpcion,idModulo,opcIdOpcionMenu,idOpcionMenu};
    }

    
    @Override
    public Boolean esNuevoRegistro() {
        return idOpcionMenu == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idOpcionMenu = id;
    }
}
