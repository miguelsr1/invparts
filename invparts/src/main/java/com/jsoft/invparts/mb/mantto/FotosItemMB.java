/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@ViewScoped
public class FotosItemMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    private String codigoItem;
    private List<String> imagenesDeProducto;

    private UploadedFile file;

    public FotosItemMB() {
    }

    @PostConstruct
    public void init() {
        imagenesDeProducto = new ArrayList();
        File folderImg = new File(RESOURCE_BUNDLE.getString("pathimagenesitem") + codigoItem);
        if (folderImg.exists()) {
            for (File listFile : folderImg.listFiles()) {
                imagenesDeProducto.add(codigoItem + File.separator + listFile.getName());
            }
        }
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
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
        try {
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            File folderImg = new File(RESOURCE_BUNDLE.getString("path_imagenes_item") + codigoItem);
            if (!folderImg.exists()) {
                folderImg.mkdir();
            }
            File img = new File(folderImg.getAbsolutePath() + File.separator + event.getFile().getFileName());
            if (!img.exists()) {
                img.createNewFile();
            }
            event.getFile().write(img.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(FotosItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
