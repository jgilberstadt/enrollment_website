package edu.wustl.mir.ctt.util;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * See http://javalabor.blogspot.com/2012/02/jsf-2-conditional-validation.html
 * 
 * @author drm
 */
public class ValidatorUtil {

    private static final String VALIDATE = "VALIDATE";

    public static boolean check() {
        return check(FacesContext.getCurrentInstance());
    }

    public static boolean check(final FacesContext context) {
        final ExternalContext externalContext = context.getExternalContext();
        final Object validate = externalContext.getRequestMap().get(VALIDATE);
        if (validate != null) {
            return (Boolean) validate;
        }
        for (final Map.Entry<String, String[]> requestParameters : externalContext.getRequestParameterValuesMap()
                .entrySet()) {
            final String key = requestParameters.getKey();
            if (key.contains(":do")) {
                externalContext.getRequestMap().put(VALIDATE, Boolean.TRUE);
                return true;
            }
        }
        externalContext.getRequestMap().put(VALIDATE, Boolean.FALSE);
        return false;
    }

}
