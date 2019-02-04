/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.servicios.ItemService;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@SessionScoped
public class FotosItemMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    private Item item;
    private List<String> imagenesDeProducto = new ArrayList();

    private File folderImg = null;
    private UploadedFile file;

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    public FotosItemMB() {
    }

    public void limpiar() {
        imagenesDeProducto.clear();
        item = new Item();
    }

    public void onClose(SelectEvent event) {
        cargarFotos();
    }

    public void cargarFotos() {
        imagenesDeProducto.clear();
        item = ((ItemMB) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "itemMB")).getItem();

        if (item != null && item.getIdItem() != null) {
            folderImg = new File(RESOURCE_BUNDLE.getString("pathimagenesitem") + item.getCodigoProducto());
            if (folderImg.exists()) {
                for (File listFile : folderImg.listFiles()) {
                    imagenesDeProducto.add(listFile.getName());
                }
            }
        }
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public String getCodigoItem() {
        if (item.getIdItem() == null) {
            return "";
        } else {
            return item.getCodigoProducto();
        }
    }

    public void setCodigoItem(String codigoItem) {
        if (item == null) {
            item = new Item();
        }
        item = itemService.getItemByCod(codigoItem);
        if (item != null) {
            cargarFotos();
        }
    }

    public List<String> getImagenesDeProducto() {
        return imagenesDeProducto;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        crearArchivo();
    }

    private void crearArchivo() {
        try {
            if (!folderImg.exists()) {
                folderImg.mkdir();
            }
            File img = new File(folderImg.getAbsolutePath() + File.separator + file.getFileName());
            file.write(img.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(FotosItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imgPrincipal() {
        if (file != null) {
            item.setUrlImagen(file.getFileName());
            itemService.guardar(item);

            crearArchivo();
        }
    }

    public void cerrar() {
        PrimeFaces.current().dialog().closeDynamic("/");
    }
}
