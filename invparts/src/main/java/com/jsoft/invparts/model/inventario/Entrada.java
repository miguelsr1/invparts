/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.model.inventario;

import com.jsoft.invparts.dao.PersistenciaDao;
import com.jsoft.invparts.model.inventario.dto.DetalleEntradaDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DesarrolloPc
 */
public class Entrada implements Serializable, PersistenciaDao {

    private static final long serialVersionUID = 1L;

    private Integer idEntrada;
    private Integer idEmpresa;
    private Date fechaEntrada;

    private List<DetalleEntradaDto> lstDetalleEntrada = new ArrayList();

    public Entrada() {
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public String generarInsertSQL() {
        return "INSERT INTO entrada (id_empresa, fecha_entrada) VALUES (?,?)";
    }

    @Override
    public Object[] getDatosInsert() {
        return new Object[]{idEmpresa, fechaEntrada};
    }

    @Override
    public String generarUpdateSQL() {
        return "UPDATE entrada SET id_empresa=?, fecha_entrada=? WHERE id_entrada=?";
    }

    @Override
    public Object[] getDatosUpdate() {
        return new Object[]{idEmpresa, fechaEntrada, idEntrada};
    }

    @Override
    public Boolean esNuevoRegistro() {
        return idEntrada == null;
    }

    @Override
    public void setIdGenerado(Integer id) {
        idEntrada = id;
    }

    public List<DetalleEntradaDto> getLstDetalleEntrada() {
        return lstDetalleEntrada;
    }

    public void setLstDetalleEntrada(List<DetalleEntradaDto> lstDetalleEntrada) {
        this.lstDetalleEntrada = lstDetalleEntrada;
    }

}
