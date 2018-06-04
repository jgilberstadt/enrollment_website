package edu.wustl.mir.ctt.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.RequiredValidator;

/**
 *
 * See http://javalabor.blogspot.com/2012/02/jsf-2-conditional-validation.html
 * 
 * @author drm
 */
public class SkipRequiredValidator extends RequiredValidator {

    @Override
    public void validate(final FacesContext context, final UIComponent component, final Object value) {
        System.out.println( "*******Here required******");
        if (ValidatorUtil.check(context)) {
            super.validate(context, component, value);
        }
    }

}
