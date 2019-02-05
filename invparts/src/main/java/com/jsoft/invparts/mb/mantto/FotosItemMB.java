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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
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

    private Boolean fotoPrincipal = true;
    private Item item;

    private File folderImg = null;
    private UploadedFile file;

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    public FotosItemMB() {
    }

    public void limpiar() {
        //item = new Item();
        fotoPrincipal = true;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        if (item != null) {
            this.item = item;
            folderImg = new File(RESOURCE_BUNDLE.getString("pathimagenesitem") + item.getCodigoProducto());
        }
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
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

            if (fotoPrincipal) {
                imgPrincipal();
            }
        } catch (Exception ex) {
            Logger.getLogger(FotosItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void imgPrincipal() {
        if (file != null) {
            item.setUrlImagen(file.getFileName());
            itemService.guardar(item);
            fotoPrincipal = false;
        }
    }

    public void cerrar() {
        PrimeFaces.current().dialog().closeDynamic("/");
    }
}
