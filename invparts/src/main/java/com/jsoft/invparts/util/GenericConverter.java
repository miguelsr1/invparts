/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.util;

/**
 *
 * @author misanchez
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jsoft.invparts.model.inventario.Categoria;
import com.jsoft.invparts.servicios.ManttoService;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author misanchez
 */
public class GenericConverter implements Converter {

    @ManagedProperty("#{manttoService}")
    private ManttoService manttoService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || value.toString().contains("Seleccione")) {
            return null;
        }
        return manttoService.findCategoriaById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        return object.toString();
    }
}
