/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.dao.ManttoDaoImpl;
import com.jsoft.invparts.model.seguridad.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class UserMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean isUserExist = false;
    private Usuario user;
    private List<Usuario> lstUsers = new ArrayList();

    @Autowired
    private ManttoDaoImpl mantenimientosDao;

    public UserMB() {
    }

    //<editor-fold desc="Metodos getters y setters">
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        if (user != null) {
            this.user = user;
        }
    }

    public List<Usuario> getLstUsers() {
        return lstUsers;
    }

    public void setLstUsers(List<Usuario> lstUsers) {
        this.lstUsers = lstUsers;
    }

    public void setMantenimientosDao(ManttoDaoImpl mantenimientosDao) {
        this.mantenimientosDao = mantenimientosDao;
    }

    public Boolean getIsUserExist() {
        return isUserExist;
    }

    public void setIsUserExist(Boolean isUserExist) {
        this.isUserExist = isUserExist;
    }
    //</editor-fold>

    public void guardar() {
        mantenimientosDao.guardarConIdAutogenerado(user);
    }

    public void findUserbyUserName() {
        isUserExist = mantenimientosDao.getUsuarioByUsu(user.getUsuario());
    }
}
