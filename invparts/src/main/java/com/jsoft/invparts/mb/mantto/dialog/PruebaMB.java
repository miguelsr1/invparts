/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto.dialog;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.dto.ProductoCategoriaDto;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class PruebaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;
    
    private Categoria categoria = new Categoria();
    private List<Categoria> lstCategoriasPorProductoDto = new ArrayList();

    public PruebaMB() {
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public List<Categoria> getLstCategoriasPorProductoDto() {
        return lstCategoriasPorProductoDto;
    }

    public void setLstCategoriasPorProductoDto(List<Categoria> lstCategoriasPorProductoDto) {
        this.lstCategoriasPorProductoDto = lstCategoriasPorProductoDto;
    }

    public List<ProductoCategoriaDto> completeCategoriaContains(String query) {
        List<ProductoCategoriaDto> lstCategoria = new ArrayList<>();

        return lstCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void guardar() {
        for (Categoria productoCategoriaDto : lstCategoriasPorProductoDto) {
            System.out.println(productoCategoriaDto);

        }
    }
    
    public void onItemSelect(SelectEvent event) {
        event.getObject();
        System.out.println("ok");
    }
}
