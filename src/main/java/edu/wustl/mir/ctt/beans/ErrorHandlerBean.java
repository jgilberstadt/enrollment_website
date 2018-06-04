package edu.wustl.mir.ctt.beans;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author drm
 */
@ManagedBean
@RequestScoped
public class ErrorHandlerBean {

    public String getStatusCode() {
        String val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.status_code"));
        return val;
    }

    public String getMessage() {
        String val = (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.message");
        return val;
    }

    public String getExceptionType() {
        String val = FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception_type").toString();
        return val;
    }

    public String getException() {
        String val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception")).toString();
        return val;
    }

    public String getRequestURI() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.request_uri");
    }

    public String getServletName() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.servlet_name");
    }
    
    public String getStackTrace() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> map = context.getExternalContext().getRequestMap();
        Throwable throwable = (Throwable) map.get("javax.servlet.error.exception");
        StringBuilder builder = new StringBuilder();
        builder.append(throwable.getMessage()).append("\n");

        for (StackTraceElement element : throwable.getStackTrace()) {
            builder.append(element).append("\n");
        }

        return builder.toString();

    }
}
