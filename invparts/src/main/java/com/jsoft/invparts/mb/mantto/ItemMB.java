/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.mb.mantto;

import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.model.inventario.Compatibilidad;
import com.jsoft.invparts.model.inventario.Estante;
import com.jsoft.invparts.model.inventario.InformacionItem;
import com.jsoft.invparts.model.inventario.Item;
import com.jsoft.invparts.model.inventario.Modelo;
import com.jsoft.invparts.model.inventario.ProductoCategoria;
import com.jsoft.invparts.model.inventario.dto.CompatibilidadDto;
import com.jsoft.invparts.model.inventario.dto.ProductoCategoriaDto;
import com.jsoft.invparts.servicios.ItemService;
import com.jsoft.invparts.servicios.ManttoService;
import com.jsoft.invparts.util.JsfUtil;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class ItemMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("etiquetas");

    @ManagedProperty("#{itemService}")
    private ItemService itemService;

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    private Integer idModelo = 0;
    private String nombreImagen;
    private List<String> imagenesDeProducto = new ArrayList();

    private Item item = new Item();
    private InformacionItem infoItem = new InformacionItem();
    private Modelo modelo = new Modelo();
    private List<InformacionItem> lstInfoItems = new ArrayList();
    private List<Compatibilidad> lstCompatibilidad = new ArrayList();
    private List<CompatibilidadDto> lstCompatibilidadDtos = new ArrayList();
    private List<Categoria> lstCategorias = new ArrayList();

    private File folderImg = null;
    private UploadedFile fileUpd;

    public ItemMB() {
    }

    @PostConstruct
    public void init() {
        item = itemService.getItemByPk(5);
        //item = new Item();
        lstInfoItems = itemService.getLstInformacionItemByIdItem(item.getIdItem());

        lstCompatibilidadDtos = itemService.getLstCompatibilidadByItem(item.getIdItem());
        
        lstCategorias = itemService.getLstCategoriaByIdItem(item.getIdItem());
        //item = new Item();
        //lstProductos = itemService.getLstProducto(0);

        if (item.getIdItem() != null) {
            cargarFotosInit();
        }
    }

    public List<Categoria> getLstCategoriasPorProducto() {
        return lstCategorias;
    }

    public void setLstCategoriasPorProducto(List<Categoria> lstCategoriasPorProducto) {
        this.lstCategorias = lstCategoriasPorProducto;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public List<String> getImagenesDeProducto() {
        return imagenesDeProducto;
    }

    public void setImagenesDeProducto(List<String> imagenesDeProducto) {
        this.imagenesDeProducto = imagenesDeProducto;
    }

    public UploadedFile getFileUpd() {
        return fileUpd;
    }

    public void setFileUpd(UploadedFile fileUpd) {
        this.fileUpd = fileUpd;
    }

    public List<CompatibilidadDto> getLstCompatibilidadDtos() {
        return lstCompatibilidadDtos;
    }

    public void setLstCompatibilidadDtos(List<CompatibilidadDto> lstCompatibilidadDtos) {
        this.lstCompatibilidadDtos = lstCompatibilidadDtos;
    }

    public Integer getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Integer idModelo) {
        if (idModelo != null) {
            this.idModelo = idModelo;
        }
    }

    public Modelo getModelo() {
        return modelo;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public ManttoService getManttoService() {
        return manttoService;
    }

    public void setManttoService(ManttoService manttoService) {
        this.manttoService = manttoService;
    }

    public List<Estante> getLstEstantes() {
        return itemService.getLstEstantesByIdSucursal(1);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<InformacionItem> getLstInfoItems() {
        return lstInfoItems;
    }

    public void setLstInfoItems(List<InformacionItem> lstInfoItems) {
        this.lstInfoItems = lstInfoItems;
    }

    public InformacionItem getInfoItem() {
        return infoItem;
    }

    public void setInfoItem(InformacionItem infoItem) {
        this.infoItem = infoItem;
    }

    public void guardar() {
        Boolean existeCompatibilidaPrimaria = false;
        for (CompatibilidadDto compatibilidad : lstCompatibilidadDtos) {
            if (compatibilidad.getCompatibilidadPrimaria()) {
                existeCompatibilidaPrimaria = true;
                break;
            }
        }

        for (Categoria categoria : lstCategorias) {
            item.getLstCategorias().add(new ProductoCategoria(categoria.getIdCategoria()));
        }

        if (existeCompatibilidaPrimaria) {
            item.setLstInformacionItem(lstInfoItems);

            itemService.guardar(item);
            if (item.getIdItem() != null) {
                for (CompatibilidadDto compatibilidad : lstCompatibilidadDtos) {
                    compatibilidad.setIdItem(item.getIdItem());

                    itemService.guardarCompatibilidad(compatibilidad);
                }
            }

            JsfUtil.addSuccessMessage("Registros almacenados satisfactoriamente");
        } else {
            JsfUtil.addWarningMessage("Debe de seleccionar un compatibilidad primaria.");
        }
    }

    public void nuevo() {
        item = new Item();
    }

    public void addInfoItem() {
        getLstInfoItems().add(new InformacionItem());
    }

    public void showDialogFotografias() {
        if (item.getIdItem() != null) {
            PrimeFaces.current().executeScript("PF('dlgFotos').show()");
        } else {
            JsfUtil.addWarningMessage("Debe de guardar primero el item");
        }
    }

    public void showModelo() {
        Map<String, Object> options = new HashMap();
        options.put("modal", true);
        options.put("width", 400);
        options.put("height", 160);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");

        PrimeFaces.current().dialog().openDynamic("/app/mantto/dialog/addModelToItem", options, null);
    }

    public void showDialogImagenes() {
        if (item.getIdItem() != null) {
            Map<String, Object> options = new HashMap();
            options.put("modal", true);
            options.put("width", 650);
            options.put("height", 260);
            options.put("closable", false);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");

            PrimeFaces.current().dialog().openDynamic("/app/mantto/dialog/addFotography", options, null);
        } else {
            JsfUtil.addWarningMessage("Debe de guardar primero el item");
        }
    }

    public void onModeloSelect(SelectEvent evt) {
        if (evt.getObject() != null) {
            idModelo = (Integer) evt.getObject();
            if (lstCompatibilidad.isEmpty()) {
                Compatibilidad primaria = new Compatibilidad();
                primaria.setTipoCompatibilidad((short) 1);
                primaria.setIdModelo(idModelo);

                lstCompatibilidad.add(primaria);
            } else {
                for (Compatibilidad compatibilidad : lstCompatibilidad) {
                    if (compatibilidad.getTipoCompatibilidad() == 1) {
                        compatibilidad.setIdModelo(idModelo);
                    }
                }
            }

            modelo = itemService.getModeloByPk(idModelo);
        }
    }

    public void addCompatibilidad(SelectEvent evt) {
        if (evt.getObject() != null) {
            Boolean noEsta = true;
            modelo = itemService.getModeloByPk((Integer) evt.getObject());

            for (CompatibilidadDto compatibilidad : lstCompatibilidadDtos) {
                if (compatibilidad.getIdModelo().equals((Integer) evt.getObject())) {
                    noEsta = false;
                    break;
                }
            }

            if (noEsta) {
                CompatibilidadDto compatibilidadDto = new CompatibilidadDto();

                compatibilidadDto.setIdItem(item.getIdItem());
                compatibilidadDto.setIdModelo(modelo.getIdModelo());
                compatibilidadDto.setNombreModelo(modelo.nombreModelo);

                lstCompatibilidadDtos.add(compatibilidadDto);
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

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

    public String reinit() {
        infoItem.setEliminar(false);
        lstInfoItems.add(infoItem);

        infoItem = new InformacionItem();

        return null;
    }

    public void eliminarInfoItem() {
        if (infoItem != null) {
            infoItem.setEliminar(!infoItem.getEliminar());
        }
    }

    public void cargarFotosInit() {
        imagenesDeProducto.clear();
        //item = ((ItemMB) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(FacesContext.getCurrentInstance().getELContext(), null, "itemMB")).getItem();

        if (item != null && item.getIdItem() != null) {
            folderImg = new File(RESOURCE_BUNDLE.getString("pathimagenesitem") + item.getCodigoProducto());
            if (folderImg.exists()) {
                for (File listFile : folderImg.listFiles()) {
                    imagenesDeProducto.add(listFile.getName());
                }
            }
        }
    }

    public void deleteImage() {
        File img = new File(RESOURCE_BUNDLE.getString("pathimagenesitem") + item.codigoProducto + "/" + nombreImagen);
        if (img.exists()) {
            img.delete();
            cargarFotosInit();
        }
    }

    //===========================================
    public void openDialogCategory() {
        Map<String, Object> options = new HashMap();
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 300);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        //options.put("headerElement", RESOURCE_BUNDLE.getString("asociarCategoriaProducto"));

        Map<String, List<String>> params = new HashMap();
        List<String> values = new ArrayList();
        values.add(String.valueOf(item.getIdItem()));
        params.put("item", values);

        PrimeFaces.current().dialog().openDynamic("/app/mantto/dialog/addCategoryProduct", options, params);
    }

    public List<Categoria> completeCategoriaContains(String query) {
        return manttoService.getLstCategoriaByLikeNombre(query, item.getIdItem());
    }
}
