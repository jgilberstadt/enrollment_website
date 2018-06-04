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

// The following @FacesValidator("edu.wustl.mir.ctt.validators.OxygenValid") is associated with 
// the <f:validator validatorId="edu.wustl.mir.ctt.validators.OxygenValid" /> in the 
// pulmEvalForm.xhtml file for question 1.

@FacesValidator("edu.wustl.mir.ctt.validators.OxygenValid")
public class OxygenValidator implements Validator {
    
   @Override
   public void validate(FacesContext facesContext, UIComponent uiComponentToValidate, Object value) throws ValidatorException {

        // Check to see if the variable 'value' is null.  A null value happens when you select the Save button on a form with no value
        // entered into the oxygen box.
        if(value != null){
            // Use negative one as an input to indicate an unknown value or no respiratory rate value was obtained for a participant.
            if((Integer) value == -1){
                ((UIInput)uiComponentToValidate).setValid(true);
            // The following are the range of values that are acceptable respiratory rates.
            } else if((Integer) value >= 60 && (Integer) value <= 100){
                ((UIInput)uiComponentToValidate).setValid(true);
            // If the rate is unknown or falls outside of the acceptable values, indicate an error message.
            } else {
                ((UIInput)uiComponentToValidate).setValid(false);
                FacesMessage respiratoryMessage = new FacesMessage("Invalid Resting Oxygen Saturation Input Value!!  It ranges from 60% to 100%, unless unknown then use -1 [negative one]");
                facesContext.addMessage(uiComponentToValidate.getClientId(facesContext), respiratoryMessage);
            }
        }
   }
}
