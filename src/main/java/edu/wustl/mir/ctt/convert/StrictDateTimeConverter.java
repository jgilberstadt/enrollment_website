package edu.wustl.mir.ctt.convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("strictDateTimeConverter")
public class StrictDateTimeConverter extends DateTimeConverter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String regex = "\\d{1,2}/\\d{1,2}/(19|20)\\d{2}";
        String pattern = "MM/dd/yyyy";

        System.out.println(regex);
        
        if (value != null && !value.matches(regex)) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format("Validation Error: Invalid date, must be in pattern %s", pattern), null));
        }

        setPattern(pattern);
        return super.getAsObject(context, component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        setPattern("MM/dd/yyyy");
        return super.getAsString(context, component, value);
    }
}
