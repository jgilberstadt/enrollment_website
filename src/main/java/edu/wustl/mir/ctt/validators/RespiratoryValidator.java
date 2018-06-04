/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wustl.mir.ctt.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Paul K. Commean
 */

// The following @FacesValidator("edu.wustl.mir.ctt.validators.RespirValid") is associated with 
// the <f:validator validatorId="edu.wustl.mir.ctt.validators.RespirValid" /> in the 
// demoMedHistForm.xhtml file for question 25.

@FacesValidator("edu.wustl.mir.ctt.validators.RespirValid")
public class RespiratoryValidator implements Validator {
    
   @Override
   public void validate(FacesContext facesContext, UIComponent uiComponentToValidate, Object value) throws ValidatorException {

        // Check to see if the variable 'value' is null.  A null value happens when you select the Save button on a form with no value
        // entered into the respiratory box.
        if(value != null){
            // Use negative one as an input to indicate an unknown value or no respiratory rate value was obtained for a participant.
            if((Integer) value == -1){
                ((UIInput)uiComponentToValidate).setValid(true);
            // The following are the range of values that are acceptable respiratory rates.
            } else if((Integer) value >= 6 && (Integer) value <= 45){
                ((UIInput)uiComponentToValidate).setValid(true);
            // If the rate is unknown or falls outside of the acceptable values, indicate an error message.
            } else {
                ((UIInput)uiComponentToValidate).setValid(false);
                FacesMessage respiratoryMessage = new FacesMessage("Invalid Respiratory Input Value!!  It ranges from 6 to 45, unless unknown then use -1 [negative one]");
                facesContext.addMessage(uiComponentToValidate.getClientId(facesContext), respiratoryMessage);
            }
        }
   }
}
