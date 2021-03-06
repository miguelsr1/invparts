package com.jsoft.invparts.util;

import com.jsoft.invparts.mb.mantto.MenuMB;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

public class JsfUtil {
    
    public static final int COD_ERROR = -1;
    public static final String S_USUARIO = "sUsuario";
    
    public static List<SelectItem> getSelectItems(List<?> entities, boolean selectOne) {
        List<SelectItem> items = new ArrayList();
        int i = 0;
        if (selectOne) {
            items.add(new SelectItem("", "---"));
            i++;
        }
        for (Object x : entities) {
            items.add(new SelectItem(x, x.toString()));
        }
        return items;
    }
    
    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }
    
    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }
    
    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public static void addWarningMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }
    
    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
    
    public static void addVariableSession(String key, Object value) {
        (((MenuMB) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "menuMB")).getVariables()).put(key, value);
    }
    
    public static Object getVariableSession(String key) {
        return (((MenuMB) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "menuMB")).getVariables()).get(key);
    }
    
    public static void removeVariableSession(String key) {
        (((MenuMB) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "menuMB")).getVariables()).remove(key);
    }
    
    public static void eliminarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().clear();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.redirect(((ServletContext) externalContext.getContext()).getContextPath() + "/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PreparedStatement setValuesPreparedStatement(PreparedStatement ps, Object[] valores) throws SQLException {
        int col = 1;
        for (Object object : valores) {
            if (object instanceof Integer) {
                ps.setInt(col, (Integer) object);
            } else if (object instanceof String) {
                ps.setString(col, object.toString());
            } else if (object instanceof Date) {
                ps.setDate(col, new java.sql.Date(((Date) object).getTime()));
            } else if (object instanceof BigDecimal) {
                ps.setFloat(col, ((BigDecimal) object).floatValue());
            } else {
                ps.setObject(col, object);
            }
            col++;
        }
        return ps;
    }

    public static void redireccionar(String url) {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        
        configurableNavigationHandler.performNavigation(url);
    }
}
