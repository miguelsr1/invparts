/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto.dialog;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;
import org.primefaces.event.NodeSelectEvent;
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
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    private String nombreCategoriaPadre = "";

    private Categoria categoria = new Categoria();
    private List<Categoria> lstCategorias = new ArrayList();
    private List<SelectItem> lstCategoria = new ArrayList();

    private TreeNode rootCategoria, nodoCategoriaPadre;

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

    public TreeNode getRootCategoria() {
        return rootCategoria;
    }

    public void setRootCategoria(TreeNode rootCategoria) {
        this.rootCategoria = rootCategoria;
    }

    public TreeNode getNodoCategoriaPadre() {
        return nodoCategoriaPadre;
    }

    public void setNodoCategoriaPadre(TreeNode nodoCategoriaPadre) {
        if (nodoCategoriaPadre != null) {
            this.nodoCategoriaPadre = nodoCategoriaPadre;
        }
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

    public String getNombreCategoriaPadre() {
        return nombreCategoriaPadre;
    }

    public void setNombreCategoriaPadre(String nombreCategoriaPadre) {
        this.nombreCategoriaPadre = nombreCategoriaPadre;
    }

    public void crearNuevaCategoria() {
        categoria = new Categoria();
    }

    public void guardarCategoria() {
        if (nodoCategoriaPadre != null && !nodoCategoriaPadre.getData().equals("Root") && ((Categoria) nodoCategoriaPadre.getData()).getIdCategoria() != null) {
            categoria.setPadreIdCategoria(((Categoria) nodoCategoriaPadre.getData()).getIdCategoria());
        }

        categoria.setIdCategoria(manttoService.guardarConIdAutogenerado(categoria));
        if (nodoCategoriaPadre == null) {
            rootCategoria.getChildren().add(new DefaultTreeNode(categoria, rootCategoria));
        } else {
            nodoCategoriaPadre.getChildren().add(new DefaultTreeNode(categoria, nodoCategoriaPadre));
        }

        categoria = new Categoria();
        nodoCategoriaPadre = new DefaultTreeNode();
        nombreCategoriaPadre = "";
    }

    public void limpiarForm() {
        nodoCategoriaPadre = null;
        nombreCategoriaPadre = "";
    }

    public void onCategoriaSelect(NodeSelectEvent event) {
        nodoCategoriaPadre = event.getTreeNode();
        nombreCategoriaPadre = ((Categoria) nodoCategoriaPadre.getData()).getNombreCategoria();
    }

    public void dlgCerrar() {
        PrimeFaces.current().dialog().closeDynamic(this);
    }

    public void eliminarCategoria() {
        /**
         * Considerar el caso de que la categoria tiene asociaciones a productos
         * existentes.
         */
        if (nodoCategoriaPadre != null) {
            if (manttoService.removerCategoria(((Categoria) nodoCategoriaPadre.getData()).getIdCategoria())) {
                JsfUtil.addSuccessMessage(RESOURCE_BUNDLE.getString("datoEliminadoOk"));
            } else {
                JsfUtil.addErrorMessage(RESOURCE_BUNDLE.getString("datoEliminadoFail"));
            }
        }
    }
}
