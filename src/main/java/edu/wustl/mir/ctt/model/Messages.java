/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wustl.mir.ctt.model;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import static org.apache.naming.ContextBindings.getClassLoader;
import static org.omnifaces.util.FacesLocal.getLocale;

/**
 *
 * @author Paul K. Commean
 */
public class Messages {
    
    public static FacesMessage getMessage(String bundleName, String resourceId, Object[] params){
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        String appBundle = app.getMessageBundle();
        Locale locale = getLocale(context);
        try {
            Context loader = getClassLoader();
        } catch (NamingException ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
        String summary = "summary";
        String detail = "detail";
        return new FacesMessage(summary, detail);
    }
    
}
