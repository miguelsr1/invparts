package com.jsoft.invparts.mb.mantto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author DesarrolloPc
 */
@ManagedBean
@SessionScoped
public class LocaleMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private String localeCode;
    private static Map<String, Object> countries;

    static {
        countries = new LinkedHashMap();
        Locale espanol = new Locale("es");
        countries.put("Español", espanol);
        countries.put("English", Locale.ENGLISH);
    }

    public Map<String, Object> getLenguajes() {
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void localeCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
}
