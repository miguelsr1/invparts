/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto.dialog;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.servicios.ManttoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class DlgCategoriaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Categoria categoria = new Categoria();
    private Categoria categoriaSelected = new Categoria();
    private List<Categoria> lstCategorias = new ArrayList();
    private List<SelectItem> lstCategoria = new ArrayList();

    private TreeNode rootCategoria, selectedNode;
    ;

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    public DlgCategoriaMB() {
    }

    @PostConstruct
    public void init() {
        rootCategoria = new DefaultTreeNode(new Categoria("Root"), null);

        lstCategorias = manttoService.lstCategoria(null);
        for (Categoria cat : lstCategorias) {
            TreeNode hijo = new DefaultTreeNode(cat, rootCategoria);
            hijo = crearListadoDeCategoria(hijo, cat);
            rootCategoria.getChildren().add(hijo);
        }
    }

    private TreeNode crearListadoDeCategoria(TreeNode padre, Categoria cat) {
        List<Categoria> lst = manttoService.lstSubCategoria(cat);
        for (Categoria subCat : lst) {
            TreeNode hijo = new DefaultTreeNode(subCat, padre);
            hijo = crearListadoDeCategoria(hijo, subCat);
            padre.getChildren().add(hijo);
        }
        return padre;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoriaSelected() {
        return categoriaSelected;
    }

    public void setCategoriaSelected(Categoria categoriaSelected) {
        this.categoriaSelected = categoriaSelected;
    }

    public TreeNode getRootCategoria() {
        return rootCategoria;
    }

    public void setRootCategoria(TreeNode rootCategoria) {
        this.rootCategoria = rootCategoria;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public List<Categoria> getLstCategorias() {
        return lstCategorias;
    }

    public void setLstCategorias(List<Categoria> lstCategorias) {
        this.lstCategorias = lstCategorias;
    }

    public List<SelectItem> getLstCategoria() {
        return lstCategoria;
    }

    public void setLstCategoria(List<SelectItem> lstCategoria) {
        this.lstCategoria = lstCategoria;
    }

    public void crearNuevaCategoria(){
        categoria = new Categoria();
    }
    
    public void guardarCategoria() {
        if (selectedNode != null && ((Categoria) selectedNode.getData()).getIdCategoria() != null) {
            categoria.setPadreIdCategoria(((Categoria) selectedNode.getData()).getIdCategoria());
        }

        categoria.setIdCategoria(manttoService.guardarConIdAutogenerado(categoria));

        selectedNode.getChildren().add(new DefaultTreeNode(categoria, selectedNode));
        /*if (categoria.getIdCategoria() > 0) {
            lstCategorias.add(categoria);
            lstCategoria.add(new SelectItem(categoria, categoria.getNombreCategoria()));
            categoria = new Categoria();
        }*/
    }
}
